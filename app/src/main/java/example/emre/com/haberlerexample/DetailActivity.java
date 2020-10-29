package example.emre.com.haberlerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import example.emre.com.haberlerexample.Adapters.NewsAdapter;
import example.emre.com.haberlerexample.Adapters.ViewPagerAdapter;
import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.Models.NewsModel;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class DetailActivity extends AppCompatActivity {
    ViewPager viewPager;
    SpringDotsIndicator dotsIndicator;
    ViewPagerAdapter viewPagerAdapter;
    ArrayList<News>news ;
    NewsModel newsModel;
    RecyclerView recyclerView;
    Context context;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        Bundle bundleObject=getIntent().getExtras();
        viewPager=findViewById(R.id.viewPager);
        backButton=(ImageView)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        Intent intent = getIntent();
        String listSerializedToJson = getIntent().getExtras().getString("newsArray");
        int currentposition=getIntent().getExtras().getInt("position");
        Log.d("Currentpost",String.valueOf(currentposition));
        newsModel=new Gson().fromJson(listSerializedToJson,  NewsModel.class);
        Log.d("News",newsModel.getNews().get(0).getImageURL());
        //dotsIndicator=findViewById(R.id.tabDots);
        ScrollingPagerIndicator indicator = findViewById(R.id.indicator);
        viewPagerAdapter= new ViewPagerAdapter(this,newsModel.getNews());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(currentposition);
        indicator.attachToPager(viewPager);
        //dotsIndicator.setViewPager(viewPager);
    }
}