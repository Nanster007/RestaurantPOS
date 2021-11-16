/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author tylar
 */
public class Employee implements Serializable{
    
    private String name, phoneNumber;
    private double payRate;
    private UUID orderID;
    private int pin;
    
    public Employee (String name, String phoneNumber, double payRate, int pin){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.payRate = payRate;
        this.pin = pin;
    }

    public void setPin(int pin){
        this.pin = pin;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }
    
    
}
