package id.kampung.moviekatalog.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.kampung.moviekatalog.View.MainMovie.MovieModel;
import id.kampung.moviekatalog.R;
import id.kampung.moviekatalog.View.DetailMovie;

public class MovieAdapter extends BaseAdapter {

    private List<MovieModel> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private final String BASE_PATH = "http://image.tmdb.org/t/p/w185/";

    public MovieAdapter( Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<MovieModel> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieModel item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getCount() {
        if (mData == null) return  0;
        return  mData.size();
    }

    @Override
    public MovieModel getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_movie,null);
            holder.textViewNama = convertView.findViewById(R.id.text_nama);
            holder.textViewDeskripsi = convertView.findViewById(R.id.text_deskripsi);
            holder.textViewRilis = convertView.findViewById(R.id.text_rilis);
            holder.imageViewCover = convertView.findViewById(R.id.imageView);
            holder.layout = convertView.findViewById(R.id.layout);
            convertView.setTag(holder);

        }else {
            holder =(ViewHolder) convertView.getTag();
        }
        String deskripsi;
        if(mData.get(position).getDeskripsi().length() <=30){
            deskripsi = mData.get(position).getDeskripsi();
        }else {
            deskripsi = mData.get(position).getDeskripsi().substring(0,30 )+ " ....";
        }
        holder.textViewNama.setText(mData.get(position).getTitle());
        holder.textViewDeskripsi.setText(deskripsi);
        holder.textViewRilis.setText(mData.get(position).getRilis());

        Glide.with(context).load(BASE_PATH+mData.get(position).getUrlGambar()).into(holder.imageViewCover);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovie.class);
                Gson gson = new Gson();
                String data = gson.toJson(mData.get(position));
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView textViewNama;
        TextView textViewDeskripsi;
        TextView textViewRilis;
        ImageView imageViewCover;
        ConstraintLayout layout;
    }
}

