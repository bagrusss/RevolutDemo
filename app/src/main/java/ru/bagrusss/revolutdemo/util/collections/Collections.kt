package ru.bagrusss.revolutdemo.util.collections

/**
 * Created by bagrusss on 10.01.2020
 */

fun <T> List<T>.intersectionFromSecond(second: List<T>, predicate: (T, T) -> Boolean): List<T> {
    val intersected = mutableListOf<T>()
    forEach { fromFirstItem ->
        val findItem = second.firstOrNull { fromSecondItem -> predicate(fromFirstItem, fromSecondItem) }
        findItem?.let(intersected::add)
    }
    return intersected
}