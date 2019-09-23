package ru.bagrusss.revolutdemo.mappers

/**
 * Created by bagrusss on 23.09.2019
 */
interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}