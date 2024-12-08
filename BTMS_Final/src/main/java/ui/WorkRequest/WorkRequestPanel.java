/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.WorkRequest;

import WorkRequest.WorkRequest;
import WorkRequest.WorkRequestService;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import ui.Admin.AdminDashboard;
import ui.Event.EventManagementPanel;
import ui.Transport.TransportManagementPanel;


/**
 *
 * @author anupbochare
 */
public class WorkRequestPanel extends javax.swing.JPanel {
    private WorkRequestService workRequestService;
    private DefaultTableModel requestTableModel;
    private String currentUsername;
    private String currentOrg;
    private String currentEnterprise;
    private String callingPanel;

    /**
     * Creates new form WorkRequestPanel
     */
    public WorkRequestPanel(String username, String org, String enterprise, String callingPanel) {
        initComponents();
        this.currentUsername = username;
        this.currentOrg = org;
        this.currentEnterprise = enterprise;
            this.callingPanel = callingPanel;

        workRequestService = WorkRequestService.getInstance();
            setupCustomLayout();

        setupTable();
        setupComboBoxes();
        loadRequests();
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
   
    styleBootstrapButton(btnBack, "← Back", "secondary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));
    // Header title
    jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel5.setForeground(Color.WHITE);
    jLabel5.setText("Work Request Management");
    headerPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, -1));

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
    styleFormField(contentCard, txtReceiver, jLabel1, "Receiver Username:", 20, 20);
    styleFormField(contentCard, txtDescription, jLabel2, "Description:", 20, 90);
    
    // Dropdown fields
    styleDropdown(contentCard, cmbReceiverOrg, jLabel3, "Receiver Organization:", 20, 160);
    styleDropdown(contentCard, cmbReceiverEnt, jLabel4, "Receiver Enterprise:", 500, 20);
    styleDropdown(contentCard, cmbStatus, jLabel5, "Status:", 500, 90);

    // Action buttons
    styleBootstrapButton(btnCreateRequest, "Create Request", "success");
    styleBootstrapButton(btnUpdateStatus, "Update Status", "primary");
    
    contentCard.add(btnCreateRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 35));
    contentCard.add(btnUpdateStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 150, 35));

    // Table section
    styleTable();
    contentCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 920, 280));
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
        @Override
        public void mousePressed(MouseEvent e) {
            button.setBackground(bgColor.darker().darker());
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

private void styleDropdown(JPanel parent, JComboBox<String> combo, JLabel label, String text, int x, int y) {
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    label.setForeground(new Color(73, 80, 87));
    parent.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    combo.setBackground(Color.WHITE);
    combo.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
    parent.add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 35, 380, 35));
}

