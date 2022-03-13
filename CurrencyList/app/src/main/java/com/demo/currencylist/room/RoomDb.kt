package com.demo.currencylist.room

import android.app.Application
import android.util.Log
import androidx.room.Room

object RoomDb {

    private lateinit var instance: AppDatabase

    fun initDB(application: Application){
        instance = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "demoDb"
        ).build()
    }

    fun getInstance() : AppDatabase{
        Log.d("RoomDb","get db instance")
        return instance
    }

}