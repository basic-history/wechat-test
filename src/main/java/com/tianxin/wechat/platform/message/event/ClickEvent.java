package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 菜单点击事件, 如果菜单为跳转到URL将不会触发
 *
 * @author snowway
 * @since 6/24/14 10:01
 */
public class ClickEvent extends AbstractEvent {

    /**
     * 点击的菜单Key
     */
    private String key;

    public ClickEvent() {
    }

    public ClickEvent(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getEventType() {
        return Event.TYPE_CLICK;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
