/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Attraction;

import User.UserSession;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import service.BookingService;
import tour.Attraction;
import tour.Tour;
import ui.WorkRequest.WorkRequestPanel;

/**
 *
 * @author anupbochare
 */
public class AttractionsManagementPanel extends javax.swing.JPanel {

    private BookingService bookingService;
    private DefaultTableModel landmarkTableModel;
    private DefaultTableModel tourTableModel;

    /**
     * Creates new form AttractionsManagementPanel
     */
    public AttractionsManagementPanel() {
    initComponents();
    bookingService = BookingService.getInstance();
        setupCustomLayout();

    setupTables();
    setupGuideComboBox();
    setupCustomStyling();
    addTableListeners();
    populateTables();
    }
private void setupCustomLayout() {
    // Main panel setup with proper margins
    setPreferredSize(new Dimension(1180, 780));
    setBackground(new Color(245, 247, 251));
    
    // Header section - centered and properly spaced
    jLabel9.setFont(new Font("Segoe UI", Font.BOLD, 28));
    add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 400, 40));
    add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 180, 40));
    
    // Attractions section title
    add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 200, 30));
    
    // Form fields - first row
    add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));
    add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 200, 35));
    
    add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));
    add(txtlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 200, 35));
    
    add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));
    add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 200, 35));
    
    // Form fields - second row
    add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
    add(txtHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 200, 35));
    
    add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));
    add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 200, 35));
    
    add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, -1, -1));
    add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));
    
    // Description area
    add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));
    add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 500, 60));
    
    // Action buttons
    add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 100, 35));
    add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 100, 35));
    add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 100, 35));
    
    // Attractions table
    add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 1080, 170));
    
    // Tours section
    add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 200, 30));
    
    // Tour form fields
    add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, -1));
    add(txtTourName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 200, 35));
    
    add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, -1, -1));
    add(txttourPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, 200, 35));
    
    add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, -1, -1));
    add(spinnerCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, 200, 35));
    
    // Tour buttons
    add(btnScheduleTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 150, 35));
    add(btnCancelTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 150, 35));
    
    // Tours table
    add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, 1080, 130));
}
private void setupCustomStyling() {
    // Main panel styling
    setBackground(new Color(245, 247, 251));
    
    // Style tables
    styleTable(tblLandmark);
    styleTable(tblTour);
    
    // Style buttons
    JButton[] buttons = {
        btnadd, btnupdate, btnDelete, 
        btnScheduleTour, btnCancelTour, btnWorkRequest
    };
    
    for (JButton button : buttons) {
        button.setPreferredSize(new Dimension(140, 40));
        button.setBackground(new Color(88, 101, 242));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(71, 82, 196));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(88, 101, 242));
            }
        });
    }
    
    // Style text fields
    JTextField[] fields = {
        txtName, txtlocation, txtPrice, txtHours, 
        txtCapacity, txtTourName, txttourPrice
    };
    
    for (JTextField field : fields) {
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
    
    // Style description area
    txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    txtDescription.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(209, 213, 219)),
        BorderFactory.createEmptyBorder(8, 10, 8, 10)
    ));
    
    // Style headers
    Font headerFont = new Font("Segoe UI", Font.BOLD, 24);
    jLabel9.setFont(headerFont);
    jLabel12.setFont(headerFont.deriveFont(20f));
    jLabel13.setFont(headerFont.deriveFont(20f));
}

private void styleTable(JTable table) {
    table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    table.setRowHeight(35);
    table.setIntercellSpacing(new Dimension(10, 10));
    table.setShowGrid(false);
    table.setBackground(Color.WHITE);
    
    // Style header
    table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    table.getTableHeader().setBackground(new Color(88, 101, 242));
    table.getTableHeader().setForeground(Color.WHITE);
    table.getTableHeader().setOpaque(true);
    
    // Style selection
    table.setSelectionBackground(new Color(237, 240, 255));
    table.setSelectionForeground(new Color(88, 101, 242));
}
    private void setupTables() {
        landmarkTableModel = (DefaultTableModel) tblLandmark.getModel();
        tourTableModel = (DefaultTableModel) tblTour.getModel();
        
        // Clear default rows
        landmarkTableModel.setRowCount(0);
        tourTableModel.setRowCount(0);
    }

