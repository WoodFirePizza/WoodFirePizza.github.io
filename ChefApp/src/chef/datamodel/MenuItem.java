/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

/**
 *
 * @author RhysJones
 */
public abstract class MenuItem{
    
    private String description;
    private double price;
    private boolean isVegan;
    private boolean isSpicy;
    private boolean isGlutenFree;
    
    public MenuItem(){
        
    }
    
    public MenuItem(String description, double price, boolean isVegan, boolean isSpicy, boolean isGlutenFree){
        this.description = description;
        this.price = price;
        this.isVegan = isVegan;
        this.isSpicy = isSpicy;
        this.isGlutenFree = isGlutenFree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsVegan() {
        return isVegan;
    }

    public void setIsVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }

    public boolean isIsSpicy() {
        return isSpicy;
    }

    public void setIsSpicy(boolean isSpicy) {
        this.isSpicy = isSpicy;
    }

    public boolean isIsGlutenFree() {
        return isGlutenFree;
    }

    public void setIsGlutenFree(boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }
    
    
}
