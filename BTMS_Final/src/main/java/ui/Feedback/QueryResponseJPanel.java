/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Feedback;

import UserFeedback.ServiceFeedback;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * @author kaushikj
 */
public class QueryResponseJPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private DefaultTableModel tableModel;

    /**
     * Creates new form QueryResponseJPanel
     */
    public QueryResponseJPanel() {
    initComponents();
    bookingService = BookingService.getInstance();
    setupCustomLayout();
    setupTable();
    setupComboBox();

    filterTableByService();
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

    // Header title and back button
    styleBootstrapButton(btnBack, "← Back", "secondary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));

    lblQueryResponse.setFont(new Font("Segoe UI", Font.BOLD, 24));
    lblQueryResponse.setForeground(Color.WHITE);
    headerPanel.add(lblQueryResponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, -1, -1));

    // Filter dropdown with Bootstrap styling
    jComboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    jComboBox1.setBackground(Color.WHITE);
    headerPanel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 12, 200, 35));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Table styling
    styleTable();
    contentCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 920, 450));

    // Action buttons
    styleBootstrapButton(btnUpdate, "Update", "primary");
    styleBootstrapButton(btnDelete, "Delete", "danger");
    
    contentCard.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 120, 35));
    contentCard.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 120, 35));

    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("© 2024 Query Management System");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
}

