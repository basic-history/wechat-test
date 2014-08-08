package com.tianxin.wechat.platform.manager;

import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;

/**
 * 当收到微信服务器推送的消息时触发
 *
 * @author snowway
 * @since 6/24/14 14:41
 */
public interface MessageHandler {


    /**
     * 适配器模式
     */
    class Adpter implements MessageHandler {

        @Override
        public Responsive onText(Message message, TextItem item) {
            return null;
        }

        @Override
        public Responsive onImage(Message message, ImageItem item) {
            return null;
        }

        @Override
        public Responsive onAudio(Message message, AudioItem item) {
            return null;
        }

        @Override
        public Responsive onVideo(Message message, VideoItem item) {
            return null;
        }

        @Override
        public Responsive onLocation(Message message, LocationItem item) {
            return null;
        }

        @Override
        public Responsive onLink(Message message, LinkItem item) {
            return null;
        }

        @Override
        public Responsive onSubscribe(Message message, SubscribeEvent event) {
            return null;
        }

        @Override
        public Responsive onUnSubscribe(Message message, UnSubscribeEvent event) {
            return null;
        }

        @Override
        public Responsive onScan(Message message, ScanEvent event) {
            return null;
        }

        @Override
        public Responsive onLocation(Message message, LocationEvent event) {
            return null;
        }

        @Override
        public Responsive onClick(Message message, ClickEvent event) {
            return null;
        }
    }

    /**
     * 当收到文本消息后触发
     *
     * @param message 消息
     * @param item    文本消息内容
     * @return 返回给发送者的消息
     */
    Responsive onText(Message message, TextItem item);


    /**
     * 当收到图片消息后触发
     *
     * @param message 消息
     * @param item    图片消息内容
     * @return 返回给发送者的消息
     */
    Responsive onImage(Message message, ImageItem item);


    /**
     * 当收到语音消息后触发
     *
     * @param message 消息
     * @param item    语音消息内容
     * @return 返回给发送者的消息
     */
    Responsive onAudio(Message message, AudioItem item);


    /**
     * 当收到视频消息后触发
     *
     * @param message 消息
     * @param item    视频消息内容
     * @return 返回给发送者的消息
     */
    Responsive onVideo(Message message, VideoItem item);


    /**
     * 当收到位置消息后触发
     *
     * @param message 消息
     * @param item    位置消息内容
     * @return 返回给发送者的消息
     */
    Responsive onLocation(Message message, LocationItem item);


    /**
     * 当收到链接消息后触发
     *
     * @param message 消息
     * @param item    链接消息内容
     * @return 返回给发送者的消息
     */
    Responsive onLink(Message message, LinkItem item);


    /**
     * 当收到订阅事件后触发
     *
     * @param message 消息
     * @param event   订阅事件
     * @return 返回给发送者的消息
     */
    Responsive onSubscribe(Message message, SubscribeEvent event);


    /**
     * 当收到订阅事件后触发
     *
     * @param message 消息
     * @param event   订阅事件
     * @return 返回给发送者的消息
     */
    Responsive onUnSubscribe(Message message, UnSubscribeEvent event);


    /**
     * 当收到扫描事件后触发
     *
     * @param message 消息
     * @param event   扫描事件
     * @return 返回给发送者的消息
     */
    Responsive onScan(Message message, ScanEvent event);


    /**
     * 当收到上报地理位置事件后触发
     *
     * @param message 消息
     * @param event   上报地理位置事件
     * @return 返回给发送者的消息
     */
    Responsive onLocation(Message message, LocationEvent event);


    /**
     * 当收到菜单点击事件后触发
     *
     * @param message 消息
     * @param event   菜单点击事件
     * @return 返回给发送者的消息
     */
    Responsive onClick(Message message, ClickEvent event);
}
