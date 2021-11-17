/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

/**
 *
 * @author tylar
 */
public class CustomButton extends JToggleButton{
    
    private final LoginPanel loginPanel;
    
    //custom toggle button allows for continuous number entry without having to manually toggle off after each numberClicked
    public CustomButton(String text, LoginPanel loginPanel){
        //create regulag toggle button with label =text
        super(text);
        
        this.loginPanel = loginPanel;
        //bigger font
        this.setFont(new Font("sansserif", 1, 30));
        
        //each button gets passed a label, set the function that is called when this button is clicked accordingly to label's text
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (getText()) {
                    //"Delete" button calls delete function
                    case "Delete" -> deleteClicked();
                    //"Clear" button calls clear function
                    case "Clear" -> clearClicked();
                    //number buttons call number clicked function
                    default -> numberClicked();
                }
            }
        });
    }
    
    //.getButtonGroup().clearSelection(); disables toggle for continous selections
    //each button press calls respective function in loginPanel
    public void numberClicked(){
        loginPanel.getButtonGroup().clearSelection();
        loginPanel.numberClicked(getText());
    }
    
    public void deleteClicked(){
        loginPanel.getButtonGroup().clearSelection();
        loginPanel.deleteEntry();
    }
    
    public void clearClicked(){
        loginPanel.getButtonGroup().clearSelection();
        loginPanel.clearEntry();
    }
    
}
