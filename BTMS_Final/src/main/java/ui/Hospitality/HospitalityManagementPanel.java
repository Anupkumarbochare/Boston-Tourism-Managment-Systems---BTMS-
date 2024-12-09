/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Hospitality;

import User.UserSession;
import hospitality.HospitalityProperty;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import service.BookingService;
import ui.Admin.AdminDashboard;
import ui.WorkRequest.WorkRequestPanel;

/**
 *
 * @author anupbochare
 */
public class HospitalityManagementPanel extends javax.swing.JPanel {
  private BookingService bookingService;
    private DefaultTableModel propertyTableModel;
    /**
     * Creates new form HospitalityManagementPanel
     */
    public HospitalityManagementPanel() {
         
        initComponents();
        bookingService = BookingService.getInstance();
        propertyTableModel = (DefaultTableModel) tblProperties.getModel();
         setupCustomLayout();
    styleTable();
        populateTable();
        
        tblProperties.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblProperties.getSelectedRow();
                if (row >= 0) {
                    txtPropertyName.setText(propertyTableModel.getValueAt(row, 1).toString());
                    txtType.setText(propertyTableModel.getValueAt(row, 0).toString());
                    txtBasePrice.setText(propertyTableModel.getValueAt(row, 2).toString());
                    txtPeakPrice.setText(propertyTableModel.getValueAt(row, 3).toString());
                    txtRating.setText(propertyTableModel.getValueAt(row, 6).toString());
                    chkAvailable.setSelected(propertyTableModel.getValueAt(row, 4).toString().equals("Active"));
                }
            }
        });
    }
private void setupCustomLayout() {
    // Main panel setup
    setBackground(new Color(248, 249, 250));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

JPanel headerPanel = new JPanel();
    headerPanel.setBackground(new Color(33, 37, 41));
    headerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 60));

    // Title
    JLabel titleLabel = new JLabel("Manager Hotels");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);
    headerPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, -1, -1));
    
    // Header buttons
    styleBootstrapButton(btnBack, "← Back", "secondary");
    styleBootstrapButton(btnWorkRequest, "Work Request", "primary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));
    headerPanel.add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 12, 150, 35));

        jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel6.setForeground(Color.WHITE);
    jLabel6.setText("Manage Hotels");
    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Table section
    styleTable();
    contentCard.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 920, 250));

    // Action buttons
    styleBootstrapButton(btnAdd, "Add Property", "success");
    styleBootstrapButton(btnUpdate, "Update", "primary");
    styleBootstrapButton(btnDelete, "Delete", "danger");
    
    contentCard.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 120, 35));
    contentCard.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 120, 35));
    contentCard.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 120, 35));

    // Form section
    JPanel formPanel = new JPanel();
    formPanel.setBackground(Color.WHITE);
    formPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    contentCard.add(formPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 920, 240));

    // Form fields with proper spacing
    styleFormField(formPanel, txtPropertyName, jLabel2, "Property Name:", 20, 20);
    styleFormField(formPanel, txtType, jLabel1, "Type:", 20, 80);
    styleFormField(formPanel, txtBasePrice, jLabel3, "Base Price:", 500, 20);
    styleFormField(formPanel, txtPeakPrice, jLabel4, "Peak Price:", 500, 80);
    styleFormField(formPanel, txtRating, jLabel5, "Rating:", 20, 140);
    styleCheckbox(formPanel, chkAvailable, jLabel7, "Active Status:", 500, 140);

    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("© 2024 Hospitality Management System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

    // Make components opaque
    tblProperties.setOpaque(true);
    jScrollPane2.setOpaque(true);
    jScrollPane2.getViewport().setOpaque(true);
    btnAdd.setOpaque(true);
    btnUpdate.setOpaque(true);
    btnDelete.setOpaque(true);
}

private void styleTable() {
    // Make table and viewport opaque
    tblProperties.setOpaque(true);
    jScrollPane2.setOpaque(true);
    jScrollPane2.getViewport().setOpaque(true);
    
    // Basic table styling
    tblProperties.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblProperties.setRowHeight(40);
    tblProperties.setShowGrid(false);
tblProperties.setBackground(Color.BLACK);    
    // Header styling
    JTableHeader header = tblProperties.getTableHeader();
    header.setOpaque(true);
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
header.setBackground(new Color(249, 250, 251));
header.setForeground(Color.BLACK);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    // Zebra striping and cell padding
    tblProperties.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(249, 250, 251));
            }
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            return c;
        }
    });
}

private void styleFormField(JPanel parent, JTextField field, JLabel label, String text, int x, int y) {
    // Label styling
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));
    
    // Field styling
    field.setPreferredSize(new Dimension(380, 35));
    field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    field.setBackground(Color.WHITE);
    field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(206, 212, 218)),
        BorderFactory.createEmptyBorder(8, 12, 8, 12)
    ));
    
    // Add focus effect
    field.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(13, 110, 253), 2),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));
        }
        @Override
        public void focusLost(FocusEvent e) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));
        }
    });
    
    parent.add(field, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 25, 380, 35));

}


private void styleCheckbox(JPanel parent, JCheckBox checkbox, JLabel label, String text, int x, int y) {
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    checkbox.setBackground(Color.WHITE);
    checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    checkbox.setFocusPainted(false);
    parent.add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(x + 120, y + 8, -1, -1));
}

