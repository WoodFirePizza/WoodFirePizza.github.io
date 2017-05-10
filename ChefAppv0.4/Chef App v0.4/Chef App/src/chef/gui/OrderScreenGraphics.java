/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Order;
import chef.datamodel.Person;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import javax.swing.JFrame;

/**
 *
 * @author RhysJones
 */
public class OrderScreenGraphics extends JFrame implements KeyListener{
    
    int width;
    int height;
    private static ArrayList<Person> orders = new ArrayList();
    private static Queue<Order> newOrders = new LinkedList<Order>();
    private static Queue<Order> prepOrders = new LinkedList<Order>();
    private static Queue<Order> cookingOrders = new LinkedList<Order>();
    private static Queue<Order> waitingDeliveryOrders = new LinkedList<Order>();
    private static Queue<Order> deliveredOrders = new LinkedList<Order>();
    private Display display = new Display();
            
    public OrderScreenGraphics(){
        createTestData();
        //this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Order Screen");
        this.setResizable(false);
        this.setFocusable(true);
        this.addKeyListener(this);
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = tk.getScreenSize().width;
        height = tk.getScreenSize().height;
        this.setSize(width, height);
        this.add(display);
        this.setVisible(true);
    }
    
    private void moveOrder(int column){
        Order orderToMove = new Order();
        
        try{
        if(column == 1){
            orderToMove = newOrders.peek();
            newOrders.remove();
            prepOrders.add(orderToMove);
        }else if(column == 2){
            orderToMove = prepOrders.peek();
            prepOrders.remove();
            cookingOrders.add(orderToMove);
        }else if(column == 3){
            orderToMove = cookingOrders.peek();
            cookingOrders.remove();
            waitingDeliveryOrders.add(orderToMove);
        }else if(column == 4){
            orderToMove = waitingDeliveryOrders.peek();
            waitingDeliveryOrders.remove();
            deliveredOrders.add(orderToMove);
        }
        update();
        }catch(NoSuchElementException e){
            System.out.println("No element exists");
        }
    }
    
    private void addOrder(){
        Order newOrder = new Order();
        Pizza meatFeast = new Pizza("Pepperoni Pizza", 8.00);
        Ingredient pepperoniIng = new Ingredient("MEAT", true, false);
        Ingredient cheeseIng = new Ingredient("LOTS OF MEAT", true, false);
        meatFeast.addIngredient(cheeseIng);
        meatFeast.addIngredient(pepperoniIng);
        
        newOrder.addPizzaItem(meatFeast);
        newOrder.addPizzaItem(meatFeast);
        newOrder.addPizzaItem(meatFeast);
        newOrder.addPizzaItem(meatFeast);
        
        newOrders.add(newOrder);
        update();
    }
    
    private void createTestData(){
        Person person1 = new Person();
        Person person2 = new Person();
        Pizza pepperoni = new Pizza("Pepperoni Pizza", 8.00);
        Drink drink = new Drink(1.5, "Coke", 1.00);
        Side side = new Side("Potato Wedges", 3.00);
        
        Ingredient pepperoniIng = new Ingredient("Peperroni", true, false);
        Ingredient cheeseIng = new Ingredient("Cheese", false, false);
        
        pepperoni.addIngredient(pepperoniIng.clone());
        pepperoni.addIngredient(cheeseIng.clone());
        
        Pizza cheese = new Pizza("Cheese Pizza", 8.00);
        cheese.addIngredient(cheeseIng.clone());
        
        pepperoni.getIngredients().get(1).setIsExtra(true);
        
        Order order1 = new Order();
        order1.addPizzaItem(pepperoni.clone());
        order1.addPizzaItem(cheese.clone());
        
        Order order2 = new Order();
        
        for(int i = 0; i < 6; i++){
            order2.addPizzaItem(pepperoni.clone());
        }
        
        pepperoni.getIngredients().get(0).setIsExtra(true);
        
        order1.addPizzaItem(pepperoni.clone());
        order1.addDrinkItem(drink);
        order1.addDrinkItem(drink);
        order1.addSideItem(side);
        
        person1.setOrder(order1);
        person2.setOrder(order2);
        
        person1.getOrder().getPizzaItems().get(0).setDescription("A different pizza");
        
        orders.add(person1);
        orders.add(person2);
        orders.add(person2);
        
        newOrders.add(order1);
        newOrders.add(order2);
        newOrders.remove(order1);
        newOrders.add(order1);
        //newOrders.add(order1);
        //prepOrders.add(order1);
        //prepOrders.add(order2);
        //cookingOrders.add(order1);
        //cookingOrders.add(order2);
        //waitingDeliveryOrders.add(order1);
        //waitingDeliveryOrders.add(order2);
    }
    
    public void update(){
        display.update();
        repaint();
    }

    public static Queue getNewOrders() {
        return newOrders;
    }

    public static Queue getPrepOrders() {
        return prepOrders;
    }

    public static Queue getCookingOrders() {
        return cookingOrders;
    }

    public static Queue getWaitingDeliveryOrders() {
        return waitingDeliveryOrders;
    }

    public static Queue getDeliveryOrders() {
        return deliveredOrders;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        //Manages key event and calls correct method depending on keypressed.
        switch(key){
            case KeyEvent.VK_1:
                moveOrder(1);
                break;
            case KeyEvent.VK_2:
                moveOrder(2);
                break;
            case KeyEvent.VK_3:
                moveOrder(3);
                break;
            case KeyEvent.VK_4:
                moveOrder(4);
                break;
            case KeyEvent.VK_0:
                addOrder();
                break;
        }
    }
}
