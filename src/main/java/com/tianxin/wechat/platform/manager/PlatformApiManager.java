package com.tianxin.wechat.platform.manager;

import com.tianxin.wechat.platform.navigation.Navigation;

/**
 * 微信公众平台接口
 *
 * @author snowway
 * @since 6/25/14 16:53
 */
public interface PlatformApiManager {


    /**
     * 初始化
     */
    void initialize();


    /**
     * 销毁
     */
    void destroy();

    /**
     * 获取AccessToken
     *
     * @return Access Token
     */
    String getAccessToken();


    /**
     * 创建导航栏
     *
     * @param navigation 导航栏对象
     */
    boolean createNavigation(Navigation navigation);
}
