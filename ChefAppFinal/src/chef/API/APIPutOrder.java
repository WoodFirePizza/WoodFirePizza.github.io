/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

import chef.datamodel.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rjones14
 */
public class APIPutOrder {

    HttpURLConnection connection;
    String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/order";
    URL obj;

    String username = "PRCS";
    String password = "251O";

    String authorization;

    Gson jsonFormatter;

    public APIPutOrder(Order orderToChange) {
        byte[] encoded = (username + ":" + password).getBytes();
        authorization = "Basic " + Base64.getEncoder().encodeToString(encoded);
        PUT(changeOrder(GET(orderToChange), orderToChange));
    }

    private String GET(Order orderToGet) {
        try {
            String orderID = String.valueOf(orderToGet.getID());
            String getUrl = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/order/" + orderID;

            obj = new URL(getUrl);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");

            System.out.println("Sending \"GET\" request to \"" + url + "\"");
            System.out.println("Response Code: " + connection.getResponseCode());

        } catch (MalformedURLException me) {
            System.out.println(me.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            return getJsonString();
        } catch (IOException ex) {
        }
        connection.disconnect();

        return null;
    }

    private APIOrderObject changeOrder(String jsonString, Order orderToChange) {
        JsonParser parser = new JsonParser();
        JsonObject jObject = parser.parse(jsonString).getAsJsonObject();

        APIOrderObject order = new APIOrderObject();
        order.setOrder_ID(jObject.get("Order_ID").getAsString());
        if (!jObject.get("Driver_ID").isJsonNull()) {
            order.setDriver_ID(jObject.get("Driver_ID").getAsString());
        } else {
            order.setDriver_ID(null);
        }
        if (!jObject.get("Member_ID").isJsonNull()) {
            order.setMember_ID(jObject.get("Member_ID").getAsString());
        } else {
            order.setMember_ID(null);
        }
        if (!jObject.get("Total_Price").isJsonNull()) {
            order.setTotal_Price(jObject.get("Total_Price").getAsString());
        } else {
            order.setTotal_Price(null);
        }
        if (!jObject.get("DELIVERYADDRESSID").isJsonNull()) {
            order.setDELIVERYADDRESSID(jObject.get("DELIVERYADDRESSID").getAsString());
        } else {
            order.setDELIVERYADDRESSID(null);
        }
        order.setTEMP_DELIVERYADDRESSID(jObject.get("TEMP_DELIVERYADDRESSID").getAsString());
        order.setISPAID(jObject.get("ISPAID").getAsString());
        order.setDate(jObject.get("Date").getAsString());
        order.setTime(jObject.get("Time").getAsString());
        if (!jObject.get("IsDelivery").isJsonNull()) {
            order.setIsDelivery(jObject.get("IsDelivery").getAsString());
        } else {
            order.setIsDelivery(null);
        }
        order.setStatus(orderToChange.getOrderStatus().toString());

        //System.out.print(jsonFormatter.toJson(order));
        return order;
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

    private void PUT(APIOrderObject orderToChange) {
        String orderID = String.valueOf(orderToChange.getOrder_ID());

        url += "/" + orderID;
        System.out.println("" + url);
        //create Person object
        APIOrderObject orderObj = orderToChange;
        jsonFormatter = new Gson();
        //pass Person object to json formatter
        String jsonObj = jsonFormatter.toJson(orderObj);
        System.out.println(jsonObj);

        //String jsonObj = "{ \"Person_ID\":\"12312312\", \"Forename\":\"JAVA\", \"Surname\":\"JAVAaa\", \"DOB\":\"26-APR-96\", \"Email\":\"java@java.j\", \"Mobile_Number\":\"12345566\", \"Town_City\":\"Java City\", \"County\":\"Devon\", \"Postcode\":\"J4 V40\", \"Address2\":\"Address2details\", \"Address1\":\"Address1details\"}";
        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Length", String.valueOf(jsonObj.length()));
            connection.setDoOutput(true);

            // OutputStreamWriter writer;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            //writer = new OutputStreamWriter(connection.getOutputStream());
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
