/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author tylar
 */
public class CustomerOrder implements Serializable{
    
    private String customerName, customerAddress, customerPhoneNumber;
    private ArrayList<MenuItem> orderItems;
    private UUID orderID;
    private double orderTotal;
    
    public CustomerOrder(String customerName, String customerPhoneNumber, String customerAddress){
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderID = UUID.randomUUID();
        this.orderTotal = 0;
        this.orderItems = new ArrayList();
    }
    
    public double getOrderTotal(){
        
        orderTotal = 0;
        
        if(orderItems.size() > 0){
            for(int i = 0; i < orderItems.size(); i++){
               orderTotal += orderItems.get(i).getItemPrice();
            }
        }
        
        return orderTotal;
    }
    
    public void addMenuItem(MenuItem menuItem){
        orderItems.add(menuItem);
    }

    public UUID getOrderID() {
        return orderID;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
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
    public String toString(){
        String toString = "----------------------------------------------------------- \n";
        
        toString += this.orderID + "\n" + this.customerName + "\n" + this.customerPhoneNumber + "\n" + this.customerAddress + "\n" + "\n";
        
        
        for(int i = 0; i<orderItems.size(); i++){
            toString += orderItems.get(i).toString() + "\n" + "\n";
        }
        
        toString += "Total: " + this.getOrderTotal();
        return toString;
    }  
    
}
