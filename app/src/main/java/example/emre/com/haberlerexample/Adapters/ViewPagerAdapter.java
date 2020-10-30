package example.emre.com.haberlerexample.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

import example.emre.com.haberlerexample.Models.News;
import example.emre.com.haberlerexample.R;

public class ViewPagerAdapter extends PagerAdapter    {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<News> news;
    private Activity activity;
    boolean flag = false;
    SimpleExoPlayer simpleExoPlayer;
    PlayerView videoView;
    MediaSource mediaSource;


    public ViewPagerAdapter(Context context, ArrayList<News> news,Activity activity){
        this.context=context;
        this.news=news;
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView textViewH3 = null;
        TextView textViewP = null;
        ImageView imageView =null;
        LinearLayout linearLayout;
        TextView tvSpot;
        TextView tvTitle;
        ProgressBar progressVideo;
        ImageView fullScreenButton;
        MediaController mediaController;


        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.detail_item,null);
        linearLayout=view.findViewById(R.id.linearDetay);
        videoView=view.findViewById(R.id.video);
        fullScreenButton=view.findViewById(R.id.fullScreen);
        tvTitle=view.findViewById(R.id.texTitle);
        progressVideo=view.findViewById(R.id.progressVideo);
        tvSpot=view.findViewById(R.id.textSpot);
        tvTitle.setText(news.get(position).getTitle());
        tvSpot.setText(news.get(position).getSpot());
        mediaController=new MediaController(context);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Uri uri =  Uri.parse(news.get(position).getVideoURL());

        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(
                new AdaptiveTrackSelection.Factory(bandwidthMeter)
        );
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(context,trackSelector,loadControl);

        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory(
                "exoplayer_video"
        );
        String userAgent = Util.getUserAgent(context, "myapp");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        mediaSource= new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri);

        videoView.setPlayer(simpleExoPlayer);
        videoView.setKeepScreenOn(true);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.getPlaybackState();
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if(playbackState==Player.STATE_BUFFERING){
                    Log.d("StateBuffering",String.valueOf(playbackState));
                    progressVideo.setVisibility(View.VISIBLE);
                }else if(playbackState==Player.STATE_READY){
                    progressVideo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
        //simpleExoPlayer.setPlayWhenReady(true);
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                   // fullScreenButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.exo_controls_fullscreen_enter));
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag=false;
                }else{
                    fullScreenButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    linearLayout.setVisibility(View.GONE);
                    flag=true;
                }
            }
        });

        try {
            JSONArray jArray = new JSONArray(new Gson().toJson( news.get(position).getBody()));
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.optJSONObject(i);
                Iterator<String> iterator = object.keys();
                while(iterator.hasNext()) {
                    String currentKey = iterator.next();
                    if (currentKey.equals("h3")){
                        textViewH3 = new TextView(context);
                        textViewH3.setTextSize(22);
                        textViewH3.setText(news.get(position).getBody().get(i).getH3());
                        if(textViewH3!=null){
                            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0,20,0,20);
                            textViewH3.setLineSpacing(1,1);
                            textViewH3.setLayoutParams(params);
                            textViewH3.setTextColor(ContextCompat.getColor(context,R.color.black));
                            linearLayout.addView(textViewH3);

                        }
                    }
                    if(currentKey.equals("image")){
                        imageView=new ImageView(context);
                        Picasso.with(context).load(news.get(position).getBody().get(i).getImage())
                                .fit()
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(imageView);
                        if(imageView!=null){
                            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
                            params.setMargins(0,20,0,20);
                            imageView.setLayoutParams(params);
                            linearLayout.addView(imageView);

                        }
                    }
                    if(currentKey.equals("p")){
                        textViewP = new TextView(context);
                        textViewP.setText(news.get(position).getBody().get(i).getP());
                        if(textViewP!=null){
                            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0,20,0,20);
                            textViewP.setLineSpacing(1,1);
                            textViewP.setTextColor(ContextCompat.getColor(context,R.color.black2));
                            textViewP.setLayoutParams(params);
                            linearLayout.addView(textViewP);
                        }
                    }
                }

            }
        }catch (Exception e){

        }

        //pausePlayer();
        ViewPager viewPager =(ViewPager)container;
        viewPager.addView(view,0);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager)container;
        View view=(View)object;

        pausePlayer();
        viewPager.removeView(view);
    }
    public void startPlayer() {
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }

    public void pausePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.stop();
            simpleExoPlayer.seekTo(0);
        }
    }



}

