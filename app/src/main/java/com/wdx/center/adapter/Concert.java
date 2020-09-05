package com.wdx.center.adapter;

import com.wdx.center.R;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:18
 */
public class Concert {
    public String title;
    public String author;
    public String content;

    public Concert() {
        this.title = "wdx";
        this.author = "author";
        this.content = "content";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public boolean equals(String obj){

        return this.equals(obj);
    }}
