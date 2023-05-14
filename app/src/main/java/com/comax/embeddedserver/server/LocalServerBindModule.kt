package com.comax.embeddedserver.server

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.server.engine.*
import io.ktor.server.netty.*

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalServerBindModule {

    @Binds
    abstract fun provideLocalHostServer(localServer: LocalServer): Server
}