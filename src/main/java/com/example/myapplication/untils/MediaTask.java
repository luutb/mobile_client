package com.example.myapplication.untils;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaTask {
    public void MediaTask()
    {

    }
    public void createMedia(String text, int lent) throws IOException {
        String url ="https://translate.google.com/translate_tts?ie=UTF-8&total=1&idx=0&textlen="+lent+"&client=tw-ob&q="+text+"&tl=en";
        MediaPlayer mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setDataSource(url);
        mPlayer.prepare(); // might take long! (for buffering, etc)
        mPlayer.start();
    }
}
