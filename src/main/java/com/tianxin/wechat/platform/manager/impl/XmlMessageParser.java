package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.exception.InvalidMessageException;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Payload;
import com.tianxin.wechat.platform.message.Visitor;
import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

/**
 * @author snowway
 * @since 6/23/14 15:35
 */
public class XmlMessageParser extends AbstractMessageParser {


    private static class VisitorSupport extends Visitor.Adapter {

        private Element wrapper;

        private VisitorSupport(Element wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void visit(TextItem textItem) {
            textItem.setContent(wrapper.elementTextTrim("Content"));
        }

        @Override
        public void visit(AudioItem audioItem) {
            audioItem.setFormat(wrapper.elementTextTrim("Format"));
            audioItem.setMediaId(wrapper.elementTextTrim("MediaId"));
            audioItem.setRecognition(wrapper.elementTextTrim("Recognition"));
        }

        @Override
        public void visit(ImageItem imageItem) {
            imageItem.setMediaId(wrapper.elementTextTrim("MediaId"));
            imageItem.setImageUrl(wrapper.elementTextTrim("PicUrl"));
        }

        @Override
        public void visit(LinkItem linkItem) {
            linkItem.setTitle(wrapper.elementTextTrim("Title"));
            linkItem.setDescription(wrapper.elementTextTrim("Description"));
            linkItem.setUrl(wrapper.elementTextTrim("Url"));
        }

        @Override
        public void visit(LocationItem locationItem) {
            locationItem.setLatitude(Double.valueOf(wrapper.elementTextTrim("Location_X")));
            locationItem.setLongitude(Double.valueOf(wrapper.elementTextTrim("Location_Y")));
            locationItem.setScale(Integer.valueOf(wrapper.elementTextTrim("Scale")));
            locationItem.setLabel(wrapper.elementTextTrim("Label"));
        }

        @Override
        public void visit(VideoItem videoItem) {
            videoItem.setMediaId(wrapper.elementTextTrim("MediaId"));
            videoItem.setThumbMediaId(wrapper.elementTextTrim("ThumbMediaId"));
        }

        @Override
        public void visit(SubscribeEvent subscribeEvent) {
            subscribeEvent.setQrcode(wrapper.elementTextTrim("EventKey"));
            subscribeEvent.setTicket(wrapper.elementTextTrim("Ticket"));
        }

        @Override
        public void visit(UnSubscribeEvent unsubscribeEvent) {
            wrapper.addElement("Event").addCDATA(unsubscribeEvent.getEventType());
        }

        @Override
        public void visit(ScanEvent scanEvent) {
            scanEvent.setQrcode(wrapper.elementTextTrim("EventKey"));
            scanEvent.setTicket(wrapper.elementTextTrim("Ticket"));
        }

        @Override
        public void visit(LocationEvent locationEvent) {
            locationEvent.setLatitude(Double.valueOf(wrapper.elementTextTrim("Latitude")));
            locationEvent.setLongitude(Double.valueOf(wrapper.elementTextTrim("Longitude")));
            locationEvent.setPrecision(Double.valueOf(wrapper.elementTextTrim("Precision")));
        }

        @Override
        public void visit(ClickEvent clickEvent) {
            clickEvent.setKey(wrapper.elementTextTrim("EventKey"));
        }
    }


    @Override
    public Message parse(String content) {
        if (logger.isDebugEnabled()) {
            logger.debug("收到微信平台消息:{}", content);
        }
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new StringReader(content));
            Element root = document.getRootElement();
            Message message = new Message();
            message.setId(Long.valueOf(root.elementTextTrim("MsgId")));
            message.setCreateTime(Long.valueOf(root.elementTextTrim("CreateTime")));
            message.setFrom(root.elementTextTrim("FromUserName"));
            message.setTo(root.elementTextTrim("ToUserName"));
            message.setType(root.elementTextTrim("MsgType"));
            Payload payload;
            if (StringUtils.equals(message.getType(), Message.TYPE_EVENT)) {
                payload = createEvent(root.elementTextTrim("Event"));
            } else {
                payload = createContent(message.getType());
            }
            if (payload != null) {
                payload.accept(new VisitorSupport(root));
                message.setPayload(payload);
            }
            return message;
        } catch (DocumentException ex) {
            throw new InvalidMessageException(ex);
        }
    }
}
