/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
}
