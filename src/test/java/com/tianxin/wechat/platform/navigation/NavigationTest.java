package com.tianxin.wechat.platform.navigation;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class NavigationTest {

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
        Assert.assertEquals(4, navigation.getItems().size());
        Assert.assertEquals("点击菜单1", navigation.getItems().get(0).getName());
        Assert.assertEquals("KEY1", navigation.getItems().get(0).<Navigation.ClickItem>cast().getKey());

        Assert.assertEquals("点击菜单2", navigation.getItems().get(1).getName());
        Assert.assertEquals("KEY2", navigation.getItems().get(1).<Navigation.ClickItem>cast().getKey());

        Assert.assertEquals("跳转菜单", navigation.getItems().get(2).getName());
        Assert.assertEquals("http://www.baidu.com", navigation.getItems().get(2).<Navigation.ViewItem>cast().getUrl());

        Navigation.CompositeItem item = navigation.getItems().get(3).cast();
        Assert.assertEquals(3, item.getItems().size());

        Assert.assertEquals("关于我们", item.getItems().get(0).getName());
        Assert.assertEquals("about", item.getItems().get(0).<Navigation.ClickItem>cast().getKey());

        Assert.assertEquals("联系我们", item.getItems().get(1).getName());
        Assert.assertEquals("contact", item.getItems().get(1).<Navigation.ClickItem>cast().getKey());

        Assert.assertEquals("主页", item.getItems().get(2).getName());
        Assert.assertEquals("http://www.shtianxin.com", item.getItems().get(2).<Navigation.ViewItem>cast().getUrl());

        JSONObject result = navigation.toJSON();
        Assert.assertNotNull(result);
    }

}