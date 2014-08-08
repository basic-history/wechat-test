package com.tianxin.wechat.platform.manager.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.tianxin.wechat.platform.exception.CodedPlatformException;
import com.tianxin.wechat.platform.exception.PlatformException;
import com.tianxin.wechat.platform.manager.Configuration;
import com.tianxin.wechat.platform.manager.PlatformApiManager;
import com.tianxin.wechat.platform.navigation.Navigation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * PlatformApiManager默认实现
 *
 * @author snowway
 * @since 6/25/14 16:56
 */
public class DefaultPlatformApiManager implements PlatformApiManager {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取access_token时AppSecret错误，或者access_token无效
     */
    public static final int ACCESS_TOKEN_EXPIRED = 42001;

    /**
     * 始终信任服务器的SSL证书
     */
    protected static class AlwaysTrustedManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    protected TrustManager[] trustManagers = new TrustManager[]{new AlwaysTrustedManager()};


    public void setTrustManagers(TrustManager[] trustManagers) {
        this.trustManagers = trustManagers;
    }

    protected Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 配置Unirest全局设置
     */
    protected void configureUnirest() {
        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            SSLContext context = SSLContext.getInstance("SSL", "SunJSSE");
            context.init(null, trustManagers, new SecureRandom());
            builder.setSslcontext(context);
            Unirest.setHttpClient(builder.build());
        } catch (Exception ex) {
            throw new PlatformException(ex);
        }
    }

    @Override
    public void initialize() {
        configureUnirest();
    }

    @Override
    public void destroy() {
        try {
            Unirest.shutdown();
        } catch (IOException ex) {
            logger.warn("销毁Unirest发生异常,{}", ex);
        }
    }


    /**
     * UnirestJsonHandler, 处理微信接口返回的JSON
     *
     * @param <T>
     */
    protected interface UnirestJsonHandler<T> {

        /**
         * 发送请求
         */
        HttpResponse<JsonNode> request() throws Exception;

        /**
         * 解析返回的JSONObject
         */
        T handle(JSONObject result);
    }

    /**
     * 发送请求给微信服务器
     */
    protected <T> T request(UnirestJsonHandler<T> handler) {
        HttpResponse<JsonNode> response;
        try {
            response = handler.request();
        } catch (Exception ex) {
            throw new PlatformException(ex);
        }
        if (response.getCode() > 300) {
            throw new PlatformException("微信API异常,HTTP返回码:" + response.getCode());
        }
        JSONObject result = response.getBody().getObject();
        if (result.has("errcode") && result.getInt("errcode") != 0) {
            int errorcode = result.getInt("errcode");
            if (errorcode == ACCESS_TOKEN_EXPIRED) {//尝试重新获取AccessToken
                logger.warn("AccessToken失效, 尝试重新获取并发起原始请求");
                getAccessToken();
                return request(handler);
            } else {
                throw new CodedPlatformException(errorcode, result.getString("errmsg"));
            }
        }
        return handler.handle(result);
    }


    /**
     * AT过期时间戳
     */
    private long accessTokenExpireTime = 0;

    /**
     * 缓存的AccessToken
     */
    private String cachedAccessToken;

    @Override
    public String getAccessToken() {
        long current = System.currentTimeMillis();
        //如果缓存的AT存在,并且当前时间小于AT即将过期时间, 则直接返回保存的AT
        if (StringUtils.isNotBlank(cachedAccessToken) && current <= accessTokenExpireTime) {
            logger.debug("返回缓存的AccessToken:{}", cachedAccessToken);
            return cachedAccessToken;
        }
        return request(new UnirestJsonHandler<String>() {

            @Override
            public HttpResponse<JsonNode> request() throws Exception {
                return Unirest.get(configuration.getAccessTokenUrl())
                        .routeParam("appid", configuration.getAppid())
                        .routeParam("secret", configuration.getSecret())
                        .asJson();
            }

            @Override
            public String handle(JSONObject result) {
                cachedAccessToken = result.getString("access_token");
                int expiresInSeconds = result.getInt("expires_in");
                accessTokenExpireTime = System.currentTimeMillis() + ((expiresInSeconds - 10) * 1000);//减少10秒提高容错
                logger.debug("获取AccessToken:{}成功,过期时间:{}", cachedAccessToken, expiresInSeconds);
                return cachedAccessToken;
            }
        });
    }

    @Override
    public boolean createNavigation(final Navigation navigation) {
        if (navigation == null) {
            throw new IllegalArgumentException("必须传递导航对象");
        }
        final String json = navigation.toJSON().toString();
        return request(new UnirestJsonHandler<Boolean>() {
            @Override
            public HttpResponse<JsonNode> request() throws Exception {
                return Unirest.post(configuration.getNavigationCreateUrl())
                        .routeParam("accessToken", getAccessToken())
                        .body(json)
                        .asJson();
            }

            @Override
            public Boolean handle(JSONObject result) {
                return result.getInt("errcode") == 0;
            }
        });
    }
}
