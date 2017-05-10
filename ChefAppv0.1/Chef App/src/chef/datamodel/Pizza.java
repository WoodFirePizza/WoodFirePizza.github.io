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
public class Pizza extends MenuItem implements Cloneable{
    
    private ArrayList<Ingredient> ingredients = new ArrayList();
    
    public Pizza(){
    }
    
    public Pizza(String description, double price, boolean isVegan, boolean isSpicy, Boolean isGlutenFree){
        super(description, price, isVegan, isSpicy, isGlutenFree);
    }

    public Pizza clone(){
        Pizza pizza = new Pizza();
        
        pizza.setIngredients(this.getIngredients());
        pizza.setDescription(this.getDescription());
        pizza.setIsGlutenFree(this.isIsGlutenFree());
        pizza.setIsSpicy(this.isIsSpicy());
        pizza.setIsVegan(this.isIsVegan());
        pizza.setPrice(this.getPrice());
        
        return pizza;
    }
    
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }
    
    public void addIngredient(Ingredient itemToAdd){
        ingredients.add(itemToAdd);
    }
    
    public MenuType getMenuType(){
        return MenuType.PIZZA;
    }
}
