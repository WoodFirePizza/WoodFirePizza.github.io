package com.example.ben.matt;

/**
 * Created by Ben on 20/03/2017.
 */
import java.util.ArrayList;

public class GroupInfo {
    private String name;
    private String address;
    private Boolean finished;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {return address; }
    public void setAddress(String a) { this.address = a; }

    public Boolean getFinished() { return finished; }
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public ArrayList<ChildInfo> getProductList() {
        return list;
    }
    public void setProductList(ArrayList<ChildInfo> productList) {
        this.list = productList;
    }
}
