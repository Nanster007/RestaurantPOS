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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tylar
 */
public class EditSchedulePanel extends CustomPanel {

    private MainFrame mainFrame;
    private SchedulingPanel schedulingPanel;
    private final SchedulingCalendar schedulingCalendar;
    private Calendar calendar;
    private int day;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private ArrayList<Shift> shifts;
    private DefaultListModel listModel;
    private Shift selectedShift;
    
    public EditSchedulePanel(MainFrame mainFrame, SchedulingPanel schedulingPanel, int day) throws IOException, FileNotFoundException, ClassNotFoundException {
        initComponents();
        this.mainFrame = mainFrame;
        this.day = day;
        this.schedulingPanel = schedulingPanel;
        setClockField(ClockLabel);
        calendar = Calendar.getInstance();
        YearLabel.setText("" + schedulingPanel.getCurrentYear());
        DateLabel.setText(months[schedulingPanel.getCurrentMonth()] + ", " + day);
        shifts = mainFrame.getShifts(schedulingPanel.getCurrentMonth(), schedulingPanel.getCurrentYear());
        updateInterface();
        this.schedulingCalendar = new SchedulingCalendar(mainFrame,schedulingPanel, this);
        CalendarPanel.add(schedulingCalendar);
        CalendarPanel.setLayout(new GridLayout(1,1));
        setSpinners();
        
        LabelsPanel.setLayout(new GridLayout(1, 7));
        for(int x=0; x<7; x++){
            LabelsPanel.add(new JLabel(daysOfWeek[x], SwingConstants.CENTER));
        }
        updateInterface();
    }
    
    public int getYear(){
        return Integer.parseInt(YearLabel.getText());
    }
    
    public int getMonth(){
        return Integer.parseInt(DateLabel.getText());
    }
    
    public SchedulingCalendar getSchedulingCalendar(){
        return schedulingCalendar;
    }
    
    private void updateInterface() throws IOException, FileNotFoundException, ClassNotFoundException{
        
        shifts = mainFrame.getShifts(schedulingPanel.getCurrentMonth(), schedulingPanel.getCurrentYear());
        listModel = new DefaultListModel();
            for(int x=0; x<shifts.size(); x++){
                listModel.addElement(shifts.get(x));
            }
        ShiftsList = new JList(listModel);
        ListScrollPane.setViewportView(ShiftsList);
        ShiftsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
//        ShiftsList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                try {
//                    selectedShift = ShiftsList.getSelectedValue();
//                } catch (IOException ex) {
//                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                NameField.setText(selectedEmployee.getName());
//                NumberField.setText(selectedEmployee.getPhoneNumber());
//                PayrateField.setText("" + selectedEmployee.getPayRate());
//            }
//            
//        });
    }
    
