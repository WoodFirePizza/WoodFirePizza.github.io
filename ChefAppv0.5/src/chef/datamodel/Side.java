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
public class Side extends MenuItem implements Cloneable{
    
    public Side(){
        
    }
    
    public Side(String description, double price){
        super(description, price);
    }
    
    public Side(String description, double price, int quantity){
        super(description, price, quantity);
    }
    
    public Side clone(){
        Side side = new Side();
        
        side.setDescription(this.getDescription());
        side.setPrice(this.getPrice());
        side.setQuantity(this.getQuantity());
        
        return side;
    }
    
    public MenuType getMenuType(){
        return MenuType.SIDE;
    }    
}
