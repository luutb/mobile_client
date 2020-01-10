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
import com.example.myapplication.model.Logsign;
import com.example.myapplication.model.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogsignActivity extends AppCompatActivity {
    TextInputEditText txt_acc;
    TextInputEditText txt_pass;
    Button btn_click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logsign);
        txt_acc = (TextInputEditText)findViewById(R.id.txt_acc);
        txt_pass =(TextInputEditText)findViewById(R.id.txt_pass);
        btn_click = (Button) findViewById(R.id.btn_click);
        final ApiInterface api = ApiUntil.getClient().create(ApiInterface.class);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Logsign logsign = new Logsign();
                final User acc = new User();
                acc.setUsername(txt_acc.getText().toString());
                acc.setPassword(txt_pass.getText().toString());
                logsign.setUser(txt_acc.getText().toString());

                api.logsign(logsign).enqueue(new Callback<ApiRseponse<Boolean>>() {
                    @Override
                    public void onResponse(Call<ApiRseponse<Boolean>> call, Response<ApiRseponse<Boolean>> response) {
                        if(response.body()!=null && response.body().data!=null && response.body().data==true)
                        {
                            Toast.makeText(LogsignActivity.this, "Tai Khoan da ton tai", Toast.LENGTH_LONG).show();
                        }
                        if(response.body()!=null && response.body().data!=null && response.body().data==false)
                        {

                            Toast.makeText(LogsignActivity.this, "That bao roi con trai", Toast.LENGTH_LONG).show();


                            api.getAcc(acc).enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {


                                }


                                @Override
                                public void onFailure(Call<User> call, Throwable t) {

                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(LogsignActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            Toast.makeText(LogsignActivity.this, "Dang ky thanh cong", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiRseponse<Boolean>> call, Throwable t) {

                    }
                });

            }
        });


    }
}
