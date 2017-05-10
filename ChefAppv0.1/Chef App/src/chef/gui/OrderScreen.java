/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui;

import chef.datamodel.Ingredient;
import chef.datamodel.MenuItem;
import chef.datamodel.Order;
import chef.datamodel.Person;
import chef.datamodel.Pizza;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author RhysJones
 */
public class OrderScreen extends javax.swing.JFrame implements KeyListener{
    
    private ArrayList<Person> orders = new ArrayList();
    private static final int ORDER_COMP_X = 250;
    private static final int ORDER_COMP_Y = 400;
    private static final int ORDER_PADDING = 30;
    private GridBagConstraints gc = new GridBagConstraints();
    private int newOrderRow = 1;
    private int prepRow = 1;
    private int cookingRow = 1;
    private int driverRow = 1;
    
    
    /**
     * Creates new form OrderScreen
     */
    public OrderScreen() {
        this.setResizable(false);
        this.setTitle("Orders");
        initComponents();
        testData();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int x = tk.getScreenSize().width;
        int y = tk.getScreenSize().height;
        this.setSize(x, y);
        this.setFocusable(true);
        this.addKeyListener(this);
        
        //createColumns();
        setScreenLayout();
        
        for(Person personOrder : orders){
            createOrderComponent(personOrder);
        }
    }
    
    private void setScreenLayout(){
        
        setLayout(new GridBagLayout());
        
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.ipady = 10;
        
        gc.gridx = 0;
        gc.gridy = 0;
        add(new JLabel("New Order"), gc);
        
        gc.gridx = 1;
        add(new JLabel("Prep"), gc);
        
        gc.gridx = 2;
        add(new JLabel("Cooking"), gc);
        
        gc.gridx = 3;
        add(new JLabel("Waiting Driver"), gc);
        
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.NORTH;
    }
    
    /**private void createColumns(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int panelWidth = (tk.getScreenSize().width / 4) - 25;
        int panelHeight = tk.getScreenSize().height - 60;
        
        JLayeredPane column1 = new JLayeredPane();
        JLabel title1 = new JLabel();
        
        Font font = title1.getFont();
        float size = font.getSize() + 6.0f;
        title1.setFont(font.deriveFont(size));
        title1.setText("New Order");
        
        //column1.setBackground(Color.DARK_GRAY);
        column1.setSize(panelWidth, panelHeight);
        column1.setLocation(20, 20);
        column1.add(title1);
        
        this.add(column1);
        
        JPanel column2 = new JPanel();
        JLabel title2 = new JLabel();
        
        title2.setFont(font.deriveFont(size));
        title2.setText("Prep");
        
        //column2.setBackground(Color.DARK_GRAY);
        column2.setSize(panelWidth, panelHeight);
        column2.setLocation(40 + panelWidth, 20);
        column2.add(title2);
        
        this.add(column2);
        
        JPanel column3 = new JPanel();
        JLabel title3 = new JLabel();
        
        title3.setFont(font.deriveFont(size));
        title3.setText("Cooking");
        
        //column3.setBackground(Color.DARK_GRAY);
        column3.setSize(panelWidth, panelHeight);
        column3.setLocation(60 + panelWidth + panelWidth, 20);
        column3.add(title3);
        
        this.add(column3);
        
        JPanel column4 = new JPanel();
        JLabel title4 = new JLabel();
        
        title4.setFont(font.deriveFont(size));
        title4.setText("Waiting Driver");
        
        //column4.setBackground(Color.DARK_GRAY);
        column4.setSize(panelWidth, panelHeight);
        column4.setLocation(80 + panelWidth + panelWidth + panelWidth, 20);
        column4.add(title4);
        
        this.add(column4);
    }*/
    
    private void createOrderComponent(Person personOrder){
        JPanel newComp = new JPanel();
        newComp.setBackground(Color.LIGHT_GRAY);
        newComp.setSize(ORDER_COMP_X, ORDER_COMP_Y);
        newComp.setLocation(ORDER_PADDING, ORDER_PADDING);
        
        gc.anchor = GridBagConstraints.NORTH;
        gc.ipady = 10;
        newComp = createListComponent(newComp, personOrder);
        gc.gridx = 0;
        gc.gridy = newOrderRow;
        
        add(newComp, gc);
        
        newOrderRow++;
    }
    
