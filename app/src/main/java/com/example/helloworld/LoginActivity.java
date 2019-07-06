package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView txtV1, txtV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btnLogin);
        txtV1 = findViewById(R.id.txtUsername);
        txtV2 = findViewById(R.id.txtPassword);
    }

    public void btnLoginClicked(View view)
    {
        String userName = txtV1.getText().toString();
        String passWord = txtV2.getText().toString();

        if (userName.equalsIgnoreCase("admin") && passWord.equals("admin"))
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            txtV1.setText(""); txtV2.setText("");
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Sorry! Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }

}


