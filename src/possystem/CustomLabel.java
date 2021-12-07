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
    
    //boolean determining whether this label has been selected
    private Boolean highlighted;
    
    //these labels are present on the schedulingCalendar Panel
    private final EditScheduleCalendar schedulingCalendar;
    
    //custom implementation to adjust actionListener to highlight label upon selection
    public CustomLabel (String text, EditScheduleCalendar schedulingCalendar){
        //create label regularly with day of month as its text
        super(text);       
        //bigger font
        this.setFont(new Font("sansserif", 1, 30));
        //align text to top of label
        this.setVerticalAlignment(JLabel.TOP);
        
        //save the panel it belongs to
        this.schedulingCalendar = schedulingCalendar;
        
        //initialize label to not highlighted
        this.highlighted = false;
        
        //add mouse listener to enable highlighting selected labels
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){                    
                if(!getText().isBlank()){
                    highlightDay();
                } 
            }
        });
    }
    
    //return if currently highlighted
    public Boolean getHighlighted(){
        return highlighted;
    }
    
    //function highlights day when label is clicked and unhighlights if clicked again
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
    
