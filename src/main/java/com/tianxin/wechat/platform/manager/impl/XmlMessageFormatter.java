package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.exception.InvalidMessageException;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Visitor;
import com.tianxin.wechat.platform.message.item.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 使用xml格式输出的XmlMessageFormatter实现
 *
 * @author snowway
 * @since 6/23/14 15:35
 */
public class XmlMessageFormatter extends AbstractMessageFormatter {


    private static class VisitorSupport extends Visitor.Adapter {

        private Element wrapper;

        private VisitorSupport(Element wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void visit(TextItem textItem) {
            wrapper.addElement("Content").addCDATA(textItem.getContent());
        }

        @Override
        public void visit(AudioItem audioItem) {
            wrapper.addElement("Voice").addElement("MediaId").addCDATA(audioItem.getMediaId());
        }

        @Override
        public void visit(ImageItem imageItem) {
            wrapper.addElement("Image").addElement("MediaId").addCDATA(imageItem.getMediaId());
        }

        @Override
        public void visit(VideoItem videoItem) {
            Element element = wrapper.addElement("Video");
            element.addElement("MediaId").addCDATA(videoItem.getMediaId());
            element.addElement("Title").addCDATA(videoItem.getTitle());
            element.addElement("Description").addCDATA(videoItem.getDescription());
        }


        @Override
        public void visit(ArticleItem articleItem) {
            wrapper.addElement("ArticleCount").setText(String.valueOf(articleItem.getCount()));
            Element articles = wrapper.addElement("Articles");
            for (ArticleItem.Article article : articleItem.getArticles()) {
                Element item = articles.addElement("item");
                item.addElement("Title").addCDATA(article.getTitle());
                item.addElement("Description").addCDATA(article.getDescription());
                item.addElement("PicUrl").addCDATA(article.getImageUrl());
                item.addElement("Url").addCDATA(article.getUrl());
            }
        }

        @Override
        public void visit(MusicItem musicItem) {
            Element music = wrapper.addElement("Music");
            music.addElement("Title").addCDATA(musicItem.getTitle());
            music.addElement("Description").addCDATA(musicItem.getDescription());
            music.addElement("MusicUrl").addCDATA(musicItem.getMusicUrl());
            music.addElement("HQMusicUrl").addCDATA(musicItem.getHighQualityMusicUrl());
            music.addElement("ThumbMediaId").addCDATA(musicItem.getThumbMediaId());
        }
    }


    @Override
    public String format(Message message) {
        StringWriter writer = new StringWriter();
        Element root = DocumentHelper.createElement("xml");
        Document document = DocumentHelper.createDocument(root);
        root.addElement("ToUserName").addCDATA(message.getTo());
        root.addElement("FromUserName").addCDATA(message.getFrom());
        root.addElement("CreateTime").setText(String.valueOf(message.getCreateTime()));
        root.addElement("MsgType").addCDATA(message.getPayload().getMessageType());
        message.accept(new VisitorSupport(root));
        try {
            document.write(writer);
        } catch (IOException ex) {
            throw new InvalidMessageException(ex);
        }
        return writer.toString();
    }
}
