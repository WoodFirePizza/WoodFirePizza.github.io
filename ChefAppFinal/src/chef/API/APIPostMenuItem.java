/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

import chef.datamodel.Dessert;
import chef.datamodel.MenuItem;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
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
public class APIPostMenuItem {
 
    private HttpURLConnection connection;
    private String url;
    private URL obj;

    private String username = "marc";
    private String password = "123";

    private String authorization;

    private Gson jsonFormatter;
    private MenuItem item;
    
    public APIPostMenuItem(MenuItem item){
        this.item = item;
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        POSTMenuItem(item);
    }
    
    private void POSTMenuItem(MenuItem item) {
        //create Person object
        url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/Menu";
        APIMenuItem itemObj = new APIMenuItem();
        
        itemObj.setMenu_ID("Dummy");
        itemObj.setDescription(item.getDescription());
        itemObj.setName(item.getName());
        itemObj.setPrice(item.getPrice());
        jsonFormatter = new Gson();
        //pass Person object to json formatter
        String jsonObj = jsonFormatter.toJson(itemObj);
        
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

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        connection.disconnect();
        
        POSTFoodItem(item);
    }
    
    private String getMenuID(){
        APIConnection apiConnection = new APIConnection("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/Menu", "get");
        
        String jsonString = apiConnection.getResponseString();
        
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();
        
        APIMenuItem apiMenuItem = new APIMenuItem();
        
        for(JsonElement jElement: jArray){
            JsonObject jObject = jElement.getAsJsonObject();
            
            if(item.getName().equals(jObject.get("Name").getAsString())){
                apiMenuItem = new Gson().fromJson(jObject, APIMenuItem.class);
            }
        }
        
        return apiMenuItem.getMenu_ID();
    }
    
    private void POSTFoodItem(MenuItem item){
        url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/food";
        APIFoodItem itemObj = new APIFoodItem();
        
        itemObj.setFood_ID("dummy");
        itemObj.setMenu_ID(getMenuID());
        itemObj.setIsGluttenFree("n");
        itemObj.setIsSpicy("n");
        itemObj.setIsVegeterian("n");
        
        try{
            Pizza pizza = (Pizza) item;
            itemObj.setType(pizza.getMenuType().toString());
        }catch(Exception e){}
        try{
            Side side = (Side) item;
            itemObj.setType(side.getMenuType().toString());
        }catch(Exception e){}
        try{
            Dessert dessert = (Dessert) item;
            itemObj.setType(dessert.getMenuType().toString());
        }catch(Exception e){}
        
        jsonFormatter = new Gson();
        //pass Person object to json formatter
        String jsonObj = jsonFormatter.toJson(itemObj);
        
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

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        connection.disconnect();
    }
}
