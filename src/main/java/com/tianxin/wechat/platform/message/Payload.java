package com.tianxin.wechat.platform.message;

/**
 * 消息体
 *
 * @author snowway
 * @since 6/23/14 15:18
 */
public interface Payload {

    /**
     * @return 消息类型
     */
    String getMessageType();

    /**
     * 接收访问者访问
     *
     * @param visitor Visitory
     */
    void accept(Visitor visitor);
}
