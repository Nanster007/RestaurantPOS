package possystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tylar
 */
//panel that is displayed upon manager account entering "adjust employee" panel
public class AdjustEmployeePanel extends CustomPanel {

    private final MainFrame mainFrame;
    
    //Jlist is visual element to display all current currentEmployees' names
    private JList EmployeesList;
    
    //buttongroup ensures only 1 selection may be made at a time with respect to manager or regular employee
    private final ButtonGroup buttonGroup;
    
    //list model ensures only one list item is chosen
    //also allows for the reading of the current selection
    private DefaultListModel employees;
    
    //selected employee object to display currentEmployees info when selected on JList and allow for editing of info
    private Employee selectedEmployee;
    
    
    public AdjustEmployeePanel(MainFrame mainFrame) throws IOException, FileNotFoundException, ClassNotFoundException {
        //standard panel initiation on every page
        initComponents();
        CurrentUserLabel.setText("Welcome: " + mainFrame.getCurrentUser().getName());
        setClockField(ClockLabel);              
        this.mainFrame = mainFrame;
        
        //reads and displays all current currentEmployees' names
        updateInterface();
        
        //set selected employee to first on list at initialization to read their info on both visual list element and user info visual elements
        selectedEmployee = mainFrame.findEmployee(employees.getElementAt(0).toString());
        EmployeesList.setSelectedIndex(0);
        
        //set employee info to newly^^selected currentEmployees information
        NameField.setText(selectedEmployee.getName());
        NumberField.setText(selectedEmployee.getPhoneNumber());
        PayrateField.setText("" + selectedEmployee.getPayRate()); 
        ShiftsField.setText(mainFrame.getEmployeesShiftsOfMonth(selectedEmployee).toString());
        
        //dont allow editing of these fields without first clicking 'edit info' button
        NameField.setEditable(false);
        PinField.setEditable(false);
        NumberField.setEditable(false);
        PayrateField.setEditable(false);
        ShiftsField.setEditable(false);
        ManagerButton.setEnabled(false);
        RegularButton.setEnabled(false);
        
        //help text for instructions when adding new employee
        //Add Employee button reenables visibility 
        HelpText.setVisible(false);
        
        //adding manager and regular employee radio buttons to buttongroup
        buttonGroup = new ButtonGroup();
        buttonGroup.add(ManagerButton);
        buttonGroup.add(RegularButton);
        
        //set selection to status of the currently selected employee in the list
        if(selectedEmployee.isManager()){
            ManagerButton.setSelected(true);
        }
        else{
            RegularButton.setSelected(true);
        }       
    }
    
