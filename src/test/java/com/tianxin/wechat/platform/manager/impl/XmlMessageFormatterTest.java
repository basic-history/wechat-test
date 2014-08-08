package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.item.*;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XmlMessageFormatterTest {


    private XmlMessageFormatter formatter = new XmlMessageFormatter();


    protected String getContent(Class type) throws Exception {
        return IOUtils.toString(getClass().getResourceAsStream("/message/responsive/" + type.getSimpleName() + ".xml"));
    }


    private Message message;

    @Before
    public void setup() {
        message = new Message();
        message.setFrom("from");
        message.setTo("to");
        message.setCreateTime(1348831860);
        message.setId(1234567890123456L);

        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }

    @Test
    public void testFormatTextItem() throws Exception {
        message.setPayload(new TextItem("文本消息内容"));
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(TextItem.class), result).similar());
    }

    @Test
    public void testFormatImageItem() throws Exception {
        message.setPayload(ImageItem.reply("media_id"));
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(ImageItem.class), result).similar());
    }


    @Test
    public void testFormatAudioItem() throws Exception {
        message.setPayload(AudioItem.reply("media_id"));
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(AudioItem.class), result).similar());
    }

    @Test
    public void testFormatVideoItem() throws Exception {
        message.setPayload(VideoItem.reply("media_id", "title", "description"));
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(VideoItem.class), result).similar());
    }

    @Test
    public void testFormatArticleItem() throws Exception {
        ArticleItem item = new ArticleItem();
        ArticleItem.Article article = new ArticleItem.Article();
        article.setTitle("title1");
        article.setDescription("description1");
        article.setImageUrl("picurl1");
        article.setUrl("url1");
        item.addArticle(article);

        article = new ArticleItem.Article();
        article.setTitle("title2");
        article.setDescription("description2");
        article.setImageUrl("picurl2");
        article.setUrl("url2");
        item.addArticle(article);

        message.setPayload(item);
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(ArticleItem.class), result).similar());
    }

    @Test
    public void testFormatMusicItem() throws Exception {
        MusicItem item = new MusicItem();
        item.setTitle("title");
        item.setDescription("description");
        item.setMusicUrl("music_url");
        item.setHighQualityMusicUrl("hq_music_url");
        item.setThumbMediaId("thumb_media_id");
        message.setPayload(item);
        String result = formatter.format(message);
        assertNotNull(result);
        assertTrue(XMLUnit.compareXML(getContent(MusicItem.class), result).similar());
    }


}