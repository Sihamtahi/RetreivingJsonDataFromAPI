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
       // progress = findViewById(R.id.progressBar)
      //  progress.visibility = View.VISIBLE
        Log.d("Sonthing ","aqli dagui")
        listView_details = findViewById<ListView>(R.id.listView) as ListView
        run("https://corona-watch-api.herokuapp.com/corona-watch-api/v1/geolocation/towns/")
    }

    fun run(url: String) {
       // progress.visibility = View.VISIBLE
        Log.d("Sonthing ","aqli deg run")
        val request = Request.Builder()
            .url(url)
            .header("Authorization","Basic YWRtaW46YWRtaW4=")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
               // progress.visibility = View.GONE
                Log.d("Sonthing ","aqli deg on faiture")
            }

            override fun onResponse(call: Call, response: Response)
            {
                Log.d("Sonthing ","aqli deg on response")
                var str_response = response.body()!!.string()

                //Log.d("Sonthing ","j'ai récupére : "+str_response)
                //creating json object
            // val json_contact = JSONObject(str_response)
                val gson = Gson()
                val listPersonType = object : TypeToken<List<Town>>() {}.type
                var persons: List<Town> = gson.fromJson(str_response, listPersonType)
                persons.forEachIndexed {
                        idx,
                        person -> Log.i("Sonthing", "> Item $idx:\n${person.name} ${person.location.latitude} ${person.location.longitude}")
                }
                //creating json array
            /*  var jsonarray_info:JSONArray= json_contact.getJSONArray("states")*/
               // var i:Int = 0
               // var size:Int = json_contact.length()
                arrayList_details= ArrayList()
               /* for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=json_contact.getJSONObject(i)
                    var model:Model= Model();
                    model.id=json_objectdetail.getString("id")
                    model.name=json_objectdetail.getString("name")
                    model.email=json_objectdetail.getString("postal_code")

                    arrayList_details.add(model)
                }*/

                runOnUiThread {
                    //stuff that updates ui
                  //  val obj_adapter : CustomAdapter
                  //  obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                   // listView_details.adapter=obj_adapter
                }
                //progress.visibility = View.GONE
            }
        })
    }
}
