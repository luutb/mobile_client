package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.ApiInterface;
import com.example.myapplication.api.ApiUntil;
import com.example.myapplication.model.ApiRseponse;
import com.example.myapplication.model.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
TextInputEditText txt_acc;
TextInputEditText txt_pass;
Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_acc = (TextInputEditText)findViewById(R.id.txt_acc);
        txt_pass =(TextInputEditText)findViewById(R.id.txt_pass);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);
                User acc = new User();
                acc.setUsername(txt_acc.getText().toString());
                acc.setPassword(txt_pass.getText().toString());
                api.login(acc).enqueue(new Callback<ApiRseponse<Boolean>>() {
                    @Override
                    public void onResponse(Call<ApiRseponse<Boolean>> call, Response<ApiRseponse<Boolean>> response) {
                        if(response.body()!=null && response.body().data!=null && response.body().data==true)
                        {
                            Intent intent = new Intent(LoginActivity.this, InterfaceActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login thanh cong", Toast.LENGTH_LONG).show();


                        }
                        else
                            Toast.makeText(LoginActivity.this, "Login khong", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<ApiRseponse<Boolean>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Login thanh cong vloz", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}
