package com.example.ben.matt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeTransportActivity extends AppCompatActivity {
    public Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_transport);
        goHome();
    }

    public void goHome(){
        but1 = (Button)findViewById(R.id.btnHome);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(ChangeTransportActivity.this,MainActivity.class);
                startActivity(toy);
            }
        });
    }
}
