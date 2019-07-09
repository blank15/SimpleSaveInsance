package id.kampung.moviekatalog.View.MainMovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import id.kampung.moviekatalog.Adapter.MovieAdapter;
import id.kampung.moviekatalog.R;

public class MainActivity extends AppCompatActivity implements MovieInterface, View.OnClickListener {


    ListView listViewMovie;
    EditText editTextMovie;
    Button buttonCari;
    ArrayList<MovieModel> movieModels = new ArrayList<>();
    private  final String API_KEY ="d81172160acd9daaf6e477f2b306e423";
    private  final String LNG = "en-US";
    private final String DATA = "data";
    MovieAdapter adapter;
    MoviePresenter presenter;
    String query="data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMovie = findViewById(R.id.edit_movie);
        buttonCari = findViewById(R.id.button_cari);
        listViewMovie = findViewById(R.id.list_movie);

        adapter = new MovieAdapter(this);
        listViewMovie.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        presenter = new MoviePresenter(this);
        buttonCari.setOnClickListener(this);


    }

    @Override
    public void cariMovie(ArrayList<MovieModel> modelList) {
        movieModels = modelList;
        setMovie(modelList);
    }

    private void setMovie(ArrayList<MovieModel> modelList) {

        adapter.setData(modelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_cari:
                cari();
                break;
        }
    }

    private void cari() {
         query= editTextMovie.getText().toString();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(query)){
            isEmptyFields = true;
            editTextMovie.setError("Field ini tidak boleh kosong");
        }

        if(!isEmptyFields){
            presenter.getData(API_KEY,LNG,query);
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList("data",movieModels);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        movieModels = savedInstanceState.getParcelableArrayList("data");
        setMovie(movieModels);
        super.onRestoreInstanceState(savedInstanceState);

    }
}
