package possystem;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author tylar
 */
//Panel contains 42 customLabels making up the calendar graphic in 'edit schedule' option
public class EditScheduleCalendar extends JPanel {
    
    //mainFrame variable to obtain needed public variables
    private final MainFrame mainFrame;
    
    //array holding references to each of the 42 calendar labels
    private CustomLabel[] daysOfMonth;
    
    //gridlayout allows simple, even spacing of labels
    private final GridLayout layout;
    
    //list to hold the dates of each label selected
    private final ArrayList<Date> selectedDays;
    
    //calendar variable necessary for calendar alignment with respect to current date
    private final Calendar calendar;
    
    public EditScheduleCalendar (MainFrame mainFrame, Calendar calendar){
        super();
        this.mainFrame = mainFrame;
        this.calendar = calendar;
        this.selectedDays = new ArrayList();
        
        //allows 6x7 calendar grid layout
        this.layout = new GridLayout(6, 7);
        
        //various adjustments to layout element
        layout.setVgap(0);
        layout.setHgap(0);
        //apply gridlayout to panel
        this.setLayout(layout);
        
        createDayLabels();
    }
    
    //creates all 42 labels making up the calendar graphic
    //labels them with respect to the day of the week the month begins, and how many days total in the month (aligning correct days with their day of the week)
    private void createDayLabels(){
        daysOfMonth = new CustomLabel[42];
        int index = 1;

        for(int x=0; x<42; x++){
            if(index <= mainFrame.daysPerMonth[getMonth()] && x >= getDay()-1){
                daysOfMonth[x] = new CustomLabel("" + (index), this);
                index++;
            }
            else{
                daysOfMonth[x] = new CustomLabel("", this);
            }
            this.add(daysOfMonth[x]);
            daysOfMonth[x].setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }

    }
    
    //allows for reseting of highlighted days after submitting the shift
    public void clearSelectedDays(){
        selectedDays.clear();
        for (CustomLabel daysOfMonth1 : daysOfMonth) {
            if (daysOfMonth1.getHighlighted()) {
                daysOfMonth1.highlightDay();
            }
        }
    }
    
    //called when a day is selected on the calendar graphic
    //getMonth and getYear always return month and year of the currently DISPLAYED month and year, 'text' string pulled from getText of the label clicked
    public void addSelectedDay(String text){    
        
        selectedDays.add(new Date(getYear(), getMonth(), Integer.parseInt(text)));
    }
    public void removeSelectedDay(String text){
        for(int x=0; x<selectedDays.size(); x++){
            if(selectedDays.get(x).getDay() == Integer.parseInt(text)){
                selectedDays.remove(x);
            }
        }
    }
    
    //various getters+setters for object
    private int getYear(){
        return calendar.get(Calendar.YEAR);
    }
    
    private int getMonth(){
        return calendar.get(Calendar.MONTH);
    }
    
    private int getDay(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public ArrayList<Date> getSelectedDays(){
        return selectedDays;
    }
}
