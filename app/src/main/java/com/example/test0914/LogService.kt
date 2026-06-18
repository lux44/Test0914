package com.example.test0914

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class LogService: Service() {

    /**
     * [SERVICE 백그라운드 서비스 실습
     * - 화면 없이 동작하는 컴포넌트
     * - 생명주기: onCreate → onStartCommand → onDestroy
     * - AndroidManifest.xml에 등록 필요
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("LogService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("LogService", "onStartCommand")
        Toast.makeText(this, "Service 실행", Toast.LENGTH_SHORT).show()
        // [SERVICE] START_STICKY: 서비스가 종료되면 자동 재시작
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d("LogService", "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}