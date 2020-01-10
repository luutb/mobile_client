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

import com.example.myapplication.AppData;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.ApiRseponse;
import com.example.myapplication.model.Favourites;
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
    Button btn_timeup;
    NavController navController;
    TextView t;
    ImageView img_like;
    ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
    Learn learn;
    Favourites favourites = new Favourites();

    Handler handler = new Handler();
    Runnable coutDown;

    List<Learn> learns;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.exam_fragment, container, false);
        txt_word = (TextView) root.findViewById(R.id.txt_word);
        txt_vietnamese = (TextView) root.findViewById(R.id.txt_vn);
        img =(ImageView)root.findViewById(R.id.img);
        t = (TextView) root.findViewById(R.id.time);
        btn_like =(Button) root.findViewById(R.id.btn_media);
        btn_timeup=(Button)root.findViewById(R.id.btn_timeup);
        img_like =(ImageView)root.findViewById(R.id.img_like);
        int id = this.getArguments().getInt("id");

        api.getLearn(id).enqueue(new Callback<List<Learn>>() {
            @Override
            public void onResponse(Call<List<Learn>> call, Response<List<Learn>> response) {
                learns = response.body();
                final int sport = learns.size();
                if(sport==0)
                {
                    return;
                }
                final Bundle bundle = new Bundle();
                bundle.putString("learn", new Gson().toJson(learns));
                learn = learns.get(i);
                String url = "http://172.19.200.214:8080/File?file="+ learn.getPicture().replaceAll("\\s","");
                new ImageLoadTash(url,img).execute();
                txt_word.setText(learn.getWord());
                txt_vietnamese.setText(learn.getVietnamese());
                clickMedia();
                setImg_like();
                setLike();
                btn_timeup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i++;
                        setData();
                        if(i==sport-1)
                        {
                            btn_timeup.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    navController = Navigation.findNavController(root);
                                    navController.navigate(R.id.timeUpFragment,bundle);
                                }
                            });
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Learn>> call, Throwable t) {
            }

        });
        return root;
    }

    void setData(){
        learn = learns.get(i);
        String url = "http://172.19.200.214:8080/File?file="+ learn.getPicture().replaceAll("\\s","");
        new ImageLoadTash(url,img).execute();
        txt_word.setText(learn.getWord());
        txt_vietnamese.setText(learn.getVietnamese());

    }
    void clickMedia(){
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    learn = learns.get(i);
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

    void setImg_like() {
        learn = learns.get(i);
        favourites.setIdword(learn.getIdword());
        api.Favourites(favourites).enqueue(new Callback<ApiRseponse<Boolean>>() {
            @Override
            public void onResponse(Call<ApiRseponse<Boolean>> call, final Response<ApiRseponse<Boolean>> response) {
                if (response.body() != null && response.body().data != null && response.body().data == true) {

                    img_like.setImageResource(R.drawable.ic_star_border_black_24dp);
                    img_like.setTag(true);

                }
                if (response.body() != null && response.body().data != null && response.body().data == false) {
                    img_like.setImageResource(R.drawable.ic_star_black_24dp);
                    img_like.setTag(false);
                }

            }

            @Override
            public void onFailure(Call<ApiRseponse<Boolean>> call, Throwable t) {

            }
        });
        setLike();
    }
    void setLike()
    {

        img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(img_like.getTag().equals(true))
                {
                    favourites.setId(favourites.getId());
                    favourites.setIduser(AppData.currentUser.id);
                    api.ddelFavourites(favourites).enqueue(new Callback<Favourites>() {
                        @Override
                        public void onResponse(Call<Favourites> call, Response<Favourites> response) {

                        }

                        @Override
                        public void onFailure(Call<Favourites> call, Throwable t) {

                        }
                    });

                }
                if(img_like.getTag().equals(false)){
                    favourites.setIdword(learn.getIdword());
                    favourites.setIduser(AppData.currentUser.id);
                    api.getFavourites(favourites).enqueue(new Callback<Favourites>() {
                        @Override
                        public void onResponse(Call<Favourites> call, Response<Favourites> response) {

                        }

                        @Override
                        public void onFailure(Call<Favourites> call, Throwable t) {

                        }
                    });
                }
                setImg_like();
            }

        });
    }



}

