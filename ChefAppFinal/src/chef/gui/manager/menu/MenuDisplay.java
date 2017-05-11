/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import chef.gui.manager.BackButton;
import chef.gui.manager.MainMenu;
import chef.gui.manager.menu.addnewitem.AddItemButton;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author RhysJones
 */
public class MenuDisplay extends JPanel {

    private int Y, X;
    private int height;
    private final int BUTTON_HEIGHT = 240;
    private final int BUTTON_WIDTH = 240;
    private final int MAX_ROW_ITEMS = 4;

    public MenuDisplay() {
        setLayout(null);
        X = 5;
        Y = 5;
    }

    public int calculateHeight(){
        int calcHeight = 35;
        
        if(Menu.getPizzaList().size() / 4 < 1 && Menu.getPizzaList().size() != 0){
            calcHeight += 245;
        }
        if(Menu.getDrinkList().size() / 4 < 1 && Menu.getDrinkList().size() != 0){
            calcHeight += 245;
        }
        if(Menu.getSideList().size() / 4 < 1 && Menu.getSideList().size() != 0){
            calcHeight += 245;
        }
        if(Menu.getDessertList().size() / 4 < 1 && Menu.getDessertList().size() != 0){
            calcHeight += 245;
        }
        if(Menu.getIngredientList().size() / 4 < 1 && Menu.getIngredientList().size() != 0){
            calcHeight += 245;
        }
        
        calcHeight += 30 + 25 + 25 + 25 + 25 + 25;
        calcHeight += ((Math.floor(Menu.getPizzaList().size() / 4) + 1) * 245);
        calcHeight += ((Math.floor(Menu.getDrinkList().size() / 4) + 1) * 245);
        calcHeight += ((Math.floor(Menu.getSideList().size() / 4) + 1) * 245);
        calcHeight += ((Math.floor(Menu.getDessertList().size() / 4) + 1) * 245);
        calcHeight += ((Math.floor(Menu.getIngredientList().size() / 4) + 1) * 245);
        calcHeight += 5;
        
        return calcHeight;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BackButton backButton = new BackButton();
        backButton.setLocation(5, 5);
        add(backButton);
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        g.setFont(getFont().deriveFont(25.0f));
        g.drawString("Edit Food Items", 425, 25);

        Y += 30;

        g.setFont(getFont().deriveFont(20.0f));
        g.drawString("Pizzas", X, Y + 15);

        Y += 25;

        int itemCounter = 0;

        for (Pizza currPizza : Menu.getPizzaList()) {

            if (itemCounter == MAX_ROW_ITEMS) {
                X = 5;
                Y += BUTTON_HEIGHT + 5;
                itemCounter = 0;
            }

            itemCounter++;
            MenuItemButton button = new MenuItemButton(currPizza);
            button.setLocation(X, Y);
            add(button);
            X += BUTTON_WIDTH + 5;
        }

        if (itemCounter == MAX_ROW_ITEMS) {
            X = 5;
            Y += BUTTON_HEIGHT + 5;
            itemCounter = 0;
        }

        AddItemButton addPizzaButton = new AddItemButton("pizza");
        addPizzaButton.setLocation(X, Y);
        add(addPizzaButton);

        X = 5;
        Y += BUTTON_HEIGHT + 5;

        g.setColor(Color.GRAY);
        g.fillRect(0, Y, this.getWidth(), 3);
        Y += 8;
        g.setColor(Color.BLACK);

        g.drawString("Drinks", X, Y + 15);
        Y += 25;

        itemCounter = 0;

        for (Drink currDrink : Menu.getDrinkList()) {
            if (itemCounter == MAX_ROW_ITEMS) {
                X = 5;
                Y += BUTTON_HEIGHT + 5;
                itemCounter = 0;
            }

            itemCounter++;

            MenuItemButton button = new MenuItemButton(currDrink);
            button.setLocation(X, Y);
            add(button);
            X += BUTTON_WIDTH + 5;
        }
        
        if (itemCounter == MAX_ROW_ITEMS) {
            X = 5;
            Y += BUTTON_HEIGHT + 5;
            itemCounter = 0;
        }

        AddItemButton addDrinkButton = new AddItemButton("drink");
        addDrinkButton.setLocation(X, Y);
        add(addDrinkButton);

        X = 5;
        Y += BUTTON_HEIGHT + 5;

        g.setColor(Color.GRAY);
        g.fillRect(0, Y, this.getWidth(), 3);
        Y += 8;
        g.setColor(Color.BLACK);

        g.drawString("Sides", X, Y + 15);
        Y += 25;

        itemCounter = 0;

        for (Side currSide : Menu.getSideList()) {
            if (itemCounter == MAX_ROW_ITEMS) {
                X = 5;
                Y += BUTTON_HEIGHT + 5;
                itemCounter = 0;
            }

            itemCounter++;

            MenuItemButton button = new MenuItemButton(currSide);
            button.setLocation(X, Y);
            add(button);
            X += BUTTON_WIDTH + 5;
        }
        
        if (itemCounter == MAX_ROW_ITEMS) {
            X = 5;
            Y += BUTTON_HEIGHT + 5;
            itemCounter = 0;
        }

        AddItemButton addSideButton = new AddItemButton("side");
        addSideButton.setLocation(X, Y);
        add(addSideButton);

        X = 5;
        Y += BUTTON_HEIGHT + 5;

        g.setColor(Color.GRAY);
        g.fillRect(0, Y, this.getWidth(), 3);
        Y += 8;
        g.setColor(Color.BLACK);

        g.drawString("Desserts", X, Y + 15);
        Y += 25;

        itemCounter = 0;

        for (Dessert currDessert : Menu.getDessertList()) {
            if (itemCounter == MAX_ROW_ITEMS) {
                X = 5;
                Y += BUTTON_HEIGHT + 5;
                itemCounter = 0;
            }

            itemCounter++;

            MenuItemButton button = new MenuItemButton(currDessert);
            button.setLocation(X, Y);
            add(button);
            X += BUTTON_WIDTH + 5;
        }
        
        if (itemCounter == MAX_ROW_ITEMS) {
            X = 5;
            Y += BUTTON_HEIGHT + 5;
            itemCounter = 0;
        }

        AddItemButton addDessertButton = new AddItemButton("dessert");
        addDessertButton.setLocation(X, Y);
        add(addDessertButton);
        
        X = 5;
        Y += BUTTON_HEIGHT + 5;

        g.setColor(Color.GRAY);
        g.fillRect(0, Y, this.getWidth(), 3);
        Y += 8;
        g.setColor(Color.BLACK);

        g.drawString("Ingredients", X, Y + 15);
        Y += 25;

        itemCounter = 0;
        
        for (Ingredient currIngredient : Menu.getIngredientList()) {
            if (itemCounter == MAX_ROW_ITEMS) {
                X = 5;
                Y += BUTTON_HEIGHT + 5;
                itemCounter = 0;
            }

            itemCounter++;

            MenuItemButton button = new MenuItemButton(currIngredient);
            button.setLocation(X, Y);
            add(button);
            X += BUTTON_WIDTH + 5;
        }
        
        if (itemCounter == MAX_ROW_ITEMS) {
            X = 5;
            Y += BUTTON_HEIGHT + 5;
            itemCounter = 0;
        }

        AddItemButton addIngredientButton = new AddItemButton("ingredient");
        addIngredientButton.setLocation(X, Y);
        add(addIngredientButton);

        height = Y;
        
        X = 5;
        Y = 5;
    }
    
    public int getDisplayHeight(){
        return calculateHeight();
    }

    private void close(){
        new MainMenu().setVisible(true);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
