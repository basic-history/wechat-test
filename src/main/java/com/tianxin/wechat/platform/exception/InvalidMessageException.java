package com.tianxin.wechat.platform.exception;

/**
 * 无效的消息异常
 *
 * @author snowway
 * @since 6/17/14 18:04
 */
public class InvalidMessageException extends PlatformException {

    public InvalidMessageException() {
    }

    public InvalidMessageException(String s) {
        super(s);
    }

    public InvalidMessageException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidMessageException(Throwable throwable) {
        super(throwable);
    }
}
