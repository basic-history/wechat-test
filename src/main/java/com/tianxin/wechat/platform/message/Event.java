package com.tianxin.wechat.platform.message;

/**
 * 微信事件
 *
 * @author snowway
 * @since 6/23/14 18:19
 */
public interface Event extends Payload {

    /**
     * 关注事件
     */
    String TYPE_SUBSCRIBE = "subscribe";


    /**
     * 取消关注事件
     */
    String TYPE_UNSUBSCRIBE = "unsubscribe";


    /**
     * 扫描关注事件
     */
    String TYPE_SCAN = "scan";

    /**
     * 上报地理位置事件
     */
    String TYPE_LOCATION = "LOCATION";


    /**
     * 自定义菜单事件
     */
    String TYPE_CLICK = "CLICK";

    /**
     * @return 事件类型
     */
    String getEventType();
}
