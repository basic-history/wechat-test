package com.tianxin.wechat.platform.manager;

import com.tianxin.wechat.platform.message.Message;

/**
 * 消息解析接口
 *
 * @author snowway
 * @since 6/24/14 13:20
 */
public interface MessageParser {

    /**
     * 解析字符串,获得消息对象
     *
     * @param content 消息内容
     * @return Message
     */
    Message parse(String content);
}
