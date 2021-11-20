/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author tylar
 */
public class Employee implements Serializable{
    
    //various info points for each employee
    private String name, phoneNumber;
    private double payRate;
    private int pin;
    private boolean clockedIn;
    private Date lastClock;
    
    //basic constructor
    public Employee (String name, String phoneNumber, double payRate, int pin){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.payRate = payRate;
        this.pin = pin;
        this.clockedIn = false;
        this.lastClock = new Date();
    }

    
    //various getters and settings for employee info

    public boolean getClockedIn() {
        return this.clockedIn;
    }
    
    public void clockOut(Date date){
        this.clockedIn = false;
        this.lastClock = date;
    }

    public void clockIn(Date date) {
        this.clockedIn = true;
        this.lastClock = date;
    }

    public Date getLastClock() {
        return this.lastClock;
    }    
    
    public void setPin(int pin){
        this.pin = pin;
    }
    
    public int getPin(){
        return this.pin;
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
