/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

import java.text.DecimalFormat;

/**
 *
 * @author RhysJones
 */
public class Drink extends MenuItem implements Cloneable{
    
    private double capacity;
    
    public Drink(){ 
    }
    
    public Drink(double capacity, String description, double price){
        super(description, price);
        this.capacity = capacity;
    }
    
    public Drink(double capacity, String description, double price, int quantity){
        super(description, price, quantity);
        this.capacity = capacity;
    }
    
    
    public Drink clone(){
        Drink drink = new Drink();
        
        drink.setDescription(this.getDescription());
        drink.setPrice(this.getPrice());
        drink.setCapacity(this.capacity);
        drink.setQuantity(this.getQuantity());
        
        return drink;
    }
    
    @Override
    public String toString(){
        String returnString = "";
        DecimalFormat df;
        
        if(capacity > 2){
            df = new DecimalFormat("###");
            returnString = df.format(capacity) + "ml " + this.getDescription();
        }else{
            df = new DecimalFormat("#.0");
            returnString = df.format(capacity) + "L " + this.getDescription();
        }
        
        return returnString;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    
    public MenuType getMenuType(){
        return MenuType.DRINK;
    }
}
