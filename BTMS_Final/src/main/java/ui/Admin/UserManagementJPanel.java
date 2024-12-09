/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Admin;

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import service.BookingService;
import tourist.Tourist;

/**
 *
 * @author anupbochare
 */
public class UserManagementJPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private UserCredentialsManager credManager;
    private DefaultTableModel userTableModel;
    /**
     * Creates new form UserManagementJPanel
     */
    public UserManagementJPanel() {
initComponents();
    bookingService = BookingService.getInstance();
    credManager = UserCredentialsManager.getInstance();
    setupCustomLayout();
    setupTable();

    populateTable();
    setupListeners();
    bookingService.registerUserManagementPanel(this);
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
    jLabel5.setText("Tourist Management System");
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

    // Form fields with Bootstrap styling - Left column
    styleFormField(contentCard, txtName, jLabel1, "Tourist Name:", 20, 20);
    styleFormField(contentCard, txtUsername, jLabel2, "Username:", 20, 90);
    styleFormField(contentCard, txtPassword, jLabel3, "Password:", 20, 160);

    // Form fields - Right column
    styleFormField(contentCard, txtEmail, lblEmail, "Email:", 500, 20);
    styleCheckbox(contentCard, chkActive, jLabel4, "Active Status:", 500, 90);

    // Action buttons with proper spacing
    styleBootstrapButton(btnAdd, "Add Tourist", "success");
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

    JLabel footerText = new JLabel("© 2024 Tourist Management System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
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
private void styleTable() {
    tblTourist.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblTourist.setRowHeight(40);
    tblTourist.setShowGrid(false);
    tblTourist.setIntercellSpacing(new Dimension(0, 0));
    tblTourist.setBackground(Color.BLACK);
    
    JTableHeader header = tblTourist.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    tblTourist.setSelectionBackground(new Color(237, 240, 255));
    tblTourist.setSelectionForeground(new Color(88, 101, 242));
    
    tblTourist.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
public void refreshData() {
        SwingUtilities.invokeLater(() -> {
            populateTable();
        });
    }
private void setupTable() {
        userTableModel = (DefaultTableModel) tblTourist.getModel();
        userTableModel.setColumnIdentifiers(new String[]{
            "Name", "Username", "Password", "Email", "Status", "Events Booked", "Attractions Booked"
        });
    }

    private void setupListeners() {
        tblTourist.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblTourist.getSelectedRow();
                if (row >= 0) {
                    loadUserDetails(row);
                }
            }
        });
    }

private void loadUserDetails(int row) {
    txtName.setText(userTableModel.getValueAt(row, 0).toString());
    txtUsername.setText(userTableModel.getValueAt(row, 1).toString());
    txtPassword.setText(userTableModel.getValueAt(row, 2).toString());
    txtEmail.setText(userTableModel.getValueAt(row, 3).toString());
    // Fix the status parsing
    String status = userTableModel.getValueAt(row, 4).toString();
    chkActive.setSelected(status.equals("Active"));
}

    private void populateTable() {
        userTableModel.setRowCount(0);
        for (Tourist tourist : bookingService.getAvailableTourists()) {
            addUserToTable(tourist);
        }
    }

private void addUserToTable(Tourist tourist) {
    userTableModel.addRow(new Object[]{
        tourist.getName(),
        tourist.getUsername(),
        tourist.getPassword(),
        tourist.getEmail(),
        tourist.isActive() ? "Active" : "Inactive",  // Convert boolean to text
        tourist.getEventsBooked(),
        tourist.getAttractionsBooked()
    });
}

    private void updateUserInTable(int row, Tourist tourist) {
        userTableModel.setValueAt(tourist.getName(), row, 0);
        userTableModel.setValueAt(tourist.getUsername(), row, 1);
        userTableModel.setValueAt(tourist.getPassword(), row, 2);
        userTableModel.setValueAt(tourist.getEmail(), row, 3);
    userTableModel.setValueAt(tourist.isActive() ? "Active" : "Inactive", row, 4);
        userTableModel.setValueAt(tourist.getEventsBooked(), row, 5);
        userTableModel.setValueAt(tourist.getAttractionsBooked(), row, 6);
    }

    private void validateInputs() throws Exception {
        if (txtName.getText().trim().isEmpty() ||
            txtUsername.getText().trim().isEmpty() ||
            txtPassword.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty()) {
            throw new Exception("All fields are required");
        }
    }

