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
    private EditScheduleCalendar schedulingCalendar;
    
    public CustomLabel (String text, EditScheduleCalendar schedulingCalendar, MainFrame mainFrame){
        super(text);
        this.setFont(new Font("sansserif", 1, 30));
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
            this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            this.setForeground(Color.red);
            this.highlighted = true;
            schedulingCalendar.addSelectedDay(getText());
        }
        else{
            this.highlighted = false;
            this.setForeground(null);
            this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            schedulingCalendar.removeSelectedDay(getText());
        }
        
        
    }
}
    
