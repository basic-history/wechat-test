package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 扫描二维码事件
 *
 * @author snowway
 * @since 6/24/14 9:54
 */
public class ScanEvent extends AbstractEvent {

    /**
     * 当扫描二维码, 且用户未关注当前公众号时, 附带二维码参数
     */
    private String qrcode;

    /**
     * 用于换取二维码图片
     */
    private String ticket;

    public ScanEvent() {
    }

    public ScanEvent(String qrcode, String ticket) {
        this.qrcode = qrcode;
        this.ticket = ticket;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String getEventType() {
        return Event.TYPE_SCAN;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
