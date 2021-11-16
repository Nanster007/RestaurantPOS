/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author tylar
 */
public class CustomLabel extends JLabel{
    
    private final MainFrame mainFrame;
    private Boolean highlighted;
    private SchedulingCalendar schedulingCalendar;
    
    public CustomLabel (String text, SchedulingCalendar schedulingCalendar, MainFrame mainFrame){
        super(text);
        this.mainFrame = mainFrame;
        this.schedulingCalendar = schedulingCalendar;
        this.highlighted = false;
        this.setVerticalAlignment(JLabel.TOP);
            this.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){                    
                    if(!getText().isBlank()){
                        highlightDay();
                    } 
                }
            });
    }
    
    public Boolean getHighlighted(){
        return highlighted;
    }
    
    public void highlightDay(){
        if(highlighted == false){
            this.setBorder(BorderFactory.createLineBorder(Color.red));
            this.highlighted = true;
            schedulingCalendar.addSelectedDay(getText());
        }
        else{
            this.highlighted = false;
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            schedulingCalendar.removeSelectedDay(getText());
        }
        
        
    }
}
    
