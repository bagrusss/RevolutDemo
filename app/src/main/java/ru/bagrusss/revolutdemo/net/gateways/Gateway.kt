package ru.bagrusss.revolutdemo.net.gateways

import ru.bagrusss.revolutdemo.net.api.ApiService

/**
 * Created by bagrusss on 13.08.2019
 */
abstract class Gateway<S: ApiService>(@JvmField protected val servive: S)