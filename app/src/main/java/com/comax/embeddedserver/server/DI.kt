package com.comax.embeddedserver.server

import com.comax.embeddedserver.BuildConfig
import com.comax.embeddedserver.feature.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Singleton
    @Provides
    fun provideAppModules(userService: UserService): AppModules {
        return AppModules(userService)
    }

    @Singleton
    @Provides
    fun provideApplicationEngineEnvironment(appModules: AppModules): ApplicationEngineEnvironment {
      return applicationEngineEnvironment {
          module { appModules.installModules(this) }
          connector {
              host = BuildConfig.LOCAL_HOST
              port = BuildConfig.PORT
          }
      }
    }

    @Singleton
    @Provides
    fun provideApplicationEngine(environmentConfig: ApplicationEngineEnvironment): NettyApplicationEngine {
        return embeddedServer(
            factory = Netty,
            environment = environmentConfig
        )
    }
}