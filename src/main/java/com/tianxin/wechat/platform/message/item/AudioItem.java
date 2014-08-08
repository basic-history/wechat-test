package com.tianxin.wechat.platform.message.item;


import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 语音消息
 *
 * @author snowway
 * @since 6/17/14 13:04
 */
public class AudioItem implements Responsive {

    /**
     * 语音格式(amr,speex)
     */
    private String format;

    /**
     * 媒体ID
     */
    private String mediaId;


    /**
     * 语音识别结果(UTF-8),需要微信认证的服务号才能开启
     */
    private String recognition;

    /**
     * 创建回复的AudioItem
     *
     * @param mediaId 媒体id
     * @return AudioItem
     */
    public static AudioItem reply(String mediaId) {
        AudioItem item = new AudioItem();
        item.setMediaId(mediaId);
        return item;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_AUDIO;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
