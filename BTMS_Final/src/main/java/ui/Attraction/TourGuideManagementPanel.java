/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Attraction;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import service.BookingService;
import tour.TourGuide;
import ui.Admin.AdminDashboard;

/**
 *
 * @author anupbochare
 */
public class TourGuideManagementPanel extends javax.swing.JPanel {
private BookingService bookingService;
    private UserCredentialsManager credManager;

    private DefaultTableModel guideTableModel;
        private String userRole;

    /**
     * Creates new form TourGuideJPanel
     */
    public TourGuideManagementPanel(String role) {
   this.userRole = role;
    initComponents();
    bookingService = BookingService.getInstance();
    credManager = UserCredentialsManager.getInstance();
    setupCustomLayout();
    setupTable();
    populateTable();
        
        tblGuides.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblGuides.getSelectedRow();
                if (row >= 0) {
                    loadGuideDetails(row);
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

    jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel3.setForeground(Color.WHITE);
    jLabel3.setText("Tour Guide Management System");
    headerPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, -1, -1));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Form fields with proper spacing - Left column
    styleFormField(contentCard, txtName, jLabel1, "Guide Name:", 20, 20);
    styleFormField(contentCard, txtUsername, jLabel4, "Username:", 20, 90);
    styleFormField(contentCard, txtPassword, jLabel5, "Password:", 20, 160);

    // Form fields - Right column
    styleFormField(contentCard, txtContactInfo, jLabel2, "Contact Info:", 450, 20);
    styleCheckbox(contentCard, chkActive, jLabel7, "Active Status:", 450, 90);

    // Action buttons with proper spacing
    styleBootstrapButton(btnAdd, "Add Guide", "success");
    styleBootstrapButton(btnUpdate, "Update", "primary");
    styleBootstrapButton(btnDelete, "Delete", "danger");
    
    contentCard.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 35));
    contentCard.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 130, 35));
    contentCard.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 130, 35));

    styleTable();
    contentCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 920, 280));


    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("© 2024 Tour Guide Management System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
}

private void styleFormField(JPanel parent, JTextField field, JLabel label, String text, int x, int y) {
    // Label styling
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));
    
    // Text field styling with proper positioning
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
    // Style label
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    // Style checkbox
    checkbox.setBackground(Color.WHITE);
    checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    checkbox.setFocusPainted(false);
    
    // Add hover effect
    checkbox.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            checkbox.setBackground(new Color(237, 240, 255));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            checkbox.setBackground(Color.WHITE);
        }
    });
    
    parent.add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(x + 120, y + 8, -1, -1));
}
private void styleTable() {
    tblGuides.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblGuides.setRowHeight(40);
    tblGuides.setShowGrid(false);
    tblGuides.setIntercellSpacing(new Dimension(0, 0));
    tblGuides.setBackground(Color.WHITE);
    
    JTableHeader header = tblGuides.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    tblGuides.setSelectionBackground(new Color(237, 240, 255));
    tblGuides.setSelectionForeground(new Color(88, 101, 242));
    
    tblGuides.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
private void styleBootstrapButton(JButton button, String text, String style) {
    button.setText(text);
    Color bgColor;
    switch(style) {
        case "primary": bgColor = new Color(13, 110, 253); break;
        case "secondary": bgColor = new Color(108, 117, 125); break;
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
        guideTableModel = (DefaultTableModel) tblGuides.getModel();
        guideTableModel.setColumnIdentifiers(new String[]{
            "Name", "Username", "Password", "Contact Info", "Status"
        });
    }
    
     private void loadGuideDetails(int row) {
        txtName.setText(guideTableModel.getValueAt(row, 0).toString());
        txtUsername.setText(guideTableModel.getValueAt(row, 1).toString());
        txtPassword.setText(guideTableModel.getValueAt(row, 2).toString());
        txtContactInfo.setText(guideTableModel.getValueAt(row, 3).toString());
        chkActive.setSelected(guideTableModel.getValueAt(row, 4).toString().equals("Active"));
    }
    
private void populateTable() {
        guideTableModel.setRowCount(0);
        for(TourGuide guide : bookingService.getAvailableGuides()) {
            addGuideToTable(guide);
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
        tblGuides = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtContactInfo = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        chkActive = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGuides.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Guide Name", "Username", "Password", "Contact Info", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblGuides);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 662, 262));

        txtName.setOpaque(true);
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 181, -1));

        txtContactInfo.setOpaque(true);
        add(txtContactInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 181, -1));

        txtUsername.setOpaque(true);
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 181, -1));

        txtPassword.setOpaque(true);
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 181, -1));

        chkActive.setOpaque(true);
        add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        jLabel1.setText("Guide Name");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel2.setText("Contact Info");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        jLabel4.setText("Username");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel5.setText("Password");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel7.setText("Active Status");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tour Guide Management System");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 10, 730, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
