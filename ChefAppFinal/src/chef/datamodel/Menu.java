/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

import chef.API.APIConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private static ArrayList<Ingredient> ingredientList = new ArrayList();
    
    public Menu(){
        getMenuFromAPI();
    }
    
    public static void updateMenu(){
        getMenuFromAPI();
    }
    
    private static void getMenuFromAPI(){
        String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/menu";
        APIConnection connection = new APIConnection(url, "get");

        String jsonString = connection.getResponseString();
        
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();
        
        for(JsonElement jElement: jArray){
            JsonObject jObject = jElement.getAsJsonObject();
            
            JsonArray jFoodArray = jObject.getAsJsonArray("FOODs");
            
            for(JsonElement jElement2: jFoodArray){
                JsonObject jObject2 = jElement2.getAsJsonObject();
                
                String ps = "Pizza";
                
                if(ps.equals(jObject2.get("Type").getAsString())){
                    
                    Pizza pizza = new Pizza();
                    
                    pizza.setName(jObject.get("Name").getAsString());
                    pizza.setPrice(jObject.get("Price").getAsDouble());
                    pizza.setID(jObject2.get("Food_ID").getAsString());
                    pizza.setMenuID(jObject.get("Menu_ID").getAsString());
                    
                    Menu.getPizzaList().add(pizza);
                }
                
                ps = "Side";
                
                if(ps.equals(jObject2.get("Type").getAsString())){                    
                    Side side = new Side();
                    
                    side.setName(jObject.get("Name").getAsString());
                    side.setPrice(jObject.get("Price").getAsDouble());
                    side.setID(jObject2.get("Food_ID").getAsString());
                    side.setMenuID(jObject.get("Menu_ID").getAsString());
                    
                    Menu.getSideList().add(side);
                }
                
                ps = "Dessert";
                
                if(ps.equals(jObject2.get("Type").getAsString())){
                    Dessert dessert = new Dessert();
                    
                    dessert.setName(jObject.get("Name").getAsString());
                    dessert.setPrice(jObject.get("Price").getAsDouble());
                    dessert.setID(jObject2.get("Food_ID").getAsString());
                    dessert.setMenuID(jObject.get("Menu_ID").getAsString());
                    
                    Menu.getDessertList().add(dessert);
                }
            }
            
            JsonArray jDrinkArray = jObject.getAsJsonArray("DRINKs");
            
            for(JsonElement jElement2: jDrinkArray){
                JsonObject jObject2 = jElement2.getAsJsonObject();
                
                Drink drink = new Drink();
                
                drink.setName(jObject.get("Name").getAsString());
                drink.setPrice(jObject.get("Price").getAsDouble());
                String size = jObject2.get("Size").getAsString();
                size = size.substring(0, size.indexOf("ml"));
                drink.setCapacity(Double.valueOf(size));
                drink.setMenuID(jObject.get("Menu_ID").getAsString());
                
                Menu.getDrinkList().add(drink);
            }
        }
        
        connection.changeURL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/ingredient");
        
        jsonString = connection.getResponseString();
        jArray = parser.parse(jsonString).getAsJsonArray();
        
        for(JsonElement jElement: jArray){
            JsonObject jObject = jElement.getAsJsonObject();
            Ingredient ing = new Ingredient();
            ing.setDescription(jObject.get("Description").getAsString());
            ing.setID(jObject.get("Ingredient_ID").getAsString());
            
            Menu.getIngredientList().add(ing);
        }
        
        for(Pizza pizza: Menu.getPizzaList()){
            connection.changeURL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/recipe/food/" + pizza.getID());
            
            jsonString = connection.getResponseString();
            jArray = parser.parse(jsonString).getAsJsonArray();
            
            for(JsonElement jElement: jArray){
                JsonObject jObject = jElement.getAsJsonObject();
                
                for(Ingredient ingredient: Menu.getIngredientList()){
                    if(ingredient.getID().equals(jObject.get("Ingredient_ID").getAsString())){
                        pizza.addIngredient(ingredient);
                    }
                }
            }
        }
        
        /*
        drinkList.add(new Drink(330, "Coke", 1.00));
        drinkList.add(new Drink(1.5, "Coke", 2.00));
        drinkList.add(new Drink(330, "Fanta", 1.00));
        drinkList.add(new Drink(1.5, "Fanta", 2.00));
        */
        
        /*
        sideList.add(new Side("Garlic Bread", 5.50));
        sideList.add(new Side("Wedges", 4.00));
        sideList.add(new Side("Spicy Wedges", 4.50));
        sideList.add(new Side("Cheese Bites", 4.50));
        sideList.add(new Side("Pepperoni Bites", 5.00));
        */
        
        /*
        ingredientList.add(new Ingredient("Cheese", false, false));
        ingredientList.add(new Ingredient("Pepperoni", false, false));
        ingredientList.add(new Ingredient("Ham", false, false));
        ingredientList.add(new Ingredient("Pineapple", false, false));
        ingredientList.add(new Ingredient("Mushroom", false, false));
        ingredientList.add(new Ingredient("Pepper", false, false));
        ingredientList.add(new Ingredient("Onion", false, false));
        ingredientList.add(new Ingredient("Sausage", false, false));
        
        pizzaList.get(0).addIngredient(ingredientList.get(1));
        pizzaList.get(0).addIngredient(ingredientList.get(0));
        pizzaList.get(0).addIngredient(ingredientList.get(0));
        pizzaList.get(0).addIngredient(ingredientList.get(0));
        pizzaList.get(0).addIngredient(ingredientList.get(0));
        pizzaList.get(0).addIngredient(ingredientList.get(0));
        */
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
    
    public static ArrayList<Ingredient> getIngredientList(){
        return ingredientList;
    }
}
