package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter = new Adapter();
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.layout_rcv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        api.getTopic().enqueue(new Callback<List<Topic>>() {

            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                List<Topic> topics = response.body();
                int soprt = topics.size();
                adapter.setTopic(topics);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                System.out.println(t);
            }
        });

        return root;
    }
}