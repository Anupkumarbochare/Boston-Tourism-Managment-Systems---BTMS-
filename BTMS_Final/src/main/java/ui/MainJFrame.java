/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import User.UserCredentialsManager;
import User.UserSession;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import service.BookingService;
import ui.Admin.AdminDashboard;
import ui.Event.EventManagementPanel;
import ui.Transport.TransportManagementPanel;

/**
 *
 * @author anupbochare
 */
public class MainJFrame extends javax.swing.JFrame {

    private UserCredentialsManager credManager;
    private BookingService bookingService;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
         try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        initComponents();
        setSize(1400, 800);
        setResizable(false);
        System.out.println("Initializing MainJFrame"); // Debug print
        credManager = UserCredentialsManager.getInstance();
        System.out.println("CredManager initialized");
        bookingService = BookingService.getInstance();
    setupCustomStyling();


    }

    public void resetToLogin() {
        txtUserName3.setText("");
        txtPassword3.setText("");
        CardSequencePanel.removeAll();
        CardSequencePanel.setVisible(false);
        this.revalidate();
        this.repaint();
    }
private void setupCustomStyling() {

    jSplitPane1.setDividerLocation(250);
    jSplitPane1.setDividerSize(1);
    
    // Set minimum sizes for the panels
    jPanel5.setMinimumSize(new Dimension(200, 400));
    CardSequencePanel.setMinimumSize(new Dimension(400, 400));
    
    // Ensure split pane expands properly
    jSplitPane1.setResizeWeight(0.2);
    
// Main panel styling
    jPanel5.setBackground(new Color(255, 255, 255));
    
    // Title styling
    lblTitle3.setFont(new Font("Segoe UI", Font.BOLD, 28));
    lblTitle3.setForeground(new Color(51, 51, 51));
    
    // Username/Password label styling
    jLabel7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    jLabel8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    
    // Login button styling
    btnLogin3.setPreferredSize(new Dimension(200, 40));
    btnLogin3.setBackground(new Color(79, 70, 229));
    btnLogin3.setForeground(Color.WHITE);
    btnLogin3.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnLogin3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btnLogin3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnLogin3.setFocusPainted(false);
    
    // Text fields styling
    styleTextField(txtUserName3);
    styleTextField(txtPassword3);
    
    // Logout button styling
    btnLogout.setBackground(new Color(239, 68, 68));
    btnLogout.setForeground(Color.WHITE);
    btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Add hover effects
    addButtonHoverEffect(btnLogin3);
    addButtonHoverEffect(btnLogout);
}

private void styleTextField(JTextField field) {
    field.setPreferredSize(new Dimension(200, 35));
    field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(209, 213, 219)),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    
    field.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(79, 70, 229), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }
    });
}

private void addButtonHoverEffect(JButton button) {
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (button == btnLogin3) {
                button.setBackground(new Color(67, 56, 202));
            } else {
                button.setBackground(new Color(220, 38, 38));
            }
        }
        
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (button == btnLogin3) {
                button.setBackground(new Color(79, 70, 229));
            } else {
                button.setBackground(new Color(239, 68, 68));
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

        jSplitPane1 = new javax.swing.JSplitPane();
        CardSequencePanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Login3 = new javax.swing.JLabel();
        txtUserName3 = new javax.swing.JTextField();
        btnLogin3 = new javax.swing.JButton();
        lblTitle3 = new javax.swing.JLabel();
        txtPassword3 = new javax.swing.JPasswordField();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSplitPane1.setDividerLocation(200);

        CardSequencePanel.setBackground(new java.awt.Color(255, 255, 255));
        CardSequencePanel.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(CardSequencePanel);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Username");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Password");

        Login3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Login3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login3.setText("Login");

        txtUserName3.setText("anup");
        txtUserName3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        btnLogin3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnLogin3.setText("Login");
        btnLogin3.setOpaque(true);
        btnLogin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle3.setText("Welcome to BTMS");
        lblTitle3.setOpaque(true);

        btnLogout.setText("Logout");
        btnLogout.setOpaque(true);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserName3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(Login3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(btnLogin3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(txtPassword3)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitle3)
                .addGap(18, 18, 18)
                .addComponent(Login3)
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtUserName3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogin3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(473, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1258, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUserName3.getText().trim();
        String password = new String(txtPassword3.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }
        System.out.println("Attempting login for: " + username); // Debug

       if (credManager.validateUser(username, password)) {
        String role = credManager.getUserRole(username);
        // Set the username in UserSession
        UserSession.getInstance().setUsername(username);
String organization = credManager.getOrganizationForUser(username);
String enterprise = credManager.getEnterpriseForUser(username);
UserSession.getInstance().setOrganization(organization);
UserSession.getInstance().setEnterprise(enterprise);
            System.out.println("Role found: " + role); // Debug

            CardSequencePanel.setVisible(true);
            CardSequencePanel.removeAll();

            switch (role) {
                case "Admin":
                    CardSequencePanel.add(new AdminDashboard());
                    break;
                case "TourGuide":
                    CardSequencePanel.add(new AttractionsManagementPanel());
                    break;
                case "Tourist":
                    CardSequencePanel.add(new TouristJPanel());
                    break;
                case "TransportManager":
                    CardSequencePanel.add(new TransportManagementPanel());
                    break;
                case "EventManager":
                    CardSequencePanel.add(new EventManagementPanel());
                    break;
                case "HospitalityManager":
                    CardSequencePanel.add(new HospitalityManagementPanel());
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid role");
                    return;
            }

            CardSequencePanel.revalidate();
            CardSequencePanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
            txtUserName3.setText("");
            txtPassword3.setText("");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
   int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to logout?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION);
        
    if (confirm == JOptionPane.YES_OPTION) {
        // Clear the card sequence panel
        CardSequencePanel.removeAll();
        
        // Reset login fields
        txtUserName3.setText("");
        txtPassword3.setText("");
        

        
        // Clear session data
        bookingService = null;
        
        // Refresh the container
        CardSequencePanel.revalidate();
        CardSequencePanel.repaint();
    }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardSequencePanel;
    private javax.swing.JLabel Login3;
    private javax.swing.JButton btnLogin3;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JPasswordField txtPassword3;
    private javax.swing.JTextField txtUserName3;
    // End of variables declaration//GEN-END:variables

}
