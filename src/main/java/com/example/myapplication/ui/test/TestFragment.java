package com.example.myapplication.ui.test;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AppData;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.Learn;
import com.example.myapplication.model.postData;
import com.example.myapplication.untils.ImageLoadTash;
import com.example.myapplication.untils.MediaTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    RecyclerView recyclerView;
    int currentLearn = 0;
    EditText txt_word;
    TextView txt_vn;
    ImageView img;
    Button btn_loa;
    Button btn_next;
    NavController navController;
    int second = 59;
    TextView t;
    int dem = 0;
    List<Learn> learns;
    int click=0;
    Learn learn;

    final ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
    final Handler handler = new Handler();
    Runnable countDown;
    View root;
    int sport;
    public static TestFragment newInstance() {
        return new TestFragment();
    }

    public void setTimeOut() {
        String a = second + "";
        t.setText(a);
        second--;

        if (second < 0) {
            second = 59;
            currentLearn++;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.test_fragment, container, false);
        txt_word = (EditText) root.findViewById(R.id.txt_word);
        txt_vn = (TextView) root.findViewById(R.id.txt_vn);
        img = (ImageView) root.findViewById(R.id.img);
        btn_loa = (Button) root.findViewById(R.id.btn_media);
        btn_next = (Button) root.findViewById(R.id.btn_xacnhan);
        t = (TextView) root.findViewById(R.id.time);
        int id = this.getArguments().getInt("id");
        String list = this.getArguments().getString("list");
        learns = new Gson().fromJson(list, new TypeToken<ArrayList<Learn>>() {}.getType());
        sport = learns.size();
        setData();
        clickMedia();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLearn++;
                if(txt_word.getText().toString().equals(learn.getWord().replaceAll("\\s","")))
                {
                    dem++;
                    postLearned();
                    setData();
                    txt_word.setText("");

                }
                else {
                    setData();
                    txt_word.setText("");
                }

                if(currentLearn==sport-1)
                {
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(txt_word.getText().toString().equals(learn.getWord().replaceAll("\\s","")))
                            {
                                dem++;
                                postLearned();
                                setData();
                                txt_word.setText("");

                            }
                            else {
                                setData();
                                txt_word.setText("");
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("dem", dem);
                            navController = Navigation.findNavController(root);
                            navController.navigate(R.id.resultFragment,bundle);
                        }
                    });
                }
            }
        });
        return root;
    }


    void setData(){
        learn = learns.get(currentLearn);
        txt_vn.setText(learn.getVietnamese());
        String url = "http://192.168.0.100:8080/File?file=" + learn.getPicture().replaceAll("\\s", "");
        new ImageLoadTash(url, img).execute();
    }
    void postLearned(){
        postData post = new postData();
        post.setIdword(learn.getIdword());
        post.setIduser(AppData.currentUser.id);
        api.postData(post).enqueue(new Callback<postData>() {
            @Override
            public void onResponse(Call<postData> call, Response<postData> response) {

            }

            @Override
            public void onFailure(Call<postData> call, Throwable t) {

            }
        });
    }
    void clickMedia(){
        btn_loa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    learn = learns.get(currentLearn);
                    setMedia();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void setMedia() throws IOException {
        MediaTask mediaTask = new MediaTask();
        mediaTask.createMedia(this.getContext(),learn.getWord(), learn.getWord().length());

    }

}

