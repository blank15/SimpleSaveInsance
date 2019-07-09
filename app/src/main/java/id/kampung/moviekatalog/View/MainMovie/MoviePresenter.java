package id.kampung.moviekatalog.View.MainMovie;

import android.util.Log;

import id.kampung.moviekatalog.Model.BaseResponse;
import id.kampung.moviekatalog.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {

    private  MovieInterface movieInterface;

    public MoviePresenter(MovieInterface movieInterface) {
        this.movieInterface = movieInterface;
    }

    public void getData(String api,String lng,String search){
        ApiService.getInstance()
                .dataService()
                .cariMovie(api,lng,search)
                .enqueue(new Callback<BaseResponse<MovieModel>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<MovieModel>> call, Response<BaseResponse<MovieModel>> response) {

                        Log.d("##",response.errorBody()+"");
                        if(response.isSuccessful()){
                            Log.d("###",response.message());
                            Log.d("#",response.body().getPage()+"");
                            if(response.body().getTotal_results() != 0){
                                movieInterface.cariMovie(response.body().getResults());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<MovieModel>> call, Throwable t) {

                        Log.d("####",t.getMessage());
                    }
                });
    }
}
