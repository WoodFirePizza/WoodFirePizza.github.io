/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.orderscreen;

import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Order;
import chef.datamodel.Person;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import chef.gui.manager.ScreenSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author RhysJones
 */
public class OrderScreenGraphics extends JFrame implements KeyListener {

    int width;
    int height;
    //private static ArrayList<Person> orders = new ArrayList();
    private static ArrayList<Order> newOrders = new ArrayList<Order>();
    private static ArrayList<Order> prepOrders = new ArrayList<Order>();
    private static ArrayList<Order> cookingOrders = new ArrayList<Order>();
    private static ArrayList<Order> waitingDeliveryOrders = new ArrayList<Order>();
    private static Stack<Order> deliveredOrders = new Stack();
    private static int selectedColumn = 1;
    private static int selectedRow = 1;
    private Display display = new Display();

    public OrderScreenGraphics() {
        //this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Order Screen");
        this.setResizable(false);
        this.setFocusable(true);
        this.addKeyListener(this);
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = tk.getScreenSize().width;
        height = tk.getScreenSize().height;
        this.setSize(width, height);
        this.add(display);
        newOrders = new ArrayList<Order>();
        prepOrders = new ArrayList<Order>();
        cookingOrders = new ArrayList<Order>();
        waitingDeliveryOrders = new ArrayList<Order>();
        deliveredOrders = new Stack();
        createTestData();
        this.setVisible(true);
    }

