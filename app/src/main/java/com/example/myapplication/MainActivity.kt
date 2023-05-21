package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.DBHolidayEntity
import com.example.myapplication.db.RoomRepository
import com.example.myapplication.db.initRoomRepo
import com.example.myapplication.retrofit.Repository
import com.example.retrofit.HolidayContry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    val imageIdList = listOf(
        R.drawable.flag1,
        R.drawable.flag2,
        R.drawable.flag3,
        R.drawable.flag4,
        R.drawable.flag5,
        R.drawable.flag6,
        R.drawable.flag7
    )

    val contryList = listOf(
        "Russia",
        "USA",
        "Germany",
        "Estonia",
        "Italy",
        "Framce",
        "Croatia"
    )

    val contryKeys = listOf(
        "ru",
        "us",
        "de",
        "ee",
        "it",
        "fr",
        "hr"
    )

    val mapCoutryes = mutableMapOf<String, HolidayContry>()

    var listItem = R.layout.list_item

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    var adapter = HolidayAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MAIN = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        init()

        initRoomRepo(applicationContext)
        val roomRepo = RoomRepository()
        val repo = Repository()

        var allData = roomRepo.getCountryHolidays()
        if (allData.isNotEmpty() && allData[0].date == LocalDate.now().toString())
        {
            for (item in allData)
            {
                var country = HolidayContry(item.yesterday, item.today, item.tomorrow)
                mapCoutryes[item.country] = country
            }
            return
        }

        for (item in allData)
        {
            var holiday = DBHolidayEntity(item.date, item.country, item.yesterday, item.today, item.yesterday)
            roomRepo.deleteHoliday(holiday)
        }

        CoroutineScope(Dispatchers.IO).launch {
            var holidayYesterday = repo.getHolidayYesterday()
            var holidayToday = repo.getHolidayToday()
            var holidayTomorrow = repo.getHolidayTomorrow()

            for (item in contryKeys) {
                var yesterday = holidayYesterday.nameday[item].toString()
                var today = holidayToday.nameday[item].toString()
                var tomorrow = holidayTomorrow.nameday[item].toString()

                var country = HolidayContry(yesterday, today, tomorrow)

                mapCoutryes[item] = country

                roomRepo.writeCountryHoliday(item, yesterday, today, tomorrow)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun init()
    {
        for (i in 0 until imageIdList.size) {
            adapter.addHoliday(Holiday(imageIdList[i], contryList[i]))
        }
    }
}