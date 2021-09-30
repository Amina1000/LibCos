package com.cocos.develop.libcos.domain

/**
 * homework com.cocos.develop.libcos.domain
 *
 * @author Amina
 * 30.09.2021
 */
interface LoginRepo {
    fun checkLogin(username:String, password:String): ResultState<LoginEntity>
}