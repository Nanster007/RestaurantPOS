/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author tylar
 */
public class MenuItem {
    
    private String itemName;
    private double itemPrice;
    private String itemType;
    private UUID itemID;
    private ArrayList<Topping> itemToppings;
    private double toppingsPrice;
    private String comments;
    
    public MenuItem(String itemName, double itemPrice, double toppingsPrice, String comments, String itemType){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemID = UUID.randomUUID();
        this.itemToppings = new ArrayList();
        this.toppingsPrice = toppingsPrice;
        this.comments = comments;
        this.itemType = itemType;
    }
    
    public double getItemPrice() {
        
        for(int i = 0; i < itemToppings.size(); i++){
           toppingsPrice += itemToppings.get(i).getToppingPrice(); 
        }
        
        return itemPrice + toppingsPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public void addTopping(Topping topping){
        itemToppings.add(topping);
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public UUID getItemID() {
        return itemID;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public ArrayList<Topping> getItemToppings() {
        return itemToppings;
    }
    
    @Override
    public String toString(){
        String toString = itemPrice + " \n" + itemName + "\n" + comments;
        
        return toString;
    }
    
    
    
    
}
