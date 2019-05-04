package com.feng.shoot.common.dao.order;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public long customerPhone;

    public long artistPhone;

    public long timestamp;

    public long Date;

    public int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(long customerPhone) {
        this.customerPhone = customerPhone;
    }

    public long getArtistPhone() {
        return artistPhone;
    }

    public void setArtistPhone(long artistPhone) {
        this.artistPhone = artistPhone;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
