/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.orderscreen;

import chef.datamodel.Order;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RhysJones
 */
public class Column extends JPanel{
    
    private final int width, height;
    private int freeSpace = 980;
    private int x = 5;
    private int y = 50;
    private final String title;
    private ArrayList orders;
    private int columnNumber;
    private int ordersDisplayed;
    
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
            columnNumber = 1;
        }else if(title == "PREP ORDERS"){
            orders = OrderScreenGraphics.getPrepOrders();
            columnNumber = 2;
        }else if(title == "COOKING ORDERS"){
            orders = OrderScreenGraphics.getCookingOrders();
            columnNumber = 3;
        }else if(title == "WAITING DELIVERY"){
            orders = OrderScreenGraphics.getWaitingDeliveryOrders();
            columnNumber = 4;
        }
    }
    
    public void update(){
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        getOrders();
        ordersDisplayed = 0;
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(0, 0, width - 10, height - 40, true);
        
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(lblTitle.getFont().deriveFont(15.0f));
        lblTitle.setLocation(180,10);
        lblTitle.setSize(400, 50);
        if(columnNumber == OrderScreenGraphics.getSelectedColumn()){
            lblTitle.setForeground(Color.yellow);
        }
        add(lblTitle);
        int rowNumber = 0;
        
        for(Object currOrder: orders){
            rowNumber++;
            OrderComponent oc = new OrderComponent((Order)currOrder, columnNumber, rowNumber);
            
            if(freeSpace - oc.getOrderSize() > 0){
                ordersDisplayed++;
                freeSpace -= oc.getOrderSize();
                oc.setLocation(x, y);
                y += oc.getOrderSize() + 10;
                oc.setSize(460, oc.getOrderSize());
                add(oc);
            }
        }
    }
    
    public int getOrdersDisplayed(){
        return ordersDisplayed;
    }
}
