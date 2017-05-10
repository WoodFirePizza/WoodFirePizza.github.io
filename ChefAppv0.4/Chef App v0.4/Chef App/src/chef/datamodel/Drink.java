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
public class Drink extends MenuItem{
    
    private double capacity;
    
    public Drink(){
        
    }
    
    public Drink(double capacity, String description, double price){
        this.capacity = capacity;
        super.setDescription(description);
        super.setPrice(price);
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
