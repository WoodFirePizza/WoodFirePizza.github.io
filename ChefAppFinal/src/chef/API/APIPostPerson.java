/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

import chef.datamodel.Person;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 *
 * @author rjones14
 */
public class APIPostPerson {
    private HttpURLConnection connection;
    private String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/person";
    private URL obj;

    private String username = "marc";
    private String password = "123";

    private String authorization;

    private Gson jsonFormatter;
    
    private Person item;
    
    public APIPostPerson(Person person){
        item = person;
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        POST(person);
    }
    
    private void POST(Person person) {
        //create Person object
        Person personObj = person;
        jsonFormatter = new Gson();
        //pass Person object to json formatter
        String jsonObj = jsonFormatter.toJson(personObj);
        
        System.out.println(jsonObj);

        //System.out.println(msg);  //test - json object structure
        /*
        String jsonObj = "{ "
                + "\"Person_ID\":\"12312312\","
                + "\"Forename\":\"JAVA\","
                + "\"Surname\":\"JAVAaa\","
                + "\"DOB\":\"26-APR-96\","
                + "\"Email\":\"java@java.j\","
                + "\"Mobile_Number\":\"12345566\","
                + "\"Town_City\":\"Java City\", "
                + "\"County\":\"Devon\","
                + "\"Postcode\":\"J4 V40\","
                + "\"Address2\":\"Address2details\","
                + "\"Address1\":\"Address1details\""
                + "}";
         */
        //String jsonObj = "{ \"Person_ID\":\"12312312\", \"Forename\":\"JAVA\", \"Surname\":\"JAVAaa\", \"DOB\":\"26-APR-96\", \"Email\":\"java@java.j\", \"Mobile_Number\":\"12345566\", \"Town_City\":\"Java City\", \"County\":\"Devon\", \"Postcode\":\"J4 V40\", \"Address2\":\"Address2details\", \"Address1\":\"Address1details\"}";
        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", String.valueOf(jsonObj.length()));
            connection.setDoOutput(true);

            // OutputStreamWriter writer;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            //writer = new OutputStreamWriter(connection.getOutputStream(s));
            writer.write(jsonObj);
            writer.flush();
            writer.close();

            System.out.println("Sending \"POST\" request to \"" + url + "\"");
            System.out.println("Response Code: " + connection.getResponseCode());
            
            //System.out.println(connection.getRequestProperty("Location"));

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
        connection.disconnect();
    }
        
}
