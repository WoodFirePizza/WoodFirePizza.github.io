/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager;

import chef.API.APILogin;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author RhysJones
 */
public class Login extends JFrame {

    private int width, height;

    private JTextField txtUsername = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private String typedUsername;
    private String typedPassword;

    public Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.setResizable(false);
        this.setLayout(null);
        width = 300;
        height = 400;
        this.setSize(width, height);
        centerWindow();
        drawScreen();
    }

    public void drawScreen() {
        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JButton btnLogin = new JButton("Login");
        this.getRootPane().setDefaultButton(btnLogin);

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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
    }

    private void loginUser() {

        typedUsername = txtUsername.getText();
        typedPassword = "";
        for (char character : txtPassword.getPassword()) {
            typedPassword += character;
        }

        if (login()) {
            new MainMenu().setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Username or Password incorrect", "Incorrect Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean login() {

        APILogin connection = new APILogin(typedUsername, typedPassword);
        int jsonString = connection.getResponseCode();
        
        if(jsonString == 200){
            return true;
        }
        
        return false;
    }

    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
}