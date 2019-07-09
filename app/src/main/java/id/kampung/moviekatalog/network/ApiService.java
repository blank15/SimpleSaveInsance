package id.kampung.moviekatalog.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private final String BaseURL = "http://api.themoviedb.org/3/";
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    private static class ApiServiceHolder {
        private static ApiService INSTANCE = new ApiService();
    }
    public static ApiService getInstance() {
        return ApiServiceHolder.INSTANCE;
    }

    private <E> E createService(Class<E> serviceClass) {

        return retrofit.create(serviceClass);
    }

    public DataService dataService(){
        return createService(DataService.class);
    }

}
