/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

import chef.API.APIPutOrder;
import java.util.ArrayList;

/**
 *
 * @author RhysJones
 */
public class Order{
    
    private String ID;
    private ArrayList<Pizza> pizzaItems = new ArrayList<>();
    private ArrayList<Drink> drinkItems = new ArrayList<>();
    private ArrayList<Side> sideItems = new ArrayList<>();
    private ArrayList<Dessert> dessertItems = new ArrayList<>();
    private double orderPrice;
    private OrderStatus orderStatus;
    private OrderStatus previousStatus = null;
    
    public Order(){
    }
    
    public Order(String ID){
        this.ID = ID;
    }
    
    public Order(ArrayList pizzaItems, ArrayList drinkItems, ArrayList sideItems, ArrayList dessertItems){
        this.pizzaItems = pizzaItems;
        this.drinkItems = drinkItems;
        this.sideItems = sideItems;
        this.dessertItems = dessertItems;
    }
    
    public Order(String ID, ArrayList pizzaItems, ArrayList drinkItems, ArrayList sideItems, ArrayList dessertItems){
        this.ID = ID;
        this.pizzaItems = pizzaItems;
        this.drinkItems = drinkItems;
        this.sideItems = sideItems;
        this.dessertItems = dessertItems;
    }
    
    public void updateOrderStatus(int i){
        previousStatus = this.orderStatus;
        
        switch(i){
            case 1:
                orderStatus = OrderStatus.NEW_ORDER;
                new APIPutOrder(this);
                break;
            case 2:
                orderStatus = OrderStatus.PREP;
                new APIPutOrder(this);
                break;
            case 3:
                orderStatus = OrderStatus.COOKING;
                new APIPutOrder(this);
                break;
            case 4:
                orderStatus = OrderStatus.WAITING_DRIVER;
                new APIPutOrder(this);
                break;
            case 5:
                orderStatus = OrderStatus.DELIVERY;
                new APIPutOrder(this);
                break;
        }
    }
    
    public void addPizzaItem(Pizza itemToAdd){
        pizzaItems.add(itemToAdd);
    }
    
    public void addDrinkItem(Drink itemToAdd){
        drinkItems.add(itemToAdd);
    }
    
    public void addSideItem(Side itemToAdd){
        sideItems.add(itemToAdd);
    }
    
    public void addDessertItem(Dessert itemToAdd){
        dessertItems.add(itemToAdd);
    }
    
    public ArrayList<Pizza> getPizzaItems(){
        return pizzaItems;
    }
    
    public void setPizzaItems(ArrayList<Pizza> orderItems){
        this.pizzaItems = orderItems;
    }  

    public ArrayList<Drink> getDrinkItems() {
        return drinkItems;
    }

    public void setDrinkItems(ArrayList<Drink> drinkItems) {
        this.drinkItems = drinkItems;
    }

    public ArrayList<Side> getSideItems() {
        return sideItems;
    }

    public void setSideItems(ArrayList<Side> sideItems) {
        this.sideItems = sideItems;
    }

    public ArrayList<Dessert> getDessertItems() {
        return dessertItems;
    }

    public void setDessertItems(ArrayList<Dessert> dessertItems) {
        this.dessertItems = dessertItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setOrderStatus(String orderStatus){
        if("New Order".equals(orderStatus)){
            this.orderStatus = OrderStatus.NEW_ORDER;
        }else if("Prep".equals(orderStatus)){
            this.orderStatus = OrderStatus.PREP;
        }else if("Cooking".equals(orderStatus)){
            this.orderStatus = OrderStatus.COOKING;
        }else if("Waiting Driver".equals(orderStatus)){
            this.orderStatus = OrderStatus.WAITING_DRIVER;
        }else if("Delivery".equals(orderStatus)){
            this.orderStatus = OrderStatus.DELIVERY;
        }
    }
    
    public OrderStatus getPreviousStatus(){
        return previousStatus;
    }
    
    public void setPreviousStatus(OrderStatus orderStatus){
        this.previousStatus = orderStatus;
    }

    public String getID() {
        return ID;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
