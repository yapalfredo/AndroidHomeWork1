package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    Button loginButton, languageButton;
    TextView txtV1, txtV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_login);

        //----------------------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        //----------------------------------------

        txtV1 = findViewById(R.id.txtUsername);
        txtV2 = findViewById(R.id.txtPassword);

        loginButton = findViewById(R.id.btnLogin);
        languageButton = findViewById(R.id.btnLanguage);

        //Common way of handling click event
        //For the Change Language Button
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageDialog();
            }
        });
    }

    private void languageDialog()
    {
        final String[] languageLists = {"English", "Cebuano", "Hiligaynon", "Tagalog"};
        AlertDialog.Builder newBuilder = new AlertDialog.Builder(LoginActivity.this);
        newBuilder.setTitle("Select Language");
        newBuilder.setSingleChoiceItems(languageLists, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0)
                {
                    setLanguage("en");
                    recreate();
                }
                else if (i == 1)
                {
                    setLanguage("ceb");
                    recreate();
                }
                else if (i == 2)
                {
                    setLanguage("hil");
                    recreate();
                }
                else if (i == 3)
                {
                    setLanguage("tl");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog nDialog = newBuilder.create();
        nDialog.show();
    }

    private void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration conf = new Configuration();
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf,
                getBaseContext().getResources().getDisplayMetrics());

        //save data
        SharedPreferences.Editor edt = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        edt.putString("Language", language);
        edt.apply();
    }

    //Load the data from share preferences
    private void loadLanguage()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("Language","");
        setLanguage(language);
    }

    //Another way of handling click event
    //Login Button
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


