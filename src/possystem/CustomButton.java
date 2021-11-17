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
    
    public CustomButton(String text, LoginPanel loginPanel){
        super(text);
        this.loginPanel = loginPanel;
        this.setFont(new Font("sansserif", 1, 30));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (getText()) {
                    case "Delete" -> deleteClicked();
                    case "Clear" -> clearClicked();
                    default -> numberClicked();
                }
            }
        });
    }
    
    public void numberClicked(){
        loginPanel.getButtonGroup().clearSelection();
        loginPanel.selection(getText());
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
