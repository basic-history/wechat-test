package com.tianxin.wechat.platform.message;


/**
 * wechat Message interface
 *
 * @author snowway
 * @since 6/23/14 15:15
 */
public class Message {


    /**
     * 文本类型消息
     */
    public static final String TYPE_TEXT = "text";

    /**
     * 图片类型消息
     */
    public static final String TYPE_IMAGE = "image";

    /**
     * 语音类型消息
     */
    public static final String TYPE_AUDIO = "voice";

    /**
     * 视频类型消息
     */
    public static final String TYPE_VIDEO = "video";

    /**
     * 链接类型消息
     */
    public static final String TYPE_LINK = "link";

    /**
     * 位置类型消息
     */
    public static final String TYPE_LOCATION = "location";


    /**
     * 图文类型消息
     */
    public static final String TYPE_ARTICLE = "news";


    /**
     * 音乐类型消息
     */
    public static final String TYPE_MUSI = "music";

    /**
     * 事件类型消息
     */
    public static final String TYPE_EVENT = "event";

    /**
     * 消息id,64位整数
     */
    private long id;

    /**
     * 发送方微信号(OpenId)
     */
    private String from;


    /**
     * 对方的微信号(OpenID)
     */
    private String to;

    /**
     * 消息创建日期
     */
    private long createTime;


    /**
     * 消息类型(text,image,voice,video,link,location,event)
     */
    private String type;


    /**
     * 消息体
     */
    private Payload payload;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public <T extends Payload> T getPayload() {
        return (T) payload;
    }

    public void setPayload(Payload payload) {
        this.setType(payload.getMessageType());
        this.payload = payload;
    }

    /**
     * 接收访问者访问
     *
     * @param visitor Visitor
     */
    public void accept(Visitor visitor) {
        this.payload.accept(visitor);
    }
}
