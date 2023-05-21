package com.example.myapplication.db

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

class RoomRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    fun writeCountryHoliday(country : String, yesterday: String, today: String, tomorrow: String) {
        DBInstance.dao.insert(DBHolidayEntity(LocalDate.now().toString(), country, yesterday, today, tomorrow))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCountryHolidays(): List<DBHolidayEntity> {
        val ins = DBInstance.dao.loadFull()
        return ins
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteHoliday(holiday: DBHolidayEntity){
        DBInstance.dao.delete(holiday)
        val ins = DBInstance.dao.loadFull()
    }
}


fun initRoomRepo(ctx: Context) {
    DBInstance = DBInstanceClass(ctx)
}

lateinit var DBInstance: DBInstanceClass

class DBInstanceClass(ctx: Context) {
    private val db = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "listDataBaseTest2"
    ).allowMainThreadQueries().build()

    val dao = db.holidayDao()
}

@Dao
interface HolidayDao {
    @Query("SELECT * FROM DBHolidayEntity")
    fun loadFull(): List<DBHolidayEntity>

    @Insert
    fun insert(DBhol: DBHolidayEntity): Long

    @Delete
    fun delete(holiday: DBHolidayEntity)
}

@Database(entities = [DBHolidayEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holidayDao(): HolidayDao
}
