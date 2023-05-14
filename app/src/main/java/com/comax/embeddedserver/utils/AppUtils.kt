package com.comax.embeddedserver.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.comax.embeddedserver.service.HostService

object AppUtils {

    fun createNotification(
        context: Context,
        title: String,
        body: String,
        @DrawableRes icon: Int
    ): Notification {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                HostService.CHANNEL_ID,
                "MyHostService",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(context, HostService.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(icon)
            .build()
    }

}