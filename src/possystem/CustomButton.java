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
    
    //custom toggle button to adjust actionListener to manually unselect after clicking
    public CustomButton(String text, LoginPanel loginPanel){
        //create regulag toggle button with label == text
        super(text);
        
        //these buttons are present on the loginPanel
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
    //each button press calls respective function in loginPanel according to button's label
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
