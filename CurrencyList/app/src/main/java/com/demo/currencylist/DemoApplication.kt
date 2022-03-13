package com.demo.currencylist

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.demo.currencylist.room.RoomDb

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RoomDb.initDB(this)
    }
}