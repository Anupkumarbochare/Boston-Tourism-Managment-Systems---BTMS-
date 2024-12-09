/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Transport;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import service.BookingService;
import transport.TransportationRoute;

/**
 *
 * @author anupbochare
 */
public class TransportManagementPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private DefaultTableModel PublicTransitTableModel;

    /**
     * Creates new form TransportManagementPanel
     */
    public TransportManagementPanel() {
   initComponents();
    setupCustomStyling();
    bookingService = BookingService.getInstance();
    setupTable();

    populateTables();
    setupTableListener();
    setupButtonListeners();
    }
private void setupCustomStyling() {
        setBackground(new Color(248, 249, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Panel Title
        JLabel titleLabel = new JLabel("Transport Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230)));
        formPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(formPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 800, 200));

        // Add form components
        setupFormComponents(formPanel);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230)));
        tablePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(tablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 800, 450));

        // Add table components
        setupTableComponents(tablePanel);
    }
    
private void setupFormComponents(JPanel panel) {
  btnAdd.setVisible(true);
    btnUpdate.setVisible(true);
    btnDelete.setVisible(true);
    btnRouteOptimization.setVisible(true);
    
    // Set opaque property
    btnAdd.setOpaque(true);
    btnUpdate.setOpaque(true);
    btnDelete.setOpaque(true);
    btnRouteOptimization.setOpaque(true);
    // Route Details
         lblRouteName = new JLabel("Route Name:");
    lblRouteName.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblRouteName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
    
    txtRouteName = new JTextField();
    panel.add(txtRouteName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 200, 25));

    lblStartLocation = new JLabel("Start Location:");
    lblStartLocation.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblStartLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
    
    txtStartLocation = new JTextField();
    panel.add(txtStartLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 200, 25));

    lblEndLocation = new JLabel("End Location:");
    lblEndLocation.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblEndLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
    
    txtEndLocation = new JTextField();
    panel.add(txtEndLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 200, 25));

    // Route Details (Right Side)
    lblPrice = new JLabel("Price:");
    lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));
    
    txtPrice = new JTextField();
    panel.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 200, 25));

    lblFrequency = new JLabel("Frequency:");
    lblFrequency.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblFrequency, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));
    
    txtFrequency = new JTextField();
    panel.add(txtFrequency, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 200, 25));

    lblCapacity = new JLabel("Capacity:");
    lblCapacity.setFont(new Font("Segoe UI", Font.BOLD, 12));
    panel.add(lblCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));
    
    txtCapacity = new JTextField();
    panel.add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 200, 25));

    // Checkbox
    chkRouteActiveStatus = new JCheckBox("Route Active Status");
    chkRouteActiveStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
    chkRouteActiveStatus.setBackground(Color.WHITE);
    panel.add(chkRouteActiveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

    // Buttons
    btnAdd = new JButton("Add Route");
    styleButton(btnAdd, new Color(40, 167, 69));
    panel.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, 35));

    btnUpdate = new JButton("Update Route");
    styleButton(btnUpdate, new Color(0, 123, 255));
    panel.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 120, 35));

    btnDelete = new JButton("Delete Route");
    styleButton(btnDelete, new Color(220, 53, 69));
    panel.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 120, 35));

    btnRouteOptimization = new JButton("Route Optimization");
    styleButton(btnRouteOptimization, new Color(108, 117, 125));
    panel.add(btnRouteOptimization, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 140, 35));
    }