private void styleTable() {
    // Make table and viewport opaque
    tblDescription.setOpaque(true);
    jScrollPane1.setOpaque(true);
    jScrollPane1.getViewport().setOpaque(true);
    
    // Basic table styling
    tblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblDescription.setRowHeight(40);
    tblDescription.setShowGrid(false);
    tblDescription.setIntercellSpacing(new Dimension(0, 0));
    tblDescription.setBackground(Color.WHITE);
    
    // Header styling
    JTableHeader header = tblDescription.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    // Selection styling
    tblDescription.setSelectionBackground(new Color(237, 240, 255));
    tblDescription.setSelectionForeground(new Color(88, 101, 242));
    
    // Zebra striping
    tblDescription.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQueryResponse = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDescription = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQueryResponse.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblQueryResponse.setText("Query Response");
        add(lblQueryResponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 45, 245, 32));

        lblDescription.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        lblDescription.setText("Description");
        add(lblDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 141, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 484, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 484, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setOpaque(true);
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 52, -1, -1));

        tblDescription.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Problem", "Quality of Service", "How can we improve", "Problem Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDescription);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 167, 711, 290));

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
int selectedRow = tblDescription.getSelectedRow();
    if (selectedRow >= 0) {
        // Get current service filter
        String currentService = jComboBox1.getSelectedItem().toString();
        
        String problem = (String) tableModel.getValueAt(selectedRow, 0);
        String qualityOfService = (String) tableModel.getValueAt(selectedRow, 1);
        String howCanWeImprove = (String) tableModel.getValueAt(selectedRow, 2);
        String description = (String) tableModel.getValueAt(selectedRow, 3);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
        
        JTextField txtProblem = new JTextField(problem);
        JTextField txtQuality = new JTextField(qualityOfService);
        JTextField txtImprove = new JTextField(howCanWeImprove);
        JTextField txtDesc = new JTextField(description);
        String[] statusOptions = {"Active", "Inactive"};
        JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
        statusCombo.setSelectedItem(currentStatus);
        
        Object[] fields = {
            "Problem:", txtProblem,
            "Quality of Service:", txtQuality,
            "How Can We Improve:", txtImprove,
            "Description:", txtDesc,
            "Status:", statusCombo
        };
        
        int result = JOptionPane.showConfirmDialog(this, fields, 
                "Update Feedback", JOptionPane.OK_CANCEL_OPTION);
                
        if (result == JOptionPane.OK_OPTION) {
            // Find and update the actual feedback in the full list
            List<ServiceFeedback> feedbacks = BookingService.getInstance().getServiceFeedbacks();
            for (ServiceFeedback feedback : feedbacks) {
                if (feedback.getProblem().equals(problem) &&
                    feedback.getQualityOfService().equals(qualityOfService) &&
                    feedback.getHowCanWeImprove().equals(howCanWeImprove) &&
                    feedback.getProblemDescription().equals(description)) {
                    
                    feedback.setProblem(txtProblem.getText());
                    feedback.setQualityOfService(txtQuality.getText());
                    feedback.setHowCanWeImprove(txtImprove.getText());
                    feedback.setProblemDescription(txtDesc.getText());
                    feedback.setStatus(statusCombo.getSelectedItem().equals("Active"));
                    break;
                }
            }
            
            // Refresh table with current service filter
            refreshTableWithService(currentService);
            
            JOptionPane.showMessageDialog(this,
                "Feedback updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this,
            "Please select a row to update",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
// TODO add your handling code here:
        int selectedRow = tblDescription.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this feedback?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Get the current service filter
                String currentService = jComboBox1.getSelectedItem().toString();

                // Get all feedbacks
                List<ServiceFeedback> allFeedbacks = BookingService.getInstance().getServiceFeedbacks();

                // Find the actual index in the full list
                ServiceFeedback feedbackToDelete = null;
                String problem = (String) tableModel.getValueAt(selectedRow, 0);
                String quality = (String) tableModel.getValueAt(selectedRow, 1);
                String improve = (String) tableModel.getValueAt(selectedRow, 2);
                String description = (String) tableModel.getValueAt(selectedRow, 3);

                for (ServiceFeedback feedback : allFeedbacks) {
                    if (feedback.getProblem().equals(problem)
                            && feedback.getQualityOfService().equals(quality)
                            && feedback.getHowCanWeImprove().equals(improve)
                            && feedback.getProblemDescription().equals(description)) {
                        feedbackToDelete = feedback;
                        break;
                    }
                }

                if (feedbackToDelete != null) {
                    allFeedbacks.remove(feedbackToDelete);
                    refreshTableWithService(currentService);

                    JOptionPane.showMessageDialog(this,
                            "Feedback deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a row to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new AdminDashboard());
        parent.revalidate();
        parent.repaint();      // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedService = jComboBox1.getSelectedItem().toString();
        refreshTableWithService(selectedService);}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblQueryResponse;
    private javax.swing.JTable tblDescription;
    // End of variables declaration//GEN-END:variables
private void setupTable() {
        String[] columns = {"Problem", "Quality of Service", "How Can We Improve", "Problem Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only status column is editable
            }
        };
        tblDescription.setModel(tableModel);

        // Add checkbox column renderer
        tblDescription.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {

        });


    }

    private void setupComboBox() {
        String[] services = {"All Services", "Hospitality Support", "Admin Support",
            "Transportation Support", "Attraction Inquiry", "Event Inquiry"};
        jComboBox1.setModel(new DefaultComboBoxModel<>(services));

        jComboBox1.addActionListener((ActionEvent e) -> {
            refreshTable();
        });
    }

    private void filterTableByService() {
        String selectedService = jComboBox1.getSelectedItem().toString();
        tableModel.setRowCount(0);

        List<ServiceFeedback> feedbacks = bookingService.getServiceFeedbacks();
        for (ServiceFeedback feedback : feedbacks) {
            if (selectedService.equals("All Services")
                    || feedback.getService().equals(selectedService)) {
                addFeedbackToTable(feedback);
            }
        }
    }

    private void addFeedbackToTable(ServiceFeedback feedback) {
        Object[] row = {
            feedback.getProblem(),
            feedback.getQualityOfService(),
            feedback.getHowCanWeImprove(),
            feedback.getProblemDescription(),
                feedback.getStatus()
        };
        tableModel.addRow(row);
    }

    public void refreshTable() {
        String selectedService = jComboBox1.getSelectedItem().toString();
        tableModel.setRowCount(0);

        List<ServiceFeedback> feedbacks = BookingService.getInstance().getServiceFeedbacks();
        for (ServiceFeedback feedback : feedbacks) {
            if (selectedService.equals("All Services")
                    || feedback.getService().equals(selectedService)) {
                addFeedbackToTable(feedback);
            }
        }
    }

    private void refreshTableWithService(String selectedService) {
        tableModel.setRowCount(0);
        List<ServiceFeedback> feedbacks = BookingService.getInstance().getServiceFeedbacks();

        for (ServiceFeedback feedback : feedbacks) {
            if (selectedService.equals("All Services")
                    || feedback.getService().equals(selectedService)) {
                Object[] row = {
                    feedback.getProblem(),
                    feedback.getQualityOfService(),
                    feedback.getHowCanWeImprove(),
                    feedback.getProblemDescription(),
                    feedback.getStatus()// Add status to table
                };
                tableModel.addRow(row);
            }
        }
    }

    private void updateStatus(int row, boolean isActive) {
        String problem = (String) tableModel.getValueAt(row, 0);
        String qualityOfService = (String) tableModel.getValueAt(row, 1);

        List<ServiceFeedback> feedbacks = BookingService.getInstance().getServiceFeedbacks();
        for (ServiceFeedback feedback : feedbacks) {
            if (feedback.getProblem().equals(problem)
                    && feedback.getQualityOfService().equals(qualityOfService)) {
                feedback.setStatus(isActive);
                break;
            }
        }
    }
   
}
