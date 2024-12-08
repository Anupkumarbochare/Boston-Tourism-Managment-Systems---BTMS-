/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Event;

import User.UserSession;
import event.Event;
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

/**
 *
 * @author anupbochare
 */
public class EventManagementPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private DefaultTableModel eventTableModel;
    /**
     * Creates new form EventManagementPanel
     */
    public EventManagementPanel() {
        initComponents();
        bookingService = BookingService.getInstance();
        eventTableModel = (DefaultTableModel) tblEvents.getModel();
            setupCustomLayout();
styleTable();
        setupTable();
        populateTable();
        
        tblEvents.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblEvents.getSelectedRow();
                if (row >= 0) {
                    txtName.setText(eventTableModel.getValueAt(row, 0).toString());
                    txtLocation.setText(eventTableModel.getValueAt(row, 1).toString());
                    txtPrice.setText(eventTableModel.getValueAt(row, 2).toString());
                    txtDate.setText(eventTableModel.getValueAt(row, 3).toString());
                    txtTime.setText(eventTableModel.getValueAt(row, 4).toString());
                    txtCapacity.setText(eventTableModel.getValueAt(row, 5).toString());
                    txtCategory.setText(eventTableModel.getValueAt(row, 6).toString());
                    txtOrganizer.setText(eventTableModel.getValueAt(row, 7).toString());
                    txtDescription.setText(eventTableModel.getValueAt(row, 8).toString());
                    jCheckBox1.setSelected(eventTableModel.getValueAt(row, 9).toString().equals("Active"));
                }
            }
        });
    }
private void setupCustomLayout() {
    // Main panel setup
    setBackground(new Color(245, 247, 251));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());


    
    styleButtonWithEffects(btnWorkRequest, "Work Request");
    add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 150, 35));

    // Table panel with background
    JPanel tablePanel = new JPanel();
    tablePanel.setBackground(Color.WHITE);
    tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    tablePanel.setLayout(new BorderLayout());
    tablePanel.add(jScrollPane1);
    add(tablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 980, 280));

    // Action buttons
    styleButtonWithEffects(btnAdd, "Add Event");
    styleButtonWithEffects(btnUpdate, "Update");
    styleButtonWithEffects(btnDelete, "Delete");
    
    add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 120, 35));
    add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 120, 35));
    add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 120, 35));

    // Form fields - Left column
    styleFormField(txtName, jLabel1, "Event Name:", 20, 420);
    styleFormField(txtLocation, jLabel2, "Location:", 20, 470);
    styleFormField(txtPrice, jLabel3, "Price:", 20, 520);
    styleFormField(txtDate, jLabel4, "Date:", 20, 570);
    styleFormField(txtTime, jLabel5, "Time:", 20, 620);

    // Form fields - Right column
    styleFormField(txtCapacity, jLabel6, "Capacity:", 520, 420);
    styleFormField(txtCategory, jLabel7, "Category:", 520, 470);
    styleFormField(txtOrganizer, jLabel8, "Organizer:", 520, 520);
    styleFormField(txtDescription, jLabel9, "Description:", 520, 570);

    // Checkbox
    styleCheckbox(jCheckBox1, jLabel10, 520, 620);
}

private void styleButtonWithEffects(JButton button, String text) {
    button.setText(text);
    button.setBackground(new Color(88, 101, 242));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.BOLD, 14));
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(71, 82, 196));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(88, 101, 242));
        }
        @Override
        public void mousePressed(MouseEvent e) {
            button.setBackground(new Color(60, 69, 165));
        }
    });
}

private void styleFormField(JTextField field, JLabel label, String text, int x, int y) {
    label.setText(text);
    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    field.setPreferredSize(new Dimension(400, 35));
    field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    field.setBackground(Color.WHITE);
    field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(209, 213, 219)),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    
    // Add focus effect
    field.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(88, 101, 242), 2),
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
    
    add(field, new org.netbeans.lib.awtextra.AbsoluteConstraints(x + 100, y, 380, 35));
}

private void styleCheckbox(JCheckBox checkbox, JLabel label, int x, int y) {
    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y + 8, -1, -1));
    
    checkbox.setBackground(new Color(245, 247, 251));
    checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    checkbox.setFocusPainted(false);
    add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(x + 100, y + 8, -1, -1));
}

