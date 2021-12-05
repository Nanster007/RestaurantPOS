/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author tylar
 */
public class NewOrderPanel extends CustomPanel {

    private MainFrame mainFrame;
    private CustomerOrder customerOrder;

    public NewOrderPanel(MainFrame mainFrame, CustomerOrder customerOrder) {
        initComponents();
        this.OrderDetailsTextArea.setFont(new Font(Font.MONOSPACED,
                this.getFont().getStyle(),
                this.getFont().getSize()));

        setClockField(ClockLabel);
        this.customerOrder = customerOrder;
        this.mainFrame = mainFrame;
        OrderIDLabel.setText(customerOrder.getOrderID().toString());
        CustomerNameLabel.setText(customerOrder.getCustomerName());
        CustomerPhoneLabel.setText(customerOrder.getCustomerPhoneNumber());
        CustomerOrderTotalLabel.setText(String.format("%.2f", customerOrder.getOrderTotal()));

        if (customerOrder.getCustomerAddress() != null) {
            CustomerAddressLabel.setText(customerOrder.getCustomerAddress());
        } else {
            AddressLabel.setVisible(false);
            CustomerAddressLabel.setVisible(false);
        }

        OrderDetailsTextArea.setText(customerOrder.toString());

        setupMenuCategories();
    }

    private void setupMenuCategories() {
        for (String categoryName : this.mainFrame.getMenu().getMenuCategoryNames()) {
            JButton button = new JButton(categoryName);
            button.addActionListener(evt -> {
                MenuCategoryButtonActionPerformed(evt);
            });

            this.MenuCategoriesPanel.add(button);
        }
    }

    private void MenuCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton button = (JButton) evt.getSource();
        String categoryName = button.getText();

