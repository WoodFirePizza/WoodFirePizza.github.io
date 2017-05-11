/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

import chef.datamodel.Order;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Queue;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RhysJones
 */
public class Column extends JPanel{
    
    private final int width, height;
    private int freeSpace = 1840;
    private int x = 5;
    private int y = 50;
    private final String title;
    private ArrayList<OrderComponent> ordersComp = new ArrayList();
    private Queue orders;
    
    public Column(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        setLayout(null);
        repaint();
    }
    
    public void getOrders(){
        if(title == "NEW ORDERS"){
            orders = OrderScreenGraphics.getNewOrders();
        }else if(title == "PREP ORDERS"){
            orders = OrderScreenGraphics.getPrepOrders();
        }else if(title == "COOKING ORDERS"){
            orders = OrderScreenGraphics.getCookingOrders();
        }else if(title == "WAITING DELIVERY"){
            orders = OrderScreenGraphics.getWaitingDeliveryOrders();
        }
    }
    
    public void update(){
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        getOrders();
        ordersComp = null;
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(0, 0, width - 10, height - 40, true);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(lblTitle.getFont().deriveFont(15.0f));
        lblTitle.setLocation(180,10);
        lblTitle.setSize(400, 50);
        add(lblTitle);
        
        for(Object currOrder: orders){
            OrderComponent oc = new OrderComponent((Order)currOrder);
            
            if(freeSpace - oc.getOrderSize() > 0){
                freeSpace -= oc.getOrderSize();
                oc.setLocation(x, y);
                y += oc.getOrderSize() + 10;
                oc.setSize(460, oc.getOrderSize());
                add(oc);
            }
        }
    }
}