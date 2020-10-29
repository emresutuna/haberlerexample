package example.emre.com.haberlerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;

import example.emre.com.haberlerexample.Adapters.NewsAdapter;
import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.Models.NewsModel;
import example.emre.com.haberlerexample.Service.ApiClient;
import example.emre.com.haberlerexample.Service.GetNews;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    ArrayList<News> news=new ArrayList<>();
    NewsAdapter adapter;
    GetNews service;
    Context context;
    int page = 1, limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nestedScrollView=findViewById(R.id.scrollView);
        recyclerView=findViewById(R.id.newsListView);
        progressBar=findViewById(R.id.progress_bar);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight() -v.getMeasuredHeight()){
                    limit++;
                }
            }
        });
        service= ApiClient.getClient().create(GetNews.class);
        Call<NewsModel> call=service.getNews();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    news=response.body().getNews();
                    Log.d("Image",response.body().getNews().get(0).getImageURL());
                    Log.d("Response",new Gson().toJson(response.body()));
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    adapter=new NewsAdapter(MainActivity.this,news,response.body());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }
    private  void getData(int page,int limit){

    }
}