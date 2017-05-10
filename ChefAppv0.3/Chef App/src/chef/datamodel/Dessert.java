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
public class Dessert extends MenuItem{
    
    public Dessert(){ 
    }
    
    public Dessert(String description, int price, boolean isVegan, boolean isSpicy, Boolean isGlutenFree){
        super(description, price, isVegan, isSpicy, isGlutenFree);
    }
    
    public MenuType getMenuType(){
        return MenuType.DESSERT;
    }
    
}
