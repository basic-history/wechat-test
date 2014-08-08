package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Visitor;

/**
 * 取消订阅事件
 *
 * @author snowway
 * @since 6/23/14 18:20
 */
public class UnSubscribeEvent extends AbstractEvent {


    @Override
    public String getEventType() {
        return TYPE_UNSUBSCRIBE;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
