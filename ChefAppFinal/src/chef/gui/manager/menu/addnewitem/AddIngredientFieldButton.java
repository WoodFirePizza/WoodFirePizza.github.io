/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author rjones14
 */
public class AddIngredientFieldButton extends JButton{
    
    public AddIngredientFieldButton(){
        
        setSize(20,20);
        
        try {
            Image img = ImageIO.read(getClass().getResource("/chef/resources/miniPlusButtonIcon.png"));
            setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    
    
    
}
