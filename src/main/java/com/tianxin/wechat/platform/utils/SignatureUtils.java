package com.tianxin.wechat.platform.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 签名工具类
 *
 * @author snowway
 * @since 6/25/14 10:46
 */
public class SignatureUtils {

    private static Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    /**
     * 验证签名是否正确
     *
     * @param req   HttpServletRequest
     * @param token 在微信平台填写的验证TOKEN
     * @return 签名是否成功
     */
    public static boolean verify(HttpServletRequest req, String token) {
        String signature = StringUtils.trimToEmpty(req.getParameter("signature"));//加密签名
        String timestamp = StringUtils.trimToEmpty(req.getParameter("timestamp"));//时间戳
        String nonce = StringUtils.trimToEmpty(req.getParameter("nonce"));//随机数

        //对token,timestamp,nonce按字典排序
        String[] params = new String[]{token, timestamp, nonce};
        Arrays.sort(params);//字典排序
        String content = StringUtils.join(params);//排序后拼接字符串

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.getBytes());
            String cipher = HexUtils.toHexString(digest);
            return StringUtils.equals(cipher, signature);
        } catch (Exception ex) {
            logger.warn("无法验证微信平台签名的有效性", ex);
            return false;
        }
    }
}
