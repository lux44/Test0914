package com.example.test0914

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryLowReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BATTERY_LOW ->
                Toast.makeText(context, "배터리가 부족합니다!", Toast.LENGTH_LONG).show()

            Intent.ACTION_BATTERY_OKAY ->
                Toast.makeText(context, "배터리가 정상입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}