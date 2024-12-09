/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Admin;

import User.UserSession;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import ui.Attraction.TourGuideManagementPanel;
import ui.Event.EventManagerManagementPanel;
import ui.Feedback.QueryResponseJPanel;
import ui.Transport.TransportManagerManagementPanel;
import ui.WorkRequest.WorkRequestPanel;


/**
 *
 * @author anupbochare
 */
public class AdminDashboard extends javax.swing.JPanel {

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
    initComponents();
    setupCustomLayout();

    revalidate();
    repaint();
    }
private void setupCustomLayout() {
     // Main panel setup
setBackground(new Color(248, 249, 250));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    // Header navbar (dark) with gradient
    JPanel headerPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(33, 37, 41),
                getWidth(), 0, new Color(52, 58, 64)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    headerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 70));

    // Header title with improved styling
    lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
    lblTitle.setForeground(Color.WHITE);
    lblTitle.setText("Welcome to Admin Dashboard");
    
    // Add subtle text shadow
    lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
    headerPanel.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

    // Add a separator line
    JPanel separator = new JPanel();
    separator.setBackground(new Color(255, 255, 255, 50));
    headerPanel.add(separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 1160, 1));
    // Main content card with optimized background image
    JPanel contentCard = new JPanel() {
        private Image backgroundImage = null;
        {
            try {
                // Load and scale image only once
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/1.jpg"));
                if (icon.getImage() != null) {
                    backgroundImage = icon.getImage().getScaledInstance(960, 600, Image.SCALE_FAST);
                    icon.getImage().flush(); // Release original image data
                }
            } catch (Exception e) {
                System.err.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this);
            }
        }
    };
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    // Semi-transparent overlay
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255, 255, 255, 220));
    buttonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    contentCard.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 920, 560));
        // Add buttons to the button panel
        styleBootstrapButton(btnAttractionsManagement, "Manage Tour Guides", "primary");
        styleBootstrapButton(btnTransportManagement, "Manage Transport Managers", "primary");
        buttonPanel.add(btnAttractionsManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 420, 50));
        buttonPanel.add(btnTransportManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 420, 50));

        // Second row buttons
        styleBootstrapButton(btnEventManagement, "Manage Event Manager", "primary");
        styleBootstrapButton(btnAnalytics, "User Feedback", "info");
        buttonPanel.add(btnEventManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 420, 50));
        buttonPanel.add(btnAnalytics, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 420, 50));

        // Third row buttons
        styleBootstrapButton(btnHospitalityManagement, "Manage Hotels", "primary");
        styleBootstrapButton(btnUserManagement, "User Management", "warning");
        buttonPanel.add(btnHospitalityManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 420, 50));
        buttonPanel.add(btnUserManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 420, 50));

        // Fourth row buttons
        styleBootstrapButton(btnworkrequest, "Work Request", "success");
        styleBootstrapButton(btnViewAnalytics, "Analytics", "info");
        buttonPanel.add(btnworkrequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 420, 50));
        buttonPanel.add(btnViewAnalytics, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 420, 50));

        add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));
    }


