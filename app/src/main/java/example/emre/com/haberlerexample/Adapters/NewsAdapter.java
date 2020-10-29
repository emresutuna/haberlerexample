package example.emre.com.haberlerexample.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import example.emre.com.haberlerexample.DetailActivity;
import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.Models.NewsModel;
import example.emre.com.haberlerexample.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News>newsArrayList;
    private Activity activity;
    private NewsModel newsModel;

    public NewsAdapter(Activity activity,ArrayList<News> newsArrayList,NewsModel newsModel){
        this.activity=activity;
        this.newsArrayList=newsArrayList;
        this.newsModel=newsModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsrow,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news=newsArrayList.get(position);

        Picasso.with(activity.getApplicationContext()).load(news.getImageURL())
                .into(holder.image);

        holder.text.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        Intent intent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            text=itemView.findViewById(R.id.newsText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("newsArray",newsArrayList);
                    String listSerialized= new Gson().toJson(newsModel);
                    intent=new Intent(activity, DetailActivity.class);
                    intent.putExtra("newsArray",listSerialized);
                    intent.putExtra("position",getAdapterPosition());
                    activity.startActivity(intent);
                    Log.d("Clicked","Clicked");
                }
            });

        }
    }
}
