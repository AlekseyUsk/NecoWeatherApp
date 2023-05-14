package com.bignerdranch.android.necoweatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bignerdranch.android.necoweatherapp.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }

    private fun onClick() {
        binding.buttonGetWeather.setOnClickListener {
            getResult("London")
        }
    }

    private fun getResult(name: String){
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$MY_API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                    response->
                val obj = JSONObject(response) //полученный string-овый список обьекта в логах передаем в Json обьект
                val temp = obj.getJSONObject("current") //достаю обьект тот что интересует меня "current"
                Log.d("MyLog","Response: ${temp.getString("temp_c")}")
                Log.d("MyLog", "Response: ${response}")
            },
            {
                Log.d("MyLog","Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}