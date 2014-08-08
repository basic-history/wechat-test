package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Visitor;
import org.apache.commons.lang.StringUtils;

/**
 * @author snowway
 * @since 6/23/14 18:20
 */
public class SubscribeEvent extends AbstractEvent {

    public static final String QRCODE_PREFIX = "qrscene_";

    /**
     * 当扫描二维码, 且用户未关注当前公众号时, 附带二维码参数
     */
    private String qrcode;

    /**
     * 用于换取二维码图片
     */
    private String ticket;

    public SubscribeEvent() {
    }

    public SubscribeEvent(String qrcode, String ticket) {
        this.qrcode = qrcode;
        this.ticket = ticket;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        if (StringUtils.startsWith(qrcode, QRCODE_PREFIX)) {
            this.qrcode = StringUtils.substringAfter(qrcode, QRCODE_PREFIX);
        } else {
            this.qrcode = qrcode;
        }
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String getEventType() {
        return TYPE_SUBSCRIBE;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return 是否通过二维码扫描产生的关注事件
     */
    public boolean isFromScan() {
        return StringUtils.isNotBlank(qrcode) && StringUtils.isNotBlank(ticket);
    }
}
