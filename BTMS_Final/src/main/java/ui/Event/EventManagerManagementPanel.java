/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Event;

import User.UserCredentialsManager;
import User.UserSession;
import event.EventManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class EventManagerManagementPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private UserCredentialsManager credManager;
    private DefaultTableModel managerTableModel;
    
    /**
     * Creates new form EventManagerManagementPanel
     */
    public EventManagerManagementPanel() {
    initComponents();
    bookingService = BookingService.getInstance();
    credManager = UserCredentialsManager.getInstance();
    setupCustomLayout();
    setupTable();
    populateTable();
                tblManagers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblManagers.getSelectedRow();
                if (row >= 0) {
                    loadManagerDetails(row);
                }
            }
        });
    }
private void setupCustomLayout() {
    // Main panel setup
    setBackground(new Color(248, 249, 250));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    // Header navbar (dark)
    JPanel headerPanel = new JPanel();
    headerPanel.setBackground(new Color(33, 37, 41));
    headerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 60));

    // Header buttons and title
    styleBootstrapButton(btnBack, "← Back", "secondary");
    styleBootstrapButton(btnWorkRequest, "Work Request", "primary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));
    headerPanel.add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 12, 150, 35));

    jLabel7.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel7.setForeground(Color.WHITE);
    jLabel7.setText("Event Manager Management System");
    headerPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, -1, -1));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Form fields with Bootstrap styling - Left column
    styleFormField(contentCard, txtName, jLabel1, "Manager Name:", 20, 20);
    styleFormField(contentCard, txtUsername, jLabel2, "Username:", 20, 90);
    styleFormField(contentCard, txtPassword, jLabel3, "Password:", 20, 160);

    // Form fields - Right column
    styleFormField(contentCard, txtContact, jLabel6, "Contact Info:", 500, 20);
    styleFormField(contentCard, txtSpecialization, jLabel5, "Specialization:", 500, 90);
    styleCheckbox(contentCard, chkActive, jLabel4, "Active Status:", 500, 160);

    // Action buttons with proper spacing
    styleBootstrapButton(btnAdd, "Add Manager", "success");
    styleBootstrapButton(btnUpdate, "Update", "primary");
    styleBootstrapButton(btnDelete, "Delete", "danger");
    
    contentCard.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 35));
    contentCard.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 130, 35));
    contentCard.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 130, 35));

    // Table section
    styleTable();
    contentCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 920, 280));
}

