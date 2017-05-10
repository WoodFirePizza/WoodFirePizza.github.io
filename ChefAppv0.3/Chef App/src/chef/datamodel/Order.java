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
    
    private ArrayList<Pizza> pizzaItems = new ArrayList<>();
    private ArrayList<Drink> drinkItems = new ArrayList<>();
    private ArrayList<Side> sideItems = new ArrayList<>();
    private ArrayList<Dessert> dessertItems = new ArrayList<>();
    private OrderStatus orderStatus;
    
    public Order(){
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public Order(ArrayList pizzaItems, ArrayList drinkItems, ArrayList sideItems, ArrayList dessertItems){
        this.pizzaItems = pizzaItems;
        this.drinkItems = drinkItems;
        this.sideItems = sideItems;
        this.dessertItems = dessertItems;
        orderStatus = OrderStatus.NEW_ORDER;
    }
    
    public void updateOrderStatus(){
        switch(orderStatus){
            case NEW_ORDER:
                orderStatus = OrderStatus.PREP;
                break;
            case PREP:
                orderStatus = OrderStatus.COOKING;
                break;
            case COOKING:
                orderStatus = OrderStatus.WAITING_DRIVER;
                break;
            case WAITING_DRIVER:
                orderStatus = OrderStatus.DELIVERY;
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
}
