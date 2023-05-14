package com.comax.embeddedserver.feature.user


import com.comax.embeddedserver.model.ResponseBase
import com.comax.embeddedserver.model.User
import com.google.gson.Gson
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    userService: UserService
) {

    get("/") {
        call.respond(mapOf("message" to "Hello world"))
    }

    get("/user") {
        println("get user endpoint reached")
        call.respond(mapOf("user" to Gson().toJson(userService.userList())))
//        call.respond(ResponseBase(data = userService.userList()))
    }

    post("/user") {
        println("post user endpoint reached")
        val person = call.receive<User>()
        call.respond(ResponseBase(data = userService.addUser(person)))
    }

    delete("/user/{id}") {
        println("delete user endpoint reached with id: ${call.parameters["id"]?.toInt() ?: "n/a"}")
        val id = call.parameters["id"]?.toInt()!! // Force just for this example
        call.respond(ResponseBase(data = userService.removeUser(id)))
    }
}