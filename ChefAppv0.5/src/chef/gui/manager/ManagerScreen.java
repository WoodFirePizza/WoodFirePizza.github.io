/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author RhysJones
 */
public class ManagerScreen extends JFrame{
    
    private int width, height;
    
    public ManagerScreen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Manager Screen");
        this.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = tk.getScreenSize().width;
        height = tk.getScreenSize().height;
        this.setSize(width, height);
        this.setVisible(true);
    }
    
}
