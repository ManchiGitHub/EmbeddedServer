package com.comax.embeddedserver.feature.user

import com.comax.embeddedserver.model.User

interface UserRepository {
    fun userList(): ArrayList<User>

    fun addUser(user: User): User

    fun removeUser(id: Int): User
}