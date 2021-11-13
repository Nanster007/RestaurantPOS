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
    
    private Employee employee;
    private Date clockStart, clockEnd, setStart, setEnd;
    
    public Shift (Employee employee, Date setStart, Date setEnd){
        this.employee = employee;
        this.setStart = setStart;
        this.setEnd = setEnd;
        this.clockStart = null;
        this.clockEnd = null;
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
            string += clockEnd + "\n";
        }
        
        string += "\n";
        
        
        return string;
    }
    
}
