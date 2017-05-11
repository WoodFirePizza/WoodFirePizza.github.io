/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;

/**
 *
 * @author mgsadumiano
 */
public class APIConnection {

    private HttpURLConnection connection;
    private String url;
    private String command;
    private URL obj;

    private String username = "PRCS";
    private String password = "251O";

    private String authorization;

    private Gson jsonFormatter;

    /**
     * @param args the command line arguments
     */
    public APIConnection(String url, String command) {
        this.url = url;
        this.command = command;
    }
    
    public void changeURL(String url){
        this.url = url;
    }

    public String getResponseString() {
        String responseString = "";
        jsonFormatter = new Gson();
        //pass Person object to json formatter

        //encode username:password to Base64
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        //System.out.println("" + authorization);

        switch (command) {
            case "get":
                responseString = GET();
                break;
            case "delete":
                DELETE("12312312"); //hardcoded Person_ID
                break;
        }
        

        return responseString;
    }

    private String GET() {

        String returnString = "";

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            returnString = getJsonString();

        } catch (IOException ex) {
            Logger.getLogger(APIConnection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        connection.disconnect();

        return returnString;
    }

    private void DELETE(String ID) {

        try {
            obj = new URL(url + "/" + ID);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("DELETE");

            System.out.println("Sending \"DELETE\" request to \"" + url + ID + "\"");
            System.out.println("Response Code: " + connection.getResponseCode());

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            getJsonString();

        } catch (IOException ex) {
            Logger.getLogger(APIConnection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        connection.disconnect();

    }

    private String getJsonString() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuffer response = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String jsonString = response.toString();

        return jsonString;
    }
}
