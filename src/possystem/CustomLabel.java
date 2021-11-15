/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author tylar
 */
public class CustomLabel extends JLabel{
    
    private SchedulingPanel schedulingPanel;
    private MainFrame mainFrame;
    private EditSchedulePanel editSchedulePanel;
    private Boolean highlighted;
    
    public CustomLabel (String text, SchedulingPanel schedulingPanel, EditSchedulePanel editSchedulePanel, MainFrame mainFrame){
        super(text);
        this.mainFrame = mainFrame;
        this.highlighted = false;
        this.setVerticalAlignment(JLabel.TOP);
        if(editSchedulePanel == null){
            this.setFont(new Font("Serif", Font.BOLD, 20));
            this.schedulingPanel = schedulingPanel;
            this.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e){
                    try {
                        mainFrame.setNewPanel(new EditSchedulePanel(mainFrame, schedulingPanel, Integer.parseInt(getText())), true, schedulingPanel);
                    } catch (IOException ex) {
                        Logger.getLogger(CustomLabel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CustomLabel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        else{
            this.schedulingPanel = schedulingPanel;
            this.editSchedulePanel = editSchedulePanel;
            this.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){                    
                    highlightDay();
                }
            });
        }
    }
    
    public Boolean getHighlighted(){
        return highlighted;
    }
    
    public void highlightDay(){
        if(highlighted == false){
            this.setBorder(BorderFactory.createLineBorder(Color.red));
            this.highlighted = true;
            editSchedulePanel.getSchedulingCalendar().addSelectedDay(getText());
        }
        else{
            this.highlighted = false;
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            editSchedulePanel.getSchedulingCalendar().removeSelectedDay(getText());
        }
        
        
    }
}
    
