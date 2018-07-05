package com.example.eugene.rxtestproject

import android.support.annotation.NonNull
import android.util.Log


fun log(message: String, key: String = "TestMessage") {
    Log.d(key, message)
}

fun log(@NonNull modelClass: Any, message: String) {
    Log.d(modelClass::class.java.simpleName, message)
}

inline fun <reified T> toArray(list: List<*>): Array<T> {
    return (list as List<T>).toTypedArray()
}