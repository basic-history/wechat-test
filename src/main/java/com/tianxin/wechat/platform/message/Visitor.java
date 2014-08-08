package com.tianxin.wechat.platform.message;

import com.tianxin.wechat.platform.message.event.*;
import com.tianxin.wechat.platform.message.item.*;

/**
 * Message Visitor
 *
 * @author snowway
 * @since 6/23/14 15:16
 */
public interface Visitor {


    class Adapter implements Visitor {

        @Override
        public void visit(TextItem textItem) {
        }

        @Override
        public void visit(AudioItem audioItem) {
        }

        @Override
        public void visit(ImageItem imageItem) {
        }

        @Override
        public void visit(LinkItem linkItem) {
        }

        @Override
        public void visit(LocationItem locationItem) {

        }

        @Override
        public void visit(VideoItem videoItem) {

        }

        @Override
        public void visit(ArticleItem articleItem) {

        }

        @Override
        public void visit(MusicItem musicItem) {

        }

        @Override
        public void visit(SubscribeEvent subscribeEvent) {

        }

        @Override
        public void visit(UnSubscribeEvent unsubscribeEvent) {

        }

        @Override
        public void visit(ScanEvent scanEvent) {

        }

        @Override
        public void visit(LocationEvent locationEvent) {

        }

        @Override
        public void visit(ClickEvent clickEvent) {

        }
    }

    /**
     * 访问文本消息
     *
     * @param textItem 文本消息
     */
    void visit(TextItem textItem);

    /**
     * 访问语音消息
     *
     * @param audioItem 语音消息
     */
    void visit(AudioItem audioItem);

    /**
     * 访问图片消息
     *
     * @param imageItem 图片消息
     */
    void visit(ImageItem imageItem);

    /**
     * 访问链接消息
     *
     * @param linkItem 链接消息
     */
    void visit(LinkItem linkItem);

    /**
     * 访问地理位置信息
     *
     * @param locationItem 地理位置信息
     */
    void visit(LocationItem locationItem);

    /**
     * 访问视频消息
     *
     * @param videoItem 视频消息
     */
    void visit(VideoItem videoItem);


    /**
     * 访问图文消息
     *
     * @param articleItem 图文消息
     */
    void visit(ArticleItem articleItem);


    /**
     * 访问音乐消息
     *
     * @param musicItem 音乐消息
     */
    void visit(MusicItem musicItem);


    /**
     * 访问关注事件
     *
     * @param subscribeEvent 关注事件
     */
    void visit(SubscribeEvent subscribeEvent);

    /**
     * 访问取消关注事件
     *
     * @param unsubscribeEvent 取消关注事件
     */
    void visit(UnSubscribeEvent unsubscribeEvent);

    /**
     * 访问扫描事件
     *
     * @param scanEvent 扫描事件
     */
    void visit(ScanEvent scanEvent);

    /**
     * 访问地理位置事件
     *
     * @param locationEvent 地位位置上报事件
     */
    void visit(LocationEvent locationEvent);

    /**
     * 访问菜单点击事件
     *
     * @param clickEvent 菜单点击事件
     */
    void visit(ClickEvent clickEvent);
}
