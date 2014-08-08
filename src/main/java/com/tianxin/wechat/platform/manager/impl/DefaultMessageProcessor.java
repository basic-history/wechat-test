package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.manager.*;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Payload;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;
import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author snowway
 * @since 6/25/14 11:12
 */
public class DefaultMessageProcessor implements MessageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageLifeCycleListener lifeCycleListener = new MessageLifeCycleListener.Adapter();

    private MessageHandler messageHandler = new MessageHandler.Adpter();

    private MessageParser messageParser = new XmlMessageParser();

    private MessageFormatter messageFormatter = new XmlMessageFormatter();

    public void setLifeCycleListener(MessageLifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }


    public void setMessageParser(MessageParser messageParser) {
        this.messageParser = messageParser;
    }

    public void setMessageFormatter(MessageFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    @Override
    public String process(String content) {
        try {
            logger.debug("开始解析微信消息:{}", content);
            lifeCycleListener.beforeParse(content);
            final Message message = messageParser.parse(content);
            logger.debug("解析微信消息成功,当前消息ID:{},类型:{}", message.getId(), message.getType());
            lifeCycleListener.afterParse(message);
            lifeCycleListener.beforeHandle(message);
            final Responsive[] responsives = new Responsive[1];
            message.accept(new Visitor.Adapter() {
                @Override
                public void visit(TextItem textItem) {
                    responsives[0] = messageHandler.onText(message, textItem);
                }

                @Override
                public void visit(AudioItem audioItem) {
                    responsives[0] = messageHandler.onAudio(message, audioItem);
                }

                @Override
                public void visit(ImageItem imageItem) {
                    responsives[0] = messageHandler.onImage(message, imageItem);
                }

                @Override
                public void visit(LinkItem linkItem) {
                    responsives[0] = messageHandler.onLink(message, linkItem);
                }

                @Override
                public void visit(LocationItem locationItem) {
                    responsives[0] = messageHandler.onLocation(message, locationItem);
                }

                @Override
                public void visit(VideoItem videoItem) {
                    responsives[0] = messageHandler.onVideo(message, videoItem);
                }

                @Override
                public void visit(SubscribeEvent subscribeEvent) {
                    responsives[0] = messageHandler.onSubscribe(message, subscribeEvent);
                }

                @Override
                public void visit(UnSubscribeEvent unsubscribeEvent) {
                    responsives[0] = messageHandler.onUnSubscribe(message, unsubscribeEvent);
                }

                @Override
                public void visit(ScanEvent scanEvent) {
                    responsives[0] = messageHandler.onScan(message, scanEvent);
                }

                @Override
                public void visit(LocationEvent locationEvent) {
                    responsives[0] = messageHandler.onLocation(message, locationEvent);
                }

                @Override
                public void visit(ClickEvent clickEvent) {
                    responsives[0] = messageHandler.onClick(message, clickEvent);
                }
            });
            lifeCycleListener.afterHandle(message);
            if (responsives[0] == null) {
                logger.warn("MessageHandler:{}对应的回调方法未返回值", messageHandler);
                return null;
            }
            Payload payload = responsives[0];
            Message result = new Message();
            result.setFrom(message.getTo());
            result.setTo(message.getFrom());
            result.setCreateTime(System.currentTimeMillis());
            result.setType(payload.getMessageType());
            result.setPayload(payload);
            logger.debug("创建返回消息,类型:{}", result.getType());
            String response = messageFormatter.format(result);
            logger.debug("即将返回消息{}给发送者", response);
            lifeCycleListener.beforeResponse(response);
            return response;
        } catch (Throwable ex) {
            lifeCycleListener.exceptionCaught(ex);
            return null;
        }
    }
}
