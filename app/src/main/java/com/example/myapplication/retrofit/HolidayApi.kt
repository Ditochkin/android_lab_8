package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HolidayApi {
//    @GET("{code}")
//    suspend fun getHoliday(@Path("code") code: String): HolidayAPI
    @GET("/api/V1/yesterday")
    suspend fun getHolidayYesterday(): HolidayObj

    @GET("/api/V1/today")
    suspend fun getHolidayToday(): HolidayObj

    @GET("/api/V1/tomorrow")
    suspend fun getHolidayTomorrow(): HolidayObj

    companion object InstanceHolidayApi {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://nameday.abalin.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: HolidayApi by lazy {
            retrofit.create(HolidayApi::class.java)
        }
    }
}