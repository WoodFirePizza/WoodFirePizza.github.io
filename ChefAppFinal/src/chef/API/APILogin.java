/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import com.google.gson.*;

/**
 *
 * @author rjones14
 */
public class APILogin {

    HttpURLConnection connection;
    String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin";
    String command;
    URL obj;

    String username;
    String password;

    String authorization;

    Gson jsonFormatter;

    public APILogin(String username, String password) {
        this.command = "get";
        this.username = username;
        this.password = password;
    }

    public int getResponseCode() {
        int responseCode = 0;

        //encode username:password to Base64
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        //System.out.println("" + authorization);
        
        if(command == "get"){
            responseCode = GET();
        }
        
        return responseCode;
    }

    private int GET() {

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");

            return connection.getResponseCode();
            
        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }   
        return 0;
    }
}
