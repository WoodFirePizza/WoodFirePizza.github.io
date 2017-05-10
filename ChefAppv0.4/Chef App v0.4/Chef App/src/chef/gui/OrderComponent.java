/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

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
    
    public OrderComponent(Order order){
        setLayout(null);
        y = 20;
        this.order = order;
        orderSize();
        repaint();
    }
    
    private void orderSize(){
        
        orderSize = 30;
        
        for(Pizza currPizza: order.getPizzaItems()){
            orderSize += 18;
            
            for(Ingredient currIng: currPizza.getIngredients()){
                if(currIng.getIsExtra() || currIng.getIsRemoved()){
                    orderSize += 15;
                }
            }
        }
        
        if(!order.getDrinkItems().isEmpty()){
            
            orderSize += 3;
            
            for(Drink currDrink: order.getDrinkItems()){
                orderSize += 16;
            }
            
            orderSize += 5;
        }
        
        if(!order.getSideItems().isEmpty()){
            
            orderSize += 3;
            
            for(Side currSide: order.getSideItems()){
                orderSize += 16;
            }
            
            orderSize += 5;
        }
        
        if(!order.getDessertItems().isEmpty()){
            
            orderSize += 3;
            
            for(Dessert currDessert: order.getDessertItems()){
                orderSize += 16;
            }
            
            orderSize += 5;
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4f);
        g.setFont(newFont);
        
        g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), false);
        
        if(!order.getPizzaItems().isEmpty()){
            for(Pizza currPizza: order.getPizzaItems()){
                
                g.setColor(Color.WHITE);
                g.drawString(currPizza.getDescription(), 5, y);
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
                g.drawString(currDrink.toString(), 5, y);
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
                g.drawString(currSide.getDescription(), 5, y);
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
                g.drawString(currDessert.getDescription(), 5, y);
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
