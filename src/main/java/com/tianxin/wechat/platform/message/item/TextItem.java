package com.tianxin.wechat.platform.message.item;

import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * @author snowway
 * @since 6/23/14 15:22
 */
public class TextItem implements Responsive {


    /**
     * 消息内容
     */
    private String content;

    public TextItem() {
    }

    public TextItem(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_TEXT;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
