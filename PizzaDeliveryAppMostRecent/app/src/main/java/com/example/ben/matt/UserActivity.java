package com.example.ben.matt;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity  {
    private Button but1;
    private String oldU;
    private String newU1;
    private String newU2;
    private String oldP;
    private String newP1;
    private String newP2;
    TestData test = new TestData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        goHome();

        but1 = (Button)findViewById(R.id.btnChangeUsername);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("u");
            }
        });

        but1 = (Button)findViewById(R.id.btnChangePassword);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("p");
            }
        });

    }

    private void updateField(){
        TextView txtV = (TextView) findViewById(R.id.txtLogin);
        txtV.setText("Username: " + test.getUsername() + "\nPassword: "+test.getPassword());
    }

    public void goHome(){
        but1 = (Button)findViewById(R.id.btnHome);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(UserActivity.this,MainActivity.class);
                startActivity(toy);
            }
        });
    }

    private void showDialog(String userInput) {
        String tog;
        LayoutInflater inflater = LayoutInflater.from(this);
        final View v = inflater.inflate(R.layout.activity_custom_dialog, null);
        final AlertDialog cdd = new AlertDialog.Builder(this).create();

        if (userInput.equals("p")) {
            tog = "password";
            TextView tTitle = (TextView) v.findViewById(R.id.lblTitle);
            tTitle.setText(tTitle.getText() + tog);
            TextView oOld = (TextView) v.findViewById(R.id.lblOld);
            oOld.setText(oOld.getText() + tog);
            TextView nNew = (TextView) v.findViewById(R.id.lblNew);
            nNew.setText(nNew.getText() + tog);

            but1 = (Button) v.findViewById(R.id.btn_Save);
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vi) {
                    EditText x = (EditText) v.findViewById(R.id.txtOld);
                    oldP = x.getText().toString();
                    EditText y = (EditText) v.findViewById(R.id.txtChk1);
                    newP1 = y.getText().toString();
                    EditText z = (EditText) v.findViewById(R.id.txtChk2);
                    newP2 = z.getText().toString();

                    if(oldP != "") {
                        verifyPassword();
                        updateField();
                        cdd.dismiss();
                    } else {
                        Toast.makeText(getBaseContext(), "oldP is empty.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else if (userInput.equals("u")) {
            tog = "username";
            TextView tTitle = (TextView) v.findViewById(R.id.lblTitle);
            tTitle.setText(tTitle.getText() + tog);
            TextView oOld = (TextView) v.findViewById(R.id.lblOld);
            oOld.setText(oOld.getText() + tog);
            TextView nNew = (TextView) v.findViewById(R.id.lblNew);
            nNew.setText(nNew.getText() + tog);

            but1 = (Button) v.findViewById(R.id.btn_Save);
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vi) {
                    EditText x = (EditText) v.findViewById(R.id.txtOld);
                    oldU = x.getText().toString();
                    EditText y = (EditText) v.findViewById(R.id.txtChk1);
                    newU1 = y.getText().toString();
                    EditText z = (EditText) v.findViewById(R.id.txtChk2);
                    newU2 = z.getText().toString();

                    if(oldU != "") {
                        verifyUsername();
                        updateField();
                        cdd.dismiss();
                    } else {
                        Toast.makeText(getBaseContext(), "oldU is empty.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        but1 = (Button) v.findViewById(R.id.btnCancel);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                cdd.dismiss();
            }
        });
        cdd.setView(v);
        cdd.show();
    }

    private void verifyUsername(){
        if (oldU.equals(TestData.testUsername)){
            if (newU1.equals(newU2)) {
                test.setUsername(newU1);
            }
        } else {
            Toast.makeText(getBaseContext(),"Sorry that doesn't match your existing username. Please try again.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getBaseContext(),"Your username has been updated!", Toast.LENGTH_SHORT).show();
    }

    private void verifyPassword(){
        if (oldP.equals(TestData.testPassword)){
            if (newP1.equals(newP2)) {
                test.setPassword(newP1);
            }
        } else {
            Toast.makeText(getBaseContext(),"Sorry that doesn't match your existing password. Please try again.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getBaseContext(),"Your password has been updated!", Toast.LENGTH_SHORT).show();
    }
}
