/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.orderscreen;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Order;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author RhysJones
 */
public class OrderComponent extends JPanel{
    
    private Order order;
    private int y;
    private static final int LINE_SIZE = 15;
    private static final int ITEM_SPACE = 3;
    private static final int ORDER_TYPE_SPACE = 2;
    private int orderSize;
    private int columnNumber, rowNumber;
    
    public OrderComponent(Order order, int columnNumber, int rowNumber){
        setLayout(null);
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        y = 20;
        this.order = order;
        orderSize();
        repaint();
    }
    
    private void orderSize(){
        
        orderSize = 50;
        
        for(Pizza currPizza: order.getPizzaItems()){
            orderSize += 18;
            
            for(Ingredient currIng: currPizza.getIngredients()){
                if(currIng.getIsExtra() || currIng.getIsRemoved()){
                    orderSize += 15;
                }
            }
        }
        
        if(!order.getDrinkItems().isEmpty()){
            orderSize += (order.getDrinkItems().size() * 16) + 8;
        }
        
        if(!order.getSideItems().isEmpty()){
            orderSize += (order.getSideItems().size() * 16) + 8;
        }
        
        if(!order.getDessertItems().isEmpty()){
            orderSize += (order.getDessertItems().size() * 16) + 8;
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4f);
        g.setFont(newFont);
        
        if(rowNumber == OrderScreenGraphics.getSelectedRow() && columnNumber == OrderScreenGraphics.getSelectedColumn()){
            g.setColor(Color.BLACK);
        }
        
        g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), false);
        
        g.setColor(Color.WHITE);
        g.drawString("Order NO. " + String.valueOf(order.getID()), 180, y);
        y += 20;
        
        if(!order.getPizzaItems().isEmpty()){
            for(Pizza currPizza: order.getPizzaItems()){
                
                g.setColor(Color.WHITE);
                g.drawString(currPizza.getQuantity() + "x " + currPizza.getDescription(), 5, y);
                y += LINE_SIZE;
            
                for(Ingredient currIng: currPizza.getIngredients()){
                    if(currIng.getIsExtra() || currIng.getIsRemoved()){
                        if(currIng.getIsExtra()){
                            g.setColor(Color.GREEN);
                        }else{
                            g.setColor(Color.RED);
                        }
                        g.drawString(currIng.toString(), 15, y);
                        y += LINE_SIZE;
                    }
                }
            
                y += ITEM_SPACE;
            }
            
            y += ORDER_TYPE_SPACE;
        }
        
        if(!order.getDrinkItems().isEmpty()){
            
            g.setColor(Color.GRAY);
            g.fillRect(0, y - 15, 470, 3);
            y += 5;
            g.setColor(Color.WHITE);
            
            for(Drink currDrink: order.getDrinkItems()){
                g.drawString(currDrink.getQuantity() + "x " + currDrink.toString(), 5, y);
                y += LINE_SIZE;
            }
            
            y += ITEM_SPACE;
        }
        
        if(!order.getSideItems().isEmpty()){
            
            g.setColor(Color.GRAY);
            g.fillRect(0, y - 15, 470, 3);
            y += 5;
            g.setColor(Color.WHITE);
            
            for(Side currSide: order.getSideItems()){
                g.drawString(currSide.getQuantity() + "x " + currSide.getDescription(), 5, y);
                y += LINE_SIZE;
            }
            
            y += ITEM_SPACE;
        }
        
        if(!order.getDessertItems().isEmpty()){
            
            g.setColor(Color.GRAY);
            g.fillRect(0, y - 15, 470, 3);
            y += 5;
            g.setColor(Color.WHITE);
            
            for(Dessert currDessert: order.getDessertItems()){
                g.drawString(currDessert.getQuantity() + "x " + currDessert.getDescription(), 5, y);
                y += LINE_SIZE;
            }  
            
            y += ITEM_SPACE;
        }
        
        g.setColor(Color.GRAY);
        g.fillRect(0, y - 15, 470, 3);
        y += 5;
        g.setColor(Color.WHITE);
        DecimalFormat df = new DecimalFormat("#.00");
        g.drawString(String.valueOf("Total: £" + df.format(order.calculatePrice())), 350, y);
        
        y = 20;
    }
    
    public int getOrderSize(){
        return this.orderSize;
    }
    
}
