package com.cocos.develop.libcos.domain

/**
 * homework com.cocos.develop.libcos
 *
 * @author Amina
 * 28.09.2021
 */
data class UserProfile(
    val email:String,
    val firstName:String,
    val secondName: String,
    val age: Int,
    val country: String,
    val organization: String,
    val picUrl: String,
    val resume:String,
    val phone: String,
    val id:String
    )
