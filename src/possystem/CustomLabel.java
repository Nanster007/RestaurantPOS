/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author tylar
 */
public class CustomLabel extends JLabel{
    
    private SchedulingPanel schedulingPanel;
    private MainFrame mainFrame;
    
    public CustomLabel (String text, SchedulingPanel schedulingPanel, MainFrame mainFrame){
        super(text);
        this.mainFrame = mainFrame;
        this.schedulingPanel = schedulingPanel;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                mainFrame.setNewPanel(new EditSchedulePanel(mainFrame, schedulingPanel, Integer.parseInt(getText())), true, schedulingPanel);
            }
            
        });
    }
    
}
