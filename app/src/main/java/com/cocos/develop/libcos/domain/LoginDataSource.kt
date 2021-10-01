package com.cocos.develop.libcos.domain

import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.fakeUser
import com.cocos.develop.libcos.utils.getErrorByCode
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): ResultState<LoginEntity> {
        try {
            if (fakeUser.email == username && fakeUser.password == password)
                return ResultState.Success(fakeUser)
            else
                return ResultState.Error(IOException(getErrorByCode(ErrorCode.NOT_FOUND, "email")))
        } catch (e: Throwable) {
            return ResultState.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}