    private void moveOrder(int column) {
        Order orderToMove = new Order();

        try {
            if (selectedColumn == 1) {
                orderToMove = newOrders.get(selectedRow - 1);
                newOrders.remove(selectedRow - 1);
            }
            if (selectedColumn == 2) {
                orderToMove = prepOrders.get(selectedRow - 1);
                prepOrders.remove(selectedRow - 1);
            }
            if (selectedColumn == 3) {
                orderToMove = cookingOrders.get(selectedRow - 1);
                cookingOrders.remove(selectedRow - 1);
            }
            if (selectedColumn == 4) {
                orderToMove = waitingDeliveryOrders.get(selectedRow - 1);
                waitingDeliveryOrders.remove(selectedRow - 1);
            }

            switch (column) {
                case 1:
                    orderToMove.updateOrderStatus(1);
                    newOrders.add(orderToMove);
                    break;
                case 2:
                    orderToMove.updateOrderStatus(2);
                    prepOrders.add(orderToMove);
                    break;
                case 3:
                    orderToMove.updateOrderStatus(3);
                    cookingOrders.add(orderToMove);
                    break;
                case 4:
                    orderToMove.updateOrderStatus(4);
                    waitingDeliveryOrders.add(orderToMove);
                    break;
                case 5:
                    orderToMove.updateOrderStatus(5);
                    deliveredOrders.add(orderToMove);
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No item Selected");
        }
        update();
    }

    private void changeSelectedColumn() {
        switch (selectedColumn) {
            case 1:
                if (selectedRow > newOrders.size()) {
                    selectedRow = newOrders.size();
                }
                break;
            case 2:
                if (selectedRow > prepOrders.size()) {
                    selectedRow = prepOrders.size();
                }
                break;
            case 3:
                if (selectedRow > cookingOrders.size()) {
                    selectedRow = cookingOrders.size();
                }
                break;
            case 4:
                if (selectedRow > waitingDeliveryOrders.size()) {
                    selectedRow = waitingDeliveryOrders.size();
                }
            default:
                break;
        }
    }

    private void addOrder() {
        Order newOrder = new Order(3);
        Pizza meatFeast = new Pizza("Meat Feast", 16.00, 2);
        Ingredient pepperoniIng = new Ingredient("MEAT", true, false);
        Ingredient cheeseIng = new Ingredient("LOTS OF MEAT", true, false);
        meatFeast.addIngredient(cheeseIng.clone());
        meatFeast.addIngredient(pepperoniIng.clone());

        newOrder.addPizzaItem(meatFeast.clone());

        meatFeast.getIngredients().get(1).setIsExtra(true);

        newOrder.addPizzaItem(meatFeast.clone());

        newOrders.add(newOrder);
        update();
    }

    private void undoMoveToDelivered() {
        Order orderToMove = new Order();

        try {
            orderToMove = deliveredOrders.pop();
            switch (orderToMove.getPreviousStatus()) {
                case NEW_ORDER:
                    newOrders.add(orderToMove);
                    break;
                case PREP:
                    prepOrders.add(orderToMove);
                    break;
                case COOKING:
                    cookingOrders.add(orderToMove);
                    break;
                case WAITING_DRIVER:
                    waitingDeliveryOrders.add(orderToMove);
                    break;
                default:
                    break;
            }
        } catch (EmptyStackException e) {
            System.out.println("Stack Empty");
        }

        update();
    }

    private void createTestData() {

        Person person1 = new Person();
        Person person2 = new Person();
        Pizza pepperoni = new Pizza("Pepperoni Pizza", 8.00, 1);
        Drink drink = new Drink(1.5, "Coke", 1.00, 1);
        Side side = new Side("Potato Wedges", 3.00, 1);

        Ingredient pepperoniIng = new Ingredient("Peperroni", true, false);
        Ingredient cheeseIng = new Ingredient("Cheese", false, false);

        pepperoni.addIngredient(pepperoniIng.clone());
        pepperoni.addIngredient(cheeseIng.clone());

        Pizza cheese = new Pizza("Cheese Pizza", 8.00, 1);
        cheese.addIngredient(cheeseIng.clone());

        pepperoni.getIngredients().get(1).setIsExtra(true);

        Order order1 = new Order(1);
        order1.addPizzaItem(pepperoni.clone());
        order1.addPizzaItem(cheese.clone());

        Order order2 = new Order(2);

        for (int i = 0; i < 2; i++) {
            order2.addPizzaItem(pepperoni.clone());
            pepperoni.getIngredients().get(1).setIsExtra(false);
            pepperoni.setQuantity(5);
            pepperoni.setPrice(8 * 5);
        }

        //pepperoni.getIngredients().get(0).setIsExtra(true);
        order1.addPizzaItem(pepperoni.clone());
        order1.addDrinkItem(drink.clone());
        drink.setDescription("Fanta");
        order1.addDrinkItem(drink.clone());
        order1.addSideItem(side.clone());

        person1.setOrder(order1);
        person2.setOrder(order2);

        order1.getPizzaItems().get(1).setDescription("Some different Pizza");

        //orders.add(person1);
        //orders.add(person2);
        //orders.add(person2);
        newOrders.add(order1);
        newOrders.add(order2);

    }

    public void update() {
        display.update();
        repaint();
    }

    public static ArrayList getNewOrders() {
        return newOrders;
    }

    public static ArrayList getPrepOrders() {
        return prepOrders;
    }

    public static ArrayList getCookingOrders() {
        return cookingOrders;
    }

    public static ArrayList getWaitingDeliveryOrders() {
        return waitingDeliveryOrders;
    }

    public static Stack getDeliveryOrders() {
        return deliveredOrders;
    }

    public static int getSelectedColumn() {
        return selectedColumn;
    }

    public static int getSelectedRow() {
        return selectedRow;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //Manages key event and calls correct method depending on keypressed.
        switch (key) {
            case KeyEvent.VK_1:
                moveOrder(1);
                break;
            case KeyEvent.VK_2:
                moveOrder(2);
                break;
            case KeyEvent.VK_3:
                moveOrder(3);
                break;
            case KeyEvent.VK_4:
                moveOrder(4);
                break;
            case KeyEvent.VK_5:
                int response = JOptionPane.showConfirmDialog(this, "Confirm Completion");
                if (response == JOptionPane.YES_OPTION) {
                    moveOrder(5);
                }
                break;
            case KeyEvent.VK_0:
                addOrder();
                break;
            case KeyEvent.VK_RIGHT:
                if (selectedColumn < 4) {
                    selectedColumn++;
                    changeSelectedColumn();
                }
                update();
                break;
            case KeyEvent.VK_LEFT:
                if (selectedColumn > 1) {
                    selectedColumn--;
                    changeSelectedColumn();
                }
                update();
                break;
            case KeyEvent.VK_DOWN:
                if (selectedColumn == 1) {
                    if (selectedRow < newOrders.size() && display.getColumn(1).getOrdersDisplayed() > selectedRow) {
                        selectedRow++;
                    }
                }
                if (selectedColumn == 2) {
                    if (selectedRow < prepOrders.size() && display.getColumn(2).getOrdersDisplayed() > selectedRow) {
                        selectedRow++;
                    }
                }
                if (selectedColumn == 3) {
                    if (selectedRow < cookingOrders.size() && display.getColumn(3).getOrdersDisplayed() > selectedRow) {
                        selectedRow++;
                    }
                }
                if (selectedColumn == 4) {
                    if (selectedRow < waitingDeliveryOrders.size() && display.getColumn(4).getOrdersDisplayed() > selectedRow) {
                        selectedRow++;
                    }
                }
                update();
                break;
            case KeyEvent.VK_UP:
                if (selectedRow > 1) {
                    selectedRow--;
                }
                update();
                break;
            case KeyEvent.VK_BACK_SPACE:
                undoMoveToDelivered();
                break;
            case KeyEvent.VK_ESCAPE:
                response = JOptionPane.showConfirmDialog(this, "Return to Screen Selection");
                if (response == JOptionPane.YES_OPTION) {
                    new ScreenSelection().setVisible(true);
                    this.dispose();
                }
                break;
            default:
                break;
        }

        System.out.println("New orders: " + newOrders.size() + " Prep orders: " + prepOrders.size() + " Cooking orders: " + cookingOrders.size() + " Waiting Delivery: " + waitingDeliveryOrders.size());
        System.out.println("Selected Column: " + selectedColumn);
        System.out.println("Selected Row: " + selectedRow);
        System.out.println("Displayed Orders in column 1: " + display.getColumn(1).getOrdersDisplayed());
    }
}
