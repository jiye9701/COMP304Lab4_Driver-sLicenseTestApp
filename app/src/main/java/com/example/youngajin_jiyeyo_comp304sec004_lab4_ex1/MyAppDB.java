package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =
        {
                Applicant.class,
                Test.class,
                Examiner.class
        },
        version = 1)


//add

public abstract class MyAppDB extends RoomDatabase {

    private static final String dbName = "myDB";
    private static MyAppDB myappdb;

    public static synchronized  MyAppDB getMyAppDB (Context context)

    {

        if (myappdb == null){
            myappdb = Room.databaseBuilder(context, MyAppDB.class, dbName)
                    .fallbackToDestructiveMigration().build();
        }

        return myappdb;

    }
    public abstract MyDao myDao();


}
