/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.orderscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author RhysJones
 */
public class OrderColumn extends JPanel{
    
    private int width;
    private int height;
    private int posX;
    private int posY = 5;
    private ArrayList<OrderComponent> orders = new ArrayList();
    private Column column;
    private int freeSpace;    
    
    public OrderColumn(int width, int height, int posX){
        this.width = (width / 4);
        this.height = height;
        this.posX = posX + 5;
        freeSpace = height - 10;
        //column = new Column(width, height, posX, 5);
    }
    
    private void addOrderComponents(){
        for(OrderComponent order: orders){
            freeSpace -= order.getOrderSize();
            if(freeSpace >= 5){
                this.add(order);
            }else{
                freeSpace += order.getOrderSize();
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize(width, height);
        this.setLocation(posX - 5, posY - 5);
        this.setBackground(Color.yellow);
        //(x, y, width, height, etched or raised)
        g.setColor(Color.BLACK);
        g.fillRect(posX, posY, width - 10, height - 40);
    }
}
