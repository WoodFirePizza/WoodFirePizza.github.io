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
public enum MenuType {
    
    PIZZA,
    DRINK,
    DESSERT,
    SIDE;
    
    public String toString(){
        String returnString = "";
        
        if(this == PIZZA){
            returnString = "Pizza";
        }
        if(this == DRINK){
            returnString = "Drink";
        }
        if(this == SIDE){
            returnString = "Side";
        }
        if(this == DESSERT){
            returnString = "Dessert";
        }
        
        return returnString;
    }
}
