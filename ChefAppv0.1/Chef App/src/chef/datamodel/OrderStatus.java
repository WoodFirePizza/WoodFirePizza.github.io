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
public enum OrderStatus {
    
    NEW_ORDER,
    PREP,
    COOKING,
    WAITING_DRIVER,
    DELIVERY;
    
    @Override
    public String toString(){
        String resultString = "";
        
        switch (this){
            case NEW_ORDER:
                resultString = "New Order";
                break;
            case PREP:
                resultString = "Prep";
                break;
            case COOKING:
                resultString = "Cooking";
                break;
            case WAITING_DRIVER:
                resultString = "Waiting Driver";
                break;
            case DELIVERY:
                resultString = "Delivery";
                break;
        }
        
        return resultString;
        
    }
    
}
