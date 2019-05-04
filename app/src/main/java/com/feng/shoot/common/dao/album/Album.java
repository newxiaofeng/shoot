package com.feng.shoot.common.dao.album;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.feng.shoot.common.dao.user.User;

import java.util.ArrayList;

@Entity
public class Album {
    @PrimaryKey(autoGenerate = true)
    private int id;


    public long phone;

    /**
     * 封面
     */
    public String cover;

    /**
     * 标题
     */
    public String title;


    /**
     * 价格
     */
    public String price;

    /**
     * 作品类型
     */
    public String type;

    /**
     * 个人资料
     */
    public String content;


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
