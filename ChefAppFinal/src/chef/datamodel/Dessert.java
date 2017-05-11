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
public class Dessert extends MenuItem implements Cloneable{
    
    public Dessert(){ 
    }
    
    public Dessert(String description, double price){
        super(description, price);
    }
    
    public Dessert(String description, double price, int quantity){
        super(description, price, quantity);
    }
    
    public Dessert clone(){
        Dessert dessert = new Dessert();
        
        dessert.setDescription(this.getDescription());
        dessert.setPrice(this.getPrice());
        dessert.setQuantity(this.getQuantity());
        
        return dessert;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
    public MenuType getMenuType(){
        return MenuType.DESSERT;
    }
    
}