private void styleTable() {
    tblRequests.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblRequests.setRowHeight(40);
    tblRequests.setShowGrid(false);
    tblRequests.setIntercellSpacing(new Dimension(0, 0));
    tblRequests.setBackground(Color.WHITE);
    
    JTableHeader header = tblRequests.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    tblRequests.setSelectionBackground(new Color(237, 240, 255));
    tblRequests.setSelectionForeground(new Color(88, 101, 242));
    
    tblRequests.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
    private void setupComboBoxes() {
        // Setup Organizations
        cmbReceiverOrg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "EventManagement", "TourGuides", "TransportManagement",
            "HotelManagement", "CustomerSupport","AttractionManagement",
        }));

        // Setup Enterprises
        cmbReceiverEnt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "AnalyticsEnterprise","AttractionEnterprise", "HospitalityEnterprise", "TransportEnterprise","EventEnterprise","TourismEnterprise"
        }));

        // Setup Status
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "PENDING", "APPROVED", "REJECTED", "IN_PROGRESS", "COMPLETED"
        }));
    }

    private void setupTable() {
        requestTableModel = new DefaultTableModel(
            new String[] {"Request ID", "Type", "Description", "Status", 
                         "From", "To", "Date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRequests.setModel(requestTableModel);
    }

    private void loadRequests() {
        requestTableModel.setRowCount(0);
        List<WorkRequest> requests = workRequestService.getRequestsForUser(currentUsername);
        
        for(WorkRequest request : requests) {
            requestTableModel.addRow(new Object[] {
                request.getRequestId(),
                request.getType(),
                request.getDescription(),
                request.getStatus(),
                request.getSenderUsername(),
                request.getReceiverUsername(),
                request.getRequestDate()
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
        tblRequests = new javax.swing.JTable();
        btnCreateRequest = new javax.swing.JButton();
        btnUpdateStatus = new javax.swing.JButton();
        txtReceiver = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        cmbReceiverOrg = new javax.swing.JComboBox<>();
        cmbReceiverEnt = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        tblRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(tblRequests);

        btnCreateRequest.setText("Create Request");
        btnCreateRequest.setOpaque(true);
        btnCreateRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRequestActionPerformed(evt);
            }
        });

        btnUpdateStatus.setText("Update Status");
        btnUpdateStatus.setOpaque(true);
        btnUpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStatusActionPerformed(evt);
            }
        });

        cmbReceiverOrg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbReceiverEnt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Receiver Username : ");

        jLabel2.setText("Description :");

        jLabel3.setText("Receiver Organization :");

        jLabel4.setText("Receiver Enterprise : ");

        jLabel5.setText("Status : ");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cmbReceiverOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnCreateRequest)
                        .addGap(42, 42, 42)
                        .addComponent(btnUpdateStatus))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbReceiverEnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnBack)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReceiverOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReceiverEnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateRequest)
                    .addComponent(btnUpdateStatus))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRequestActionPerformed
 if(validateInputs()) {
            String receiverUsername = txtReceiver.getText().trim();
            String description = txtDescription.getText().trim();
            String receiverOrg = cmbReceiverOrg.getSelectedItem().toString();
            String receiverEnt = cmbReceiverEnt.getSelectedItem().toString();
            
            String type = currentEnterprise.equals(receiverEnt) ? 
                "CROSS_ORG" : "CROSS_ENTERPRISE";

            WorkRequest request = new WorkRequest(
                type, description, currentUsername, receiverUsername,
                currentOrg, receiverOrg, currentEnterprise, receiverEnt
            );
            
            workRequestService.createWorkRequest(request);
            loadRequests();
            clearForm();
            JOptionPane.showMessageDialog(this, "Work request created successfully!");
        }  // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateRequestActionPerformed

    private void btnUpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStatusActionPerformed
int row = tblRequests.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a request");
            return;
        }

        String requestId = tblRequests.getValueAt(row, 0).toString();
        String newStatus = cmbStatus.getSelectedItem().toString();
        
        workRequestService.updateRequestStatus(requestId, newStatus);
        loadRequests();
        JOptionPane.showMessageDialog(this, "Status updated successfully!");
           // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateStatusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
Container parent = this.getParent();
    parent.removeAll();
    
    // Return to appropriate panel based on caller
    switch(callingPanel) {
        case "Tourist":
            parent.add(new TouristJPanel());
            break;
        case "Admin":
            parent.add(new AdminDashboard());
            break;
        case "EventManager":
            parent.add(new EventManagementPanel());
            break;
        case "TourGuide":
            parent.add(new AttractionsManagementPanel());
            break;
        case "TransportManager":
            parent.add(new TransportManagementPanel());
            break;
        default:
            parent.add(new TouristJPanel());
    }
    
    parent.revalidate();
    parent.repaint();      // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed
private boolean validateInputs() {
        if(txtReceiver.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter receiver username");
            return false;
        }
        if(txtDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter request description");
            return false;
        }
        return true;
    }

    private void clearForm() {
        txtReceiver.setText("");
        txtDescription.setText("");
        cmbReceiverOrg.setSelectedIndex(0);
        cmbReceiverEnt.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateRequest;
    private javax.swing.JButton btnUpdateStatus;
    private javax.swing.JComboBox<String> cmbReceiverEnt;
    private javax.swing.JComboBox<String> cmbReceiverOrg;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRequests;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtReceiver;
    // End of variables declaration//GEN-END:variables
}
