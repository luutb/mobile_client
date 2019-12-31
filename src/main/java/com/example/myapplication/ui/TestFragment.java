package com.example.myapplication.ui;

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

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    Button btn_lv1;
    Button btn_lv2;
    Button btn_lv3;
    NavController navController;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final String list = this.getArguments().getString("learn");
        final View root = inflater.inflate(R.layout.test_fragment, container, false);
        btn_lv1 =(Button)root.findViewById(R.id.btn_lv1);
        btn_lv2 =(Button)root.findViewById(R.id.btn_lv2);
        btn_lv3 =(Button)root.findViewById(R.id.btn_lv3);
        final Bundle bundle = new Bundle();
        btn_lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id","1");
                bundle.putString("list",list);
                navController = Navigation.findNavController(root);
                navController.navigate(R.id.navigation_notifications,bundle);


            }
        });
        btn_lv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id","2");
                bundle.putString("list",list);
                navController = Navigation.findNavController(root);
                navController.navigate(R.id.navigation_dashboard,bundle);


            }
        });
        btn_lv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id","3");
                bundle.putString("list",list);
                navController = Navigation.findNavController(root);
                navController.navigate(R.id.navigation_dashboard,bundle);


            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        // TODO: Use the ViewModel
    }

}
