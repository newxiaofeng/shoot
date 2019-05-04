package com.feng.shoot.common.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.feng.shoot.common.dao.album.Album;
import com.feng.shoot.common.dao.album.AlbumDao;
import com.feng.shoot.common.dao.order.Order;
import com.feng.shoot.common.dao.order.OrderDao;
import com.feng.shoot.common.dao.user.User;
import com.feng.shoot.common.dao.user.UserDao;

@Database(entities = {User.class, Album.class, Order.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    public abstract AlbumDao getAlbumDao();

    public abstract OrderDao getOrderDao();
}
