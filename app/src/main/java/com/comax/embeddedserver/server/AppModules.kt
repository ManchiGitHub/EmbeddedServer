package com.comax.embeddedserver.server

import com.comax.embeddedserver.feature.user.UserService
import com.comax.embeddedserver.feature.user.userRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppModules @Inject constructor(
	private val userService: UserService
) {
	fun Application.installModules() {
		setupContentNegotiation()
		routesModule()
		corsModule()
	}

	private fun Application.routesModule() {
		install(Routing) {
			userRoutes(userService)
		}
	}

	private fun Application.corsModule() {
		install(CORS) { anyHost() }
	}

	private fun Application.setupContentNegotiation() {
		install(ContentNegotiation) {
			json(Json {
				prettyPrint = true
				isLenient = true
			})
		}
	}
}