/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.MenuItem;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import chef.gui.manager.MainMenu;
import chef.gui.manager.menu.updateinfo.RemoveIngredientButton;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author rjones14
 */
public class AddItem extends JFrame {

    private int width;
    private int height;
    private Object item;
    private int center, y;
    private String itemType;
    private ArrayList<JComboBox> ingredients = new ArrayList();
    private double price;

    public AddItem(Object item) {
        this.width = 400;
        this.height = 500;
        center = width / 2;
        y = 50;
        this.item = item;
        
        try{
            Pizza pizza = (Pizza) item;
            itemType = "pizza";
        }catch(Exception e){}
        
        try{
            Drink drink = (Drink) item;
            itemType = "drink";
        }catch(Exception e){}
        
        try{
            Side pizza = (Side) item;
            itemType = "side";
        }catch(Exception e){}
        
        try{
            Dessert pizza = (Dessert) item;
            itemType = "dessert";
        }catch(Exception e){}
        
        try{
            Ingredient ingredient = (Ingredient) item;
            itemType = "ingredient";
        }catch(Exception e){}
        
        setSize(width, height);
        centerWindow();
        setLayout(null);
        drawScreen();
    }

    private void redrawScreen() {
        new AddItem(item).setVisible(true);
        this.dispose();
    }

    private void drawScreen() {
        if("pizza".equals(itemType)){
            new AddNewPizzaItem((Pizza) item).setVisible(true);
            this.dispose();
        }else if("drink".equals(itemType)){
            add(new AddNewDrinkItem());
        }else if("side".equals(itemType) || "dessert".equals(itemType)){
            add(new AddNewOtherItem(itemType));
        }else if("ingredient".equals(itemType)){
            add(new AddIngredientItem());
        }
    }

    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

}
