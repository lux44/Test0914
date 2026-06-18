package com.example.test0914

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class LogService: Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("LogService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("LogService", "onStartCommand")
        Toast.makeText(this, "Service 실행", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d("LogService", "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}