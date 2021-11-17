/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author tylar
 */
public class CustomLabel extends JLabel{
    
    private final MainFrame mainFrame;
    private Boolean highlighted;
    private EditScheduleCalendar schedulingCalendar;
    
    //customLabels displayed on edit schedule panel - custom implementation for highlighting selections
    //I almost definitely shouldve used toggle buttons but i discovered those later
    //recieves its calendar day of the month label 'text', the calendar graphic it belongs to 'editschedulingcalendar' and mainframe
    public CustomLabel (String text, EditScheduleCalendar schedulingCalendar, MainFrame mainFrame){
        //create label regularly with day of month 'text'
        super(text);
        
        //bigger text
        this.setFont(new Font("sansserif", 1, 30));
        
        //arbitrary
        this.mainFrame = mainFrame;
        
        //save calendar graphic 'schedulingCalendar'
        this.schedulingCalendar = schedulingCalendar;
        
        //initialize to not highlighted
        this.highlighted = false;
        
        //align text to top of label
        this.setVerticalAlignment(JLabel.TOP);
        
        //add mouse listener to enable highlighting selected days
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){                    
                if(!getText().isBlank()){
                    highlightDay();
                } 
            }
        });
    }
    
    //return if highlighted
    public Boolean getHighlighted(){
        return highlighted;
    }
    
    //function highlights day when label is clicked
    public void highlightDay(){
        //if not currently highlighted when clicked, do this
        if(highlighted == false){
            //set border to thicker and red
            this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            //set text color to red
            this.setForeground(Color.red);
            //set highlighted boolean to true
            this.highlighted = true;
            //add this day of the month to the currently selected days list
            schedulingCalendar.addSelectedDay(getText());
        }
        //button was highlighted/selected already when clicked
        else{
            //set boolean 'highlighted' to false
            this.highlighted = false;
            //set text color to original color
            this.setForeground(null);
            //set border back to normal
            this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            //remove this day of the month from currently selected days list
            schedulingCalendar.removeSelectedDay(getText());
        }
        
        
    }
}
    
