/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

/**
 *
 * @author rjones14
 */
public class APIOrderObject {
     
    String Order_ID;
    String Member_ID;
    String Driver_ID;
    String Total_Price;
    String DELIVERYADDRESSID;
    String TEMP_DELIVERYADDRESSID;
    String ISPAID;
    String Date;
    String Time;
    String isDelivery;
    String Status;

    public APIOrderObject(){
        
    }
    
    public APIOrderObject(String Order_ID, String Member_ID, String Driver_ID, String Total_Price, String DELIVERYADDRESSID, String TEMP_DELIVERYADDRESSID, String ISPAID, String Date, String Time, String isDelivery, String Status) {
        this.Order_ID = Order_ID;
        this.Member_ID = Member_ID;
        this.Driver_ID = Driver_ID;
        this.Total_Price = Total_Price;
        this.DELIVERYADDRESSID = DELIVERYADDRESSID;
        this.TEMP_DELIVERYADDRESSID = TEMP_DELIVERYADDRESSID;
        this.ISPAID = ISPAID;
        this.Date = Date;
        this.Time = Time;
        this.isDelivery = isDelivery;
        this.Status = Status;
    }

    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String Order_ID) {
        this.Order_ID = Order_ID;
    }

    public String getMember_ID() {
        return Member_ID;
    }

    public void setMember_ID(String Member_ID) {
        this.Member_ID = Member_ID;
    }

    public String getDriver_ID() {
        return Driver_ID;
    }

    public void setDriver_ID(String Driver_ID) {
        this.Driver_ID = Driver_ID;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String Total_Price) {
        this.Total_Price = Total_Price;
    }

    public String getDELIVERYADDRESSID() {
        return DELIVERYADDRESSID;
    }

    public void setDELIVERYADDRESSID(String DELIVERYADDRESSID) {
        this.DELIVERYADDRESSID = DELIVERYADDRESSID;
    }

    public String getTEMP_DELIVERYADDRESSID() {
        return TEMP_DELIVERYADDRESSID;
    }

    public void setTEMP_DELIVERYADDRESSID(String TEMP_DELIVERYADDRESSID) {
        this.TEMP_DELIVERYADDRESSID = TEMP_DELIVERYADDRESSID;
    }

    public String getISPAID() {
        return ISPAID;
    }

    public void setISPAID(String ISPAID) {
        this.ISPAID = ISPAID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(String isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    
}
