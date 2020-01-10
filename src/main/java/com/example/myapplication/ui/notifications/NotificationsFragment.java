package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AppData;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.Learned;
import com.example.myapplication.model.RecyclerBaseItem;
import com.example.myapplication.ui.dashboard.Myadapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    RecyclerView recyclerView;
    Button button;
    Myadapter myadapter = new Myadapter();
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.layout_rcv1);
        button = (Button)root.findViewById(R.id.btn_exam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController = Navigation.findNavController(root);
                navController.navigate(R.id.kiemtralaiFragment);

            }
        });
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myadapter);
        int iduser =AppData.currentUser.id;
        api.getLearned(iduser).enqueue(new Callback<List<Learned>>() {
            @Override
            public void onResponse(Call<List<Learned>> call, Response<List<Learned>> response) {
                List<RecyclerBaseItem> data = new ArrayList<>();
                List<Learned> learneds = response.body();
                if(learneds==null)
                {
                    return;
                }
                else {
                    for (Learned l : learneds) {
                        data.add(l);
                    }
                    myadapter.setTopic(data);
                    myadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Learned>> call, Throwable t) {

            }
        });

//


        return root;
    }
}