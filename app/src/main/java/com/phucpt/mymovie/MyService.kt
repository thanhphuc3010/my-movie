package com.phucpt.mymovie

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by Phucpt on 15/07/2023 at 16:10
 */

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}