private void styleBootstrapButton(JButton button, String text, String style) {
    button.setText(text);
    Color bgColor;
    switch(style) {
        case "primary": bgColor = new Color(13, 110, 253); break;
        case "secondary": bgColor = new Color(108, 117, 125); break;
        case "success": bgColor = new Color(25, 135, 84); break;
        case "danger": bgColor = new Color(220, 53, 69); break;
        case "warning": bgColor = new Color(255, 193, 7); break;
        case "info": bgColor = new Color(13, 202, 240); break;
        default: bgColor = new Color(13, 110, 253);
    }
    
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.BOLD, 14));
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(bgColor, 1),
        BorderFactory.createEmptyBorder(10, 15, 10, 15)
    ));
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(bgColor.darker());
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 2),
                BorderFactory.createEmptyBorder(9, 14, 9, 14)
            ));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(bgColor);
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
        }
        @Override
        public void mousePressed(MouseEvent e) {
            button.setBackground(bgColor.darker().darker());
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

        btnAttractionsManagement = new javax.swing.JButton();
        btnTransportManagement = new javax.swing.JButton();
        btnEventManagement = new javax.swing.JButton();
        btnAnalytics = new javax.swing.JButton();
        btnHospitalityManagement = new javax.swing.JButton();
        btnUserManagement = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnViewAnalytics = new javax.swing.JButton();
        btnworkrequest = new javax.swing.JButton();

        btnAttractionsManagement.setText("Manage Tour Guides");
        btnAttractionsManagement.setOpaque(true);
        btnAttractionsManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttractionsManagementActionPerformed(evt);
            }
        });

        btnTransportManagement.setText("Manage Transport Managers");
        btnTransportManagement.setOpaque(true);
        btnTransportManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransportManagementActionPerformed(evt);
            }
        });

        btnEventManagement.setText("Manage Event Manager");
        btnEventManagement.setOpaque(true);
        btnEventManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventManagementActionPerformed(evt);
            }
        });

        btnAnalytics.setText("User Feedback");
        btnAnalytics.setOpaque(true);
        btnAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyticsActionPerformed(evt);
            }
        });

        btnHospitalityManagement.setText("Manage Hotels");
        btnHospitalityManagement.setOpaque(true);
        btnHospitalityManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHospitalityManagementActionPerformed(evt);
            }
        });

        btnUserManagement.setText("User Management");
        btnUserManagement.setOpaque(true);
        btnUserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserManagementActionPerformed(evt);
            }
        });

        lblTitle.setText("Welcome To Admin Dashboard");

        btnViewAnalytics.setText("Analytics");
        btnViewAnalytics.setOpaque(true);
        btnViewAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAnalyticsActionPerformed(evt);
            }
        });

        btnworkrequest.setText("Work Request");
        btnworkrequest.setOpaque(true);
        btnworkrequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnworkrequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnAttractionsManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTransportManagement))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnworkrequest, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnViewAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHospitalityManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnEventManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(273, 580, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnalytics, btnAttractionsManagement, btnEventManagement, btnHospitalityManagement, btnTransportManagement, btnUserManagement, btnViewAnalytics});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTitle)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAttractionsManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransportManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEventManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHospitalityManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnworkrequest, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAnalytics, btnAttractionsManagement, btnEventManagement, btnHospitalityManagement, btnTransportManagement, btnUserManagement, btnViewAnalytics, btnworkrequest});

    }// </editor-fold>//GEN-END:initComponents

    private void btnAttractionsManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttractionsManagementActionPerformed
        Container container = this.getParent();
        container.removeAll();
        TourGuideManagementPanel tourguidePanel = new TourGuideManagementPanel("Admin"); // Pass the role
        container.add(tourguidePanel);
        container.revalidate();
        container.repaint();
    }//GEN-LAST:event_btnAttractionsManagementActionPerformed

    private void btnEventManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventManagementActionPerformed
        try {
            Container parent = this.getParent();
            if (parent != null) {
                parent.removeAll();
                EventManagerManagementPanel panel = new EventManagerManagementPanel();
                parent.add(panel);
                parent.revalidate();
                parent.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Could not find parent container");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error switching to Event Manager panel: " + e.getMessage());
        }     // TODO add your handling code here:
    }//GEN-LAST:event_btnEventManagementActionPerformed

    private void btnHospitalityManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospitalityManagementActionPerformed
        Container container = this.getParent();
        container.removeAll();
        container.add(new HospitalityManagementPanel());
        container.revalidate();
        container.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnHospitalityManagementActionPerformed

    private void btnAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyticsActionPerformed
        Container container = this.getParent();
        container.removeAll();
        container.add(new QueryResponseJPanel());
        container.revalidate();
        container.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnalyticsActionPerformed

    private void btnUserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserManagementActionPerformed
        Container container = this.getParent();
        container.removeAll();
        container.add(new UserManagementJPanel());
        container.revalidate();
        container.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnUserManagementActionPerformed

    private void btnTransportManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransportManagementActionPerformed
        Container container = this.getParent();
        container.removeAll();
        container.add(new TransportManagerManagementPanel());
        container.revalidate();
        container.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnTransportManagementActionPerformed

    private void btnViewAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAnalyticsActionPerformed
        Container container = this.getParent();
        container.removeAll();
        container.add(new AnalyticsDashboardPanel());
        container.revalidate();
        container.repaint();
    }//GEN-LAST:event_btnViewAnalyticsActionPerformed

    private void btnworkrequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnworkrequestActionPerformed
        String username = UserSession.getInstance().getUsername();
        String organization = UserSession.getInstance().getOrganization();
        String enterprise = UserSession.getInstance().getEnterprise();

        Container container = this.getParent();
        container.removeAll();
        container.add(new WorkRequestPanel(username, organization, enterprise,"Admin"));
        container.revalidate();
        container.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_btnworkrequestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalytics;
    private javax.swing.JButton btnAttractionsManagement;
    private javax.swing.JButton btnEventManagement;
    private javax.swing.JButton btnHospitalityManagement;
    private javax.swing.JButton btnTransportManagement;
    private javax.swing.JButton btnUserManagement;
    private javax.swing.JButton btnViewAnalytics;
    private javax.swing.JButton btnworkrequest;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
