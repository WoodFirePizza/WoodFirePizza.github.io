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
public class Drink {
    
    private double capacity;
    
    public Drink(){
        
    }
    
    public Drink(double capacity){
        this.capacity = capacity;
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
