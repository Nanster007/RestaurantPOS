/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author tylar
 */
public class ViewScheduleCalendar extends JPanel {
    
    private final MainFrame mainFrame;
    private JTextArea[] daysOfMonth;
    private GridLayout layout;
    private Calendar calendar;
    
    public ViewScheduleCalendar (MainFrame mainFrame, Calendar calendar) throws IOException, FileNotFoundException, ClassNotFoundException{
        super();
        this.mainFrame = mainFrame;
        this.calendar = calendar;
        this.layout = new GridLayout(6, 7);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        createDayLabels();
    }
    
    private void createDayLabels() throws IOException, FileNotFoundException, ClassNotFoundException{
        daysOfMonth = new JTextArea[42];
        int index = 1;
        
        for(int x=0; x<42; x++){
            if(index <= mainFrame.daysPerMonth[getMonth()] && x >= getDay()-1){
                daysOfMonth[x] = new JTextArea("" + index + "\n" + mainFrame.getShiftsOfDay(getMonth(), getYear(), index));
                index++;
            }
            else{
                daysOfMonth[x] = new JTextArea();
            }
            this.add(daysOfMonth[x]);
//            daysOfMonth[x].setLocation(x%7*width/7+30, x/7*height/5+30);
            daysOfMonth[x].setBorder(BorderFactory.createLineBorder(Color.black));
            daysOfMonth[x].setEditable(false);
        }

    }
    
    public void changeMonth(){
        
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