try {
        validateInputs();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        // Add to credentials manager first
        credManager = UserCredentialsManager.getInstance(); // Get singleton instance
        credManager.addUser(username, password, "TourGuide");
        
        TourGuide guide = new TourGuide(
            guideTableModel.getRowCount() + 1,
            txtName.getText(),
            username,
            password,
            txtContactInfo.getText(),
            chkActive.isSelected()
        );
        
        bookingService.addGuide(guide);
        addGuideToTable(guide);
        clearForm();
        
        JOptionPane.showMessageDialog(this, "Tour Guide added successfully!");
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
 int selectedRow = tblGuides.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a guide");
            return;
        }
        
        try {
            validateInputs();
            String oldUsername = guideTableModel.getValueAt(selectedRow, 1).toString();
            String newUsername = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            
            credManager.updateUser(oldUsername, newUsername, password, "TourGuide");
            
            TourGuide guide = new TourGuide(
                selectedRow + 1,
                txtName.getText(),
                newUsername,
                password,
                txtContactInfo.getText(),
                chkActive.isSelected()
            );
            
            bookingService.updateGuide(guide);
            updateGuideInTable(selectedRow, guide);
            clearForm();
            
            JOptionPane.showMessageDialog(this, "Guide updated successfully!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblGuides.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a guide");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this guide?");
            
        if(confirm == JOptionPane.YES_OPTION) {
            String username = guideTableModel.getValueAt(selectedRow, 1).toString();
            credManager.removeUser(username);
            bookingService.deleteGuide(selectedRow + 1);
            guideTableModel.removeRow(selectedRow);
            clearForm();
            JOptionPane.showMessageDialog(this, "Guide deleted successfully!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
     saveChanges();
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();      // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed
private void validateInputs() throws Exception {
    if (txtName.getText().trim().isEmpty() ||
        txtUsername.getText().trim().isEmpty() ||
        txtPassword.getText().trim().isEmpty() ||
        txtContactInfo.getText().trim().isEmpty()) {
        throw new Exception("Name, username, password and contact info are required");
    }
}

private void addGuideToTable(TourGuide guide) {
    guideTableModel.addRow(new Object[]{
        guide.getName(),
        guide.getUsername(),
        guide.getPassword(),
        guide.getContactInfo(),
        guide.isActive() ? "Active" : "Inactive"
    });
}

    private void updateGuideInTable(int row, TourGuide guide) {
guideTableModel.setValueAt(guide.getName(), row, 0);
        guideTableModel.setValueAt(guide.getUsername(), row, 1);
        guideTableModel.setValueAt(guide.getPassword(), row, 2);
        guideTableModel.setValueAt(guide.getContactInfo(), row, 3);
        guideTableModel.setValueAt(guide.isActive() ? "Active" : "Inactive", row, 4);
    }

private void saveChanges() {
    try {
        int rows = guideTableModel.getRowCount();
        for(int i = 0; i < rows; i++) {
            TourGuide guide = new TourGuide(
                i + 1,
                guideTableModel.getValueAt(i, 0).toString(), // name
                guideTableModel.getValueAt(i, 1).toString(), // username
                guideTableModel.getValueAt(i, 2).toString(), // password
                guideTableModel.getValueAt(i, 3).toString(), // contact info
                guideTableModel.getValueAt(i, 4).toString().equals("Active") // active status
            );
            bookingService.updateGuide(guide);
        }
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving changes: " + e.getMessage());
    }
    }

    private void clearForm() {
txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtContactInfo.setText("");
        chkActive.setSelected(false);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGuides;
    private javax.swing.JTextField txtContactInfo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}

