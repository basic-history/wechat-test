package com.tianxin.wechat.platform.exception;

/**
 * 带有错误码的异常
 *
 * @author snowway
 * @since 6/25/14 17:12
 */
public class CodedPlatformException extends PlatformException {

    private int code;

    private String reason;

    public CodedPlatformException(int code, String reason) {
        super("微信平台异常, 错误码:" + code + ", 原因:" + reason);
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
