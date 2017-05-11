package com.example.ben.matt;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.google.gson.*;

/**
 * Created by Ben on 10/05/2017.
 */

public class APILogin {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    HttpURLConnection connection;
    String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin";
    String command;
    URL obj;

    String username = "PRCS";
    String password = "251O";

    String authorization;

    Gson jsonFormatter;

    public APILogin(String url, String command, String username, String password) {
        this.url = url;
        this.command = command;
        this.username = username;
        this.password = password;
    }

    public int getResponseCode() {
        int responseCode = 0;

        //encode username:password to Base64
        byte[] encoded = (username + ":" + password).getBytes();
        //authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        authorization = "Basic " + Base64.encodeToString(encoded, Base64.DEFAULT);
        if(command == "get"){
            responseCode = GET();
        }

        return responseCode;
    }
    String getA() { return authorization; }

    private int GET() {

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Authorization", authorization); //authorization

            return connection.getResponseCode();
        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return 0;
    }
}
