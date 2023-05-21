package com.example.myapplication.retrofit

import com.example.retrofit.HolidayApi
import com.example.retrofit.HolidayObj

class Repository {
    suspend fun getHolidayYesterday(): HolidayObj {
        return HolidayApi.api.getHolidayYesterday()
    }

    suspend fun getHolidayToday(): HolidayObj {
        return HolidayApi.api.getHolidayToday()
    }

    suspend fun getHolidayTomorrow(): HolidayObj {
        return HolidayApi.api.getHolidayTomorrow()
    }
}