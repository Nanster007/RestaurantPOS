/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tylar
 */
public class SchedulingCalendar extends JPanel {
    
    MainFrame mainFrame;
    private int height, width;
    private int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private CustomLabel[] daysOfMonth;
    private SchedulingPanel schedulingPanel;
    private EditSchedulePanel editSchedulePanel;
    private GridLayout layout;
    private ArrayList<Date> selectedDays;
    
    public SchedulingCalendar (MainFrame mainFrame, SchedulingPanel schedulingPanel, EditSchedulePanel editSchedulePanel){
        super();
        this.mainFrame = mainFrame;
        this.layout = new GridLayout(6, 7);
        selectedDays = new ArrayList();
        this.height = schedulingPanel.getCalendarPanelHeight();
        this.width = schedulingPanel.getCalendarPanelWidth();
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.schedulingPanel = schedulingPanel;
        this.editSchedulePanel = editSchedulePanel;
        createDayLabels();
    }
    
    private void createDayLabels(){
        daysOfMonth = new CustomLabel[42];
        int index = 1;
        
        for(int x=0; x<42; x++){
            if(index <= daysPerMonth[schedulingPanel.getCurrentMonth()] && x >= schedulingPanel.getFirstDay()){
                daysOfMonth[x] = new CustomLabel("" + (index), schedulingPanel, editSchedulePanel, mainFrame);
                index++;
            }
            else{
                daysOfMonth[x] = new CustomLabel("", schedulingPanel, editSchedulePanel, mainFrame);
            }
            this.add(daysOfMonth[x]);
//            daysOfMonth[x].setLocation(x%7*width/7+30, x/7*height/5+30);
            daysOfMonth[x].setBorder(BorderFactory.createLineBorder(Color.black));
        }

    }
    
    public void clearSelectedDays(){
        selectedDays.clear();
        for(int x=0; x<daysOfMonth.length && daysOfMonth[x].getHighlighted(); x++){
            daysOfMonth[x].highlightDay();
        }
    }
    
    public void addSelectedDay(String text){    
        
        selectedDays.add(new Date(schedulingPanel.getCurrentYear(), schedulingPanel.getCurrentMonth(), Integer.parseInt(text)));
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

    public void changeMonth(){
        
        int index = 1;
        
        if(schedulingPanel.getCurrentYear()%4 == 0){
            daysPerMonth[1] = 29;
        }
        else{
            daysPerMonth[1] = 28;
        }
        
        for(int x=0; x<42; x++){
            if(index <= daysPerMonth[schedulingPanel.getCurrentMonth()] && x >= schedulingPanel.getFirstDay()){
                daysOfMonth[x].setText("" + (index));
                index++;
            }
            else{
                daysOfMonth[x].setText("");
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //resize calendar
//        if(editSchedulePanel == null){
//            this.width = schedulingPanel.getCalendarPanelWidth();
//            this.height = schedulingPanel.getCalendarPanelHeight();
//        }
//        
//        this.setSize(width, height);        
//        g.setColor(Color.black);
//        
//        //draw main box
//        g.drawLine(0, 0, width, 0);
//        g.drawLine(0, 0, 0, height);
//        g.drawLine(width-1, height-1, width-1, 0);
//        g.drawLine(width-1, height-1, 0, height-1);
//        
//        //draw inside vertical lines
//        for(int x=1; x<7; x++){
//            g.drawLine(width/7*x, 0, width/7*x, height);
//        }
//        //draw inside horizontal lines
//        for(int x=1; x<6; x++){
//            g.drawLine(0, height/6*x, width, height/6*x);
//        }
    }
    
}
