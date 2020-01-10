package com.example.myapplication.ui.timeup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;

public class TimeUpFragment extends Fragment {

    private TimeUpViewModel mViewModel;
    Button btn_click1;
    Button btn_click2;
    Button btn_click3;
    NavController navController;

    public static TimeUpFragment newInstance() {
        return new TimeUpFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.time_up_fragment, container, false);
        btn_click1 =(Button)root.findViewById(R.id.btn_lv1);
        btn_click2 =(Button)root.findViewById(R.id.btn_lv2);


        final String list = this.getArguments().getString("learn");
        btn_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",1);
                bundle.putString("list",list);
                navController = Navigation.findNavController(root);
                navController.navigate(R.id.testFragment,bundle);


            }
        });
        btn_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",2);
                bundle.putString("list",list);
                navController = Navigation.findNavController(root);
                navController.navigate(R.id.lv2Fragment,bundle);


            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TimeUpViewModel.class);
        // TODO: Use the ViewModel
    }

}
