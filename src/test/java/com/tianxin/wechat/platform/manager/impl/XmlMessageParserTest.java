package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.manager.impl.XmlMessageParser;
import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlMessageParserTest {


    private XmlMessageParser parser = new XmlMessageParser();


    protected String getContent(Class type) throws Exception {
        return IOUtils.toString(getClass().getResourceAsStream("/message/item/" + type.getSimpleName() + ".xml"));
    }

    protected String getEvent(Class type) throws Exception {
        return IOUtils.toString(getClass().getResourceAsStream("/message/event/" + type.getSimpleName() + ".xml"));
    }


    @Before
    public void setup() {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }


    @Test
    public void testParseTextItem() throws Exception {
        String content = getContent(TextItem.class);
        Message message = parser.parse(content);
        assertEquals("to", message.getTo());
        assertEquals("from", message.getFrom());
        assertEquals(Message.TYPE_TEXT, message.getType());
        assertEquals(1348831860, message.getCreateTime());
        assertEquals(1234567890123456L, message.getId());
        assertNotNull(message.getPayload());
        assertEquals("文本消息内容", message.<TextItem>getPayload().getContent());
    }


    @Test
    public void testParseImageItem() throws Exception {
        String content = getContent(ImageItem.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_IMAGE, message.getType());
        assertEquals("/pic/url", message.<ImageItem>getPayload().getImageUrl());
        assertEquals("media_id", message.<ImageItem>getPayload().getMediaId());
    }


    @Test
    public void testParseAudioItem() throws Exception {
        String content = getContent(AudioItem.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_AUDIO, message.getType());
        AudioItem item = message.getPayload();
        assertEquals("amr", item.getFormat());
        assertEquals("media_id", item.getMediaId());
        assertEquals("语音转中文", item.getRecognition());
    }


    @Test
    public void testParseVideoItem() throws Exception {
        String content = getContent(VideoItem.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_VIDEO, message.getType());
        VideoItem item = message.getPayload();
        assertEquals("media_id", item.getMediaId());
        assertEquals("thumb_media_id", item.getThumbMediaId());
    }


    @Test
    public void testParseLocationItem() throws Exception {
        String content = getContent(LocationItem.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_LOCATION, message.getType());
        LocationItem item = message.getPayload();
        assertEquals(23.1234, item.getLatitude(), 0.1);
        assertEquals(113.3232, item.getLongitude(), 0.1);
        assertEquals(20, item.getScale());
        assertEquals("地理位置", item.getLabel());
    }


    @Test
    public void testParseLinkItem() throws Exception {
        String content = getContent(LinkItem.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_LINK, message.getType());
        LinkItem item = message.getPayload();
        assertEquals("title", item.getTitle());
        assertEquals("description", item.getDescription());
        assertEquals("url", item.getUrl());
    }


    @Test
    public void testParseSubscribeEvent() throws Exception {
        String content = getEvent(SubscribeEvent.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_EVENT, message.getType());
        SubscribeEvent event = message.getPayload();
        assertEquals(Event.TYPE_SUBSCRIBE, event.getEventType());
        assertEquals("qrcode", event.getQrcode());
        assertEquals("ticket", event.getTicket());
    }


    @Test
    public void testParseUnSubscribeEvent() throws Exception {
        String content = getEvent(UnSubscribeEvent.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_EVENT, message.getType());
        UnSubscribeEvent event = message.getPayload();
        assertEquals(Event.TYPE_UNSUBSCRIBE, event.getEventType());
    }


    @Test
    public void testParseScanEvent() throws Exception {
        String content = getEvent(ScanEvent.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_EVENT, message.getType());
        ScanEvent event = message.getPayload();
        assertEquals(Event.TYPE_SCAN, event.getEventType());
        assertEquals("qrcode", event.getQrcode());
        assertEquals("ticket", event.getTicket());
    }


    @Test
    public void testParseLocationEvent() throws Exception {
        String content = getEvent(LocationEvent.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_EVENT, message.getType());
        LocationEvent event = message.getPayload();
        assertEquals(Event.TYPE_LOCATION, event.getEventType());
        assertEquals(23.1234, event.getLatitude(), 0.1);
        assertEquals(123.1234, event.getLongitude(), 0.1);
        assertEquals(119.1234, event.getPrecision(), 0.1);
    }


    @Test
    public void testParseClickEvent() throws Exception {
        String content = getEvent(ClickEvent.class);
        Message message = parser.parse(content);
        assertEquals(Message.TYPE_EVENT, message.getType());
        ClickEvent event = message.getPayload();
        assertEquals(Event.TYPE_CLICK, event.getEventType());
        assertEquals("key", event.getKey());
    }
}