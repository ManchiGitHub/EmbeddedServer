package com.comax.embeddedserver.server

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.comax.embeddedserver.service.HostService

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> startService(context)
        }
    }

    private fun startService(context: Context?) {
        val startIntent = Intent(context, HostService::class.java)
        ContextCompat.startForegroundService(context!!, startIntent)
    }
}