package id.kampung.moviekatalog.View;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import id.kampung.moviekatalog.View.MainMovie.MovieModel;
import id.kampung.moviekatalog.R;

public class DetailMovie extends AppCompatActivity {

    ImageView imageViewCover;
    ImageView imageViewPoster;
    TextView textViewDeskripsi;
    TextView textViewRilis;
    TextView textViewRating;
    TextView textViewJudul;
    MovieModel movieModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        imageViewCover = findViewById(R.id.image_cover);
        imageViewPoster = findViewById(R.id.image_poster);
        textViewDeskripsi = findViewById(R.id.text_deskripsi);
        textViewJudul = findViewById(R.id.text_judul);
        textViewRating = findViewById(R.id.text_rating);
        textViewRilis = findViewById(R.id.text_rilis);

        Gson gson = new Gson();
        String data = getIntent().getStringExtra("data");
        movieModel = gson.fromJson(data,MovieModel.class);

        setUI();
    }

    @SuppressLint("SetTextI18n")
    private void setUI() {
        String BASE_PATH = "http://image.tmdb.org/t/p/w500/";
        Glide.with(this).load(BASE_PATH +movieModel.getUrlGambarSampul()).into(imageViewCover);
        Glide.with(this).load(BASE_PATH +movieModel.getUrlGambar()).into(imageViewPoster);

        textViewJudul.setText(movieModel.getTitle());
        textViewRilis.setText(movieModel.getRilis());
        textViewRating.setText("Rating :" + movieModel.getRating());
        textViewDeskripsi.setText(movieModel.getDeskripsi());
    }
}
