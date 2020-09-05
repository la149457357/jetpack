package com.wdx.center.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.wdx.center.room.UserDao;
import com.wdx.center.room.User;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 17:36
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}