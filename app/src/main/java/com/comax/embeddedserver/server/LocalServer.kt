package com.comax.embeddedserver.server

import com.comax.embeddedserver.di.LocalServerDispatcher
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalServer @Inject constructor(
    private val engine: NettyApplicationEngine,
//    private val appModules: AppModules,
    @LocalServerDispatcher private val dispatcherIO: CoroutineDispatcher,
) : Server, CoroutineScope {

    private val serverJob: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = dispatcherIO + serverJob

//    private val engine by lazy {
//        embeddedServer(
//            factory = Netty,
//            environment = environmentConfig
//        )
//    }
//
//    private val environmentConfig = applicationEngineEnvironment {
//        module { appModules.installModules(this) }
//        connector {
//            host = BuildConfig.LOCAL_HOST
//            port = BuildConfig.PORT
//        }
//    }

    override fun start() {
        launch { engine.start(wait = true) }
    }

    override fun stop() {
        launch {
            runCatching {
                engine.stop(GRACE_PERIOD, TIMEOUT, TimeUnit.MILLISECONDS)
            }.onFailure {
                Timber.e(it)
            }.onSuccess {
                Timber.i("Server has stopped")
            }
        }
    }



    companion object {

        /**
         * The maximum amount of time for activity to cool down before stopping the server
         */
        private const val GRACE_PERIOD = 1_000L

        /**
         * The maximum amount of time to wait until server stops gracefully
         */
        private const val TIMEOUT = 2_000L
    }
}

