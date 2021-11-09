/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.Serializable;

/**
 *
 * @author tylar
 */
public class Topping implements Serializable{
    
    private String toppingName;
    private double toppingPrice;
    
    public Topping (String toppingName, double toppingPrice){
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }
    
    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(double toppingPrice) {
        this.toppingPrice = toppingPrice;
    }
    
    
    
}
