package com.org.profburo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.org.profburo.R;
import com.org.profburo.entities.User;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.login.LoginBody;
import com.org.profburo.network.responsesEntities.login.LoginResponse;
import com.org.profburo.others.PermissionCode;
import com.org.profburo.others.SelectDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class LoginForm extends AppCompatActivity {

    private SelectDialog dialog;
    private Button loginButton;
    private TextView regButton;
    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        authorisedUser.setPermission(PermissionCode.WORKER);

        login = (EditText) findViewById(R.id.loginText);
        password = (EditText) findViewById(R.id.passwordText);

        initClickable();

    }

    private void initClickable()
    {

        loginButton = (Button) findViewById(R.id.loginButton);
        regButton = (TextView) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new SelectDialog(LoginForm.this);
                dialog.show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().length() == 0 || password.getText().length() == 0)
                {
                    Toast.makeText(LoginForm.this, "Введите данные в пустые поля", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login();
                }
            }
        });
    }

    private void login()
    {
        String loginVar = login.getText().toString();
        String passwordVar = password.getText().toString();

        RestApi.getInstance()
                .getApi()
                .loginUser(new LoginBody(loginVar, passwordVar))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getStatus().equals("succeed"))
                            {
                                authorisedUser = new User(response.body());
                                Intent toLogPage = new Intent(LoginForm.this, MainPage.class);
                                startActivity(toLogPage);
                            }
                            else
                            {
                                Toast.makeText(LoginForm.this, "Такого пользователя в системе не найдено", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
    }


}