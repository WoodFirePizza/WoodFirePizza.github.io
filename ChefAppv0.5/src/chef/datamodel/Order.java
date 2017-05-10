/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

import java.util.ArrayList;

/**
 *
 * @author RhysJones
 */
public class Order{
    
    private int ID;
    private ArrayList<Pizza> pizzaItems = new ArrayList<>();
    private ArrayList<Drink> drinkItems = new ArrayList<>();
    private ArrayList<Side> sideItems = new ArrayList<>();
    private ArrayList<Dessert> dessertItems = new ArrayList<>();
    private OrderStatus orderStatus;
    private OrderStatus previousStatus = null;
    
    public Order(){
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public Order(int ID){
        this.ID = ID;
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public Order(ArrayList pizzaItems, ArrayList drinkItems, ArrayList sideItems, ArrayList dessertItems){
        this.pizzaItems = pizzaItems;
        this.drinkItems = drinkItems;
        this.sideItems = sideItems;
        this.dessertItems = dessertItems;
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public Order(int ID, ArrayList pizzaItems, ArrayList drinkItems, ArrayList sideItems, ArrayList dessertItems){
        this.ID = ID;
        this.pizzaItems = pizzaItems;
        this.drinkItems = drinkItems;
        this.sideItems = sideItems;
        this.dessertItems = dessertItems;
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public void updateOrderStatus(int i){
        previousStatus = this.orderStatus;
        
        switch(i){
            case 1:
                orderStatus = OrderStatus.NEW_ORDER;
                break;
            case 2:
                orderStatus = OrderStatus.PREP;
                break;
            case 3:
                orderStatus = OrderStatus.COOKING;
                break;
            case 4:
                orderStatus = OrderStatus.WAITING_DRIVER;
                break;
            case 5:
                orderStatus = OrderStatus.DELIVERY;
                break;
        }
    }
    
    public double calculatePrice(){
        double orderTotal = 0;
        
        for(Pizza currPizza: pizzaItems){
            orderTotal += currPizza.getPrice();
        }
        
        for(Drink currDrink: drinkItems){
            orderTotal += currDrink.getPrice();
        }
        
        for(Side currSide: sideItems){
            orderTotal += currSide.getPrice();
        }
        
        for(Dessert currDessert: dessertItems){
            orderTotal += currDessert.getPrice();
        }
        
        return orderTotal;
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
    
    public OrderStatus getPreviousStatus(){
        return previousStatus;
    }
    
    public void setPreviousStatus(OrderStatus orderStatus){
        this.previousStatus = orderStatus;
    }

    public int getID() {
        return ID;
    }
}
