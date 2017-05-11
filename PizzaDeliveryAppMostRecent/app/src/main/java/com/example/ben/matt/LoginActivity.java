package com.example.ben.matt;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public Button but1;
    public static Boolean loggedIn = false;
    public static long startTime = 0;
    private int responseCode;
    public String typedUsername, typedPassword, authorization;


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
        EditText username = (EditText) findViewById(R.id.txtOld);
        ;
        username.requestFocus();
    }

    public void navigate() {
        but1 = (Button) findViewById(R.id.btnLogin);
        but1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.txtOld);
                EditText password = (EditText) findViewById(R.id.txtPassword);

                typedUsername = username.getText().toString().trim();
                typedPassword = password.getText().toString().trim();
                new getData().execute();
            }
        });
    }

    public Boolean login() {
        String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin";
        final EditText password, username;
        username = (EditText) findViewById(R.id.txtOld);
        password = (EditText) findViewById(R.id.txtPassword);

        typedUsername = username.getText().toString();
        typedPassword = password.getText().toString();

        if (responseCode == 200) {
            setLoggedIn(true);
            return true;
        } else if ((typedUsername.equals("") || typedPassword.equals(""))) {
            Toast.makeText(this, "Please enter a value into Username or Password.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry the Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    class getData extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection = null;

        @Override
        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();

            byte[] credentials = (typedUsername + ":" + typedPassword).getBytes();
            authorization = "Basic " + Base64.encodeToString(credentials, Base64.DEFAULT);

            try {
                URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251O/api/userlogin");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestProperty("Authorization", authorization);

                urlConnection.connect();

                Log.d("Status of code:", urlConnection.getResponseCode() + "");
                responseCode = urlConnection.getResponseCode();
                Log.d("Status of code:", urlConnection.getResponseMessage());

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                char[] buffer = new char[1024];

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line + "\n");
                }
                br.close();

                String jsonString = result.toString();

                System.out.println("JSON: " + jsonString);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result){
            if(login()) {
                loggedIn = true;
                Intent toy = new Intent(LoginActivity.this, MainActivity.class);
                toy.putExtra("auth", authorization);
                startActivity(toy);
            }
        }

    }
}