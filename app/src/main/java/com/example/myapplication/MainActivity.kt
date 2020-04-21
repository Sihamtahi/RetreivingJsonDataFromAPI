package com.example.myapplication
import android.app.Person
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
//    lateinit var progress:ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Sonthing ","aqli dagui")
        listView_details = findViewById<ListView>(R.id.listView) as ListView
        run("https://corona-watch-api.herokuapp.com/corona-watch-api/v1/geolocation/towns/")
    }

    fun run(url: String) {

        Log.d("Sonthing ","aqli deg run")
        //getting the response from our URL API
        val request = Request.Builder()
            .url(url)
            .header("Authorization","Basic YWRtaW46YWRtaW4=")
            .build()
       //use the onFaiture and ionResponse to treat our request
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
               // progress.visibility = View.GONE
                Log.d("Sonthing ","aqli deg on faiture")
            }

            override fun onResponse(call: Call, response: Response)
            {
                Log.d("Sonthing ","aqli deg on response")
                //get the response as a sting
                var str_response = response.body()!!.string()

               //convert the jsonString into a list of calsse with GSON
                val gson = Gson()
                val listPersonType = object : TypeToken<List<Town>>() {}.type
                var persons: List<Town> = gson.fromJson(str_response, listPersonType)
                persons.forEachIndexed {
                        idx,
                        person -> Log.i("Sonthing", "> Item $idx:\n${person.name} ${person.location.latitude} ${person.location.longitude}")
                }

                arrayList_details= ArrayList()

                runOnUiThread {
                    //stuff that updates ui
                }

            }
        })
    }
}
