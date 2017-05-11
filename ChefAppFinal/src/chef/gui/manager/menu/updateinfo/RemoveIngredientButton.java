/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.updateinfo;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author rjones14
 */
public class RemoveIngredientButton extends JButton{
    
    private int index;
    
    public RemoveIngredientButton(int index){
        setSize(20, 20);
        this.index = index;
        
        try {
            Image img = ImageIO.read(getClass().getResource("/chef/resources/miniMinusButtonIcon.png"));
            setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getIndex(){
        return index;
    }
    
}
