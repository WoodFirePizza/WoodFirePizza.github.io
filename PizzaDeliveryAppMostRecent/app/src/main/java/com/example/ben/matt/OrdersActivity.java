package com.example.ben.matt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OrdersActivity extends AppCompatActivity {
    public Button but1;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> custOrderList = new ArrayList<GroupInfo>();
    private GroupInfo headerInfo = new GroupInfo();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    public ArrayList<String> customerAddresses = new ArrayList<>();
    private String currentCust = "";
    private String authorization;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        authorization = getIntent().getStringExtra("auth");
        goToHomepage();
        goToFinishedOrders();
        finishOrder();
        showBtnCompleted();
        loadData();
        goFindRoute();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(OrdersActivity.this, custOrderList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //collapse all the Groups
        collapseAll();

        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                headerInfo = custOrderList.get(groupPosition);
                currentCust = headerInfo.getAddress();
                //display it or do something with it
                Toast.makeText(getBaseContext(), "You've selected the customer " + headerInfo.getName() + "." + " With the address " + currentCust, Toast.LENGTH_LONG).show();
                headerInfo.setAddress(currentCust);

                return false;
            }
        });
    }

    //method to check off completed orders
    private void finishOrder() {
        but1 = (Button)findViewById(R.id.btnFinish);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select an order
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //get the right order from the headerInfo
                                custOrderList.remove(headerInfo);
                                //CustomAdapter.notifyDataSetChanged();
                                Toast.makeText(getBaseContext(), "You've removed the customer order " + headerInfo.getName() + ".", Toast.LENGTH_LONG).show();

                                //update the page
                                //finish();
                                //startActivity(intent);

                                //navigate to completed orders
                                goToFinishedOrders();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(OrdersActivity.this);
                builder.setMessage("Are you sure you want to finish " + headerInfo.getName() +"'s order?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {
        new getData().execute();

        customerAddresses.add("54 Connaught Avenue\nMutley\nPlymouth\nPL47BY\nEngland");
        customerAddresses.add("61A Alexandra Road\nMutley\nPlymouth\nPL47EF");
        customerAddresses.add("96 Beaumont Road\nPlymouth\nDevon\nPL49EA");

        addProduct("Aldmar Joubert", "Small Hawaiian", false, customerAddresses.get(2));
        addProduct("Aldmar Joubert", "Potatoe Wedges", false, customerAddresses.get(2));
        addProduct("Aldmar Joubert", "1.5lt Coca Cola", false, customerAddresses.get(2));

        addProduct("Kieran Bass", "Small Margarita", false, customerAddresses.get(1));
        addProduct("Kieran Bass", "Ben & Jerrys Fish Food", false, customerAddresses.get(1));

        addProduct("Ben Shafto", "Large Meat Feast", false, customerAddresses.get(0));
    }
    private void showBtnCompleted(){
        but1 = (Button)findViewById(R.id.btnCompleted);
        for(GroupInfo g : custOrderList) {
            if (headerInfo.getFinished()) {
                //If there is nothing the the orders list yet that are "finished", hide the button.
                but1.setVisibility(View.VISIBLE);
            }
        }
    }

    //here we maintain our products for various customers
    private int addProduct(String customer, String product, Boolean finished, String address) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(customer);
        //add the group if it doesn't exist
        if (headerInfo == null) {
            headerInfo = new GroupInfo();

            headerInfo.setName(customer);
            headerInfo.setFinished(finished);
            headerInfo.setAddress(address);

            subjects.put(customer, headerInfo);
            custOrderList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = custOrderList.indexOf(headerInfo);
        return groupPosition;
    }

    public void goToHomepage(){
        but1 = (Button)findViewById(R.id.btnGoToHomepage);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(OrdersActivity.this,MainActivity.class);
                toy.putExtra("currDelAdd", headerInfo.getAddress());
                startActivity(toy);
            }
        });
    }

    public void goFindRoute(){
        but1 = (Button)findViewById(R.id.btnRoute);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCust == "") {
                    Toast.makeText(getBaseContext(), currentCust + "Please select a customer to find their route.", Toast.LENGTH_LONG).show();
                } else {
                    Intent toy = new Intent(OrdersActivity.this, MapsActivity.class);
                    toy.putExtra("currDelAdd", headerInfo.getAddress());
                    startActivity(toy);
                }
            }
        });

    }

    public void goToFinishedOrders(){
        but1 = (Button)findViewById(R.id.btnCompleted);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(OrdersActivity.this,CompletedOrdersActivity.class);
                startActivity(toy);
            }
        });
    }

    class getData extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection = null;
        LoginActivity login = new LoginActivity();
        @Override
        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251O/api/order");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Authorization", authorization);
                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                char[] buffer = new char[1024];

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line + "\n");
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

    }
}

