package com.cocos.develop.libcos.utils

import android.content.Context
import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.ui.App
import java.util.*

/**
 * homework com.cocos.develop.libcos.utils
 *
 * @author Amina
 * 29.09.2021
 */
val fakeUser = LoginEntity(
    UUID.randomUUID().toString(),
    "amina1000@test.ru", "654321"
)

fun getErrorByCode(errorCode: ErrorCode, field: String): String {
    return when (errorCode) {
        ErrorCode.NOT_FOUND -> "$field have not founded"
        ErrorCode.UNAUTHORIZED -> "$field have not unauthorized"
        ErrorCode.GATEWAY_TIMEOUT -> "$field have not founded server timeout"
        ErrorCode.SERVICE_UNAVAILABLE -> "$field have not founded server unavailable"
        ErrorCode.TYPE_ERROR -> "$field have error format"
    }
}

fun checkCurrentEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".")
}

val Context.app: App
    get() {
        return  applicationContext as App
    }

