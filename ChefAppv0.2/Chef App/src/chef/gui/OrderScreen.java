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
import java.util.concurrent.TimeUnit;
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
    private GridBagLayout screenLayout = new GridBagLayout();
    private int newOrderRow = 1;
    private int prepRow = 1;
    private int cookingRow = 1;
    private int driverRow = 1;
    private int selectedOrder = 4;
    private int selectedOrderColumn = 0;
    private ArrayList<JPanel> newOrders = new ArrayList();
    private ArrayList<JPanel> prepOrders = new ArrayList();
    private ArrayList<JPanel> cookingOrders = new ArrayList();
    private ArrayList<JPanel> DeliveryOrders = new ArrayList();
    
    
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
        
        this.getContentPane().getComponent(selectedOrder).setBackground(Color.GRAY);
        
    }
    
    private void setScreenLayout(){
        
        setLayout(screenLayout);
        
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
    
    private void createOrderComponent(Person personOrder){
        JPanel newComp = new JPanel();
        newComp.setBackground(Color.LIGHT_GRAY);
        //newComp.setSize(ORDER_COMP_X, ORDER_COMP_Y);
        //newComp.setLocation(ORDER_PADDING, ORDER_PADDING);
        
        gc.anchor = GridBagConstraints.NORTH;
        gc.ipady = 0;
        newComp = createListComponent(newComp, personOrder);
        gc.gridx = 0;
        gc.gridy = newOrderRow;
        
        if(newOrderRow == 2){
            gc.gridx = 1;
            gc.gridy = 1;
        }
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
        /**if(selectedOrder - 1 < this.getContentPane().getComponentCount()){
            this.getContentPane().getComponent(selectedOrder).setBackground(Color.LIGHT_GRAY);
            Component compToMove = this.getContentPane().getComponent(4);
            compToMove.setBackground(Color.gray);
            selectedOrder = 4;
        }*/
    }
    
    private void moveRight(){
        
    }
    
    private void moveUp(){
        if(selectedOrder > 4){
            this.getContentPane().getComponent(selectedOrder).setBackground(Color.LIGHT_GRAY);
            selectedOrder--;
            this.getContentPane().getComponent(selectedOrder).setBackground(Color.gray);
        }
    }
    
    private void moveDown(){
        if(selectedOrder + 1 < this.getContentPane().getComponentCount()){
            this.getContentPane().getComponent(selectedOrder).setBackground(Color.LIGHT_GRAY);
            selectedOrder++;
            this.getContentPane().getComponent(selectedOrder).setBackground(Color.gray);
        }
    }
    
    private void update(Person personOrder){
        JPanel newOrder = new JPanel();
        newOrder.setBackground(Color.LIGHT_GRAY);
        newOrder = createListComponent(newOrder, personOrder);
        gc.gridx = 3;
        gc.gridy = 2;
        add(newOrder, gc);
        newOrderRow++;
    }
    
    private Person addNewOrder(){
        Person person1 = new Person();
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

        person1.getOrder().getPizzaItems().get(0).setDescription("A different pizza");
        
        orders.add(person1);
        return person1;
    }
    
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
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_LEFT:
                moveLeft();
                System.out.println("Key Released left ");
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                System.out.println("Key Released right ");
                break;
            case KeyEvent.VK_UP:
                moveUp();
                System.out.println("Key Released up ");
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                System.out.println("Key Released down ");
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Key Pressed enter");
                Person newOrder = addNewOrder();
                update(newOrder);
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
