/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import chef.gui.manager.addnewuser.AddNewUser;
import chef.gui.manager.menu.MenuScreen;
import chef.gui.orderscreen.OrderScreenGraphics;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author RhysJones
 */
public class MainMenu extends JFrame implements KeyListener{
    
    private int width, height;
    
    public MainMenu(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Screen Select");
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(true);
        width = 350;
        height = 400;
        this.setSize(width, height);
        centerWindow();
        drawScreen();
    }
    
    private void drawScreen(){
        Button btnManagerScreen = new Button("Manage Menu");
        Button btnOrderScreen = new Button("Orders");
        Button btnAddUserScreen = new Button("Add User");
        LogoutButton btnLogOut = new LogoutButton();
        btnManagerScreen.setSize(width / 2, (height / 2) - 60);
        btnAddUserScreen.setSize(width / 2, (height / 2) - 60);
        btnOrderScreen.setSize(width, (height / 2) - 20);
        btnLogOut.setSize(30,30);
        //btnManagerScreen.setEnabled(false);
        
        btnManagerScreen.setLocation(0 ,0);
        btnAddUserScreen.setLocation(width / 2, 0);
        btnOrderScreen.setLocation(0, (height / 2) - 60);
        btnLogOut.setLocation(10, height - 70);
        
        try{
        }catch(Exception e){}
        add(btnManagerScreen);
        add(btnAddUserScreen);
        add(btnOrderScreen);
        add(btnLogOut);
        
        btnManagerScreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openManagerScreen();
            }
        });
        
        btnAddUserScreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openAddUserScreen();
            }
        });
        
        btnOrderScreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openOrderScreen();
            }
        });
        
        btnLogOut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                logout();
            }
        });
    }
    
    public void logout(){
        new Login().setVisible(true);
        this.dispose();
    }
    
    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
    
    private void openManagerScreen(){
        new MenuScreen().setVisible(true);
        this.dispose();
    }
    
    private void openOrderScreen(){
        new OrderScreenGraphics().setVisible(true);
        this.dispose();
    }
    
    private void openAddUserScreen(){
        new AddNewUser().setVisible(true);
        this.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
        }
    }
    
}
