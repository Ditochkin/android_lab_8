package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["country"])
class DBHolidayEntity (
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "yesterday") var yesterday: String,
    @ColumnInfo(name = "today") var today: String,
    @ColumnInfo(name = "tomorrow") var tomorrow: String,
)