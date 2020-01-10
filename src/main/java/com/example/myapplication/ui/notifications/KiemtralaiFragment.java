package com.example.myapplication.ui.notifications;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.AppData;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.Learn;
import com.example.myapplication.model.Learned;
import com.example.myapplication.untils.ImageLoadTash;
import com.example.myapplication.untils.MediaTask;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KiemtralaiFragment extends Fragment {

    private KiemtralaiViewModel mViewModel;
    TextView txt_word;
    TextView txt_vietnamese;
    ImageView img;
    int currentLearn =0;
    int second =60;
    Button btn_like;
    Button btn_timeup;
    NavController navController;
    TextView t;
    int count;
    ImageView img_like;
    Runnable countDown;
    Handler handler = new Handler();
    int sport;
    int dem =0;
    View root;
    List<Learned> learneds;
    Learned learned;




    List<Learn> learns;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.kiemtralai_fragment, container, false);
        txt_word = (TextView) root.findViewById(R.id.txt_word);
        txt_vietnamese = (TextView) root.findViewById(R.id.txt_vn);
        img =(ImageView)root.findViewById(R.id.img);
        t = (TextView) root.findViewById(R.id.time);
        btn_like =(Button) root.findViewById(R.id.btn_media);
        btn_timeup=(Button)root.findViewById(R.id.btn_timeup);
        img_like =(ImageView)root.findViewById(R.id.img_like);
        ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
        api.getLearned(AppData.currentUser.id).enqueue(new Callback<List<Learned>>() {
            @Override
            public void onResponse(Call<List<Learned>> call, final Response<List<Learned>> response) {
                learneds = response.body();
                final int sport = learneds.size();
                    // int sport = learneds.size();
                        learned = learneds.get(count);
                        txt_vietnamese.setText(learned.getVietnamese());
                        txt_word.setText(learned.getWord());
                        String url = "http://192.168.0.100:8080/File?file="+ learned.getPicture().replaceAll("\\s","");
                        new ImageLoadTash(url,img).execute();

                        clickMedia();
                        btn_timeup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                count++;
                                setData();
                                if(count==sport-1)
                                {
                                    btn_timeup.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("dem",1);
                                            navController = Navigation.findNavController(root);
                                            navController.navigate(R.id.resultFragment,bundle);
                                        }
                                    });
                                }
                            }
                        });

          }

            @Override
            public void onFailure(Call<List<Learned>> call, Throwable t) {

            }
        });

        return root;
    }
    void setData(){
        learned = learneds.get(count);
        txt_vietnamese.setText(learned.getVietnamese());
        txt_word.setText(learned.getWord());
        String url = "http://172.19.200.214:8080/File?file="+ learned.getPicture().replaceAll("\\s","");
        new ImageLoadTash(url,img).execute();

    }
    void clickMedia(){
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    learned = learneds.get(count);
                    setMedia();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void setMedia() throws IOException {
        MediaTask mediaTask = new MediaTask();
        mediaTask.createMedia(this.getContext(),learned.getWord(), learned.getWord().length());

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KiemtralaiViewModel.class);
        // TODO: Use the ViewModel
    }

}
