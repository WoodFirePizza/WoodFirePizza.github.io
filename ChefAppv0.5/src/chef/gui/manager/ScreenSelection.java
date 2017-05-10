/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import chef.gui.orderscreen.OrderScreenGraphics;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author RhysJones
 */
public class ScreenSelection extends JFrame{
    
    private int width, height;
    
    public ScreenSelection(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Screen Select");
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(true);
        width = 400;
        height = 250;
        this.setSize(width, height);
        centerWindow();
        drawScreen();
    }
    
    private void drawScreen(){
        Button btnManagerScreen = new Button("Manager Options");
        Button btnOrderScreen = new Button("Order Screen");
        btnManagerScreen.setSize(width / 2, height - 30);
        btnOrderScreen.setSize(width / 2, height - 30);
        btnManagerScreen.setEnabled(false);
        
        btnManagerScreen.setLocation(0 ,0);
        btnOrderScreen.setLocation(width / 2, 0);
        
        add(btnManagerScreen);
        add(btnOrderScreen);
        
        btnManagerScreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openManagerScreen();
            }
        });
        
        btnOrderScreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openOrderScreen();
            }
        });
    }
    
    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
    
    private void openManagerScreen(){
        new ManagerScreen().setVisible(true);
        this.dispose();
    }
    
    private void openOrderScreen(){
        new OrderScreenGraphics().setVisible(true);
        this.dispose();
    }
    
}
