package com.tianxin.wechat.platform.manager;

/**
 * 微信平台系统配置
 *
 * @author snowway
 * @since 6/26/14 11:54
 */
public class Configuration {

    /**
     * app id
     */
    private String appid;

    /**
     * app secret;
     */
    private String secret;

    /**
     * 获取access token的url
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}
     */
    private String accessTokenUrl;


    /**
     * 导航创建url
     * https://api.weixin.qq.com/cgi-bin/menu/create?access_token={accessToken}
     */
    private String navigationCreateUrl;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getNavigationCreateUrl() {
        return navigationCreateUrl;
    }

    public void setNavigationCreateUrl(String navigationCreateUrl) {
        this.navigationCreateUrl = navigationCreateUrl;
    }
}
