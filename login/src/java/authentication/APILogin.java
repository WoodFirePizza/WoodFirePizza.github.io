/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author rjones14
 */
public class APILogin {

    HttpURLConnection connection;
    String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin";
    String command = "get";
    URL obj;

    String username = "PRCS";
    String password = "251O";

    String authorization;

    Gson jsonFormatter;

    public APILogin(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("We're IN");
    }

    public String getResponseString() {
        String responseString = "";

        //encode username:password to Base64
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        //System.out.println("" + authorization);

        if (command == "get") {
            responseString = GET();
        }

        return responseString;
    }

    private String GET() {

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonString = response.toString();
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(jsonString).getAsJsonArray();

            String responseString = "";

            for (JsonElement jElement : jArray) {
                JsonObject jObject = jElement.getAsJsonObject();
                if (username.equals(jObject.get("Username").getAsString()) && password.equals(jObject.get("Password").getAsString())) {
                    responseString = jObject.get("Person_ID").getAsString();
                }
            }

            return responseString;

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    
        return responseString;

    }
}
