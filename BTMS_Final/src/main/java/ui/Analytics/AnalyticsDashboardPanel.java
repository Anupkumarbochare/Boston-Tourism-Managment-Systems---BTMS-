/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Analytics;

import User.UserSession;
import event.Event;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.BookingService;
import tour.Attraction;
import ui.Admin.AdminDashboard;
import ui.WorkRequest.WorkRequestPanel;

/**
 *
 * @author anupbochare
 */
public class AnalyticsDashboardPanel extends javax.swing.JPanel {

    private BookingService bookingService;
    private DefaultTableModel attractionsTableModel;
    private DefaultTableModel eventsTableModel;
    private String callingPanel;

    /**
     * Creates new form AnalyticsDashboardPanel
     */
    public AnalyticsDashboardPanel(String callingPanel) {
initComponents();
        bookingService = BookingService.getInstance();
        
        // Make components visible and opaque
        setOpaque(true);
        jTabbedPane1.setOpaque(true);
        btnBack.setOpaque(true);
        btnWorkRequest.setOpaque(true);
        setupCustomLayout();
        loadData();
        this.callingPanel = callingPanel;

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

    jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel5.setForeground(Color.WHITE);
    jLabel5.setText("Analytics Dashboard");
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

    // Style tabbed pane
    jTabbedPane1.setFont(new Font("Segoe UI", Font.BOLD, 14));
    jTabbedPane1.setBackground(Color.WHITE);
    jTabbedPane1.setBorder(null);
    contentCard.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 920, 560));

    // Setup tables inside tabbed pane
    setupTabbedPanes();

    // Footer
    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(new Color(52, 58, 64));
    footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1200, 60));

    JLabel footerText = new JLabel("Analytics Dashboard");
    footerText.setForeground(Color.WHITE);
    footerText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    footerPanel.add(footerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
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
private void setupTabbedPanes() {
    // Create tables with modern styling
    JTable attractionsTable = new JTable();
    JTable eventsTable = new JTable();

    // Style both tables
    styleTable(attractionsTable);
    styleTable(eventsTable);

    // Set up table models
    String[] columnNames = {"Name", "Total Bookings", "Revenue", "Capacity Utilization"};
    attractionsTableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    attractionsTable.setModel(attractionsTableModel);

    eventsTableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    eventsTable.setModel(eventsTableModel);

    // Style scroll panes
    JScrollPane attractionsScroll = new JScrollPane(attractionsTable);
    JScrollPane eventsScroll = new JScrollPane(eventsTable);
    attractionsScroll.setBorder(BorderFactory.createEmptyBorder());
    eventsScroll.setBorder(BorderFactory.createEmptyBorder());

    // Add styled tabs
    jTabbedPane1.addTab("Attractions Analytics", attractionsScroll);
    jTabbedPane1.addTab("Events Analytics", eventsScroll);
}


    private void styleTable(Component table) {
        if (table instanceof JTable) {
            JTable jTable = (JTable) table;
            jTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            jTable.setRowHeight(35);
            jTable.setIntercellSpacing(new Dimension(10, 10));
            jTable.setShowGrid(false);
            jTable.setBackground(Color.WHITE);
            jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
            jTable.getTableHeader().setBackground(new Color(88, 101, 242));
            jTable.getTableHeader().setForeground(Color.WHITE);
            jTable.getTableHeader().setOpaque(true);
            jTable.setSelectionBackground(new Color(237, 240, 255));
            jTable.setSelectionForeground(new Color(88, 101, 242));
        }
    }




    private void loadData() {
        loadAttractionAnalytics();
        loadEventAnalytics();
    }

    private void loadAttractionAnalytics() {
        while (attractionsTableModel.getRowCount() > 0) {
            attractionsTableModel.removeRow(0);
        }

        for (Attraction attraction : bookingService.getAvailableAttractions()) {
            if (attraction.isIsActive()) {
                int initialCapacity = attraction.getInitialCapacity();
                int currentCapacity = attraction.getCapacity();
                int bookings = initialCapacity - currentCapacity;
                double revenue = bookings * attraction.getTicketPrice();
                double utilization = (bookings * 100.0) / initialCapacity;

                attractionsTableModel.addRow(new Object[]{
                    attraction.getName(),
                    bookings,
                    String.format("$%.2f", revenue),
                    String.format("%.1f%%", utilization)
                });
            }
        }
    }

    private void loadEventAnalytics() {
        while (eventsTableModel.getRowCount() > 0) {
            eventsTableModel.removeRow(0);
        }

        for (Event event : bookingService.getAvailableEvents()) {
            if (event.isActive()) {
                int initialCapacity = event.getInitialCapacity();
                int currentCapacity = event.getCapacity();
                int bookings = initialCapacity - currentCapacity;
                double revenue = bookings * event.getTicketPrice();
                double utilization = (bookings * 100.0) / initialCapacity;

                eventsTableModel.addRow(new Object[]{
                    event.getName(),
                    bookings,
                    String.format("$%.2f", revenue),
                    String.format("%.1f%%", utilization)
                });
            }
        }
    }

    // Similar methods for events...
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        btnWorkRequest = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setOpaque(true);
        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 680, 280));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 180, 30));

        jLabel5.setText("Analytics Dashboard");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
Container parent = this.getParent();
    parent.removeAll();
    
    // Return to appropriate panel based on caller
    switch(callingPanel) {

        case "Admin":
            parent.add(new AdminDashboard());
            break;

        default:
            parent.add(new AnalyticsDashboardPanel(callingPanel));
    }
    
    parent.revalidate();
    parent.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = "DataAnalyticsTeam";
        String enterprise = "AnalyticsEnterprise";

        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new WorkRequestPanel(username, organization, enterprise,"Admin"));
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
