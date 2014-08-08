package com.tianxin.wechat.platform.manager;

import com.tianxin.wechat.platform.message.Message;

/**
 * 消息格式化输出接口
 *
 * @author snowway
 * @since 6/24/14 13:25
 */
public interface MessageFormatter {


    /**
     * 转换成字符串对象
     *
     * @param message 消息对象
     * @return 字符串表示
     */
    String format(Message message);
}
