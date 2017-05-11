package com.example.ben.matt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.ben.matt.R.id.Titre;

public class MainActivity extends AppCompatActivity {

    private Button but1;
    private TextView timerTextView;
    TextView Titre;


    //runs without a timer reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerTextView = (TextView) findViewById(R.id.txtSession);
            timerTextView.setText(LoginActivity.getLoginTime());
            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeUser();
        goToMaps();
        goToOrders();
        goToLogout();
        goToChangeTransport();
        goToUserSettings();
        timerHandler.postDelayed(timerRunnable, 0);
        Titre = (TextView) findViewById(R.id.Titre);

        new getData().execute();
    }

    public void goToMaps() {
        but1 = (Button) findViewById(R.id.btnOpenMaps);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(toy);
            }
        });
    }

    public void goToOrders() {
        but1 = (Button) findViewById(R.id.btnViewOrders);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, OrdersActivity.class);
                startActivity(toy);
            }
        });
    }

    public void goToUserSettings() {
        but1 = (Button) findViewById(R.id.btnUserSettings);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, UserActivity.class);
                startActivity(toy);
            }
        });
    }

    public void goToChangeTransport() {
        but1 = (Button) findViewById(R.id.btnChangeTransport);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, ChangeTransportActivity.class);
                startActivity(toy);
            }
        });
    }

    public void goToLogout() {
        but1 = (Button) findViewById(R.id.btnLogout);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, LoginActivity.class);
                LoginActivity.setLoggedIn(false);
                timerHandler.removeCallbacks(timerRunnable);
                startActivity(toy);
            }
        });
    }

    public void storeUser() {
        TextView s = (TextView) findViewById(R.id.txtDisplayUser);
        s.setText("Welcome back, " + TestData.testUsername + ".");
    }


    class getData extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection = null;

        @Override
        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251O/api/person");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.setDoOutput(true);

                urlConnection.connect();

                BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

                char[] buffer = new char[1024];

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line+"\n");
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
        protected void onPostExecute(String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String t = jsonObject.getString("0.Person_ID");

                Titre.setText(t);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

