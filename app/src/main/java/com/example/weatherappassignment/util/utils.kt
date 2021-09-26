package com.example.weatherappassignment.util

fun Long?.notNullLong(): Long {
    return this ?: 0
}

fun Int?.notNullInt(): Int {
    return this ?: 0
}

fun Double?.notNullDouble(): Double {
    return this ?: 0.0
}