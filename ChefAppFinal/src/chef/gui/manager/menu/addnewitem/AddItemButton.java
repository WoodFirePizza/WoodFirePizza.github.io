/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.menu.addnewitem;

import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author rjones14
 */
public class AddItemButton extends JButton {

    String itemType;

    public AddItemButton(String itemType) {

        this.itemType = itemType;

        setSize(240, 240);

        try {
            Image img = ImageIO.read(getClass().getResource("/chef/resources/plusButtonIcon.png"));
            setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewItem();

            }
        });
    }

    public void addNewItem() {
        if(itemType == "pizza"){
            new AddItem(new Pizza()).setVisible(true);
        }else if(itemType == "drink"){
            new AddItem(new Drink()).setVisible(true);
        }else if(itemType == "side"){
            new AddItem(new Side()).setVisible(true);
        }else if(itemType == "dessert"){
            new AddItem(new Dessert()).setVisible(true);
        }else if(itemType == "ingredient"){
            new AddItem(new Ingredient()).setVisible(true);
        }
    }

}
