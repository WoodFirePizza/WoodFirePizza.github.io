/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author RhysJones
 */
public class Login extends JFrame implements KeyListener{
    
    private int width, height;
    
    public Login(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(this);
        width = 300;
        height = 400;
        this.setSize(width, height);
        centerWindow();
        drawScreen();
    }
    
    public void drawScreen(){
        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        
        int x, y;
        
        x = width / 2;
        y = height / 2;
        
        lblUsername.setSize(75, 25);
        txtUsername.setSize(150, 25);
        lblPassword.setSize(75, 25);
        txtPassword.setSize(150, 25);
        btnLogin.setSize(80, 30);
        
        lblUsername.setLocation(x - 125, y - 70);
        txtUsername.setLocation(x - 50, y - 70);
        lblPassword.setLocation(x - 125, y - 40);
        txtPassword.setLocation(x - 50, y - 40);
        btnLogin.setLocation(x + 19, y - 10);
        
        this.add(lblUsername);
        this.add(txtUsername);
        this.add(lblPassword);
        this.add(txtPassword);
        this.add(btnLogin);
        
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loginUser();
            }
        });
    }
    
    private void loginUser(){
        new ScreenSelection().setVisible(true);
        this.dispose();
    }
    
    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
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
        
        if(key == KeyEvent.VK_ENTER){
            loginUser();
        }
    }
}
