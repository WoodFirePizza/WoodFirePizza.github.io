package com.example.ben.matt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CompletedOrdersActivity extends AppCompatActivity {
    public Button but1;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> custOrderList = new ArrayList<GroupInfo>();
    private GroupInfo headerInfo;
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__completed__orders);
        goToHomepage();
        goBack();
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(CompletedOrdersActivity.this, custOrderList);
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
                //display it or do something with it
                Toast.makeText(getBaseContext(), "You've selected the customer " + headerInfo.getName() + ".", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {
        //Iterate through "custOrderList" getting each 'group' and corresponding 'children', then display them
        addProduct("Aldmar Joubert", "Large Meat Feast", false);
        addProduct("Aldmar Joubert", "Cheesy Chips", false);
        addProduct("Aldmar Joubert", "1.5lt Coca Cola", false);

        addProduct("Kieran Bass", "Small Margarita", false);
        addProduct("Kieran Bass", "Ben & Jerrys Fish Food", false);

        addProduct("Ben Shafto", "Fuck off big pizza", false);
    }

    //here we maintain our products for various customers
    private int addProduct(String customer, String product, Boolean finished) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(customer);
        //add the group if it doesn't exist
        if(headerInfo == null) {
            headerInfo = new GroupInfo();
            headerInfo.setName(customer);
            headerInfo.setFinished(finished);
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

    public void goToHomepage() {
        but1 = (Button) findViewById(R.id.btnGoToHomepage);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(CompletedOrdersActivity.this, MainActivity.class);
                startActivity(toy);
            }
        });
    }

    public void goBack() {
        but1 = (Button) findViewById(R.id.btnBack);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(CompletedOrdersActivity.this, OrdersActivity.class);
                startActivity(toy);
            }
        });
    }
}