    private void setSpinners() throws IOException, FileNotFoundException, ClassNotFoundException{
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 12, 1);
        StartHour.setModel(model);
        model = new SpinnerNumberModel(1, 1, 12, 1);
        EndHour.setModel(model);
        model = new SpinnerNumberModel(00, 00, 45, 15);
        StartMinute.setModel(model);
        model = new SpinnerNumberModel(00, 00, 45, 15);
        EndMinute.setModel(model);
        String[] array = {"AM", "PM"};
        SpinnerListModel model2 = new SpinnerListModel(array);
        StartPhase.setModel(model2);
        model2 = new SpinnerListModel(array);
        EndPhase.setModel(model2);
        model2 = new SpinnerListModel(mainFrame.getEmployeeNames());
        EmployeeSpinner.setModel(model2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CurrentUserLabel = new javax.swing.JLabel();
        ClockLabel = new javax.swing.JTextField();
        YearLabel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        ShiftInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ListScrollPane = new javax.swing.JScrollPane();
        ShiftsList = new javax.swing.JList<>();
        AddShiftPanel = new javax.swing.JPanel();
        AddShiftLabel = new javax.swing.JLabel();
        EmployeeSpinner = new javax.swing.JSpinner();
        EmployeeLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        StartLabel = new javax.swing.JLabel();
        StartHour = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        StartMinute = new javax.swing.JSpinner();
        StartPhase = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        StartLabel2 = new javax.swing.JLabel();
        EndHour = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        EndMinute = new javax.swing.JSpinner();
        EndPhase = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        CalendarPanel = new javax.swing.JPanel();
        LabelsPanel = new javax.swing.JPanel();
        CreateShiftButton = new javax.swing.JButton();
        NextDayButton = new javax.swing.JButton();
        PreviousDayButton = new javax.swing.JButton();

        CurrentUserLabel.setText("Welcome: User's Name");

        ClockLabel.setText("jTextField1");
        ClockLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClockLabelActionPerformed(evt);
            }
        });

        YearLabel.setText("jLabel1");

        DateLabel.setText("jLabel1");

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Day's Shifts:");

        ShiftsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListScrollPane.setViewportView(ShiftsList);

        AddShiftLabel.setText("Add Shift");

        EmployeeLabel.setText("Employee:");

        StartLabel.setText("Start Time:");

        jLabel4.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StartLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(StartHour, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StartMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(StartPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(StartLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StartHour)
                    .addComponent(StartPhase)
                    .addComponent(StartMinute)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        StartLabel2.setText("End Time");

        jLabel7.setText(":");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EndHour)
                    .addComponent(StartLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EndMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EndPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(StartLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EndPhase, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EndHour)
                    .addComponent(EndMinute)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout CalendarPanelLayout = new javax.swing.GroupLayout(CalendarPanel);
        CalendarPanel.setLayout(CalendarPanelLayout);
        CalendarPanelLayout.setHorizontalGroup(
            CalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CalendarPanelLayout.setVerticalGroup(
            CalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout LabelsPanelLayout = new javax.swing.GroupLayout(LabelsPanel);
        LabelsPanel.setLayout(LabelsPanelLayout);
        LabelsPanelLayout.setHorizontalGroup(
            LabelsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        LabelsPanelLayout.setVerticalGroup(
            LabelsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CalendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(LabelsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CalendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CreateShiftButton.setText("Submit Shift");
        CreateShiftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateShiftButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddShiftPanelLayout = new javax.swing.GroupLayout(AddShiftPanel);
        AddShiftPanel.setLayout(AddShiftPanelLayout);
        AddShiftPanelLayout.setHorizontalGroup(
            AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AddShiftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddShiftPanelLayout.createSequentialGroup()
                        .addGroup(AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmployeeLabel)
                            .addComponent(EmployeeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddShiftPanelLayout.createSequentialGroup()
                        .addComponent(AddShiftLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddShiftPanelLayout.setVerticalGroup(
            AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddShiftPanelLayout.createSequentialGroup()
                .addGroup(AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddShiftLabel)
                    .addComponent(CreateShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddShiftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddShiftPanelLayout.createSequentialGroup()
                        .addComponent(EmployeeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmployeeSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NextDayButton.setText("Next Day");
        NextDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextDayButtonActionPerformed(evt);
            }
        });

        PreviousDayButton.setText("Previous Day");
        PreviousDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousDayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShiftInfoPanelLayout = new javax.swing.GroupLayout(ShiftInfoPanel);
        ShiftInfoPanel.setLayout(ShiftInfoPanelLayout);
        ShiftInfoPanelLayout.setHorizontalGroup(
            ShiftInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShiftInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ShiftInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShiftInfoPanelLayout.createSequentialGroup()
                        .addComponent(PreviousDayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NextDayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(ListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddShiftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShiftInfoPanelLayout.setVerticalGroup(
            ShiftInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShiftInfoPanelLayout.createSequentialGroup()
                .addGroup(ShiftInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextDayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreviousDayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
            .addComponent(AddShiftPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ShiftInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(YearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CurrentUserLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CurrentUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(YearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShiftInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ClockLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClockLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClockLabelActionPerformed

    private void PreviousDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousDayButtonActionPerformed
        if(schedulingPanel.getCurrentYear()%4 == 0){
            daysPerMonth[1] = 29;
        }
        else{
            daysPerMonth[1] = 28;
        }
        
        if(day > 1){
            day--;
            schedulingPanel.setDate(new Date(schedulingPanel.getCurrentYear()-1900, schedulingPanel.getCurrentMonth(), day));
        }
        else{          
            if(schedulingPanel.getCurrentMonth() == 0){ 
                day = daysPerMonth[11];
                schedulingPanel.setDate(new Date((schedulingPanel.getCurrentYear()-1901), 11, day));
            }
            else{
                day = daysPerMonth[schedulingPanel.getCurrentMonth()-1];
                schedulingPanel.setDate(new Date((schedulingPanel.getCurrentYear()-1900), schedulingPanel.getCurrentMonth()-1, day));
            }
            try {
                shifts = mainFrame.getShifts(schedulingPanel.getCurrentMonth(), schedulingPanel.getCurrentYear());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            mainFrame.setNewPanel(new EditSchedulePanel(mainFrame, schedulingPanel, day), Boolean.FALSE, this);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_PreviousDayButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        mainFrame.setNewPanel(new SchedulingPanel(mainFrame), Boolean.FALSE, this);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NextDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextDayButtonActionPerformed
        if(schedulingPanel.getCurrentYear()%4 == 0){
            daysPerMonth[1] = 29;
        }
        else{
            daysPerMonth[1] = 28;
        }
        
        if(day < daysPerMonth[schedulingPanel.getCurrentMonth()]){
            day++;
            schedulingPanel.setDate(new Date((schedulingPanel.getCurrentYear()-1900), schedulingPanel.getCurrentMonth(), day));
        }
        else{
            day=1;
            if(schedulingPanel.getCurrentMonth() == 11){
                schedulingPanel.setDate(new Date((schedulingPanel.getCurrentYear()-1899), 0, 1));
            }
            else{
                schedulingPanel.setDate(new Date((schedulingPanel.getCurrentYear()-1900), schedulingPanel.getCurrentMonth()+1, 1));
            }
            try {
                shifts = mainFrame.getShifts(schedulingPanel.getCurrentMonth(), schedulingPanel.getCurrentYear());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            mainFrame.setNewPanel(new EditSchedulePanel(mainFrame, schedulingPanel, day), Boolean.FALSE, this);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NextDayButtonActionPerformed

    private void CreateShiftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateShiftButtonActionPerformed
        try {
            Employee employee = mainFrame.findEmployee((String)EmployeeSpinner.getValue());
            Date startDate = new Date(Integer.parseInt(YearLabel.getText()), schedulingPanel.getCurrentMonth(), day, (Integer)StartHour.getValue(), (Integer)StartMinute.getValue(), 0);
            Date endDate = new Date(Integer.parseInt(YearLabel.getText()), schedulingPanel.getCurrentMonth(), day, (Integer)EndHour.getValue(), (Integer)EndMinute.getValue(), 0);  
            ArrayList<Date> selectedDays = schedulingCalendar.getSelectedDays();
            for(int x=0; x<selectedDays.size(); x++){
                startDate.setDate(selectedDays.get(x).getDate());
                endDate.setDate(selectedDays.get(x).getDate());
                mainFrame.addShift(new Shift(employee, startDate, endDate));
                
            }
            schedulingCalendar.clearSelectedDays();
            
        } catch (IOException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            updateInterface();
        } catch (IOException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CreateShiftButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddShiftLabel;
    private javax.swing.JPanel AddShiftPanel;
    private javax.swing.JButton BackButton;
    private javax.swing.JPanel CalendarPanel;
    private javax.swing.JTextField ClockLabel;
    private javax.swing.JButton CreateShiftButton;
    private javax.swing.JLabel CurrentUserLabel;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel EmployeeLabel;
    private javax.swing.JSpinner EmployeeSpinner;
    private javax.swing.JSpinner EndHour;
    private javax.swing.JSpinner EndMinute;
    private javax.swing.JSpinner EndPhase;
    private javax.swing.JPanel LabelsPanel;
    private javax.swing.JScrollPane ListScrollPane;
    private javax.swing.JButton NextDayButton;
    private javax.swing.JButton PreviousDayButton;
    private javax.swing.JPanel ShiftInfoPanel;
    private javax.swing.JList<String> ShiftsList;
    private javax.swing.JSpinner StartHour;
    private javax.swing.JLabel StartLabel;
    private javax.swing.JLabel StartLabel2;
    private javax.swing.JSpinner StartMinute;
    private javax.swing.JSpinner StartPhase;
    private javax.swing.JLabel YearLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
