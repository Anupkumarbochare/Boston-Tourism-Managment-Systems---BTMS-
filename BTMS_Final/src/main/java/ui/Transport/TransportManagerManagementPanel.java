/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Transport;

import Transport.TransportManager;
import User.UserCredentialsManager;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import service.BookingService;
import ui.Admin.AdminDashboard;

/**
 *
 * @author anupbochare
 */
public class TransportManagerManagementPanel extends javax.swing.JPanel {
private BookingService bookingService;
    private UserCredentialsManager credManager;
    private DefaultTableModel managerTableModel;
    /**
     * Creates new form TransportManagerManagementPanel
     */
    public TransportManagerManagementPanel() {
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
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));

    jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel5.setForeground(Color.WHITE);
    jLabel5.setText("Transport Manager Management System");
    headerPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, -1, -1));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Form fields with Bootstrap styling
    styleFormField(contentCard, txtName, jLabel1, "Manager Name:", 20, 20);
    styleFormField(contentCard, txtUsername, jLabel2, "Username:", 20, 90);
    styleFormField(contentCard, txtPassword, jLabel3, "Password:", 20, 160);
    styleCheckbox(contentCard, chkActive, jLabel4, "Active Status:", 500, 20);

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

    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("© 2024 Transport Management System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
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
            "Name", "Username", "Password", "Status"
        });
    }
    
    private void loadManagerDetails(int row) {
        txtName.setText(managerTableModel.getValueAt(row, 0).toString());
        txtUsername.setText(managerTableModel.getValueAt(row, 1).toString());
        txtPassword.setText(managerTableModel.getValueAt(row, 2).toString());
        chkActive.setSelected(managerTableModel.getValueAt(row, 3).toString().equals("Active"));
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
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        chkActive = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblManagers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Username", "Password", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblManagers);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 247, 632, 274));

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 218, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 218, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 218, -1, -1));

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        txtName.setOpaque(true);
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 68, 135, -1));

        txtUsername.setOpaque(true);
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 109, 135, -1));

        txtPassword.setOpaque(true);
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 150, 135, -1));

        chkActive.setOpaque(true);
        add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 154, -1, -1));

        jLabel1.setText("Manager Name: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 71, -1, -1));

        jLabel2.setText("Enter Username: ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 112, -1, -1));

        jLabel3.setText("Enter Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 150, -1, -1));

        jLabel4.setText("Active Status:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 153, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Transport Manager Management System");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 10, 730, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            validateInputs();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            
            System.out.println("Adding new transport manager: " + username);
            
            credManager.addUser(username, password, "TransportManager");
            
            TransportManager manager = new TransportManager(
                managerTableModel.getRowCount() + 1,
                txtName.getText(),
                username,
                password,
                chkActive.isSelected()
            );
            
            bookingService.addTransportManager(manager);
            addManagerToTable(manager);
            clearForm();
            
            JOptionPane.showMessageDialog(this, "Transport Manager added successfully!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed
private void validateInputs() throws Exception {
        if (txtName.getText().trim().isEmpty() || 
            txtUsername.getText().trim().isEmpty() || 
            txtPassword.getText().trim().isEmpty()) {
            throw new Exception("Name, username and password are required");
        }
    }
    
    private void addManagerToTable(TransportManager manager) {
        managerTableModel.addRow(new Object[]{
            manager.getName(),
            manager.getUsername(),
            manager.getPassword(),
            manager.isActive() ? "Active" : "Inactive"
        });
    }
    private void updateManagerInTable(int row, TransportManager manager) {
        managerTableModel.setValueAt(manager.getName(), row, 0);
        managerTableModel.setValueAt(manager.getUsername(), row, 1);
        managerTableModel.setValueAt(manager.getPassword(), row, 2);
        managerTableModel.setValueAt(manager.isActive() ? "Active" : "Inactive", row, 3);
    }    
    private void clearForm() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        chkActive.setSelected(false);
    }
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
            String password = txtPassword.getText().trim();
            
            credManager.updateUser(oldUsername, newUsername, password, "TransportManager");
            
            TransportManager manager = new TransportManager(
                selectedRow + 1,
                txtName.getText(),
                newUsername,
                password,
                chkActive.isSelected()
            );
            
            bookingService.updateTransportManager(manager);
            updateManagerInTable(selectedRow, manager);
            clearForm();
            
            JOptionPane.showMessageDialog(this, "Manager updated successfully!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            JOptionPane.showMessageDialog(this, "Manager deleted successfully!");
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed
private void populateTable() {
    managerTableModel.setRowCount(0);
    List<TransportManager> managers = bookingService.getTransportManagers();
    for(TransportManager manager : managers) {
        addManagerToTable(manager);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblManagers;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
