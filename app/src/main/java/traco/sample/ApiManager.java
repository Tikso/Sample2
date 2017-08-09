package traco.sample;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tikson.tom on 5/29/2017.
 */

public class ApiManager {
    public static final String BASE_URL = "https://ceapspan.slpuat.com/";
    private static Retrofit retrofit = null;
    static Api api;
    private static final boolean WEBSERVICE_LOG = true;


    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (WEBSERVICE_LOG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient OKHttpclient = new OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS)
                    .writeTimeout(80, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(OKHttpclient)
                    .build();
        }
        return retrofit;
    }
}
