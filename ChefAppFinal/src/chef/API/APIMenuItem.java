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
public class APIMenuItem {
    
    private String Menu_ID;
    private String name;
    private double Price;
    private String img_source;
    private String Description;
    
    public APIMenuItem(){
    }

    public APIMenuItem(String Menu_ID, String name, double Price, String img_source, String Description) {
        this.Menu_ID = Menu_ID;
        this.name = name;
        this.Price = Price;
        this.img_source = img_source;
        this.Description = Description;
    }

    public String getMenu_ID() {
        return Menu_ID;
    }

    public void setMenu_ID(String Menu_ID) {
        this.Menu_ID = Menu_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getImg_source() {
        return img_source;
    }

    public void setImg_source(String img_source) {
        this.img_source = img_source;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
