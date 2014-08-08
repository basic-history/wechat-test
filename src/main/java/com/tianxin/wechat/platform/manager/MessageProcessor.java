package com.tianxin.wechat.platform.manager;

/**
 * 消息处理器接口
 *
 * @author snowway
 * @since 6/25/14 11:10
 */
public interface MessageProcessor {


    /**
     * 处理消息
     *
     * @param content 字符串形式消息
     * @return 返回的消息字符串
     */
    String process(String content);
}
