package ir.Hs.coronastatus.interfaces;

import java.util.List;

import ir.Hs.coronastatus.Views.models.Country;
import retrofit2.Call;
import retrofit2.http.GET;
public interface APIInterface {
    @GET("countries")
    Call<List<Country>> getCountries();
}