private void styleButton(JButton button, Color bgColor) {
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setOpaque(true);
    button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
}
private void setupButtonListeners() {
    btnAdd.addActionListener(e -> btnAddActionPerformed(e));
    btnUpdate.addActionListener(e -> btnUpdateActionPerformed(e));
    btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));
    btnRouteOptimization.addActionListener(e -> btnRouteOptimizationActionPerformed(e));
}
    private void setupTable() {
        PublicTransitTableModel = (DefaultTableModel) tblPublicTransit.getModel();
        PublicTransitTableModel.setColumnIdentifiers(new String[]{
            "Route Name", "Start Location", "End Location", 
            "Price", "Frequency", "Capacity", "Status"
        });
    }



    private void setupTableComponents(JPanel panel) {
        JLabel tableTitle = new JLabel("Public Transit Routes");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(tableTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        tblPublicTransit = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblPublicTransit);
        panel.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 760, 390));
    }

    private void populateTables() {
        PublicTransitTableModel.setRowCount(0);
        for (TransportationRoute route : bookingService.getRoutes()) {
            PublicTransitTableModel.addRow(new Object[]{
                route.getRouteName(),
                route.getStartLocation(),
                route.getEndLocation(),
                route.getPrice(),
                route.getFrequency(),
                route.getCapacity(),
                route.isRouteActiveStatus()? "Active" : "Inactive"
            });
        }
    }

    private void validateInputs() throws Exception {
        if (txtRouteName.getText().trim().isEmpty() ||
            txtStartLocation.getText().trim().isEmpty() ||
            txtEndLocation.getText().trim().isEmpty() ||
            txtPrice.getText().trim().isEmpty() ||
            txtFrequency.getText().trim().isEmpty() ||
            txtCapacity.getText().trim().isEmpty()) {
            throw new Exception("All fields are required");
        }
    }

    private void clearForm() {
        txtRouteName.setText("");
        txtStartLocation.setText("");
        txtEndLocation.setText("");
        txtPrice.setText("");
        txtFrequency.setText("");
        txtCapacity.setText("");
        chkRouteActiveStatus.setSelected(false);
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRouteName = new javax.swing.JLabel();
        txtRouteName = new javax.swing.JTextField();
        txtStartLocation = new javax.swing.JTextField();
        lblStartLocation = new javax.swing.JLabel();
        lblEndLocation = new javax.swing.JLabel();
        txtEndLocation = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtPrice = new javax.swing.JTextField();
        txtFrequency = new javax.swing.JTextField();
        txtCapacity = new javax.swing.JTextField();
        chkRouteActiveStatus = new javax.swing.JCheckBox();
        btnRouteOptimization = new javax.swing.JButton();
        lblPublicTransitRoutes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPublicTransit = new javax.swing.JTable();
        lblPrice = new javax.swing.JLabel();
        lblFrequency = new javax.swing.JLabel();
        lblCapacity = new javax.swing.JLabel();

        lblRouteName.setText("Route Name");

        lblStartLocation.setText("Start Location");

        lblEndLocation.setText("End Location");

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        chkRouteActiveStatus.setText("Route Active Status");
        chkRouteActiveStatus.setOpaque(true);

        btnRouteOptimization.setText("Route Optimization");
        btnRouteOptimization.setOpaque(true);
        btnRouteOptimization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteOptimizationActionPerformed(evt);
            }
        });

        lblPublicTransitRoutes.setText("Public Transit Routes ");

        tblPublicTransit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Route Name", "Start Location", "End Location", "Price", "Frequency", "Capacity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPublicTransit);

        lblPrice.setText("Price");

        lblFrequency.setText("Frequency");

        lblCapacity.setText("Capacity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(17, 17, 17)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPublicTransitRoutes)
                            .addComponent(btnRouteOptimization)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStartLocation)
                                .addGap(10, 10, 10)
                                .addComponent(txtStartLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEndLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtEndLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFrequency)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCapacity))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(chkRouteActiveStatus))
                            .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(294, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblRouteName))
                            .addComponent(txtRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblStartLocation))
                            .addComponent(txtStartLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkRouteActiveStatus))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEndLocation)
                            .addComponent(txtEndLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(lblFrequency)
                        .addGap(13, 13, 13)
                        .addComponent(lblCapacity)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(27, 27, 27)
                .addComponent(lblPublicTransitRoutes)
                .addGap(23, 23, 23)
                .addComponent(btnRouteOptimization)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    int selectedRow = tblPublicTransit.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a route to update");
        return;
    }
    
    try {
        validateInputs();
        TransportationRoute route = new TransportationRoute(
    txtRouteName.getText(),
    txtStartLocation.getText(),
    txtEndLocation.getText(),
    Double.parseDouble(txtPrice.getText()),
    txtFrequency.getText(),
    Integer.parseInt(txtCapacity.getText()),
    chkRouteActiveStatus.isSelected()
        );
        bookingService.updateRoute(route);
        populateTables();
        clearForm();
        JOptionPane.showMessageDialog(this, "Route updated successfully!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblPublicTransit.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a route to delete");
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete this route?", 
        "Confirm Delete", 
        JOptionPane.YES_NO_OPTION);
        
    if (confirm == JOptionPane.YES_OPTION) {
        String routeName = tblPublicTransit.getValueAt(selectedRow, 0).toString();
        bookingService.deleteRoute(routeName);
        populateTables();
        clearForm();
        JOptionPane.showMessageDialog(this, "Route deleted successfully!");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRouteOptimizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteOptimizationActionPerformed
 try {
        List<TransportationRoute> routes = bookingService.getRoutes();
        optimizeRoutes(routes);
        JOptionPane.showMessageDialog(this, "Routes optimized successfully!");
        populateTables();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error optimizing routes: " + e.getMessage());
    }
}

