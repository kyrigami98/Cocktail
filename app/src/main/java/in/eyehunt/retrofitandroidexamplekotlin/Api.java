package in.eyehunt.retrofitandroidexamplekotlin;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("filter.php?c=Cocktail")
    Call<CocktailsList> getCocktails();
}
