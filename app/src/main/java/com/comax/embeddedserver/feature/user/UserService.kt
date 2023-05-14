package com.comax.embeddedserver.feature.user

import com.comax.embeddedserver.model.User
import javax.inject.Inject


class UserService @Inject constructor(
    private val userRepository: UserRepository
) {

    fun userList(): List<User> = userRepository.userList()

    fun addUser(user: User): User {
        if (user.name == null)
            throw MissingParamsException("name")
        if (user.age == null)
            throw MissingParamsException("age")
        if (user.age < 0)
            throw GeneralException("Age cannot be negative number")
        return userRepository.addUser(user)
    }

    fun removeUser(id: Int): User = userRepository.removeUser(id)
}

open class CustomExceptions(val status: Int, val description: String) : Exception(description)

class MissingParamsException(param: String) : CustomExceptions(100, "Missing parameter: $param")
class GeneralException(description: String) : CustomExceptions(999, description)