package com.cocos.develop.libcos.ui

import android.app.Application
import com.cocos.develop.libcos.domain.LoginRepo
import com.cocos.develop.libcos.domain.UserRepo
import com.cocos.develop.libcos.impl.LoginRoomRepoImpl
import com.cocos.develop.libcos.impl.UserRoomRepoImpl

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 06.10.2021
 */
class App:Application() {
    val userRepo: UserRepo = UserRoomRepoImpl()
    val loginRepo: LoginRepo = LoginRoomRepoImpl()
}