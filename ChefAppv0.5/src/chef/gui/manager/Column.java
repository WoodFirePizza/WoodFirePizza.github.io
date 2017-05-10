/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Menu;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author RhysJones
 */
public class Column {
    
    private ArrayList<JButton> buttons = new ArrayList();
    
    public Column(){
        createButtons();
    }
    
    private void createButtons(){
        for(Object pizza: Menu.getPizzaList()){
            
        }
        
        for(Drink drink: Menu.getDrinkList()){
            
        }
        
        for(Side side: Menu.getSideList()){
            
        }
        
        for(Dessert dessert: Menu.getDessertList()){
            
        }
    }
    
}
