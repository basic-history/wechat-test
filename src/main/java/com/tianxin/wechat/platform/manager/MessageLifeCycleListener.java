package com.tianxin.wechat.platform.manager;

import com.tianxin.wechat.platform.message.Message;

/**
 * 微信平台消息声明周期接口
 *
 * @author snowway
 * @since 6/24/14 14:59
 */
public interface MessageLifeCycleListener {

    /**
     * MessageLifeCycleListener适配器
     */
    class Adapter implements MessageLifeCycleListener {

        @Override
        public void beforeParse(String content) {

        }

        @Override
        public void afterParse(Message message) {

        }

        @Override
        public void beforeHandle(Message message) {

        }

        @Override
        public void afterHandle(Message message) {

        }

        @Override
        public void beforeResponse(String message) {

        }

        @Override
        public void exceptionCaught(Throwable reason) {

        }
    }


    /**
     * 未解析时触发
     *
     * @param content 消息原始内容
     */
    void beforeParse(String content);

    /**
     * 解析消息成功后触发
     *
     * @param message 解析后的消息对象
     */
    void afterParse(Message message);


    /**
     * 消息即将分发时调用
     */
    void beforeHandle(Message message);

    /**
     * 消息分发并调用成功后调用
     */
    void afterHandle(Message message);


    /**
     * 当消息即将返回时回调
     *
     * @param message 返回给用户的消息字符串
     */
    void beforeResponse(String message);


    /**
     * 当处理过程发生异常时回调
     *
     * @param reason 原因
     */
    void exceptionCaught(Throwable reason);
}
