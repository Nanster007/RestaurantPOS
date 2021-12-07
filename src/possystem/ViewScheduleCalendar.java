/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author tylar
 */
public class ViewScheduleCalendar extends JPanel {
    
    private final MainFrame mainFrame;
    
    //array of calendar textareas for each spot on calendar
    //textareas display all shifts on their day
    private JTextArea[] daysOfMonth;
    private JScrollPane[] scrollPanes;
    
    //gridlayout ensures even spacing for calendar spots
    private final GridLayout layout;
    
    //calendar used to generate graphics with respect to current month
    private final Calendar calendar;
    
    public ViewScheduleCalendar (MainFrame mainFrame, Calendar calendar) throws IOException, FileNotFoundException, ClassNotFoundException{
        super();
        this.mainFrame = mainFrame;
        this.calendar = calendar;
        
        //set gridlayout for calendar table 6x7
        this.layout = new GridLayout(6, 7);
        
        //to remove padding
        layout.setVgap(0);
        layout.setHgap(0);
        
        //add 6x7 layout to this panel
        this.setLayout(layout);
        
        //create the text areas for each layout spot
        createDayLabels();
    }
    
    //creates 42 Textareas (which is the 6x7 grid) 
    private void createDayLabels() throws IOException, FileNotFoundException, ClassNotFoundException{
        daysOfMonth = new JTextArea[42];
        scrollPanes = new JScrollPane[42];
        
        //index tracks what day of the month the label is
        int index = 1;
        for(int x=0; x<42; x++){
            //first boolean to ensure labeling stops when current month has no more days in it (ex: stop labeling at 28 for February)
            //second boolean ensures it doesnt label the blank days on the calendar BEFORE the first of the month
            if(index <= mainFrame.daysPerMonth[getMonth()] && x >= getDay()-1){
                daysOfMonth[x] = new JTextArea("" + index + "\n" + mainFrame.getEmployeesShiftsOfDay(getMonth(), getYear(), index, mainFrame.getCurrentUser()));
                scrollPanes[x] = new JScrollPane(daysOfMonth[x]);
                
                index++;
            }
            //dont label if didnt pass check
            else{
                daysOfMonth[x] = new JTextArea();
                scrollPanes[x] = new JScrollPane(daysOfMonth[x]);
            }
            //add newly created label to panel
            this.add(scrollPanes[x]);
            //set border and non editable
            daysOfMonth[x].setBorder(BorderFactory.createLineBorder(Color.black));
            daysOfMonth[x].setEditable(false);
            daysOfMonth[x].setFont(new Font("sansserif", 1, 10));
        }

    }
    
    //changes month for calendar scrolling
    public void changeMonth(){
        
        int index = 1;
        
        //checks for leap years
        if(getYear()%4 == 0){
            mainFrame.daysPerMonth[1] = 29;
        }
        else{
            mainFrame.daysPerMonth[1] = 28;
        }
        
        //relabel 42 text areas to new month's parameters (see createDayLabels()'s loop for more info on index variable)
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
    
    
    //return date info
    
    private int getYear(){
        return calendar.get(Calendar.YEAR);
    }
    
    private int getMonth(){
        return calendar.get(Calendar.MONTH);
    }
    
    private int getDay(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
}
