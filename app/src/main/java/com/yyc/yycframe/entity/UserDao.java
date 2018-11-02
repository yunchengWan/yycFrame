package com.yyc.yycframe.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    Maybe<List<User>> getAllUser();

    @Insert
    void insertUser(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void deleteUser(User user);
}
