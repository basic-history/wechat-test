package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.manager.Configuration;
import com.tianxin.wechat.platform.navigation.Navigation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultPlatformApiManagerTest {

    private DefaultPlatformApiManager manager;

    @Before
    public void setup() {
        manager = new DefaultPlatformApiManager();
        Configuration configuration = new Configuration();
        configuration.setAppid("appid");
        configuration.setSecret("secret");
        configuration.setAccessTokenUrl("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid={appid}&secret={secret}");
        configuration.setNavigationCreateUrl("https://api.weixin.qq.com/cgi-bin/menu/create?access_token={accessToken}");
        manager.setConfiguration(configuration);
        manager.initialize();
    }

    @After
    public void teardown() {
        manager.destroy();
    }

    @Test
    public void testGetAccessToken() throws Exception {
        String accessToken = manager.getAccessToken();
    }

    @Test
    public void testCreateNavigation() {
        Navigation navigation = new Navigation();
        navigation.addClickItem("点击菜单1", "KEY1")
                .addClickItem("点击菜单2", "KEY2")
                .addViewItem("跳转菜单", "http://www.baidu.com")
                .addCompositeItem("更多")
                .addClickItem("关于我们", "about")
                .addClickItem("联系我们", "contact")
                .addViewItem("主页", "http://www.shtianxin.com");
        manager.createNavigation(navigation);
    }
}