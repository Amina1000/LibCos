package com.cocos.develop.libcos.utils

/**
 * homework com.cocos.develop.libcos.utils
 *
 * @author Amina
 * 29.09.2021
 */
fun getErrorByCode(errorCode: Int): String {
    when (errorCode) {
        0 -> return "Organization have not founded"
        1 -> return "Organization disabled"
    }
    return "Organization error"
}
