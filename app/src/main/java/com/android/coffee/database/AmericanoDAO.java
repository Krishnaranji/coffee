package com.android.coffee.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AmericanoDAO {

    @Query("Select * from Coffee")
    List<AmericanaEntity> getEntityData();

    @Insert
    void insertAmericana(AmericanaEntity entity);

    @Update
    void updateAmericana(AmericanaEntity entity);
}
