package com.demo.currencylist.room

import android.app.Application
import android.util.Log
import androidx.room.Room

object RoomDb {

    private lateinit var instance: AppDatabase

    fun getInstance(application: Application) : AppDatabase{
        Log.d("RoomDb","get db instance")
        if(this::instance.isInitialized){
            return instance
        }
        Log.d("RoomDb","init db instance")
        instance = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "demoDb"
        ).build()
        return instance
    }

}