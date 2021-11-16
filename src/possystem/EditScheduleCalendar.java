/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EditScheduleCalendar extends JPanel {
    
    private final MainFrame mainFrame;
    private CustomLabel[] daysOfMonth;
    private GridLayout layout;
    private ArrayList<Date> selectedDays;
    private Calendar calendar;
    
    public EditScheduleCalendar (MainFrame mainFrame, Calendar calendar){
        super();
        this.mainFrame = mainFrame;
        this.layout = new GridLayout(6, 7);
        this.calendar = calendar;
        selectedDays = new ArrayList();
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        createDayLabels();
    }
    
    private void createDayLabels(){
        daysOfMonth = new CustomLabel[42];
        int index = 1;
        
        System.out.println(getMonth());
        for(int x=0; x<42; x++){
            if(index <= mainFrame.daysPerMonth[getMonth()] && x >= getDay()-1){
                daysOfMonth[x] = new CustomLabel("" + (index), this, mainFrame);
                index++;
            }
            else{
                daysOfMonth[x] = new CustomLabel("", this, mainFrame);
            }
            this.add(daysOfMonth[x]);
//            daysOfMonth[x].setLocation(x%7*width/7+30, x/7*height/5+30);
            daysOfMonth[x].setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }

    }
    
    public void clearSelectedDays(){
        selectedDays.clear();
        for(int x=0; x<daysOfMonth.length; x++){
            if(daysOfMonth[x].getHighlighted()){
                daysOfMonth[x].highlightDay();
            }
        }
    }
    
    private int getYear(){
        return calendar.get(Calendar.YEAR);
    }
    
    private int getMonth(){
        return calendar.get(Calendar.MONTH);
    }
    
    private int getDay(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
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
    
    public ArrayList<Date> getSelectedDays(){
        return selectedDays;
    }

    private void changeMonth(){
        
        int index = 1;
        
        if(getYear()%4 == 0){
            mainFrame.daysPerMonth[1] = 29;
        }
        else{
            mainFrame.daysPerMonth[1] = 28;
        }
        
        for(int x=0; x<42; x++){
            if(index <= mainFrame.daysPerMonth[getMonth()] && x >= getDay()){
                daysOfMonth[x].setText("" + (index));
                index++;
            }
            else{
                daysOfMonth[x].setText("");
            }
        }
    }
}