private boolean validateEmailFormat(String email) {
    String pattern = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|northeastern\\.edu)$";
    return email.matches(pattern);
}
private boolean validateName(String name) {
    // Check if name is empty or null
    if (name == null || name.trim().isEmpty()) {
        return false;
    }
    
    // Allow only alphabets and spaces
    String nameRegex = "^[a-zA-Z\\s]{2,30}$";
    
    // Validate name format
    if (!name.matches(nameRegex)) {
        return false;
    }
    
    // Additional validation - no consecutive spaces
    if (name.contains("  ")) {
        return false;
    }
    
    return true;
}
    private void clearForm() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        chkActive.setSelected(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTourist = new javax.swing.JTable();
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
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTourist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Username", "Password", "Email", "Status", "Events Booked", "Attraction Booked"
            }
        ));
        jScrollPane1.setViewportView(tblTourist);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 670, 274));

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 218, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 218, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 218, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 68, 135, -1));
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 109, 135, -1));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 150, 135, -1));
        jPanel1.add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 154, -1, -1));

        jLabel1.setText("Name: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 71, -1, -1));

        jLabel2.setText("Enter Username: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 112, -1, -1));

        jLabel3.setText("Enter Password:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 150, -1, -1));

        jLabel4.setText("Active Status:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 153, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tourist Management System");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 10, 730, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 130, 30));

        lblEmail.setText("Email:");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1082, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblTourist.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a tourist to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this tourist?");
        if (confirm == JOptionPane.YES_OPTION) {
            String username = userTableModel.getValueAt(selectedRow, 1).toString();
            credManager.removeUser(username);
            bookingService.deleteTourist(selectedRow + 1);
            userTableModel.removeRow(selectedRow);
            clearForm();
            JOptionPane.showMessageDialog(this, "Tourist deleted successfully!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblTourist.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a tourist to update");
            return;
        }

        try {
            validateInputs();
            String oldUsername = userTableModel.getValueAt(selectedRow, 1).toString();
            String newUsername = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String email = txtEmail.getText().trim();
                    String name = txtName.getText().trim();  // Get name from text field

   if (!validateEmailFormat(email)) {
                JOptionPane.showMessageDialog(this,
                    "Invalid email format. Please use valid Email format.",
                    "Email Format Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

                      if (!validateName(name)) {
            JOptionPane.showMessageDialog(this,
                "Invalid name format. Please use only alphabets and spaces.",
                "Name Format Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
            Tourist tourist = new Tourist(
                selectedRow + 1,
                txtName.getText(),
                newUsername,
                password,
                email,
                "",  // phone
                "",  // nationality
                "",  // passportNumber
                "",  // preferences
                chkActive.isSelected(),
                Integer.parseInt(userTableModel.getValueAt(selectedRow, 5).toString()),
                Integer.parseInt(userTableModel.getValueAt(selectedRow, 6).toString())
            );

            credManager.updateUser(oldUsername, newUsername, password, "Tourist");
            bookingService.updateTourist(tourist);
            updateUserInTable(selectedRow, tourist);
            clearForm();
            JOptionPane.showMessageDialog(this, "Tourist updated successfully!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            validateInputs();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String email = txtEmail.getText().trim();
        String name = txtName.getText().trim();  // Get name from text field

            if (!validateEmailFormat(email)) {
                JOptionPane.showMessageDialog(this,
                    "Invalid email format. Please use @gmail.com or @northeastern.edu",
                    "Email Format Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
                   if (!validateName(name)) {
            JOptionPane.showMessageDialog(this,
                "Invalid name format. Please use only alphabets and spaces.",
                "Name Format Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

            Tourist tourist = new Tourist(
                userTableModel.getRowCount() + 1,
                txtName.getText(),
                username,
                password,
                email,
                "",  // phone
                "",  // nationality
                "",  // passportNumber
                "",  // preferences
                chkActive.isSelected(),
                0,   // eventsBooked
                0    // attractionsBooked
            );

            credManager.addUser(username, password, "Tourist");
            bookingService.addTourist(tourist);
            addUserToTable(tourist);
            clearForm();
            JOptionPane.showMessageDialog(this, "Tourist added successfully!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed


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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTable tblTourist;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
