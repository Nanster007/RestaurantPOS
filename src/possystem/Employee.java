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
public class Employee implements Serializable{
    
    String name, phoneNumber;
    double payRate;
    
    public Employee (String name, String phoneNumber, double payRate){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.payRate = payRate;
    }
    
}
