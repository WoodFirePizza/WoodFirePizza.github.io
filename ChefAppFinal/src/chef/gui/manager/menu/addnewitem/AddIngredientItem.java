/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.MenuItem;
import chef.datamodel.Pizza;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author rjones14
 */
public class AddIngredientItem extends JPanel{

    private int width;
    private int height;
    private Object item;
    private int center, y;
    private String itemType;
    private ArrayList<JComboBox> ingredients = new ArrayList();
    private double price;

    public AddIngredientItem() {
        this.width = 400;
        this.height = 500;
        center = width / 2;
        y = 50;
        this.item = item;
        setSize(width, height);
        setLayout(null);
        drawScreen();
    }

    public void drawScreen() {
        
        JLabel title = new JLabel("Add Ingredient");
        title.setLocation(center - 30, 10);
        title.setSize(100, 30);
        add(title);
        
        y = 50;

        JLabel name = new JLabel("Name: ");
        name.setLocation(center - 43, y);
        name.setSize(100, 20);
        add(name);
        JTextField txtDescription = new JTextField();
        txtDescription.setLocation(center, y);
        txtDescription.setSize(100, 20);
        add(txtDescription);

        JButton btnSave = new JButton("Save");
        btnSave.setLocation(width - 100, height - 100);
        btnSave.setSize(65, 40);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ingredient ingredient = new Ingredient();

                ingredient.setDescription(txtDescription.getText());
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
}
