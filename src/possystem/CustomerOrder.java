/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import possystem.menuitems.MenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import possystem.menuitems.OrderedMenuItem;

/**
 *
 * @author tylar
 */
public class CustomerOrder implements Serializable {

    private String customerName, customerAddress, customerPhoneNumber;
    private ArrayList<OrderedMenuItem> orderedItems;
    private final UUID orderID;
    private boolean delivery;

    public CustomerOrder(String customerName, String customerPhoneNumber, String customerAddress, boolean delivery) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderID = UUID.randomUUID();
        this.orderedItems = new ArrayList();
        this.delivery = delivery;
    }

    public double getOrderTotal() {

        double orderTotal = 0;

        if (orderedItems.size() > 0) {
            for (int i = 0; i < orderedItems.size(); i++) {
                orderTotal += orderedItems.get(i).getPrice();
            }
        }

        return orderTotal;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }
    
    

    public void addMenuItem(OrderedMenuItem menuItem) {
        orderedItems.add(menuItem);
    }

    public UUID getOrderID() {
        return orderID;
    }

    public ArrayList<OrderedMenuItem> getOrderedItems() {
        return orderedItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public String toString() {
        String toString = "---------------------------------------------- \n";

        toString += this.orderID + "\n" + this.customerName + "\n" + this.customerPhoneNumber + "\n" + this.customerAddress + "\n" + "\n";

        for (int i = 0; i < orderedItems.size(); i++) {
            toString += orderedItems.get(i).toString() + "\n\n";
        }

        toString += "Total: " + String.format("$%.2f", this.getOrderTotal());
        return toString;
    }

}