        mainFrame.setNewPanel(new MenuCategoryPanel(mainFrame, customerOrder, categoryName), true, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuCategoriesPanel = new javax.swing.JPanel();
        HeaderPanel = new javax.swing.JPanel();
        CurrentOrderLabel = new javax.swing.JLabel();
        UsernameLabel = new javax.swing.JLabel();
        ClockLabel = new javax.swing.JTextField();
        OrderIDLabel = new javax.swing.JLabel();
        CancelOrderButton = new javax.swing.JButton();
        OrderInfoPanel = new javax.swing.JPanel();
        TicketTextArea = new javax.swing.JScrollPane();
        OrderDetailsTextArea = new javax.swing.JTextArea();
        NameLabel = new javax.swing.JLabel();
        PhoneNumberLabel = new javax.swing.JLabel();
        OrderTotalLabel = new javax.swing.JLabel();
        EditInfoButton = new javax.swing.JButton();
        CustomerNameLabel = new javax.swing.JLabel();
        CustomerPhoneLabel = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        CustomerAddressLabel = new javax.swing.JLabel();
        CustomerOrderTotalLabel = new javax.swing.JLabel();
        PlaceOrderButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        MenuCategoriesPanel.setLayout(new java.awt.GridLayout(5, 2, 12, 12));
        add(MenuCategoriesPanel, java.awt.BorderLayout.CENTER);

        CurrentOrderLabel.setText("Current Order:");

        UsernameLabel.setText("User's Name");

        ClockLabel.setText("jTextField1");
        ClockLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClockLabelActionPerformed(evt);
            }
        });

        OrderIDLabel.setText("jLabel6");

        CancelOrderButton.setText("Cancel Order");
        CancelOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(CurrentOrderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OrderIDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(CancelOrderButton)
                .addContainerGap())
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderPanelLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addComponent(UsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(ClockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(CurrentOrderLabel))
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(OrderIDLabel))
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(CancelOrderButton))))
        );

        add(HeaderPanel, java.awt.BorderLayout.PAGE_START);

        OrderDetailsTextArea.setColumns(20);
        OrderDetailsTextArea.setRows(5);
        OrderDetailsTextArea.setText("order items & prices");
        TicketTextArea.setViewportView(OrderDetailsTextArea);

        NameLabel.setText("Name:");

        PhoneNumberLabel.setText("Phone Number:");

        OrderTotalLabel.setText("Order Total:  $");

        EditInfoButton.setText("Change Customer Info");
        EditInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditInfoButtonActionPerformed(evt);
            }
        });

        CustomerNameLabel.setText("jLabel7");

        CustomerPhoneLabel.setText("jLabel8");

        AddressLabel.setText("Address:");

        CustomerAddressLabel.setText("jLabel10");

        CustomerOrderTotalLabel.setText("jLabel11");

        PlaceOrderButton.setText("Place Order");
        PlaceOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaceOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrderInfoPanelLayout = new javax.swing.GroupLayout(OrderInfoPanel);
        OrderInfoPanel.setLayout(OrderInfoPanelLayout);
        OrderInfoPanelLayout.setHorizontalGroup(
            OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderInfoPanelLayout.createSequentialGroup()
                        .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TicketTextArea)
                            .addComponent(EditInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                        .addComponent(PlaceOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                        .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CustomerAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddressLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                        .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                                .addComponent(PhoneNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CustomerPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                                .addComponent(OrderTotalLabel)
                                .addGap(6, 6, 6)
                                .addComponent(CustomerOrderTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(OrderInfoPanelLayout.createSequentialGroup()
                                .addComponent(NameLabel)
                                .addGap(6, 6, 6)
                                .addComponent(CustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        OrderInfoPanelLayout.setVerticalGroup(
            OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderInfoPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameLabel)
                    .addComponent(CustomerNameLabel))
                .addGap(6, 6, 6)
                .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerPhoneLabel)
                    .addComponent(PhoneNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TicketTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OrderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OrderTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerOrderTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlaceOrderButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(OrderInfoPanel, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void EditInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditInfoButtonActionPerformed
        mainFrame.setNewPanel(new CustomerInfoPanel(mainFrame, customerOrder), true, this);
    }//GEN-LAST:event_EditInfoButtonActionPerformed

    private void CancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelOrderButtonActionPerformed
        try {
            mainFrame.setNewPanel(new MainMenuPanel(mainFrame), false, this);
        } catch (IOException ex) {
            Logger.getLogger(NewOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainFrame.clearLastPanel();
    }//GEN-LAST:event_CancelOrderButtonActionPerformed

    private void PlaceOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaceOrderButtonActionPerformed
        try {
            mainFrame.setNewPanel(new MainMenuPanel(mainFrame), false, this);
        } catch (IOException ex) {
            Logger.getLogger(NewOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainFrame.removeCustomerOrder(customerOrder.getOrderID().toString());
        try {
            mainFrame.addCustomerOrder(customerOrder);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_PlaceOrderButtonActionPerformed

    private void ClockLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClockLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClockLabelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JButton CancelOrderButton;
    private javax.swing.JTextField ClockLabel;
    private javax.swing.JLabel CurrentOrderLabel;
    private javax.swing.JLabel CustomerAddressLabel;
    private javax.swing.JLabel CustomerNameLabel;
    private javax.swing.JLabel CustomerOrderTotalLabel;
    private javax.swing.JLabel CustomerPhoneLabel;
    private javax.swing.JButton EditInfoButton;
    private javax.swing.JPanel HeaderPanel;
    private javax.swing.JPanel MenuCategoriesPanel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextArea OrderDetailsTextArea;
    private javax.swing.JLabel OrderIDLabel;
    private javax.swing.JPanel OrderInfoPanel;
    private javax.swing.JLabel OrderTotalLabel;
    private javax.swing.JLabel PhoneNumberLabel;
    private javax.swing.JButton PlaceOrderButton;
    private javax.swing.JScrollPane TicketTextArea;
    private javax.swing.JLabel UsernameLabel;
    // End of variables declaration//GEN-END:variables
}
