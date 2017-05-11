/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import chef.datamodel.Drink;
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
public class AddNewDrinkItem extends JPanel {

    private int width;
    private int height;
    private Object item;
    private int center, y;
    private String itemType;
    private ArrayList<JComboBox> ingredients = new ArrayList();
    private double price;

    public AddNewDrinkItem() {
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

        JLabel title = new JLabel("Add Drink");
        title.setLocation(center - 30, 10);
        title.setSize(100, 30);
        add(title);
        
        y = 50;

        JLabel name = new JLabel("Name:");
        name.setLocation(center - 43 , y);
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
        double minVolume = 0;
        double valueVolume = 0;
        double maxVolume = 2000;
        double stepSizeVolume = 100;
        SpinnerNumberModel modelVolume = new SpinnerNumberModel(valueVolume, minVolume, maxVolume, stepSizeVolume);
        JSpinner spnVolume = new JSpinner(modelVolume);
        JSpinner.NumberEditor editorVolume = (JSpinner.NumberEditor) spnVolume.getEditor();
        DecimalFormat formatVolume = editorVolume.getFormat();
        format.setMinimumFractionDigits(0);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        spnVolume.setLocation(center, y);
        spnVolume.setSize(55, 22);
        add(spnVolume);

        JLabel ml = new JLabel("ml");
        ml.setSize(20, 20);
        ml.setLocation(center + 56, y);
        add(ml);

        JButton btnSave = new JButton("Save");
        btnSave.setLocation(width - 100, height - 100);
        btnSave.setSize(65, 40);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Drink drink = new Drink();

                drink.setDescription(txtName.getText());
                drink.setPrice((Double) spnPrice.getValue());
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
