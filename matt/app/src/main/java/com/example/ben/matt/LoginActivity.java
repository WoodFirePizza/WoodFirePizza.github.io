package com.example.ben.matt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;

public class LoginActivity extends AppCompatActivity {
    public Button but1;
    public static Boolean loggedIn = false;
    public static long startTime = 0;

    public static void setLoggedIn(Boolean aloggedIn) {
        loggedIn = aloggedIn;
        startTime = System.currentTimeMillis();
    }

    public static String getLoginTime() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        return ("Session time: " + (String.format("%d:%02d", minutes, seconds)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        navigate();
    }

    public void navigate(){
        but1 = (Button)findViewById(R.id.btnLogin);
        but1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyLogin()) {
                    loggedIn = true;
                    Intent toy = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(toy);
                }
            }
        });
    }

    public Boolean verifyLogin() {
        final EditText username, password;
        String u, p;
        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);

        u = username.getText().toString();
        p = password.getText().toString();

        if ((u.equals(TestData.testUsername)) &&(p.equals(TestData.testPassword))) {
            setLoggedIn(true);
            return true;
        }
        else if((u.equals("") || p.equals(""))) {
            Toast.makeText(this, "Please enter a value into Username or Password.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sorry the Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
