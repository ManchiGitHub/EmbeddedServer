package com.comax.embeddedserver.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.comax.embeddedserver.R
import com.comax.embeddedserver.server.Server
import com.comax.embeddedserver.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HostService @Inject constructor() : Service() {

    @Inject
    lateinit var localServer: Server

    override fun onCreate() {
        super.onCreate()
        startForeground()
        startServer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            STOP_SERVICE -> stopServer()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForeground() {

        val notification = AppUtils.createNotification(
            context = this@HostService,
            title = "Comax Service",
            body = "Host service running",
            R.drawable.ic_sync
        )

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun startServer() {
        localServer.start()
    }

    private fun stopServer() {
        localServer.stop()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? = null

    companion object {

        const val CHANNEL_ID = "com.comax.embeddedserver.service.host_service"
        const val STOP_SERVICE = "com.comax.embeddedserver.ACTION_STOP_SERVICE"
        const val NOTIFICATION_ID = 1001
    }

}