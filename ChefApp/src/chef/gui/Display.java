/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author RhysJones
 */
public class Display extends JPanel{
  
    public Display(){
        setLayout(null);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLACK);
        
        Column newOrders = new Column("NEW ORDERS", 480, 1880);
        newOrders.setLocation(0, 0);
        newOrders.setSize(480, 1920);
        add(newOrders);
        Column prepOrders = new Column("PREP ORDERS", 480, 1880);
        prepOrders.setLocation(480, 0);
        prepOrders.setSize(480, 1920);
        add(prepOrders);
        Column cookingOrders = new Column("COOKING ORDERS", 480, 1880);
        cookingOrders.setLocation(960, 0);
        cookingOrders.setSize(480, 1920);
        add(cookingOrders);
        Column waitingDeliveryOrders = new Column("WAITING DELIVERY", 490, 1880);
        waitingDeliveryOrders.setLocation(1440, 0);
        waitingDeliveryOrders.setSize(480, 1920);
        add(waitingDeliveryOrders);
    }
    
}
