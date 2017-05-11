/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import chef.API.APIPostMenuItem;
import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.MenuItem;
import chef.datamodel.Pizza;
import chef.gui.manager.menu.updateinfo.RemoveIngredientButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author rjones14
 */
public class AddNewPizzaItem extends JFrame{

    private int width;
    private int height;
    private Pizza item;
    private int center, y;
    private ArrayList<JComboBox> ingredients = new ArrayList();

    public AddNewPizzaItem(Pizza item) {
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
        new AddNewPizzaItem(item).setVisible(true);
        this.dispose();
    }

    private void drawScreen() {
        
        JLabel title = new JLabel("Add Pizza");
        title.setLocation(center - 30, 10);
        title.setSize(100, 30);
        add(title);
        
        y = 50;

        JLabel name = new JLabel("Name: ");
        name.setLocation(center - 43, y);
        name.setSize(100, 20);
        add(name);
        JTextField txtName = new JTextField();
        txtName.setLocation(center, y);
        txtName.setSize(100, 20);
        add(txtName);

        y += 30;

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
        spnPrice.setLocation(center, y);
        spnPrice.setSize(50, 22);
        add(spnPrice);
        
        y += 30;
        
        JLabel lblImgSrc = new JLabel("Image Source:");
        lblImgSrc.setLocation(center - 89, y);
        lblImgSrc.setSize(100, 20);
        add(lblImgSrc);
        
        JTextField txtImgSrc = new JTextField();
        txtImgSrc.setLocation(center, y);
        txtImgSrc.setSize(100, 20);
        add(txtImgSrc);

        y += 30;
        
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setLocation(center - 75, y);
        lblDescription.setSize(100, 20);
        add(lblDescription);
        
        JTextArea txtDescription = new JTextArea();
        txtDescription.setLocation(center, y);
        txtDescription.setSize(100, 50);
        add(txtDescription);
        
        y += 60;

        int ingCounter = 0;

        Pizza pizza = (Pizza) item;
        
        for (Ingredient currIng : pizza.getIngredients()) {
            ingCounter++;
            JLabel lblIng = new JLabel("Ingredient " + ingCounter + ":");
            lblIng.setSize(100, 20);
            lblIng.setLocation(center - 77, y + 5);
            add(lblIng);

            y += 3;

            JComboBox ingredient = new JComboBox(Menu.getIngredientList().toArray());
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
                    pizza.getIngredients().remove(removeButton.getIndex());
                    item = pizza;
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
                    pizza.addIngredient(Menu.getIngredientList().get(0));
                    item = pizza;
                    redrawScreen();
            }
        });

        JButton btnSave = new JButton("Save");
        btnSave.setLocation(width - 100, height - 100);
        btnSave.setSize(65, 40);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pizza pizza = new Pizza();

                pizza.setDescription(txtDescription.getText());
                pizza.setPrice((Double) spnPrice.getValue());
                pizza.setName(txtName.getText());
                pizza.setImgSrc(txtImgSrc.getText());

                for (JComboBox ingredient : ingredients) {
                    pizza.addIngredient(Menu.getIngredientList().get(ingredient.getSelectedIndex()));
                }
                
                new APIPostMenuItem(pizza);
                
                Menu.updateMenu();
                
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setLocation(width - 200, height - 100);
        btnCancel.setSize(90, 40);
        add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    public void cancel() {

    }
    
    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
}
