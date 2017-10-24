package com.omar.lopez.femco;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class YoutubeActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener{
    static  final String youtube_api_key="AIzaSyAOmOErfJDyZIm7wJUoytc8OUOqen5i_L0";
    static  final String youtube_video_id="JPXbOZiASyo";
    static  final String youtube_play_list="PLuEZQoW9bRnT5LUI1rQFwu9ujQVIVQTF3";


    private static final String TAG = "YoutubeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_youtube);


       /* btnyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(YoutubeActivity.this, youtube_api_key, youtube_video_id,0,true,false);
                startActivity(intent);
            }
        });*/
        ConstraintLayout layout= (ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_youtube,null);
        setContentView(layout);


        //creamos un control en la vista desde codigo
        YouTubePlayerView player=new YouTubePlayerView(this);
        player.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.MarginLayoutParams.MATCH_PARENT));
        layout.addView(player);
        player.initialize(youtube_api_key,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        Log.d(TAG, "onInitializationFailure: el provider que me a llegado es: "+provider.getClass().toString());
        Toast.makeText(this, " video inicializado con exito desde youtube", Toast.LENGTH_SHORT).show();
       youTubePlayer.setPlaybackEventListener(playbackeventlistener);//llama el metodo para detectar si se dio pausa, play, etc.
        youTubePlayer.setPlayerStateChangeListener(playerstatechanges);

        if (!b){
            youTubePlayer.cueVideo(youtube_video_id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int request_code=1;
        if (youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,request_code).show();
        }
        else {
            String errormessage=String.format("Ha bido un error al iniciar el reproductor(%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this, errormessage, Toast.LENGTH_LONG).show();
        }

    }
    YouTubePlayer.PlaybackEventListener playbackeventlistener=new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "el video se  esta reproduciendo", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "el video se pauso", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "el video se a parado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {
            Toast.makeText(YoutubeActivity.this, "el usuario se a movido asta el segundo"+i, Toast.LENGTH_SHORT).show();
        }
    };

    YouTubePlayer.PlayerStateChangeListener playerstatechanges=new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "se invoca cuando comiensa la reproduccion de un anuncio o publicidad", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Toast.makeText(YoutubeActivity.this, "se invoca cuando se a producido un error"+errorReason , Toast.LENGTH_SHORT).show();
        }
    };
}