private void styleBootstrapButton(JButton button, String text, String style) {
    button.setText(text);
    Color bgColor;
    switch(style) {
        case "primary": bgColor = new Color(13, 110, 253); break;  // Bootstrap primary
        case "secondary": bgColor = new Color(108, 117, 125); break;  // Bootstrap secondary
        case "success": bgColor = new Color(25, 135, 84); break;  // Bootstrap success
        case "danger": bgColor = new Color(220, 53, 69); break;  // Bootstrap danger
        default: bgColor = new Color(13, 110, 253);
    }
    
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Hover effect
    button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            button.setBackground(bgColor.darker());
        }
        public void mouseExited(MouseEvent e) {
            button.setBackground(bgColor);
        }
    });
}


    private void populateTable() {
    propertyTableModel.setRowCount(0);
    List<HospitalityProperty> properties = bookingService.getPropertiesByManager("All");
    
    for(HospitalityProperty property : properties) {
        propertyTableModel.addRow(new Object[]{
            property.getType(),
            property.getName(),
            property.getFloorPrice(),
            property.getCeilingPrice(),
            property.isActive() ? "Active" : "Inactive",
            property.getType().equals("Hotel") ? "Amenities" : "Cuisine",
            "4.5"  // Default rating for demo
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblProperties = new javax.swing.JTable();
        txtPropertyName = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        txtBasePrice = new javax.swing.JTextField();
        txtPeakPrice = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        chkAvailable = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnWorkRequest = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Type", "Property Name", "Base Price", "Peak Price", "Status", "Feature/Cuisine", "Rating"
            }
        ));
        jScrollPane2.setViewportView(tblProperties);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 73, 752, 260));
        add(txtPropertyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 409, 114, -1));
        add(txtType, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 450, 114, -1));
        add(txtBasePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 409, 114, -1));
        add(txtPeakPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 114, -1));
        add(txtRating, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 491, 114, -1));
        add(chkAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 491, -1, -1));

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 345, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 345, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 345, -1, -1));

        jLabel1.setText("Type :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 453, -1, -1));

        jLabel2.setText("Property Name: ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 412, -1, -1));

        jLabel3.setText("Base Price : ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 415, -1, -1));

        jLabel4.setText("Peak Price : ");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 453, -1, -1));

        jLabel5.setText("Rating  :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, -1, -1));

        jLabel7.setText("Active Status:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 491, -1, -1));

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 130, 40));

        jLabel6.setText("Manage Hotels");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
try {
        String type = txtType.getText();
        String name = txtPropertyName.getText();
        double basePrice = Double.parseDouble(txtBasePrice.getText());
        double peakPrice = Double.parseDouble(txtPeakPrice.getText());
        boolean isActive = chkAvailable.isSelected();
        
        
        // Create new property
        HospitalityProperty property = new HospitalityProperty(
            bookingService.getPropertiesByManager("All").size() + 1,
            name,
            type,
            basePrice,
            peakPrice,
            isActive,
            type + " Manager"
        );
        
        // Add to booking service
        bookingService.addProperty(property);
        
        // Update table
        propertyTableModel.addRow(new Object[]{
            type, name, basePrice, peakPrice,
            isActive ? "Active" : "Inactive",
            type.equals("Hotel") ? "Amenities" : "Cuisine",
            txtRating.getText()
        });
        
        clearForm();
    } catch(NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid numbers");
    }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       int selectedRow = tblProperties.getSelectedRow();
    if(selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a property");
        return;
    }
    
    try {
        String type = txtType.getText();
        String name = txtPropertyName.getText();
        double basePrice = Double.parseDouble(txtBasePrice.getText());
        double peakPrice = Double.parseDouble(txtPeakPrice.getText());
        boolean isActive = chkAvailable.isSelected();
        
        // Update in booking service
        HospitalityProperty property = new HospitalityProperty(
            selectedRow + 1,
            name,
            type,
            basePrice,
            peakPrice,
            isActive,
            type + " Manager"
        );
        bookingService.updateProperty(property);
        
        // Update table
        propertyTableModel.setValueAt(type, selectedRow, 0);
        propertyTableModel.setValueAt(name, selectedRow, 1);
        propertyTableModel.setValueAt(basePrice, selectedRow, 2);
        propertyTableModel.setValueAt(peakPrice, selectedRow, 3);
        propertyTableModel.setValueAt(isActive ? "Active" : "Inactive", selectedRow, 4);
        
        clearForm();
    } catch(NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid numbers");
    }
    }//GEN-LAST:event_btnUpdateActionPerformed
 private void clearForm() {
        txtPropertyName.setText("");
        txtType.setText("");
        txtBasePrice.setText("");
        txtPeakPrice.setText("");
        txtRating.setText("");
        chkAvailable.setSelected(false);
    }
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblProperties.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a property");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this property?");
            
        if(confirm == JOptionPane.YES_OPTION) {
            propertyTableModel.removeRow(selectedRow);
            clearForm();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = "HotelManagement";
        String enterprise = "HospitalityEnterprise";

        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new WorkRequestPanel(username, organization, enterprise,"HospitalityManager"));
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JCheckBox chkAvailable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProperties;
    private javax.swing.JTextField txtBasePrice;
    private javax.swing.JTextField txtPeakPrice;
    private javax.swing.JTextField txtPropertyName;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
