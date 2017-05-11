/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

/**
 *
 * @author RhysJones
 */
public abstract class MenuItem{
    
    private String ID;
    private String menuID;
    private String description;
    private String name;
    private double price;
    private int quantity;
    private String imgSrc;
    
    public MenuItem(){
    }
    
    public MenuItem(String description, double price){
        this.description = description;
        this.price = price;
    }
    
    public MenuItem(String description, double price, int quantity){
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    
    public void setID(String id){
        this.ID = id;
    }
    
    public String getID(){
        return ID;
    }
    
    public void setMenuID(String menuID){
        this.menuID = menuID;
    }
    
    public String getMenuID(){
        return menuID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }   
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
