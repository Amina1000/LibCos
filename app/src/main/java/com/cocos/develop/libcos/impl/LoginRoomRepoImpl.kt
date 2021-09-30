package com.cocos.develop.libcos.impl

import com.cocos.develop.libcos.domain.LoginDataSource
import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.domain.LoginRepo
import com.cocos.develop.libcos.domain.ResultState

/**
 * homework com.cocos.develop.libcos.impl
 *
 * @author Amina
 * 30.09.2021
 */
class LoginRoomRepoImpl(private val dataSource:LoginDataSource = LoginDataSource()):LoginRepo {

    override fun checkLogin(username: String, password: String): ResultState<LoginEntity> {
        return dataSource.login(username, password)
    }

}