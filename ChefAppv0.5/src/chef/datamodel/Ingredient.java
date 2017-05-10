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
public class Ingredient implements Cloneable{
    
    private String description;
    private Boolean isRemoved;
    private Boolean isExtra;
    
    public Ingredient(){
        
    }
    
    public Ingredient(String description, Boolean isRemoved, Boolean isExtra){
        this.description = description;
        this.isRemoved = isRemoved;
        this.isExtra = isExtra;
    }
    
    public Ingredient clone(){
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(this.getDescription());
        ingredient.setIsRemoved(this.getIsRemoved());
        ingredient.setIsExtra(this.getIsExtra());
        
        return ingredient;
    }
    
    @Override
    public String toString(){
        
        String returnString;
        
        if(isRemoved){
            returnString = ("- NO " + description.toUpperCase());
        }else{
            returnString = ("- EXTRA " + description.toUpperCase());
        }
        
        return returnString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
        if(isRemoved){
            this.isExtra = false;
        }
    }

    public Boolean getIsExtra() {
        return isExtra;
    }

    public void setIsExtra(Boolean isExtra) {
        this.isExtra = isExtra;
        if(isExtra){
            this.isRemoved = false;
        }
    }
    
}
