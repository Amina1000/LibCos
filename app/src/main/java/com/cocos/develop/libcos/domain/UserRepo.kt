package com.cocos.develop.libcos.domain

/**
 * homework com.cocos.develop.libcos.domain
 *
 * @author Amina
 * 29.09.2021
 */
interface UserRepo {
    fun saveUser(user:UserProfile)
    fun getUsers(): List<UserProfile>
    fun getUser(email: String):UserProfile?
    fun getOrganization(): String
}