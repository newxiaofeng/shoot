package com.feng.shoot.common.dao.album;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface AlbumDao {

    @Query("select * from Album")
    List<Album> getAllAlbums();

    @Query("select * from Album where phone=:phone")
    Album getAlbumByPhone(long phone);


    @Insert(onConflict = OnConflictStrategy.ABORT)
    long[]  insertAll(Album... albums);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Album album);

    @Update
    void Update(Album... albums);

    @Delete
    void delete(Album album);
}
