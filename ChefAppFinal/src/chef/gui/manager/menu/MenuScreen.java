/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu;

import chef.gui.manager.MainMenu;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author RhysJones
 */
public class MenuScreen extends JFrame implements KeyListener{
    
    private int width, height;
    private MenuDisplay display = new MenuDisplay();
    
    public MenuScreen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Manager Screen");
        this.setResizable(false);
        this.addKeyListener(this);
        this.setFocusable(true);
        width = 1012;
        height = 848;
        this.setSize(width, height);
        JScrollPane scrollPane = new JScrollPane(display, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        display.setPreferredSize(new Dimension(display.getWidth(), display.getDisplayHeight()));
        centerWindow();
        this.setVisible(true);
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
        
        if(key == KeyEvent.VK_ESCAPE){
            int response = JOptionPane.showConfirmDialog(this, "Return to Main Menu?", "Go Back", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                new MainMenu().setVisible(true);
                this.dispose();
            }
        }
    }
    
}
