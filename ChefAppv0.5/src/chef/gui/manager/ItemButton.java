/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import javax.swing.JButton;

/**
 *
 * @author RhysJones
 */
public class ItemButton extends JButton{
    
    private final int BUTTON_HEIGHT = 200;
    private final int BUTTON_WIDTH = 240;
    private String title;
    
    public ItemButton(String title){
        setSize(BUTTON_HEIGHT, BUTTON_WIDTH);
        this.title = title;
    }
    
}
