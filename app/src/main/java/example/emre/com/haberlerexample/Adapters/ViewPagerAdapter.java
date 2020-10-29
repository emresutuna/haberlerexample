package example.emre.com.haberlerexample.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.R;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<News> news;
    public ViewPagerAdapter(Context context, ArrayList<News> news){
        this.context=context;
        this.news=news;
    }
    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView textViewH3 = null;
        TextView textViewP = null;
        ImageView imageView =null;
        LinearLayout linearLayout;
        TextView tvSpot;
        TextView tvTitle;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.detail_item,null);
        linearLayout=view.findViewById(R.id.linearDetay);
        tvTitle=view.findViewById(R.id.texTitle);
        tvSpot=view.findViewById(R.id.textSpot);
        tvTitle.setText(news.get(position).getTitle());
        tvSpot.setText(news.get(position).getSpot());
        try {
            JSONArray jArray = new JSONArray(new Gson().toJson( news.get(position).getBody()));
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.optJSONObject(i);
                Log.d("OBJECT2", String.valueOf(object.names()));

                Iterator<String> iterator = object.keys();
                while(iterator.hasNext()) {
                    String currentKey = iterator.next();
                    Log.d("Key",currentKey);
                    if (currentKey.equals("h3")){
                        textViewH3 = new TextView(context);
                        textViewH3.setTextSize(22);

                        textViewH3.setText(news.get(position).getBody().get(i).getH3());
                        if(textViewH3!=null){
                            linearLayout.addView(textViewH3);

                        }
                    }
                    if(currentKey.equals("image")){
                        imageView=new ImageView(context);
                        //imageView.setMaxHeight(300);
                       // imageView.setMaxWidth(200);
                        Picasso.with(context).load(news.get(position).getBody().get(i).getImage())
                                .fit()
                                .centerCrop()
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(imageView);
                        if(imageView!=null){
                            linearLayout.addView(imageView);

                        }
                    }
                    if(currentKey.equals("p")){
                        textViewP = new TextView(context);
                        textViewP.setText(news.get(position).getBody().get(i).getP());
                        if(textViewP!=null){
                            linearLayout.addView(textViewP);

                        }
                    }
                }

            }
        }catch (Exception e){

        }

        ViewPager viewPager =(ViewPager)container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager)container;
        View view=(View)object;
        viewPager.removeView(view);
    }
}
