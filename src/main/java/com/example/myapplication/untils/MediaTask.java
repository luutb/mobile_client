package com.example.myapplication.untils;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class MediaTask {
    ExecutorService service ;
    public void MediaTask() throws Exception {
        service = Executors.newFixedThreadPool(5);

    }
    public File downFile(Context context, String url){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Request request = new Request.Builder().url(url).get()
                .build();
        try {
            InputStream stream = client.newCall(request).execute().body().byteStream();
            File cache = context.getCacheDir();
            File tempFile = new File(cache,"temp.wav");

            FileOutputStream fs = new FileOutputStream(tempFile);
            byte[]data= new byte[1024*1024];
            while(stream.read(data)>-1){
                fs.write(data);
            }
            fs.flush();
            fs.close();
            stream.close();


            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public void createMedia( Context context,  String text, int lent) throws IOException {

         String url ="https://translate.google.com/translate_tts?ie=UTF-8&total=1&idx=0&textlen="+lent+"&client=tw-ob&q="+ URLEncoder.encode(text,"UTF-8") +"&tl=en";

      try{
          if (service == null){
              service = Executors.newFixedThreadPool(5);
          }
          service.execute( new ExecutePlayAudio(url,context));
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public class ExecutePlayAudio implements  Runnable{
        String url ;
        Context context;
        public ExecutePlayAudio(String url,Context context){
            this.url = url;
            this.context = context;
        }

        @Override
        public void run() {
            File file = downFile(context, url);
            MediaPlayer mPlayer = new MediaPlayer();
            // mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource(file.getAbsolutePath());
                mPlayer.prepare(); // might take long! (for buffering, etc)
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
