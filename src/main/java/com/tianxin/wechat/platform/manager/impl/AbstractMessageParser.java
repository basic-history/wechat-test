package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.manager.MessageParser;
import com.tianxin.wechat.platform.message.Content;
import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author snowway
 * @since 6/23/14 15:40
 */
public abstract class AbstractMessageParser implements MessageParser {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 创建Content
     *
     * @param type 类型
     * @return Content
     */
    protected Content createContent(String type) {
        if (StringUtils.equals(type, Message.TYPE_TEXT)) {
            return new TextItem();
        } else if (StringUtils.equals(type, Message.TYPE_IMAGE)) {
            return new ImageItem();
        } else if (StringUtils.equals(type, Message.TYPE_AUDIO)) {
            return new AudioItem();
        } else if (StringUtils.equals(type, Message.TYPE_VIDEO)) {
            return new VideoItem();
        } else if (StringUtils.equals(type, Message.TYPE_LINK)) {
            return new LinkItem();
        } else if (StringUtils.equals(type, Message.TYPE_LOCATION)) {
            return new LocationItem();
        } else {
            logger.warn("未识别的输入消息类型:{}", type);
            return null;
        }
    }


    /**
     * 创建Event
     *
     * @param event 事件类型
     * @return Event
     */
    protected Event createEvent(String event) {
        if (StringUtils.equals(event, Event.TYPE_SUBSCRIBE)) {
            return new SubscribeEvent();
        } else if (StringUtils.equals(event, Event.TYPE_UNSUBSCRIBE)) {
            return new UnSubscribeEvent();
        } else if (StringUtils.equals(event, Event.TYPE_SCAN)) {
            return new ScanEvent();
        } else if (StringUtils.equals(event, Event.TYPE_LOCATION)) {
            return new LocationEvent();
        } else if (StringUtils.equals(event, Event.TYPE_CLICK)) {
            return new ClickEvent();
        } else {
            logger.warn("未识别的输入事件类型:{}", event);
            return null;
        }
    }
}
