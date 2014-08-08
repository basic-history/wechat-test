package com.tianxin.wechat.platform.message.item;

import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 音乐消息
 *
 * @author snowway
 * @since 6/24/14 14:10
 */
public class MusicItem implements Responsive {


    private String title;

    private String description;

    private String musicUrl;

    private String highQualityMusicUrl;

    private String thumbMediaId;

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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHighQualityMusicUrl() {
        return highQualityMusicUrl;
    }

    public void setHighQualityMusicUrl(String highQualityMusicUrl) {
        this.highQualityMusicUrl = highQualityMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_MUSI;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