private void styleTable() {
    tblManagers.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblManagers.setRowHeight(40);
    tblManagers.setShowGrid(false);
    tblManagers.setIntercellSpacing(new Dimension(0, 0));
    tblManagers.setBackground(Color.WHITE);
    
    JTableHeader header = tblManagers.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    tblManagers.setSelectionBackground(new Color(237, 240, 255));
    tblManagers.setSelectionForeground(new Color(88, 101, 242));
    
    tblManagers.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    field.setPreferredSize(new Dimension(380, 35));
    field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    field.setBackground(Color.WHITE);
    field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(206, 212, 218)),
        BorderFactory.createEmptyBorder(8, 12, 8, 12)
    ));
    
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
    
    parent.add(field, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 35, 380, 35));
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
        case "primary": bgColor = new Color(13, 110, 253); break;
        case "secondary": bgColor = new Color(108, 117, 125); break;
        case "success": bgColor = new Color(25, 135, 84); break;
        case "danger": bgColor = new Color(220, 53, 69); break;
        default: bgColor = new Color(13, 110, 253);
    }
    
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.BOLD, 14));
    button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(bgColor.darker());
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(bgColor);
        }
    });
}



   
private void setupTable() {
        managerTableModel = (DefaultTableModel) tblManagers.getModel();
        managerTableModel.setColumnIdentifiers(new String[]{
            "Name", "Username", "Contact Info", "Specialization", "Status"
        });
    }
    
    private void loadManagerDetails(int row) {
        txtName.setText(managerTableModel.getValueAt(row, 0).toString());
        txtUsername.setText(managerTableModel.getValueAt(row, 1).toString());
        txtContact.setText(managerTableModel.getValueAt(row, 2).toString());
        txtSpecialization.setText(managerTableModel.getValueAt(row, 3).toString());
        chkActive.setSelected(managerTableModel.getValueAt(row, 4).toString().equals("Active"));
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
        tblManagers = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtSpecialization = new javax.swing.JTextField();
        chkActive = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnWorkRequest = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblManagers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Username", "Contact Info", "Specialization", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblManagers);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 267, 621, 263));

        txtName.setOpaque(true);
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 76, 104, -1));
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 117, 104, -1));
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 158, 104, -1));

        txtContact.setOpaque(true);
        add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 76, 104, -1));

        txtSpecialization.setOpaque(true);
        add(txtSpecialization, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 117, 104, -1));

        chkActive.setOpaque(true);
        add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 162, -1, -1));

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 238, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 238, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 238, -1, -1));

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel1.setText("Manager Name: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 79, -1, -1));

        jLabel2.setText("Enter Username: ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 120, -1, -1));

        jLabel3.setText("Enter Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 161, -1, -1));

        jLabel4.setText("Active Status:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 162, -1, -1));

        jLabel5.setText("Specialization:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 123, -1, -1));

        jLabel6.setText("Contact Info:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 79, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Event Manager Management System");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 10, 670, -1));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 140, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblManagers.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a manager to delete");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this manager?");
            
        if(confirm == JOptionPane.YES_OPTION) {
            String username = managerTableModel.getValueAt(selectedRow, 1).toString();
            credManager.removeUser(username);
            managerTableModel.removeRow(selectedRow);
            clearForm();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
try {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        // Add debug prints
        System.out.println("Adding new event manager: " + username);
        
        // Add to UserCredentialsManager first
        credManager.addUser(username, password, "EventManager");
        
        // Then create manager object
        EventManager manager = new EventManager(
            managerTableModel.getRowCount() + 1,
            txtName.getText(),
            username,
            password,
            txtContact.getText(),
            txtSpecialization.getText(),
            chkActive.isSelected()
        );
        
        bookingService.addEventManager(manager);
        addManagerToTable(manager);
        clearForm();
        
        System.out.println("Event manager added successfully");
        JOptionPane.showMessageDialog(this, "Event Manager added successfully!");
    } catch(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
 int selectedRow = tblManagers.getSelectedRow();
    if(selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a manager to update");
        return;
    }
    
    try {
        validateInputs();
        String oldUsername = managerTableModel.getValueAt(selectedRow, 1).toString();
        String newUsername = txtUsername.getText().trim();
        String newPassword = txtPassword.getText().trim();
        
        // Update credentials
        credManager.updateUser(oldUsername, newUsername, newPassword, "EventManager");
        
        EventManager manager = new EventManager(
            selectedRow + 1,
            txtName.getText(),
            newUsername,
            newPassword,
            txtContact.getText(),
            txtSpecialization.getText(),
            chkActive.isSelected()
        );
        
        bookingService.updateEventManager(manager);
        updateManagerInTable(selectedRow, manager);
        clearForm();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = "EventManagement";
        String enterprise = "HospitalityEnterprise";

        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new WorkRequestPanel(username, organization, enterprise,"Admin"));
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed
private void validateInputs() throws Exception {
        if (txtName.getText().trim().isEmpty() || 
            txtUsername.getText().trim().isEmpty() ||
            txtPassword.getText().trim().isEmpty() ||
            txtContact.getText().trim().isEmpty() ||
            txtSpecialization.getText().trim().isEmpty()) {
            throw new Exception("All fields are required");
        }
    }
    
    private void addManagerToTable(EventManager manager) {
        managerTableModel.addRow(new Object[]{
            manager.getName(),
            manager.getUsername(),
            manager.getContactInfo(),
            manager.getSpecialization(),
            manager.isIsActive()? "Active" : "Inactive"
        });
    }
    
    private void clearForm() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtContact.setText("");
        txtSpecialization.setText("");
        chkActive.setSelected(false);
    
}
private void updateManagerInTable(int row, EventManager manager) {
        managerTableModel.setValueAt(manager.getName(), row, 0);
        managerTableModel.setValueAt(manager.getUsername(), row, 1);
        managerTableModel.setValueAt(manager.getContactInfo(), row, 2);
        managerTableModel.setValueAt(manager.getSpecialization(), row, 3);
        managerTableModel.setValueAt(manager.isIsActive()? "Active" : "Inactive", row, 4);
    }
private void populateTable() {
    managerTableModel.setRowCount(0);
    List<EventManager> managers = bookingService.getEventManagers();
    
    for(EventManager manager : managers) {
        managerTableModel.addRow(new Object[]{
            manager.getName(),
            manager.getUsername(),
            manager.getContactInfo(),
            manager.getSpecialization(),
            manager.isIsActive()? "Active" : "Inactive"
        });
    }}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblManagers;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSpecialization;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
