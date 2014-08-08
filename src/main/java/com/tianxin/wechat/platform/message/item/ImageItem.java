package com.tianxin.wechat.platform.message.item;


import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 图片消息
 *
 * @author snowway
 * @since 6/17/14 13:02
 */
public class ImageItem implements Responsive {


    /**
     * 媒体Id
     */
    private String mediaId;

    /**
     * 图片链接地址
     */
    private String imageUrl;

    /**
     * 创建回复的ImageItem
     *
     * @param mediaId 媒体id
     * @return AudioItem
     */
    public static ImageItem reply(String mediaId) {
        ImageItem item = new ImageItem();
        item.setMediaId(mediaId);
        return item;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_IMAGE;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