private void setupGuideComboBox() {
    List<String> guideNames = bookingService.getGuideNames();
    if (guideNames.isEmpty()) {
        guideNames.add("No guides available");
    }
    jComboBox1.setModel(new DefaultComboBoxModel<>(guideNames.toArray(new String[0])));
}
public void refreshGuideComboBox() {
    setupGuideComboBox();
}
    private void addTableListeners() {
        tblLandmark.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblLandmark.getSelectedRow();
                if (row >= 0) {
                    loadAttractionDetails(row);
                }
            }
        });
    }

    private void loadAttractionDetails(int row) {
        txtName.setText(landmarkTableModel.getValueAt(row, 0).toString());
        txtlocation.setText(landmarkTableModel.getValueAt(row, 1).toString());
        txtPrice.setText(landmarkTableModel.getValueAt(row, 2).toString());
        txtHours.setText(landmarkTableModel.getValueAt(row, 3).toString());
        txtCapacity.setText(landmarkTableModel.getValueAt(row, 4).toString());
        txtDescription.setText(landmarkTableModel.getValueAt(row, 5).toString());
        jCheckBox1.setSelected(landmarkTableModel.getValueAt(row, 6).toString().equals("Active"));
    }

    private void populateTables() {
        // Populate attractions table
        for (Attraction attraction : bookingService.getAvailableAttractions()) {
            landmarkTableModel.addRow(new Object[]{
                attraction.getName(),
                attraction.getLocation(),
                attraction.getTicketPrice(),
                attraction.getHours(),
                attraction.getCapacity(),
                attraction.getDescription(),
                attraction.isIsActive()? "Active" : "Inactive"
            });
        }

        // Populate tours table
        for (Tour tour : bookingService.getAvailableTours()) {
            tourTableModel.addRow(new Object[]{
                tour.getTourName(),
                tour.getGuide(),
                tour.getCapacity(),
                tour.getPrice()
            });
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLandmark = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtlocation = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtPrice = new javax.swing.JTextField();
        txtHours = new javax.swing.JTextField();
        txtCapacity = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTour = new javax.swing.JTable();
        txtTourName = new javax.swing.JTextField();
        spinnerCapacity = new javax.swing.JSpinner();
        txttourPrice = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnScheduleTour = new javax.swing.JButton();
        btnCancelTour = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnWorkRequest = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLandmark.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Location", "Price", "Hours", "Capacity", "Description", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblLandmark);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 870, 170));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 80, 120, -1));
        add(txtlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 80, 120, -1));

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 208, 498, 45));
        add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 120, -1));
        add(txtHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 137, 120, -1));
        add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 137, 120, -1));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 487, 120, -1));

        tblTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tour Name", "Guide", "Capacity", "Price"
            }
        ));
        jScrollPane3.setViewportView(tblTour);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 880, 140));
        add(txtTourName, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 522, 120, -1));
        add(spinnerCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 522, 120, -1));
        add(txttourPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 522, 120, -1));

        btnadd.setText("Add");
        btnadd.setOpaque(true);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 218, -1, 35));

        btnupdate.setText("Update");
        btnupdate.setOpaque(true);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 218, -1, 35));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 218, -1, 35));

        btnScheduleTour.setText("Schedule Tour");
        btnScheduleTour.setOpaque(true);
        btnScheduleTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleTourActionPerformed(evt);
            }
        });
        add(btnScheduleTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 575, 132, 35));

        btnCancelTour.setText("Cancel Tour");
        btnCancelTour.setOpaque(true);
        btnCancelTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelTourActionPerformed(evt);
            }
        });
        add(btnCancelTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 575, -1, 35));

        jLabel1.setText("Name :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 83, -1, -1));

        jLabel2.setText("Price :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 83, -1, -1));

        jLabel3.setText("Location :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 83, -1, -1));

        jLabel4.setText("Hours :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 140, -1, -1));

        jLabel5.setText("Capacity :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 140, -1, -1));

        jLabel6.setText("Tour Name");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 525, -1, -1));

        jLabel7.setText("Tour Price");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 525, -1, -1));

        jLabel8.setText("Capacity");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 525, -1, -1));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Manage Attractions and Tours");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-190, 0, 1330, -1));

        jLabel10.setText("Description");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel11.setText("Active Status:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel12.setText("Manage Attractions");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 35, -1, -1));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel13.setText("Manage Tours");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 489, -1, -1));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 260, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
       try {
            validateInputs();
            
            Attraction attraction = new Attraction(
                landmarkTableModel.getRowCount() + 1,
                txtName.getText(),
                txtlocation.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtHours.getText(),
                Integer.parseInt(txtCapacity.getText()),
                    
                jCheckBox1.isSelected()
            );
            
           bookingService.addAttraction(attraction);
        addAttractionToTable(attraction);
        clearForm();
        bookingService.notifyTouristPanels();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
         int selectedRow = tblLandmark.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an attraction");
            return;
        }

        try {
            validateInputs();
            
            Attraction attraction = new Attraction(
                selectedRow + 1,
                txtName.getText(),
                txtlocation.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtHours.getText(),
                Integer.parseInt(txtCapacity.getText()),
                jCheckBox1.isSelected()
            );
            
            bookingService.updateAttraction(attraction);
            updateAttractionInTable(selectedRow, attraction);
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnupdateActionPerformed
private void validateInputs() throws Exception {
        if (txtName.getText().trim().isEmpty() || 
            txtlocation.getText().trim().isEmpty() ||
            txtPrice.getText().trim().isEmpty() ||
            txtHours.getText().trim().isEmpty() ||
            txtCapacity.getText().trim().isEmpty()) {
            throw new Exception("All fields are required");
        }
    }

    private void addAttractionToTable(Attraction attraction) {
        landmarkTableModel.addRow(new Object[]{
            attraction.getName(),
            attraction.getLocation(),
            attraction.getTicketPrice(),
            attraction.getHours(),
            attraction.getCapacity(),
            attraction.getDescription(),
            attraction.isIsActive()? "Active" : "Inactive"
        });
    }
private void updateAttractionInTable(int row, Attraction attraction) {
        landmarkTableModel.setValueAt(attraction.getName(), row, 0);
        landmarkTableModel.setValueAt(attraction.getLocation(), row, 1);
        landmarkTableModel.setValueAt(attraction.getTicketPrice(), row, 2);
        landmarkTableModel.setValueAt(attraction.getHours(), row, 3);
        landmarkTableModel.setValueAt(attraction.getCapacity(), row, 4);
        landmarkTableModel.setValueAt(attraction.getDescription(), row, 5);
        landmarkTableModel.setValueAt(attraction.isIsActive()? "Active" : "Inactive", row, 6);
    }    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       int selectedRow = tblLandmark.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select an attraction");
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete this attraction?");
        
    if (confirm == JOptionPane.YES_OPTION) {
        String name = landmarkTableModel.getValueAt(selectedRow, 0).toString();
        bookingService.deleteAttraction(name);
        landmarkTableModel.removeRow(selectedRow);
        clearForm();
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnScheduleTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleTourActionPerformed
       try {
            Tour tour = new Tour(
                tourTableModel.getRowCount() + 1,
                txtTourName.getText(),
                jComboBox1.getSelectedItem().toString(),
                (Integer) spinnerCapacity.getValue(),
                Double.parseDouble(txttourPrice.getText())
            );
            
            bookingService.addTour(tour);
            tourTableModel.addRow(new Object[]{
                tour.getTourName(),
                tour.getGuide(),
                tour.getCapacity(),
                tour.getPrice()
            });
            clearTourForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error scheduling tour: " + e.getMessage());
        }
    }//GEN-LAST:event_btnScheduleTourActionPerformed

    private void btnCancelTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelTourActionPerformed
        int selectedRow = tblTour.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a tour");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel this tour?");

        if (confirm == JOptionPane.YES_OPTION) {
            tourTableModel.removeRow(selectedRow);
        }  // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelTourActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = "LandmarkManagement";
        String enterprise = "AttractionEnterprise";

        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new WorkRequestPanel(username, organization, enterprise,"TourGuide"));
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed

private void clearForm() {
        txtName.setText("");
        txtlocation.setText("");
        txtPrice.setText("");
        txtHours.setText("");
        txtCapacity.setText("");
        txtDescription.setText("");
        jCheckBox1.setSelected(false);
    }

    private void clearTourForm() {
        txtTourName.setText("");
        txttourPrice.setText("");
        spinnerCapacity.setValue(0);
        jComboBox1.setSelectedIndex(0);
    
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelTour;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnScheduleTour;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnupdate;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner spinnerCapacity;
    private javax.swing.JTable tblLandmark;
    private javax.swing.JTable tblTour;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtHours;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTourName;
    private javax.swing.JTextField txtlocation;
    private javax.swing.JTextField txttourPrice;
    // End of variables declaration//GEN-END:variables

}
