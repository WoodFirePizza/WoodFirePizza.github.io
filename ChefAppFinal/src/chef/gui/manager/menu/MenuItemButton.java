/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu;

import chef.datamodel.Ingredient;
import chef.gui.manager.menu.updateinfo.UpdateItemInfo;
import chef.datamodel.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author RhysJones
 */
public class MenuItemButton extends JButton{
    
    private final int BUTTON_HEIGHT = 240;
    private final int BUTTON_WIDTH = 240;
    private String title;
    
    public MenuItemButton(Object item){
        setSize(BUTTON_HEIGHT, BUTTON_WIDTH);
        
        setFont(this.getFont().deriveFont(20.0f));
        
        try{
            MenuItem menuItem = (MenuItem) item;
            setText(menuItem.toString());
        }catch(Exception e){}
        
        try{
            Ingredient ingredientItem = (Ingredient) item;
            setText(ingredientItem.getDescription());
        }catch(Exception e){}
        
        addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateItemInfo(item);
            }
        });
    }
    
    private void updateItemInfo(Object item){
        new UpdateItemInfo(item).setVisible(true);
    }
    
}
