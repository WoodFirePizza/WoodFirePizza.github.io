/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author rjones14
 */
public class LogoutButton extends JButton{
    
    public LogoutButton(){
        setSize(20, 20);
        
        try {
            Image img = ImageIO.read(getClass().getResource("/chef/resources/logout.png"));
            setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
