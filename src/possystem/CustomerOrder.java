package possystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import possystem.menuitems.OrderedMenuItem;

/**
 *
 * @author tylar
 */
//customer order object for each order placed on the system
public class CustomerOrder implements Serializable {

    //strings containing customer info
    private String customerName, customerAddress, customerPhoneNumber;
    //boolean for deilvery or pickup order types
    private boolean delivery;
    
    //list of items for the order
    private final ArrayList<OrderedMenuItem> orderedItems;
    
    //unique id of order
    private final UUID orderID;

    public CustomerOrder(String customerName, String customerPhoneNumber, String customerAddress, boolean delivery) {
        //basic order info initilization 
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderID = UUID.randomUUID();
        this.orderedItems = new ArrayList();
        this.delivery = delivery;
    }

    //returns the total price of the customerOrder instance
    public double getOrderTotal() {

        double orderTotal = 0;

        if (orderedItems.size() > 0) {
            for (int i = 0; i < orderedItems.size(); i++) {
                orderTotal += orderedItems.get(i).getPrice();
            }
        }

        return orderTotal;
    }

    
    //various getters+setters for object variables
    
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

    //used to display orders in UI
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
