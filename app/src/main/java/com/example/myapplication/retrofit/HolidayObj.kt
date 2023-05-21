package com.example.retrofit

import java.util.Vector

data class Holildays(
    val rates: HolidayObj
)

data class HolidayObj(
    val day: Int,
    val month: Int,
    val nameday: Map<String, String>
)

data class HolidayContry(
    var yesterday: String,
    val today: String,
    val tomorrow: String
)