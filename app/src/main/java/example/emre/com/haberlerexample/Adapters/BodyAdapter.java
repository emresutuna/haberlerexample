//package example.emre.com.haberlerexample.Adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Objects;
//
//import example.emre.com.haberlerexample.DetailActivity;
//import example.emre.com.haberlerexample.Models.Body;
//import example.emre.com.haberlerexample.Models.News;
//import example.emre.com.haberlerexample.Models.NewsModel;
//import example.emre.com.haberlerexample.R;
//
//public class BodyAdapter extends RecyclerView.Adapter<BodyAdapter.ViewHolder> {
//    private ArrayList<News> newsArrayList;
//    private  ArrayList<Body>bodyArrayList;
//    private Context context;
//    private NewsModel newsModel;
//
//    public BodyAdapter(Context context,ArrayList<News> newsArrayList,ArrayList<Body> bodyArrayList){
//        this.context=context;
//        this.newsArrayList=newsArrayList;
//        this.bodyArrayList=bodyArrayList;
//
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.detail_body_items,parent,false);
//        return new ViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        //Body body=bodyArrayList.get(position);
//        Body body1 = new Body();
//        Gson gson=new Gson();
//        TextView textViewH3 = null;
//        TextView textViewP = null;
//        ImageView imageView =null;
//       // Log.d("Body",new Gson().toJson(body));
//       // Log.d("dadada",body.getClass().getCanonicalName().getClass().getSimpleName());
//        String classType;
//        JSONArray jArray = null;
//        try {
//            jArray = new JSONArray(new Gson().toJson(bodyArrayList));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject object = jArray.optJSONObject(position);
//         Log.d("OBJECT2", String.valueOf(object.names()));
//
//        if(String.valueOf(object.names()).equals("[\"p\"]")){
//            Log.d("dsadasdasdsa","tRUEEEEE");
//            textViewP = new TextView(context);
//            textViewP.setText(bodyArrayList.get(position).getP());
//        }
//
////        if(body.getH3()!=null){
////            if(body.getH3()==bodyArrayList.get(position).getH3()){
////                textViewH3 = new TextView(context);
////                textViewH3.setTextSize(22);
////                textViewH3.setText(body.getH3());
////            }
////        }
////        for (int i = 0; i < body.getP().length(); i++) {
////            if(body.getH3()!=null){
////                for (int j = 0; j <body.getH3().length() ; j++) {
////
////                }
////            }
////            if(body.getP()!=null){
////                for (int j = 0; j <body.getP().length() ; j++) {
////                    textViewP = new TextView(context);
////                    textViewP.setText(body.getP());
////                }
////            }
////
////            if(body.getImage()!=null){
////                for (int j = 0; j <body.getImage().length() ; j++) {
////                    imageView= new ImageView(context);
////                    imageView.setMaxHeight(200);
////                    imageView.setMaxWidth(200);
////                    Picasso.with(context).load(body.getImage())
////                            .centerCrop().fit()
////                            .into(imageView);
////                }
////            }
////
////
////
////
////
////        }
//        if(textViewH3!=null){
//            holder.linearLayout.addView(textViewH3);
//        }
//        if(textViewP!=null){
//            holder.linearLayout.addView(textViewP);
//
//        }
//        if(imageView!=null){
//            holder.linearLayout.addView(imageView);
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return bodyArrayList.size();
//    }
//
//    public  class ViewHolder extends RecyclerView.ViewHolder {
//        LinearLayout linearLayout;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            linearLayout=itemView.findViewById(R.id.bodyLinear);
//
//        }
//    }
//    public static class ODeserialiser implements JsonSerializer<Object> {
//
//        @Override
//        public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
//            Gson gson = new Gson();
//            JsonElement serialize = gson.toJsonTree(src);
//            JsonObject o = (JsonObject) serialize;
//            o.addProperty("class", src.getClass().getName());
//            return serialize;
//        }
//    }
//
//    public static class X {
//        public String test = "asd";
//    }
//}
//
