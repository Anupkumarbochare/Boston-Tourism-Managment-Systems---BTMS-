/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Feedback;

import UserFeedback.ServiceFeedback;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import service.BookingService;

/**
 *
 * @author kaushikj
 */
public class QueryJPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private QueryResponseJPanel responsePanel;
    

    /**
     * Creates new form QueryJPanel
     */
    public QueryJPanel() {
        initComponents();
    bookingService = BookingService.getInstance();
        setupCustomLayout();

    setupServiceComboBox();
    chkStatus.setVisible(false); // Hide the checkbox

        
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

    // Header buttons
    styleBootstrapButton(btnBack, "← Back", "secondary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Title
    lblHowCanWeHelpYou.setFont(new Font("Segoe UI", Font.BOLD, 24));
    lblHowCanWeHelpYou.setForeground(new Color(33, 37, 41));
    contentCard.add(lblHowCanWeHelpYou, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

    // Subtitle
    lblPleaseSelectService.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblPleaseSelectService.setForeground(new Color(73, 80, 87));
    contentCard.add(lblPleaseSelectService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

    // Form fields
    styleBootstrapFormField(contentCard, cmbService, lblService, "Service:", 20, 100);
    styleBootstrapFormField(contentCard, txtProblem, lblProblem, "Problem:", 20, 170);
    styleBootstrapFormField(contentCard, txtQualityOfService, lblQualityOfService, "Quality of Service:", 20, 240);
    styleBootstrapFormField(contentCard, txtHowCanWeImprove, lblHowCanWeImprove, "How Can We Improve:", 20, 310);
    
    // Problem Description Area
    lblProblemDescription.setText("Problem Description:");
    lblProblemDescription.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblProblemDescription.setForeground(new Color(73, 80, 87));
    contentCard.add(lblProblemDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));
    
    txtProblemDescription.setPreferredSize(new Dimension(920, 100));
    txtProblemDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    contentCard.add(txtProblemDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 920, 100));

    // Submit button
    styleBootstrapButton(btnSubmit, "Submit Query", "primary");
    contentCard.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 150, 40));

    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("© 2024 Tourist Support System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
}

private void styleBootstrapFormField(JPanel parent, JComponent field, JLabel label, String text, int x, int y) {
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));
    
    field.setPreferredSize(new Dimension(400, 35));
    field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    
    if (field instanceof JTextField) {
        JTextField textField = (JTextField) field;
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(13, 110, 253), 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            @Override
            public void focusLost(FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218)),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
    }
    
    parent.add(field, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 25, 400, 35));
}

private void styleBootstrapButton(JButton button, String text, String style) {
    button.setText(text);
    Color bgColor;
    switch(style) {
        case "primary": bgColor = new Color(13, 110, 253); break;
        case "secondary": bgColor = new Color(108, 117, 125); break;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblService = new javax.swing.JLabel();
        cmbService = new javax.swing.JComboBox<>();
        lblHowCanWeHelpYou = new javax.swing.JLabel();
        lblPleaseSelectService = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblProblem = new javax.swing.JLabel();
        txtProblem = new javax.swing.JTextField();
        lblQualityOfService = new javax.swing.JLabel();
        txtQualityOfService = new javax.swing.JTextField();
        lblHowCanWeImprove = new javax.swing.JLabel();
        txtHowCanWeImprove = new javax.swing.JTextField();
        lblProblemDescription = new javax.swing.JLabel();
        txtProblemDescription = new javax.swing.JTextField();
        chkStatus = new javax.swing.JCheckBox();
        btnBack = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblService.setText("Service");
        add(lblService, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 93, 73, 28));

        cmbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 96, -1, -1));

        lblHowCanWeHelpYou.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblHowCanWeHelpYou.setText("How can we help you....!!!");
        add(lblHowCanWeHelpYou, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 19, 287, 36));

        lblPleaseSelectService.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        lblPleaseSelectService.setText("Please Select the Service you need Assistance with");
        add(lblPleaseSelectService, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 61, -1, -1));

        btnSubmit.setText("Submit");
        btnSubmit.setOpaque(true);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 442, -1, -1));

        lblProblem.setText("Problem");
        add(lblProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 139, -1, -1));
        add(txtProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 139, 78, -1));

        lblQualityOfService.setText("Quality of Service");
        add(lblQualityOfService, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 180, 114, 27));
        add(txtQualityOfService, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 182, 78, -1));

        lblHowCanWeImprove.setText("How Can We Improve");
        add(lblHowCanWeImprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 219, -1, 29));
        add(txtHowCanWeImprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 222, 78, -1));

        lblProblemDescription.setText("Problem Description");
        add(lblProblemDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 266, -1, -1));
        add(txtProblemDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 266, 251, 158));

        chkStatus.setText("Active or Inactive");
        chkStatus.setOpaque(true);
        chkStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkStatusActionPerformed(evt);
            }
        });
        add(chkStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
if (validateFields()) {
        ServiceFeedback feedback = new ServiceFeedback(
            cmbService.getSelectedItem().toString(),
            txtProblem.getText().trim(),
            txtQualityOfService.getText().trim(),
            txtHowCanWeImprove.getText().trim(),
            txtProblemDescription.getText().trim(),
            true  // Set initial status as Active
        );
        
        BookingService.getInstance().addServiceFeedback(feedback);
        
        if (responsePanel != null) {
            responsePanel.refreshTable();
        }
        
        JOptionPane.showMessageDialog(this, 
            "Your message is received and an immediate action will be taken based on your problem faced, Thank you....",
            "Feedback Submitted",
            JOptionPane.INFORMATION_MESSAGE);
            
        clearFields();
    }
      
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void chkStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkStatusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new TouristJPanel());
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JCheckBox chkStatus;
    private javax.swing.JComboBox<String> cmbService;
    private javax.swing.JLabel lblHowCanWeHelpYou;
    private javax.swing.JLabel lblHowCanWeImprove;
    private javax.swing.JLabel lblPleaseSelectService;
    private javax.swing.JLabel lblProblem;
    private javax.swing.JLabel lblProblemDescription;
    private javax.swing.JLabel lblQualityOfService;
    private javax.swing.JLabel lblService;
    private javax.swing.JTextField txtHowCanWeImprove;
    private javax.swing.JTextField txtProblem;
    private javax.swing.JTextField txtProblemDescription;
    private javax.swing.JTextField txtQualityOfService;
    // End of variables declaration//GEN-END:variables

    private void setupServiceComboBox() {
         String[] services = {"Hospitality Support", "Admin Support", 
                        "Transportation Support", "Attraction Inquiry", "Event Inquiry"};
    cmbService.setModel(new DefaultComboBoxModel<>(services));
    }

    private void clearFields() {
    txtProblem.setText("");
    txtQualityOfService.setText("");
    txtHowCanWeImprove.setText("");
    txtProblemDescription.setText("");
       
    }
    public void setResponsePanel(QueryResponseJPanel panel) {
    this.responsePanel = panel;
}
    private boolean validateFields() {
    return !txtProblem.getText().isEmpty() &&
           !txtQualityOfService.getText().isEmpty() &&
           !txtHowCanWeImprove.getText().isEmpty() &&
           !txtProblemDescription.getText().isEmpty();
}

}
