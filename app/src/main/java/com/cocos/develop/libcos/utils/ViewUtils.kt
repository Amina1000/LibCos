package com.cocos.develop.libcos.utils

import android.widget.EditText

/**
 * homework com.cocos.develop.libcos.utils
 *
 * @author Amina
 * 29.09.2021
 */
const val DEFAULT_AGE = 0

fun EditText.parsToInt(): Int {
    return if (this.text.toString() == "") DEFAULT_AGE
    else this.text.toString().toInt()
}

fun EditText.parsToString(): String {
    return this.text.toString()
}