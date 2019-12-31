package com.example.myapplication.ui.exam;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.Learn;
import com.example.myapplication.untils.ImageLoadTash;
import com.example.myapplication.untils.MediaTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamFragment extends Fragment {

    private ExamViewModel mViewModel;
    TextView txt_word;
    TextView txt_vietnamese;
    ImageView img;
    int i =0;
    int second =60;
    Button btn_like;

    NavController navController;
    TextView t;

    public static ExamFragment newInstance() {
        return new ExamFragment();
    }

    public  void setTimeOut()
    {
        String a =  second+"";
        t.setText(a);
            second--;

            if(second<0)
            {
                second=59;
               i++;
            }
        }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.exam_fragment, container, false);
        txt_word = (TextView) root.findViewById(R.id.txt_word);
        txt_vietnamese = (TextView) root.findViewById(R.id.txt_vn);
        img =(ImageView)root.findViewById(R.id.img);
        t = (TextView) root.findViewById(R.id.time);
        btn_like =(Button) root.findViewById(R.id.btn_media);
        ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
        int id = this.getArguments().getInt("id");
        api.getLearn(id).enqueue(new Callback<List<Learn>>() {
            @Override
            public void onResponse(Call<List<Learn>> call, Response<List<Learn>> response) {
                final List<Learn> learns = response.body();
                final int sport = learns.size();
                final Bundle bundle = new Bundle();
                bundle.putString("learn", new Gson().toJson(learns) );
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Learn learn = learns.get(i);

                        String url = "http://192.168.1.8:8080/File?file="+ learn.getPicture().replaceAll("\\s","");
                        new ImageLoadTash(url,img).execute();
                        txt_word.setText(learn.getWord());                        txt_vietnamese.setText(learn.getVietnamese());
                        btn_like.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
//                                    createMedia(learn.getWord(),learn.getWord().length());
                                    MediaTask mediaTask =  new MediaTask();
                                    mediaTask.createMedia(learn.getWord(), learn.getWord().length());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        setTimeOut();
                        if(i>=sport)
                        {
                            navController = Navigation.findNavController(root);
                            navController.navigate(R.id.timeUpFragment,bundle);
                        }
                        else {
                            handler.postDelayed(this, 1000);
                        }
                    }
                },0);

            }

            @Override
            public void onFailure(Call<List<Learn>> call, Throwable t) {
            }
        });
        return root;
    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(ExamViewModel.class);
//        // TODO: Use the ViewModel
//    }

}
