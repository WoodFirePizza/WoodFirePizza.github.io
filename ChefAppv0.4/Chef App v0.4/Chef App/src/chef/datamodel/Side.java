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
public class Side extends MenuItem{
    
    public Side(){
        
    }
    
    public Side(String description, double price){
        super.setDescription(description);
        super.setPrice(price);
    }
    
    public MenuType getMenuType(){
        return MenuType.SIDE;
    }
    
}