private void optimizeRoutes(List<TransportationRoute> routes) {
    // Sort routes by capacity utilization
    Collections.sort(routes, (r1, r2) -> {
        double util1 = r1.getCapacity() * r1.getPrice();
        double util2 = r2.getCapacity() * r2.getPrice();
        return Double.compare(util2, util1);
    });
    
    // Adjust frequencies based on utilization
    for (TransportationRoute route : routes) {
        if (route.getCapacity() > 500) {
            route.setFrequency("Every 10 mins");
        } else if (route.getCapacity() > 200) {
            route.setFrequency("Every 15 mins");
        } else {
            route.setFrequency("Every 20 mins");
        }
        bookingService.updateRoute(route);
    }

    }//GEN-LAST:event_btnRouteOptimizationActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    try {
        validateInputs();
        TransportationRoute route = new TransportationRoute(
            txtRouteName.getText(),
            txtStartLocation.getText(),
            txtEndLocation.getText(),
            Double.parseDouble(txtPrice.getText()),
            txtFrequency.getText(),  // Changed to String
            Integer.parseInt(txtCapacity.getText()),
            chkRouteActiveStatus.isSelected()
        );
        bookingService.addRoute(route);
        populateTables();
        clearForm();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed
private void setupTableListener() {
    tblPublicTransit.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = tblPublicTransit.getSelectedRow();
            if (selectedRow >= 0) {
                txtRouteName.setText(tblPublicTransit.getValueAt(selectedRow, 0).toString());
                txtStartLocation.setText(tblPublicTransit.getValueAt(selectedRow, 1).toString());
                txtEndLocation.setText(tblPublicTransit.getValueAt(selectedRow, 2).toString());
                txtPrice.setText(tblPublicTransit.getValueAt(selectedRow, 3).toString());
                txtFrequency.setText(tblPublicTransit.getValueAt(selectedRow, 4).toString());
                txtCapacity.setText(tblPublicTransit.getValueAt(selectedRow, 5).toString());
                chkRouteActiveStatus.setSelected(
                    tblPublicTransit.getValueAt(selectedRow, 6).toString().equals("Active")
                );
            }
        }
    });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRouteOptimization;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkRouteActiveStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblEndLocation;
    private javax.swing.JLabel lblFrequency;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPublicTransitRoutes;
    private javax.swing.JLabel lblRouteName;
    private javax.swing.JLabel lblStartLocation;
    private javax.swing.JTable tblPublicTransit;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtEndLocation;
    private javax.swing.JTextField txtFrequency;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRouteName;
    private javax.swing.JTextField txtStartLocation;
    // End of variables declaration//GEN-END:variables

 
}

