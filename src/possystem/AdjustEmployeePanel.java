/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tylar
 */
public class AdjustEmployeePanel extends CustomPanel {

    private final MainFrame mainFrame;
    private JList EmployeesList;
    private DefaultListModel listModel;
    private Employee selectedEmployee;
    
    
    public AdjustEmployeePanel(MainFrame mainFrame) throws IOException, FileNotFoundException, ClassNotFoundException {
        initComponents();
        this.mainFrame = mainFrame;
        setClockField(ClockLabel);
        HelpText.setVisible(false);
        updateInterface();
        selectedEmployee = mainFrame.findEmployee(listModel.getElementAt(0).toString());
        NameField.setText(selectedEmployee.getName());
        NumberField.setText(selectedEmployee.getPhoneNumber());
        PayrateField.setText("" + selectedEmployee.getPayRate());      
        NameField.setEditable(false);
        PinField.setEditable(false);
        NumberField.setEditable(false);
        PayrateField.setEditable(false);
        ShiftsField.setEditable(false);
        EmployeesList.setSelectedIndex(0);
    }

    private void updateInterface() throws IOException, FileNotFoundException, ClassNotFoundException{
        listModel = new DefaultListModel();
        for(int x=0; x<mainFrame.getEmployees().size(); x++){
            listModel.addElement(mainFrame.getEmployees().get(x).getName());
        }
        EmployeesList = new JList(listModel);
        EmployeesScrollPane.setViewportView(EmployeesList);
        EmployeesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        EmployeesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    selectedEmployee = mainFrame.findEmployee(EmployeesList.getSelectedValue().toString());
                } catch (IOException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                NameField.setText(selectedEmployee.getName());
                NumberField.setText(selectedEmployee.getPhoneNumber());
                PayrateField.setText("" + selectedEmployee.getPayRate());
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
        EmployeesPanel = new javax.swing.JPanel();
        CurrentEmployeesLabel = new javax.swing.JLabel();
        EmployeesScrollPane = new javax.swing.JScrollPane();
        EmployeeInfoPanel = new javax.swing.JPanel();
        AddEmployeeButton = new javax.swing.JButton();
        EditSaveButton = new javax.swing.JButton();
        ShiftsField = new javax.swing.JTextField();
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
        ErrorText = new javax.swing.JLabel();

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        CurrentUserLabel.setText("Welcome: User's Name");

        ClockLabel.setText("jTextField1");
        ClockLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClockLabelActionPerformed(evt);
            }
        });

        CurrentEmployeesLabel.setText("Current Employees:");

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
                .addComponent(EmployeesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        AddEmployeeButton.setText("Add Employee");
        AddEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeButtonActionPerformed(evt);
            }
        });

        EditSaveButton.setText("Edit Info");
        EditSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSaveButtonActionPerformed(evt);
            }
        });

        ShiftsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftsFieldActionPerformed(evt);
            }
        });

        ShiftsLabel.setText("Month's Shifts:");

        PayrateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayrateFieldActionPerformed(evt);
            }
        });

        PayrateLabel.setText("Payrate:");

        NumberLabel.setText("Phone Number:");

        NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });

        NameLabel.setText("Name:");

        HelpText.setText("Please fill in required information");

        DeleteEmployeeButton.setText("Delete Employee");
        DeleteEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployeeButtonActionPerformed(evt);
            }
        });

        PinLabel.setText("Pin:");

        PinField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PinFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EmployeeInfoPanelLayout = new javax.swing.GroupLayout(EmployeeInfoPanel);
        EmployeeInfoPanel.setLayout(EmployeeInfoPanelLayout);
        EmployeeInfoPanelLayout.setHorizontalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(EditSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ShiftsField)
                    .addComponent(ShiftsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PayrateField)
                    .addComponent(NumberField)
                    .addComponent(NameField)
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                                .addComponent(NameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                                .addComponent(HelpText))
                            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                                .addComponent(PayrateLabel)
                                .addGap(0, 346, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(PinField)
                    .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                        .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NumberLabel)
                            .addComponent(PinLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        EmployeeInfoPanelLayout.setVerticalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeInfoPanelLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel)
                    .addComponent(HelpText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PayrateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PayrateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(PinLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ShiftsLabel)
                .addGap(18, 18, 18)
                .addComponent(ShiftsField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(EmployeesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(EmployeeInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addComponent(ErrorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CurrentUserLabel)
                    .addComponent(ErrorText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmployeesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(EmployeeInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        mainFrame.setNewPanel(new ManagerSettingsPanel(mainFrame), Boolean.FALSE, this);
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

    private void AddEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeButtonActionPerformed
        if(AddEmployeeButton.getText().equals("Submit")){
                try {
                    mainFrame.addEmployee(new Employee(NameField.getText(), NumberField.getText(), Double.parseDouble(PayrateField.getText()), Integer.parseInt(PinField.getText())));
                } catch (IOException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddEmployeeButton.setText("Add New Employee");
                HelpText.setVisible(false);
                EditSaveButton.setEnabled(true);
                DeleteEmployeeButton.setEnabled(true);
                EmployeesList.setEnabled(true);
    //            EmployeesList.setEnabled(true);
                try {
                    updateInterface();
                } catch (IOException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                EmployeesList.setSelectedIndex(listModel.getSize()-1);
                PinField.setEnabled(false);
            }
        else{
            PinField.setEditable(true);
            HelpText.setVisible(true);
            EditSaveButton.setEnabled(false);
            DeleteEmployeeButton.setEnabled(false);
            AddEmployeeButton.setText("Submit");
            NameField.setEditable(true);
            NameField.setText("");
            NumberField.setEditable(true);
            NumberField.setText("");
            PayrateField.setEditable(true);
            PayrateField.setText("");
            EmployeesList.setEnabled(false);
        }

        
        
    }//GEN-LAST:event_AddEmployeeButtonActionPerformed

    private void EditSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSaveButtonActionPerformed
        if(EditSaveButton.getText().equals("Edit Info")){
            PinField.setEditable(true);
            NameField.setEditable(true);
            NumberField.setEditable(true);
            PayrateField.setEditable(true);
            EditSaveButton.setText("Save Changes");
            AddEmployeeButton.setEnabled(false);
            DeleteEmployeeButton.setEnabled(false);
            EmployeesList.setEnabled(false);
        }
        else{
            EditSaveButton.setText("Edit Info");
            System.out.println(selectedEmployee.getName());
            selectedEmployee.setName(NameField.getText());
            selectedEmployee.setPayRate(Double.parseDouble(PayrateField.getText()));
            selectedEmployee.setPhoneNumber(NumberField.getText());
            if(!PinField.getText().isBlank()){
                selectedEmployee.setPin(Integer.parseInt(PinField.getText()));
            }
            try {
                mainFrame.saveEmployees();
            } catch (IOException ex) {
                Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            PinField.setText("");
            PinField.setEditable(false);
            NameField.setEditable(false);
            NumberField.setEditable(false);
            PayrateField.setEditable(false);
            AddEmployeeButton.setEnabled(true);
            DeleteEmployeeButton.setEnabled(true);
            EmployeesList.setEnabled(true);
        }
        
    }//GEN-LAST:event_EditSaveButtonActionPerformed

    private void ShiftsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShiftsFieldActionPerformed

    private void DeleteEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteEmployeeButtonActionPerformed
        try {
            mainFrame.removeEmployee(selectedEmployee);
        } catch (IOException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            updateInterface();
        } catch (IOException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdjustEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        EmployeesList.setSelectedIndex(0);
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
    private javax.swing.JLabel ErrorText;
    private javax.swing.JLabel HelpText;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField NumberField;
    private javax.swing.JLabel NumberLabel;
    private javax.swing.JTextField PayrateField;
    private javax.swing.JLabel PayrateLabel;
    private javax.swing.JTextField PinField;
    private javax.swing.JLabel PinLabel;
    private javax.swing.JTextField ShiftsField;
    private javax.swing.JLabel ShiftsLabel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
