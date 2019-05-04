package com.feng.shoot.common.dao.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from User")
    List<User> getAll();

    @Query("select * from User where phone = :phone")
    User getUserByPhone(long phone);


    @Insert(onConflict = OnConflictStrategy.ABORT)
    long[]  insertAll(User... users);

    @Update
    void Update(User... users);

    @Delete
    void delete(User user);
}