private void styleTable() {
    // Table styling
    tblEvents.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblEvents.setRowHeight(40);
    tblEvents.setShowGrid(false);
    tblEvents.setIntercellSpacing(new Dimension(0, 0));
    tblEvents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tblEvents.setBackground(Color.WHITE);
    
    // Header styling
    JTableHeader header = tblEvents.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(88, 101, 242));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    // Selection styling
    tblEvents.setSelectionBackground(new Color(237, 240, 255));
    tblEvents.setSelectionForeground(new Color(88, 101, 242));
    
    // Add zebra striping
    tblEvents.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(249, 250, 251));
            }
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            return c;
        }
    });
    
    // Add scroll pane styling
    JScrollPane scrollPane = (JScrollPane) tblEvents.getParent().getParent();
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
    scrollPane.getViewport().setBackground(Color.WHITE);
}  
    private void setupTable() {
        eventTableModel.setColumnIdentifiers(new String[]{
            "Event Name", "Location", "Price", "Date", "Time", 
            "Capacity", "Category", "Organizer", "Description", "Status"
        });
    }
    
    private void populateTable() {
       // First clear all existing rows
    eventTableModel.setRowCount(0);
    // Then add current events from booking service
    for(Event event : bookingService.getAvailableEvents()) {
        eventTableModel.addRow(new Object[]{
            event.getName(),
            event.getLocation(),
            event.getTicketPrice(),
            event.getDate(),
            event.getTime(),
            event.getCapacity(),
            event.getCategory(),
            event.getOrganizer(),
            event.getDescription(),
            event.isActive() ? "Active" : "Inactive"
        });
    }
    eventTableModel.fireTableDataChanged();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtCapacity = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        txtOrganizer = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvents = new javax.swing.JTable();
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
        btnWorkRequest = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdd.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 100, 30));

        btnUpdate.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 100, 30));

        btnDelete.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 100, 30));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 206, 24));
        add(txtLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 206, -1));
        add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 206, -1));
        add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, 206, -1));
        add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 206, -1));
        add(txtCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 206, -1));
        add(txtOrganizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 206, -1));
        add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 206, -1));
        add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 206, -1));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, -1, -1));

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Event Name", "Location", "Price", "Date", "TIme", "Capacity", "Category", "Organizer", "Description", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblEvents);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 811, 307));

        jLabel1.setText("Event Name :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 94, -1));

        jLabel2.setText("Event Location :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel3.setText("Price :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 94, 18));

        jLabel4.setText("Date :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 94, 18));

        jLabel5.setText("Time :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 94, 18));

        jLabel6.setText("Capacity: ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, 94, 18));

        jLabel7.setText("Category :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 94, 18));

        jLabel8.setText("Organizer :");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 94, 18));

        jLabel9.setText("Description : ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 94, 18));

        jLabel10.setText("Active Status :");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, -1, -1));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 130, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
try {
            String name = txtName.getText();
            String location = txtLocation.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String date = txtDate.getText();
            String time = txtTime.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());
            String category = txtCategory.getText();
            String organizer = txtOrganizer.getText();
            String description = txtDescription.getText();
            boolean isActive = jCheckBox1.isSelected();
            
            Event event = new Event(
                bookingService.getAvailableEvents().size() + 1,
                name, location, description, price, date, time,
                capacity, isActive, category, organizer
            );
            
            bookingService.addEvent(event);
            populateTable();
            clearForm();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
int selectedRow = tblEvents.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an event");
            return;
        }
        
        try {
            String name = txtName.getText();
            String location = txtLocation.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String date = txtDate.getText();
            String time = txtTime.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());
            String category = txtCategory.getText();
            String organizer = txtOrganizer.getText();
            String description = txtDescription.getText();
            boolean isActive = jCheckBox1.isSelected();
            
            Event event = new Event(
                selectedRow + 1,
                name, location, description, price, date, time,
                capacity, isActive, category, organizer
            );
            
            bookingService.updateEvent(event);
            populateTable();
            clearForm();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblEvents.getSelectedRow();
    if(selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select an event");
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete this event?");
        
    if(confirm == JOptionPane.YES_OPTION) {
        String eventName = eventTableModel.getValueAt(selectedRow, 0).toString();
        bookingService.deleteEvent(selectedRow + 1);
        eventTableModel.removeRow(selectedRow);
        clearForm();
        populateTable(); // Refresh the table
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = "EventManagement";
        String enterprise = "EventEnterprise";

        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new WorkRequestPanel(username, organization, enterprise,"EventManager"));
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed
private void clearForm() {
        txtName.setText("");
        txtLocation.setText("");
        txtPrice.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtCapacity.setText("");
        txtCategory.setText("");
        txtOrganizer.setText("");
        txtDescription.setText("");
        jCheckBox1.setSelected(false);
    }
private void saveChanges() {
        try {
            int rows = eventTableModel.getRowCount();
            for(int i = 0; i < rows; i++) {
                Event event = new Event(
                    i + 1,
                    eventTableModel.getValueAt(i, 0).toString(),
                    eventTableModel.getValueAt(i, 1).toString(),
                    eventTableModel.getValueAt(i, 8).toString(),
                    Double.parseDouble(eventTableModel.getValueAt(i, 2).toString()),
                    eventTableModel.getValueAt(i, 3).toString(),
                    eventTableModel.getValueAt(i, 4).toString(),
                    Integer.parseInt(eventTableModel.getValueAt(i, 5).toString()),
                    eventTableModel.getValueAt(i, 9).toString().equals("Active"),
                    eventTableModel.getValueAt(i, 6).toString(),
                    eventTableModel.getValueAt(i, 7).toString()
                );
                bookingService.updateEvent(event);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving changes: " + e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEvents;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOrganizer;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
