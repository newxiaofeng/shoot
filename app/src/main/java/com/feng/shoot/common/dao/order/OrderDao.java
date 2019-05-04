package com.feng.shoot.common.dao.order;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.feng.shoot.common.dao.album.Album;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("select * from `Order` where customerPhone=:phone")
    List<Order> getOrderByCustomerPhone(long phone);

    @Query("select * from `Order` where artistPhone=:phone")
    List<Order> getOrderByArtistPhone(long phone);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Order order);
}
