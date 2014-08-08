package com.tianxin.wechat.platform.navigation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表导航栏
 *
 * @author snowway
 * @since 6/26/14 15:48
 */
public class Navigation {

    private List<Item> items = new ArrayList<Item>();

    public abstract static class Item {

        public static final String TYPE_VIEW = "view";

        public static final String TYPE_CLICK = "click";

        public static final String TYPE_COMPOSITE = "composite";

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public abstract String getType();

        public boolean isClickItem() {
            return this instanceof ClickItem;
        }

        public boolean isViewItem() {
            return this instanceof ViewItem;
        }

        public boolean isCompositeItem() {
            return this instanceof CompositeItem;
        }

        public <T extends Item> T cast() {
            return (T) this;
        }

        public abstract JSONObject toJSON();
    }

    /**
     * 点击项
     */
    public static class ClickItem extends Item {


        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String getType() {
            return TYPE_CLICK;
        }

        @Override
        public JSONObject toJSON() {
            JSONObject object = new JSONObject();
            object.put("type", getType());
            object.put("name", getName());
            object.put("key", getKey());
            return object;
        }
    }

    /**
     * ViewItem
     */
    public static class ViewItem extends Item {


        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String getType() {
            return TYPE_VIEW;
        }

        @Override
        public JSONObject toJSON() {
            JSONObject object = new JSONObject();
            object.put("type", getType());
            object.put("name", getName());
            object.put("url", getUrl());
            return object;
        }
    }


    /**
     * 符合导航项
     */
    public static class CompositeItem extends Item {


        private List<Item> items = new ArrayList<Item>();

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        @Override
        public String getType() {
            return TYPE_COMPOSITE;
        }

        @Override
        public JSONObject toJSON() {
            JSONObject object = new JSONObject();
            object.put("name", getName());
            if (!this.items.isEmpty()) {
                JSONArray array = new JSONArray();
                for (Item item : items) {
                    array.put(item.toJSON());
                }
                object.put("sub_button", array);
            }
            return object;
        }

        /**
         * 添加可点击的项目
         *
         * @param name 名称
         * @param key  点击项附加的key
         * @return this
         */
        public CompositeItem addClickItem(String name, String key) {
            this.items.add(createClickItem(name, key));
            return this;
        }

        /**
         * 添加可点击的项目
         *
         * @param name 名称
         * @param url  跳转地址
         * @return this
         */
        public CompositeItem addViewItem(String name, String url) {
            this.items.add(createViewItem(name, url));
            return this;
        }
    }


    /**
     * 创建点击Item
     *
     * @param name 名称
     * @param key  点击项附加的key
     * @return Item
     */
    private static Item createClickItem(String name, String key) {
        ClickItem item = new ClickItem();
        item.setName(name);
        item.setKey(key);
        return item;
    }

    /**
     * 创建ViewItem
     *
     * @param name 名称
     * @param url  跳转地址
     * @return Item
     */
    private static Item createViewItem(String name, String url) {
        ViewItem item = new ViewItem();
        item.setName(name);
        item.setUrl(url);
        return item;
    }

    /**
     * 添加可点击的项目
     *
     * @param name 名称
     * @param key  点击项附加的key
     * @return this
     */
    public Navigation addClickItem(String name, String key) {
        this.items.add(createClickItem(name, key));
        return this;
    }

    /**
     * 添加可点击的项目
     *
     * @param name 名称
     * @param url  跳转地址
     * @return this
     */
    public Navigation addViewItem(String name, String url) {
        this.items.add(createViewItem(name, url));
        return this;
    }

    /**
     * 添加可点击的项目
     *
     * @param name 名称
     * @return this
     */
    public CompositeItem addCompositeItem(String name) {
        CompositeItem item = new CompositeItem();
        item.setName(name);
        this.items.add(item);
        return item;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        if (!this.items.isEmpty()) {
            JSONArray buttons = new JSONArray();
            for (Item item : items) {
                buttons.put(item.toJSON());
            }
            object.put("button", buttons);
        }
        return object;
    }
}
