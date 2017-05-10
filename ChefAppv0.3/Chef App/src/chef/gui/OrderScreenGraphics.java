/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

import chef.datamodel.Ingredient;
import chef.datamodel.Order;
import chef.datamodel.Person;
import chef.datamodel.Pizza;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author RhysJones
 */
public class OrderScreenGraphics extends JFrame{
    
    int width;
    int height;
    private static ArrayList<Person> orders = new ArrayList();
    private static ArrayList<Order> newOrders = new ArrayList();
    private static ArrayList<Order> prepOrders = new ArrayList();
    private static ArrayList<Order> cookingOrders = new ArrayList();
    private static ArrayList<Order> waitingDeliveryOrders = new ArrayList();
    private static ArrayList<Order> deliveryOrders = new ArrayList();
            
    public OrderScreenGraphics(){
        createTestData();
        //this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Order Screen");
        this.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = tk.getScreenSize().width;
        height = tk.getScreenSize().height;
        this.setSize(width, height);
        this.add(new Display());
        this.setVisible(true);
    }
    
    private void createTestData(){
        Person person1 = new Person();
        Person person2 = new Person();
        Pizza pepperoni = new Pizza("Pepperoni Pizza", 8.00, false, false, false);
        
        Ingredient pepperoniIng = new Ingredient("Peperroni", true, false);
        Ingredient cheeseIng = new Ingredient("Cheese", false, false);
        
        pepperoni.addIngredient(pepperoniIng.clone());
        pepperoni.addIngredient(cheeseIng.clone());
        
        Pizza cheese = new Pizza("Cheese Pizza", 8.00, false, false, false);
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
        
        person1.setOrder(order1);
        person2.setOrder(order2);
        
        person1.getOrder().getPizzaItems().get(0).setDescription("A different pizza");
        
        orders.add(person1);
        orders.add(person2);
        orders.add(person2);
        
        newOrders.add(order1);
        newOrders.add(order2);
        prepOrders.add(order1);
        prepOrders.add(order2);
        cookingOrders.add(order1);
        cookingOrders.add(order2);
        waitingDeliveryOrders.add(order1);
        waitingDeliveryOrders.add(order2);
    }

    public static ArrayList<Order> getNewOrders() {
        return newOrders;
    }

    public static ArrayList<Order> getPrepOrders() {
        return prepOrders;
    }

    public static ArrayList<Order> getCookingOrders() {
        return cookingOrders;
    }

    public static ArrayList<Order> getWaitingDeliveryOrders() {
        return waitingDeliveryOrders;
    }

    public static ArrayList<Order> getDeliveryOrders() {
        return deliveryOrders;
    }
}
