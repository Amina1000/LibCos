package com.cocos.develop.libcos.domain

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultState<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success sing in"
            is Error -> "Error[exception=$exception]"
        }
    }
}