package example.emre.com.haberlerexample.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News>newsArrayList;
    private Activity activity;

    public NewsAdapter(Activity activity,ArrayList<News> newsArrayList){
        this.activity=activity;
        this.newsArrayList=newsArrayList;
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            text=itemView.findViewById(R.id.newsText);

        }
    }
}
