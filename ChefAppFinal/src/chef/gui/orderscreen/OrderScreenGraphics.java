/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.orderscreen;

import chef.API.APIConnection;
import chef.datamodel.Dessert;
import chef.datamodel.Drink;
import chef.datamodel.Ingredient;
import chef.datamodel.Menu;
import chef.datamodel.Order;
import chef.datamodel.OrderStatus;
import chef.datamodel.Pizza;
import chef.datamodel.Side;
import chef.gui.manager.MainMenu;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
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
    Timer timer = new Timer();

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
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getOrders();
                update();
                System.out.println("Thread works");
            }
        }, 500, 500);
        getOrders();
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
        Order newOrder = new Order("3");
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
                    orderToMove.setPreviousStatus(OrderStatus.NEW_ORDER);
                    newOrders.add(orderToMove);
                    break;
                case PREP:
                    orderToMove.setPreviousStatus(OrderStatus.PREP);
                    prepOrders.add(orderToMove);
                    break;
                case COOKING:
                    orderToMove.setPreviousStatus(OrderStatus.COOKING);
                    cookingOrders.add(orderToMove);
                    break;
                case WAITING_DRIVER:
                    orderToMove.setPreviousStatus(OrderStatus.WAITING_DRIVER);
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

    private void getOrders() {
        String url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/order";
        APIConnection connection = new APIConnection(url, "get");

        newOrders = new ArrayList();
        
        String jsonString = connection.getResponseString();

        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();

        ArrayList<Order> orders = new ArrayList();

        for (JsonElement jElement : jArray) {
            JsonObject jObject = jElement.getAsJsonObject();
            Order order = new Order(jObject.get("Order_ID").getAsString());
            if(!jObject.get("Total_Price").isJsonNull()){
                order.setOrderPrice(jObject.get("Total_Price").getAsDouble());
            }else{
                order.setOrderPrice(0.00);
            }
            if(!jObject.get("Status").isJsonNull()){
            order.setOrderStatus(jObject.get("Status").getAsString());
            }else{
                order.setOrderStatus("New Order");
            }
            orders.add(order);
        }
        
        for (Order order : orders) {
            if (order.getOrderStatus() == null || order.getOrderStatus() == OrderStatus.NEW_ORDER) {
                url = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/orderitem/order/" + order.getID();

                connection.changeURL(url);
                jsonString = connection.getResponseString();
                jArray = parser.parse(jsonString).getAsJsonArray();
                for (JsonElement jElement : jArray) {
                    JsonObject jObject = jElement.getAsJsonObject();

                    String menu_ID = jObject.get("Menu_ID").getAsString();

                    for (Pizza pizza : Menu.getPizzaList()) {
                        if (pizza.getMenuID().equals(menu_ID)) {
                            pizza.setQuantity(jObject.get("Quantity").getAsInt());
                            order.addPizzaItem(pizza);
                        }
                    }

                    for (Drink drink : Menu.getDrinkList()) {
                        if (drink.getMenuID().equals(menu_ID)) {
                            drink.setQuantity(jObject.get("Quantity").getAsInt());
                            order.addDrinkItem(drink);
                        }
                    }

                    for (Side side : Menu.getSideList()) {
                        if (side.getMenuID().equals(menu_ID)) {
                            side.setQuantity(jObject.get("Quantity").getAsInt());
                            order.addSideItem(side);
                        }
                    }

                    for (Dessert dessert : Menu.getDessertList()) {
                        if (dessert.getMenuID().equals(menu_ID)) {
                            dessert.setQuantity(jObject.get("Quantity").getAsInt());
                            order.addDessertItem(dessert);
                        }
                    }   
                }
                newOrders.add(order);
            }
        }
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
                response = JOptionPane.showConfirmDialog(this, "Return to Main Menu?", "Go Back", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    new MainMenu().setVisible(true);
                    this.dispose();
                }
                break;
            default:
                break;
        }
    }
}
