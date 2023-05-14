package com.comax.embeddedserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.comax.embeddedserver.service.HostService
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startIntent = Intent(this, HostService::class.java)
        ContextCompat.startForegroundService(this, startIntent)

        findViewById<MaterialButton>(R.id.kill_server_btn).setOnClickListener {
            val stopIntent = Intent(this, HostService::class.java)
            stopIntent.action = HostService.STOP_SERVICE
            startService(stopIntent)
        }
    }
}