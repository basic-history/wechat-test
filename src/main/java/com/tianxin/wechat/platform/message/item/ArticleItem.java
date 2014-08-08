package com.tianxin.wechat.platform.message.item;

import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Responsive;
import com.tianxin.wechat.platform.message.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图文消息
 *
 * @author snowway
 * @since 6/24/14 13:49
 */
public class ArticleItem implements Responsive {


    private List<Article> articles = new ArrayList<Article>();

    public int getCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleItem() {
    }

    public ArticleItem(Article... articles) {
        if (articles != null && articles.length > 0) {
            Collections.addAll(this.articles, articles);
        }
    }


    public void addArticle(Article article) {
        this.articles.add(article);
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_ARTICLE;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    public static class Article {

        /**
         * 标题
         */
        private String title;

        /**
         * 描述
         */
        private String description;

        /**
         * 图片连接
         */
        private String imageUrl;

        /**
         * 跳转地址
         */
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
