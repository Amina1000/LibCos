package com.cocos.develop.libcos.impl

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.domain.UserRepo

/**
 * homework com.cocos.develop.libcos.impl
 *
 * @author Amina
 * 29.09.2021
 */
class UserRoomRepoImpl: UserRepo {
    override fun saveUser(user: UserRepo) {
        //TODO("Not yet implemented")
    }

    override fun getUsers(): List<UserProfile> {
        return listOf()
    }

    override fun getUser(email: String): UserProfile?{
       return null
    }
}