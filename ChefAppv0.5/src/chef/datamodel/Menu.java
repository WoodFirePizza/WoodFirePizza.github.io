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
public final class Menu {
    
    private static ArrayList<Pizza> pizzaList = new ArrayList();
    private static ArrayList<Drink> drinkList = new ArrayList();
    private static ArrayList<Side> sideList = new ArrayList();
    private static ArrayList<Dessert> dessertList = new ArrayList();
    
    public Menu(){
        createTestData();
    }
    
    private static void createTestData(){
        pizzaList.add(new Pizza("Pepperoni", 8.00));
        pizzaList.add(new Pizza("Cheese Pizza", 7.00));
        pizzaList.add(new Pizza("MeatFeast", 9.00));
        pizzaList.add(new Pizza("Meteor", 9.00));
        
        drinkList.add(new Drink(300, "Coke", 1.00));
        drinkList.add(new Drink(1.5, "Coke", 2.00));
        drinkList.add(new Drink(300, "Fanta", 1.00));
        drinkList.add(new Drink(1.5, "Fanta", 2.00));
        
        sideList.add(new Side("Garlic Bread", 5.50));
        sideList.add(new Side("Wedges", 4.00));
        sideList.add(new Side("Spicy Wedges", 4.50));
        sideList.add(new Side("Cheese Bites", 4.50));
        sideList.add(new Side("Pepperoni Bites", 5.00));
    }

    public static ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }

    public static ArrayList<Drink> getDrinkList() {
        return drinkList;
    }

    public static ArrayList<Side> getSideList() {
        return sideList;
    }

    public static ArrayList<Dessert> getDessertList() {
        return dessertList;
    }
}
