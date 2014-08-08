package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Message;

/**
 * @author snowway
 * @since 6/23/14 18:22
 */
public abstract class AbstractEvent implements Event {

    @Override
    public String getMessageType() {
        return Message.TYPE_EVENT;
    }
}
