/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tylar
 */

//CustomPanel class allows for clock thread to be referenced continously in each new panel
public class CustomPanel extends JPanel {
    
    //each panel must have a clock field to display the clock thread
    private JTextField clockField;
 
    public CustomPanel(){
        super();
        clockField = new javax.swing.JTextField();
    }
    
    public void setClock(String time){
        clockField.setText(time);
    }
    
    public JTextField getClockField() {
        return clockField;
    }

    public void setClockField(JTextField clockField) {
        this.clockField = clockField;
        clockField.setEditable(false);
    }
   
}
