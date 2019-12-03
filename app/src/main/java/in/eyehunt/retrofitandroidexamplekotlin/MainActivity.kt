package `in`.eyehunt.retrofitandroidexamplekotlin

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    var tv_user: TextView? = null
    var str:String = ""

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.recipe_list_view)

        getCocktails()
    }

    fun getCocktails() {

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create(Api::class.java)

        var call = api.cocktails

        call.enqueue(object : Callback<CocktailsList> {

            override fun onResponse(call: Call<CocktailsList>?, response: Response<CocktailsList>?) {
                var usres = response?.body()
                var cocktail = usres?.cocktails
                var length = cocktail!!.size


                val listItems = arrayOfNulls<String>(length)

                for (i in 0 until length) {
                    str = str + "\n" + cocktail.get(i).strDrink + " " + cocktail.get(i).strDrinkThumb

                    val recipe = cocktail.get(i)
                    listItems[i] = recipe.strDrink

                }
                val adapter = ArrayAdapter(this@MainActivity,
                    android.R.layout.simple_list_item_1, listItems)
                listView.adapter = adapter

            }

            override fun onFailure(call: Call<CocktailsList>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
    }
}
