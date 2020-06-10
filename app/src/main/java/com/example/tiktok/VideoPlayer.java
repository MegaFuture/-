package com.example.tiktok;

import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayer extends AppCompatActivity {
    private VideoView videoView;
    private RelativeLayout relativeLayout;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view);
        String vURL = getIntent().getStringExtra("vURL");
        String videoURL = vURL;
        Uri uri = Uri.parse( videoURL );
        videoView = this.findViewById(R.id.video_view );
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);

        relativeLayout= findViewById(R.id.relative_layout);
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if( videoView.isPlaying() )    videoView.pause();
                    else videoView.start();
                return false;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        String result;
        if(intent!=null){
            result = intent.getStringExtra("vURL");
            setIntent(intent);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        if(intent!=null){
            String result = intent.getStringExtra("vURL");
        }
    }
}