    //this fucnction reads all 
    private void updateInterface() throws IOException, FileNotFoundException, ClassNotFoundException{
        //listModel structure ensures only 1 option selected at a time
        employees = new DefaultListModel();
        
        //loops to display each current employee (from file) on the visual list
        ArrayList<Employee> currentEmployees = mainFrame.getEmployees();
        for(int x=0; x<currentEmployees.size(); x++){
            employees.addElement(currentEmployees.get(x).getName());
        }
        
        //EmployeesList is the visual element to display employee names, which holds the list of currentEmployees
        EmployeesList = new JList(employees);
        //ensuring visual list can only have 1 selection at a time
        EmployeesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        //graphics adjustment
        EmployeesScrollPane.setViewportView(EmployeesList);        
        
        //add actionlistener on visual EmployeesList to update displayed employee info when new selection is made
        EmployeesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            //this is whats called when user clicks a new employee selection on the list
            public void valueChanged(ListSelectionEvent e) {
                //try catch for possible employee.txt file reading failes
                try {
                    selectedEmployee = mainFrame.findEmployee(EmployeesList.getSelectedValue().toString());
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                //update visual elements to newly selected employee
                NameField.setText(selectedEmployee.getName());
                NumberField.setText(selectedEmployee.getPhoneNumber());
                PayrateField.setText("" + selectedEmployee.getPayRate());
                if(selectedEmployee.isManager()){
                    ManagerButton.setSelected(true);
                }
                else{
                    RegularButton.setSelected(true);
                }
            }
            
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BackButton = new javax.swing.JButton();
        CurrentUserLabel = new javax.swing.JLabel();
        ClockLabel = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        EmployeesPanel = new javax.swing.JPanel();
        CurrentEmployeesLabel = new javax.swing.JLabel();
        EmployeesScrollPane = new javax.swing.JScrollPane();
        EmployeeInfoPanel = new javax.swing.JPanel();
        AddEmployeeButton = new javax.swing.JButton();
        EditSaveButton = new javax.swing.JButton();
        ShiftsLabel = new javax.swing.JLabel();
        PayrateField = new javax.swing.JTextField();
        PayrateLabel = new javax.swing.JLabel();
        NumberField = new javax.swing.JTextField();
        NumberLabel = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        NameLabel = new javax.swing.JLabel();
        HelpText = new javax.swing.JLabel();
        DeleteEmployeeButton = new javax.swing.JButton();
        PinLabel = new javax.swing.JLabel();
        PinField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShiftsField = new javax.swing.JTextArea();
        ManagerButton = new javax.swing.JRadioButton();
        RegularButton = new javax.swing.JRadioButton();

        BackButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BackButton.setText("Back");
        BackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        CurrentUserLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CurrentUserLabel.setText("Welcome: User's Name");

        ClockLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ClockLabel.setText("jTextField1");
        ClockLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClockLabelActionPerformed(evt);
            }
        });

        CurrentEmployeesLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CurrentEmployeesLabel.setText("Current Employees:");

        EmployeesScrollPane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout EmployeesPanelLayout = new javax.swing.GroupLayout(EmployeesPanel);
        EmployeesPanel.setLayout(EmployeesPanelLayout);
        EmployeesPanelLayout.setHorizontalGroup(
            EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                .addComponent(CurrentEmployeesLabel)
                .addGap(0, 163, Short.MAX_VALUE))
            .addComponent(EmployeesScrollPane)
        );
        EmployeesPanelLayout.setVerticalGroup(
            EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CurrentEmployeesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
        );

        EmployeeInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        AddEmployeeButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddEmployeeButton.setText("Add Employee");
        AddEmployeeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeButtonActionPerformed(evt);
            }
        });

        EditSaveButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        EditSaveButton.setText("Edit Info");
        EditSaveButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EditSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSaveButtonActionPerformed(evt);
            }
        });

        ShiftsLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ShiftsLabel.setText("Month's Shifts:");

        PayrateField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PayrateField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PayrateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayrateFieldActionPerformed(evt);
            }
        });

        PayrateLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PayrateLabel.setText("Payrate:");

        NumberField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NumberField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        NumberLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NumberLabel.setText("Phone Number:");

        NameField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });

        NameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NameLabel.setText("Name:");

        HelpText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        HelpText.setText("Please fill in required information");

        DeleteEmployeeButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        DeleteEmployeeButton.setText("Delete Employee");
        DeleteEmployeeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DeleteEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployeeButtonActionPerformed(evt);
            }
        });

        PinLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PinLabel.setText("Pin:");

        PinField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PinField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PinField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PinFieldActionPerformed(evt);
            }
        });

        ShiftsField.setColumns(20);
        ShiftsField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ShiftsField.setRows(5);
        ShiftsField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(ShiftsField);

        ManagerButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ManagerButton.setText("Manager");

        RegularButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        RegularButton.setText("Regular");

        javax.swing.GroupLayout EmployeeInfoPanelLayout = new javax.swing.GroupLayout(EmployeeInfoPanel);
        EmployeeInfoPanel.setLayout(EmployeeInfoPanelLayout);
        EmployeeInfoPanelLayout.setHorizontalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(NameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HelpText))
                    .addComponent(ShiftsLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                                .addComponent(ManagerButton)
                                .addGap(18, 18, 18)
                                .addComponent(RegularButton))
                            .addComponent(PayrateLabel)
                            .addComponent(NumberLabel)
                            .addComponent(PinLabel)
                            .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(NameField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(NumberField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(PinField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                .addComponent(PayrateField, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(EditSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeleteEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddEmployeeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                .addContainerGap())
        );
        EmployeeInfoPanelLayout.setVerticalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel)
                    .addComponent(HelpText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PayrateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PayrateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(PinLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ManagerButton)
                    .addComponent(RegularButton))
                .addGap(15, 15, 15)
                .addComponent(ShiftsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EmployeesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EmployeeInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EmployeesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(EmployeeInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(661, 661, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CurrentUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        try {
            //previous panel button
            mainFrame.setNewPanel(new ManagerSettingsPanel(mainFrame));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameFieldActionPerformed

    private void PayrateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayrateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PayrateFieldActionPerformed

    private void ClockLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClockLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClockLabelActionPerformed

    //button acts as enabling editing to add new employee ("Add New Employee") and as a ("Submit") button
    private void AddEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeButtonActionPerformed
        //when clicked - if button says "Submit", do this
        if(AddEmployeeButton.getText().equals("Submit")){
            String phonePattern = "\\d{10}";
            String pinPattern = "\\d{4}";
            if(!NumberField.getText().matches(phonePattern)){
                if(NumberField.getText().isBlank() && NameField.getText().isBlank() && PayrateField.getText().isBlank() && PinField.getText().isBlank()){
                    AddEmployeeButton.setText("Add New Employee");
                    HelpText.setVisible(false);
                    EditSaveButton.setEnabled(true);
                    DeleteEmployeeButton.setEnabled(true);
                    EmployeesList.setEnabled(true);
                    try {
                        updateInterface();
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    EmployeesList.setSelectedIndex(0);
                    PinField.setEditable(false);
                    PinField.setText("");
                }
                else{
                   NumberField.setText("Please Enter a Valid Phone Number"); 
                }
            }
            else if(!PinField.getText().matches(pinPattern)){
                PinField.setText("Please Enter a Valid 4-Digit Pin");
            }
            else{
            
                //try catch for possible file writing errors    
                try {
                        mainFrame.addEmployee(new Employee(NameField.getText(), NumberField.getText(), Double.parseDouble(PayrateField.getText()), Integer.parseInt(PinField.getText()), ManagerButton.isSelected()));
                        //change phase of current back to "Add New Employee"
                        AddEmployeeButton.setText("Add New Employee");
                        //disable help text for instructions when adding new employee
                        HelpText.setVisible(false);
                        //edit/save, employeeslist and delete buttons get disabled when entering new employee info
                        EditSaveButton.setEnabled(true);
                        DeleteEmployeeButton.setEnabled(true);
                        EmployeesList.setEnabled(true);
                        RegularButton.setEnabled(false);
                        ManagerButton.setEnabled(false);

                        //try catch for possible file reading failes in updateInterface method
                        try {
                            updateInterface();
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //set currently selected employee to newly added submission
                        EmployeesList.setSelectedIndex(employees.getSize()-1);

                        //pinfield not editable unless adding or adjusting info (and never displays pins read from file, only displays on pin entry
                        PinField.setEditable(false);
                        PinField.setText("");
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }catch(NumberFormatException e){
                        PinField.setText("Please Enter a Numerical Pin");
                        PayrateField.setText("Please Enter a Valid Hourly Wage");
                    }
            }
                
        }
        //if button doesnt say "Submit" do this("Add New Employee" phase")
        else{
            //allow pin entry when adding currentEmployees
            PinField.setEditable(true);
            
            //enable instructions
            HelpText.setText("Please fill in required information");
            HelpText.setVisible(true);
            
            //disable edit and delete button when adding new employee
            EditSaveButton.setEnabled(false);
            DeleteEmployeeButton.setEnabled(false);
            
            //change this button to "Submit" phase
            AddEmployeeButton.setText("Submit");
            
            //clear all display fields and allow editing for entry of new employee info
            NameField.setEditable(true);
            NameField.setText("");
            NumberField.setEditable(true);
            NumberField.setText("");
            PayrateField.setEditable(true);
            PayrateField.setText("");
            EmployeesList.setEnabled(false);
            RegularButton.setSelected(true);
            RegularButton.setEnabled(true);
            ManagerButton.setEnabled(true);
        }

        
        
    }//GEN-LAST:event_AddEmployeeButtonActionPerformed

    private void EditSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSaveButtonActionPerformed
        //if button clicked in "Edit Info" phase, do this
        if(EditSaveButton.getText().equals("Edit Info")){
            //allow editing of currently displayed employee info fields
            PinField.setEditable(true);
            NameField.setEditable(true);
            NumberField.setEditable(true);
            PayrateField.setEditable(true);
            
            //change current button to "Save Changes" phase
            EditSaveButton.setText("Save Changes");
            
            //disable add employee, delete button, and employeeslist when editing current info
            AddEmployeeButton.setEnabled(false);
            DeleteEmployeeButton.setEnabled(false);
            EmployeesList.setEnabled(false);
            
        }
        //button is clicked in "Submit Changes" phase, do this
        else{
            String phonePattern = "\\d{10}";
            if(!NumberField.getText().matches(phonePattern)){
                NumberField.setText("Please Enter a Valid Phone Number");
            }
            else{
                
                try{
                    selectedEmployee.setPayRate(Double.parseDouble(PayrateField.getText()));
                    selectedEmployee.setManager(ManagerButton.isSelected());
                    if(!PinField.getText().isBlank()){
                        try{
                            selectedEmployee.setPin(Integer.parseInt(PinField.getText()));
                            try {
                                mainFrame.saveEmployees();
                            } catch (IOException | ClassNotFoundException ex) {
                                Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //clear visual pin field incase new one was entered
                            PinField.setText("");
                            //change button to "Edit Info" phase
                            EditSaveButton.setText("Edit Info");

                            //dont allow editing of fields unless editing or adding employee
                            PinField.setEditable(false);
                            NameField.setEditable(false);
                            NumberField.setEditable(false);
                            PayrateField.setEditable(false);

                            //enable fields for adding emplooyees, selecting new currentEmployees, and deleting currentEmployees
                            AddEmployeeButton.setEnabled(true);
                            DeleteEmployeeButton.setEnabled(true);
                            EmployeesList.setEnabled(true);
                        }
                        catch(NumberFormatException e){
                            PinField.setText("Please Enter a Numerical Pin");

                        }
                    }else{
                        try {
                            mainFrame.saveEmployees();
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //clear visual pin field incase new one was entered
                        PinField.setText("");
                        //change button to "Edit Info" phase
                        EditSaveButton.setText("Edit Info");
                        
                        //dont allow editing of fields unless editing or adding employee
                        PinField.setEditable(false);
                        NameField.setEditable(false);
                        NumberField.setEditable(false);
                        PayrateField.setEditable(false);

                        //enable fields for adding emplooyees, selecting new currentEmployees, and deleting currentEmployees
                        AddEmployeeButton.setEnabled(true);
                        DeleteEmployeeButton.setEnabled(true);
                        EmployeesList.setEnabled(true);
                    }   
                }
                catch(NumberFormatException e){
                    PayrateField.setText("Please Enter a Valid Hourly Wage");
                }
            }
        }
        
    }//GEN-LAST:event_EditSaveButtonActionPerformed

    private void DeleteEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteEmployeeButtonActionPerformed
        
        if(!(employees.size()> 1)){
            HelpText.setText("You may not delete the only employee.");
            HelpText.setVisible(true); 
        }
        else try {
            if(mainFrame.getCurrentUser().getName().equals(selectedEmployee.getName())){
                HelpText.setText("You may not delete the current user.");
                HelpText.setVisible(true);
            }else{
                
                //try catch for possible file writing errors when deleting employee selection
                try {
                    mainFrame.removeEmployee(selectedEmployee);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                //try catch for file reading failes when updating interface, which reads currentEmployees file
                try {
                    updateInterface();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                //rest displayed employee selection to first element after deleting employee
                EmployeesList.setSelectedIndex(0);
                HelpText.setVisible(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DeleteEmployeeButtonActionPerformed

    private void PinFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PinFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PinFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddEmployeeButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField ClockLabel;
    private javax.swing.JLabel CurrentEmployeesLabel;
    private javax.swing.JLabel CurrentUserLabel;
    private javax.swing.JButton DeleteEmployeeButton;
    private javax.swing.JButton EditSaveButton;
    private javax.swing.JPanel EmployeeInfoPanel;
    private javax.swing.JPanel EmployeesPanel;
    private javax.swing.JScrollPane EmployeesScrollPane;
    private javax.swing.JLabel HelpText;
    private javax.swing.JRadioButton ManagerButton;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField NumberField;
    private javax.swing.JLabel NumberLabel;
    private javax.swing.JTextField PayrateField;
    private javax.swing.JLabel PayrateLabel;
    private javax.swing.JTextField PinField;
    private javax.swing.JLabel PinLabel;
    private javax.swing.JRadioButton RegularButton;
    private javax.swing.JTextArea ShiftsField;
    private javax.swing.JLabel ShiftsLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
