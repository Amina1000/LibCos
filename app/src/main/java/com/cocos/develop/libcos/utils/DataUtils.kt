package com.cocos.develop.libcos.utils

/**
 * homework com.cocos.develop.libcos.utils
 *
 * @author Amina
 * 29.09.2021
 */
fun getErrorByCode(errorCode: ErrorCode, field:String): String {
    return when (errorCode) {
        ErrorCode.NOT_FOUND -> "$field have not founded"
        ErrorCode.UNAUTHORIZED -> "$field have not unauthorized"
        ErrorCode.GATEWAY_TIMEOUT -> "$field have not founded server timeout"
        ErrorCode.SERVICE_UNAVAILABLE -> "$field have not founded server unavailable"
    }
}
