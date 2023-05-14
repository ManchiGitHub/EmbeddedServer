package com.comax.embeddedserver.feature.user

import com.comax.embeddedserver.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImp @Inject constructor() : UserRepository {

    private var idCount = 0

    // Dummy content
    private val userList = ArrayList<User>().apply {
        add(User(1,"Marko Katziv", 31))
        add(User(2,"Yossi", 46))
        add(User(3,"Lionel Messi", 35))
        add(User(4,"Christinao Ronaldo", 27))
        add(User(5,"Jeymar Jr.", 29))
    }

    override fun userList(): ArrayList<User> = userList

    override fun addUser(user: User): User {
        val newUser = user.copy(id = ++idCount);
        userList.add(newUser)
        return newUser
    }

    override fun removeUser(id: Int): User {
        userList.find { it.id == id }?.let {
            userList.remove(it)
            return it
        }
        throw GeneralException("Cannot remove user: $id")
    }

}