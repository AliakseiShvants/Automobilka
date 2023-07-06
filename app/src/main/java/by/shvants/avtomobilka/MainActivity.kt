package by.shvants.avtomobilka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import by.shvants.avtomobilka.repository.CarRepository
import by.shvants.avtomobilka.utils.Result
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val carRepository: CarRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val res = carRepository.fetchCarsFromServer()

            when(res) {
                is Result.Success -> {
                    res.value.forEach {
                        Log.d("MainActivity", it.toString())
                    }
                }
                is Result.Error -> Log.d("MainActivity", res.exception.message.orEmpty())
            }
        }
    }
}