package com.example.tiktok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<VideoResponse> mDataset;
    Context mcontext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView nickname;
        public TextView likecount;
        public ImageButton cover;
        public ImageView avatar;
        public MyViewHolder(View v) {
            super(v);
            description = v.findViewById(R.id.description);
            nickname = v.findViewById(R.id.nickname);
            likecount = v.findViewById(R.id.likecount);
            cover = v.findViewById(R.id.cover);
            cover.setScaleType(ImageView.ScaleType.CENTER);
            avatar = v.findViewById(R.id.avatar);
            ViewGroup.LayoutParams params = avatar.getLayoutParams();
            params.width = 200;
            params.height = 200;
            avatar.setLayoutParams(params);
        }
    }


    public void setData(List<VideoResponse> myDataset) {
        mDataset = myDataset;
    }
    public void setContext(Context context) {
        mcontext = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int index) {
        holder.description.setText(mDataset.get(index).description);
        holder.nickname.setText(mDataset.get(index).nickname);
        Glide.with(mcontext)
                .load(mDataset.get(index).feedurl)
                .into(holder.cover);
        Glide.with(mcontext)
                .load(mDataset.get(index).avatar)
                .into(holder.avatar);


        holder.likecount.setText(mDataset.get(index).likecount + " like");
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    Toast.makeText(v.getContext(), "url:" + index, Toast.LENGTH_SHORT).show();
            //    Log.d("URL", mDataset.get(position).feedurl);//视频地址
                String temp = mDataset.get(index).feedurl;
            //    Log.d("URL", temp);//视频地址

                Intent intent = new Intent(mcontext, VideoPlayer.class);//跳转到播放器
                intent.putExtra("vURL", temp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        };
        holder.cover.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

}
