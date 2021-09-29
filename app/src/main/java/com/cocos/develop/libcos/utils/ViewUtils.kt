package com.cocos.develop.libcos.utils

import android.widget.EditText

/**
 * homework com.cocos.develop.libcos.utils
 *
 * @author Amina
 * 29.09.2021
 */
fun EditText.parsToInt():Int{
    return this.text?.toString()?.toInt()?:0
}

fun EditText.parsToString():String{
    return this.text.toString()
}