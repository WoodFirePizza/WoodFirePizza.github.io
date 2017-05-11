/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.API;

/**
 *
 * @author rjones14
 */
public class APIFoodItem {
    private String Food_ID;
    private String Menu_ID;
    private String Type;
    private String IsGluttenFree;
    private String IsVegeterian;
    private String IsSpicy;
    
    public APIFoodItem(){
        
    }

    public APIFoodItem(String Food_ID, String Menu_ID, String Type, String isGluttenFree, String isVegeterian, String isSpicy) {
        this.Food_ID = Food_ID;
        this.Menu_ID = Menu_ID;
        this.Type = Type;
        this.IsGluttenFree = isGluttenFree;
        this.IsVegeterian = isVegeterian;
        this.IsSpicy = isSpicy;
    }

    public String getFood_ID() {
        return Food_ID;
    }

    public void setFood_ID(String Food_ID) {
        this.Food_ID = Food_ID;
    }

    public String getMenu_ID() {
        return Menu_ID;
    }

    public void setMenu_ID(String Menu_ID) {
        this.Menu_ID = Menu_ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getIsGluttenFree() {
        return IsGluttenFree;
    }

    public void setIsGluttenFree(String isGluttenFree) {
        this.IsGluttenFree = isGluttenFree;
    }

    public String getIsVegeterian() {
        return IsVegeterian;
    }

    public void setIsVegeterian(String isVegeterian) {
        this.IsVegeterian = isVegeterian;
    }

    public String getIsSpicy() {
        return IsSpicy;
    }

    public void setIsSpicy(String isSpicy) {
        this.IsSpicy = isSpicy;
    }
    
    
}
