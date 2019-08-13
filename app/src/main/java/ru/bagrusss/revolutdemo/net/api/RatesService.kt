package ru.bagrusss.revolutdemo.net.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bagrusss.revolutdemo.net.models.RatesResponse

/**
 * Created by bagrusss on 12.08.2019
 */
interface RatesService {

    @GET("latest")
    fun getRates(@Query("base") base: String = "EUR"): Single<RatesResponse>

}
