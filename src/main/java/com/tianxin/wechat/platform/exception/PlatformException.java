package com.tianxin.wechat.platform.exception;

/**
 * 微信平台系统异常
 *
 * @author snowway
 * @since 6/17/14 18:04
 */
public class PlatformException extends RuntimeException {

    public PlatformException() {
    }

    public PlatformException(String s) {
        super(s);
    }

    public PlatformException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PlatformException(Throwable throwable) {
        super(throwable);
    }
}
