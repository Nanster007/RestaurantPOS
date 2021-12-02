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
public class Shift implements Serializable{
    
    //employee and time variables for each shifts
    private Employee employee;
    private Date clockStart, clockEnd, setStart, setEnd;
    private boolean shiftCompleted;
    
    //basic constructor
    public Shift (Employee employee, Date setStart, Date setEnd){
        this.employee = employee;
        this.setStart = setStart;
        this.setEnd = setEnd;
        this.clockStart = new Date();
        this.clockEnd = new Date();
        this.shiftCompleted = false;
    }
    
    //likely a gross overcomplication of a formatted output of the shift
    public String formattedShift(){
        String string = "";
        
        string += this.employee.getName() + " ";
 
        if(this.getSetStart().getHours()>11){
            if(this.getSetStart().getHours() == 12){
                string += this.getSetStart().getHours() + ":" + this.getSetStart().getMinutes(); 
            }
            else{
                string += this.getSetStart().getHours()-12 + ":" + this.getSetStart().getMinutes();
            }
            string += "PM";
        }
        else{
            if(this.getSetStart().getHours() == 0){
                string += "12" + ":" + this.getSetStart().getMinutes();
            }
            else{
                string += this.getSetStart().getHours() + ":" + this.getSetStart().getMinutes();
            }
            string += "AM";
        }
        
        string += "-";
        
        if(this.getSetEnd().getHours()>11){
            if(this.getSetEnd().getHours() == 12){
                string += this.getSetEnd().getHours() + ":" + this.getSetEnd().getMinutes();
            }
            else{
                string += this.getSetEnd().getHours()-12 + ":" + this.getSetEnd().getMinutes();
            }          
            string += "PM";
        }
        
        else{
            if(this.getSetEnd().getHours() == 0){
                string += "12" + ":" + this.getSetEnd().getMinutes();
            }
            else{
                string += this.getSetEnd().getHours() + ":" + this.getSetEnd().getMinutes();
            }            
            string += "AM";
        }
        
        return string;
    }
    
    
    //various getters and setters for shift info

    public boolean getShiftCompleted() {
        return shiftCompleted;
    }

    public void setShiftCompleted(boolean shiftCompleted) {
        this.shiftCompleted = shiftCompleted;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getClockStart() {
        return clockStart;
    }

    public void setClockStart(Date clockStart) {
        this.clockStart = clockStart;
    }

    public Date getClockEnd() {
        return clockEnd;
    }

    public void setClockEnd(Date clockEnd) {
        this.clockEnd = clockEnd;
    }

    public Date getSetStart() {
        return setStart;
    }

    public void setSetStart(Date setStart) {
        this.setStart = setStart;
    }

    public Date getSetEnd() {
        return setEnd;
    }

    public void setSetEnd(Date setEnd) {
        this.setEnd = setEnd;
    }
    
    //basic toString for file writing and reading
    @Override
    public String toString(){
        String string = "";
        
        string += this.employee.getName() + "\n";
        string += this.setStart + "\n";
        
        if(setEnd == null){
            string += "to \n" + "TBD \n";
        }
        else{
            string += "to \n" + this.setEnd + "\n";
        }
        
        if(clockStart != null){
            string += clockStart + "\n";
        }
        if(clockEnd != null){
            string += clockEnd;
        }
        
        
        return string;
    }
    
}
