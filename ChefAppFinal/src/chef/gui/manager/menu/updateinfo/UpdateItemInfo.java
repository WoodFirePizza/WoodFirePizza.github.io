/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.updateinfo;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.MenuItem;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import chef.gui.manager.MainMenu;
import chef.gui.manager.menu.addnewitem.AddIngredientFieldButton;
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
public class UpdateItemInfo extends JFrame {

    private int width;
    private int height;
    private Object item;
    private int center, y;

    public UpdateItemInfo(Object item) {
        this.width = 400;
        this.height = 500;
        center = width / 2;
        y = 50;
        this.item = item;
        setSize(width, height);
        centerWindow();
        setLayout(null);
        drawScreen();
    }

    private void redrawScreen() {
        new UpdateItemInfo(item).setVisible(true);
        this.dispose();
    }

    private void drawScreen() {

        y = 50;
        
        JLabel name = new JLabel("Name: ");
        name.setLocation(center - 43, y);
        name.setSize(100, 20);
        add(name);
        JTextField txtDescription = new JTextField();
        txtDescription.setLocation(center, y);
        txtDescription.setSize(100, 20);
        add(txtDescription);

        y += 30;

        try {
            MenuItem menuItem = (MenuItem) item;

            txtDescription.setText(menuItem.getName());

            JLabel price = new JLabel("Price:");
            price.setLocation(center - 40, y);
            price.setSize(100, 20);
            add(price);
            double min = 0.00;
            double value = 0;
            double max = 50.00;
            double stepSize = 1.0;
            SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, stepSize);
            JSpinner spnPrice = new JSpinner(model);
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spnPrice.getEditor();
            DecimalFormat format = editor.getFormat();
            format.setMinimumFractionDigits(2);
            editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
            spnPrice.setValue(menuItem.getPrice());
            spnPrice.setLocation(center, y);
            spnPrice.setSize(50, 22);
            add(spnPrice);

            y += 30;
        } catch (Exception e) {
        }

        try {
            Ingredient ingredientItem = (Ingredient) item;

            txtDescription.setText(ingredientItem.getDescription());
        } catch (Exception e) {
        }

        try {
            Pizza foodItem = (Pizza) item;

            ArrayList<JComboBox> ingredients = new ArrayList();

            int ingCounter = 0;

            for (Ingredient currIng : foodItem.getIngredients()) {
                ingCounter++;
                JLabel lblIng = new JLabel("Ingredient " + ingCounter + ":");
                lblIng.setSize(100, 20);
                lblIng.setLocation(center - 77, y + 5);
                add(lblIng);

                y += 3;

                JComboBox ingredient = new JComboBox(Menu.getIngredientList().toArray());
                ingredient.setSelectedItem(currIng);
                ingredient.setLocation(center, y);
                ingredient.setSize(100, 30);
                ingredients.add(ingredient);
                add(ingredient);
                
                RemoveIngredientButton removeButton = new RemoveIngredientButton(ingCounter - 1);
                removeButton.setLocation(center + 110, y + 5);
                add(removeButton);
                
                removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    foodItem.getIngredients().remove(removeButton.getIndex());
                    item = (Object) foodItem;
                    redrawScreen();
                }
            });
                
                y += 35;
            }

            AddIngredientFieldButton addIngButton = new AddIngredientFieldButton();
            addIngButton.setLocation(width - 120, y);
            add(addIngButton);

            addIngButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    foodItem.addIngredient(Menu.getIngredientList().get(0));
                    item = (Object) foodItem;
                    redrawScreen();
                }
            });

        } catch (Exception e) {
        }

        try {
            Drink drinkItem = (Drink) item;

            JLabel lblVolume = new JLabel("Volume:");
            lblVolume.setSize(100, 20);
            lblVolume.setLocation(center - 53, y);
            add(lblVolume);

            /*JTextField txtVolume = new JTextField();
            txtVolume.setText(String.valueOf(drinkItem.getCapacity()));
            txtVolume.setSize(100, 20);
            txtVolume.setLocation(center, y);
            add(txtVolume);*/
            double min = 0;
            double value = 0;
            double max = 2000;
            double stepSize = 100;
            SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, stepSize);
            JSpinner spnVolume = new JSpinner(model);
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spnVolume.getEditor();
            DecimalFormat format = editor.getFormat();
            format.setMinimumFractionDigits(0);
            editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
            spnVolume.setValue(drinkItem.getCapacity());
            spnVolume.setLocation(center, y);
            spnVolume.setSize(55, 22);
            add(spnVolume);

            JLabel ml = new JLabel("ml");
            ml.setSize(20, 20);
            ml.setLocation(center + 56, y);
            add(ml);

        } catch (Exception e) {
        }

        JButton btnSave = new JButton("Save");
        btnSave.setLocation(width - 100, height - 100);
        btnSave.setSize(65, 40);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setLocation(width - 200, height - 100);
        btnDelete.setSize(90, 40);
        add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    private void save() {
        
        int index;
        
    }

    private void delete() {
        try{
            Menu.getPizzaList().remove((Pizza) item);
            new MainMenu().setVisible(true);
            this.dispose();
        }catch(Exception e){
            System.out.println("NOT DELETED");
        }
    }

    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

}