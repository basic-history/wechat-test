package com.tianxin.wechat.platform.message.item;


import com.tianxin.wechat.platform.message.Content;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 链接消息
 *
 * @author snowway
 * @since 6/17/14 13:14
 */
public class LinkItem implements Content {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 连接地址
     */
    private String url;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_LINK;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