    private JPanel createListComponent(JPanel newComp, Person personOrder){
        
        newComp.setLayout(new GridBagLayout());
        GridBagConstraints orderGC = new GridBagConstraints();
        int currOrderRow = 0;
        
        orderGC.anchor = GridBagConstraints.NORTH;
        orderGC.weightx = 0.5;
        orderGC.weighty = 0.5;
        orderGC.gridx = 0;
        
        for(Pizza pizzaItem : personOrder.getOrder().getPizzaItems()){
            
            orderGC.gridy = currOrderRow;
            
            JLabel currLine = new JLabel();
            currLine.setText(pizzaItem.getDescription());
            Font font = currLine.getFont();
            float size = font.getSize() + 3.0f;
            currLine.setFont(font.deriveFont(size));
            
            newComp.add(currLine, orderGC);
            currOrderRow++;
            
            for(Ingredient ingredient : pizzaItem.getIngredients()){
                
                orderGC.gridy = currOrderRow;
                
                if(ingredient.getIsRemoved() == true){
                    JLabel removedIngredient = new JLabel();
                    removedIngredient.setFont(font.deriveFont(size));
                    removedIngredient.setForeground(Color.red);
                    removedIngredient.setText("    - No " + ingredient.getDescription());
                    newComp.add(removedIngredient, orderGC);
                    currOrderRow++;
                }else if(ingredient.getIsExtra()){
                    JLabel extraIngredient = new JLabel();
                    extraIngredient.setFont(font.deriveFont(size));
                    extraIngredient.setForeground(Color.GREEN);
                    extraIngredient.setText("    - Extra " + ingredient.getDescription());
                    newComp.add(extraIngredient, orderGC);
                    currOrderRow++;
                }
            }
        }
        return newComp;
    }
    
    private void moveLeft(){
        
    }
    
    private void moveRight(){
        
    }
    
    private void moveUp(){
        
    }
    
    private void moveDown(){
        
    }
    
    /**private JPanel createListComponent(JPanel newComp, Person personOrder){
        
        for(Pizza pizzaItem : personOrder.getOrder().getPizzaItems()){
            
            JLabel orderItemDesc = new JLabel();
            JLabel orderItemPrice = new JLabel();
            Font font = orderItemDesc.getFont();
            float size = font.getSize() + 3.0f;
            orderItemDesc.setFont(font.deriveFont(size));
            orderItemPrice.setFont(font.deriveFont(size));
            
            String textToDisplay = pizzaItem.getDescription();
            
            if(pizzaItem.getDescription().length() < 18){
                for(int i = pizzaItem.getDescription().length(); i < 18; i++){
                    textToDisplay += " ";
                }
            }
            orderItemDesc.setText(textToDisplay);
            
            newComp.add(orderItemDesc);
            
            for(Ingredient ingredient : pizzaItem.getIngredients()){
                
                textToDisplay = ingredient.getDescription();
                
                if(ingredient.getIsRemoved() == true){
                    JLabel removedIngredient = new JLabel();
                    
                    if(ingredient.getDescription().length() < 12){
                        for(int i = pizzaItem.getDescription().length(); i < 18; i++){
                            textToDisplay += " ";
                        }
                    }
                    
                    removedIngredient.setFont(font.deriveFont(size));
                    removedIngredient.setForeground(Color.red);
                    removedIngredient.setText("\t - No " + textToDisplay);
                    newComp.add(removedIngredient);
                }
            }
            //heightPreviousItem = orderItem.getSize().height;
        }   
        
        return newComp;
        
    }*/
    
    private void testData(){
        Person person1 = new Person();
        Person person2 = new Person();
        Pizza peperroni = new Pizza("Peperroni Pizza", 8.00, false, false, false);
        
        Ingredient peperroniIng = new Ingredient("Peperroni", true, false);
        Ingredient cheeseIng = new Ingredient("Cheese", false, false);
        
        peperroni.addIngredient(peperroniIng.clone());
        peperroni.addIngredient(cheeseIng.clone());
        
        Pizza cheese = new Pizza("Cheese Pizza", 8.00, false, false, false);
        cheese.addIngredient(cheeseIng.clone());
        
        peperroni.getIngredients().get(1).setIsExtra(true);
        
        Order order1 = new Order();
        order1.addPizzaItem(peperroni.clone());
        order1.addPizzaItem(cheese.clone());
        
        Order order2 = new Order();
        
        for(int i = 0; i < 6; i++){
            order2.addPizzaItem(peperroni.clone());
        }
        
        cheese.getIngredients().get(0).setIsExtra(true);
        
        order1.addPizzaItem(cheese.clone());
        
        person1.setOrder(order1);
        person2.setOrder(order2);
        
        person1.getOrder().getPizzaItems().get(0).setDescription("A different pizza");
        
        orders.add(person1);
        orders.add(person2);
        orders.add(person2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderScreen().setVisible(true);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_LEFT:
                moveLeft();
            case KeyEvent.VK_RIGHT:
                moveRight();
            case KeyEvent.VK_UP:
                moveUp();
            case KeyEvent.VK_DOWN:
                moveDown();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        System.out.print("Key Pressed");
        
        switch(key){
            case KeyEvent.VK_LEFT:
                moveLeft();
            case KeyEvent.VK_RIGHT:
                moveRight();
            case KeyEvent.VK_UP:
                moveUp();
            case KeyEvent.VK_DOWN:
                moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_LEFT:
                moveLeft();
            case KeyEvent.VK_RIGHT:
                moveRight();
            case KeyEvent.VK_UP:
                moveUp();
            case KeyEvent.VK_DOWN:
                moveDown();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
