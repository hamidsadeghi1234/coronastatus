package ir.Hs.coronastatus.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    //  public static final String BASE_URL="http://192.168.43.37/api/retrofit/"; //mobile p9lite IP
    public static final String BASE_URL="https://coronavirus-19-api.herokuapp.com/";   //Modem D-lunk IP
    private  static Retrofit retrofit=null;

    public static Retrofit getClient(){
        retrofit=new  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}