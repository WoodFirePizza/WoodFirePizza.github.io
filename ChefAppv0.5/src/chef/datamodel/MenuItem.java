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
    
    private String description;
    private double price;
    private int quantity;
    
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
}
