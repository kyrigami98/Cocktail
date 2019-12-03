package `in`.eyehunt.retrofitandroidexamplekotlin

import com.google.gson.annotations.SerializedName

class CocktailsList {
    @SerializedName("drinks")
    var cocktails: List<Cocktails>? = null
}