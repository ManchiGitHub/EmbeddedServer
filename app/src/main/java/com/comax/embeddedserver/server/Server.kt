package com.comax.embeddedserver.server

import kotlinx.coroutines.Job

interface Server {
    fun start()
    fun stop()
}