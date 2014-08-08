package com.tianxin.wechat.platform.message.item;


import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 视频消息
 *
 * @author snowway
 * @since 6/17/14 13:09
 */
public class VideoItem implements Responsive {


    /**
     * 媒体ID
     */
    private String mediaId;

    /**
     * 视频缩略图ID
     */
    private String thumbMediaId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;


    /**
     * 创建回复的视频消息
     *
     * @param mediaId     媒体id
     * @param title       标题
     * @param description 描述
     * @return VideoItem
     */
    public static VideoItem reply(String mediaId, String title, String description) {
        VideoItem item = new VideoItem();
        item.setMediaId(mediaId);
        item.setTitle(title);
        item.setDescription(description);
        return item;
    }


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_VIDEO;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
