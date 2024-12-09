/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Tourist;

import User.UserSession;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import org.json.JSONObject;
import service.BookingService;
import ui.Feedback.QueryJPanel;
import ui.PhotoGallery.PhotoGalleryPanel;
import ui.View.ViewAttractionAndEventPanel;
import ui.View.ViewHospitalityPanel;
import ui.WorkRequest.WorkRequestPanel;

/**
 *
 * @author anupbochare
 */
public class TouristJPanel extends javax.swing.JPanel {
    private BookingService bookingService;
private Timer weatherUpdateTimer;
    /**
     * Creates new form ViewAttractions
     */
    public TouristJPanel() {
            bookingService = BookingService.getInstance();

        initComponents();
    setupWeatherWidget();  

        setupCustomLayout();


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

    // Welcome message in header
    JLabel welcomeLabel = new JLabel("Welcome, " + UserSession.getInstance().getUsername());
    welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
    welcomeLabel.setForeground(Color.WHITE);
    headerPanel.add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, -1));

    // Main content card with background image
    JPanel contentCard = new JPanel() {
        private Image backgroundImage = null;
        {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/tourist-bg.jpg"));
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

    // Semi-transparent overlay for better readability
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255, 255, 255, 180));
    buttonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    contentCard.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 920, 560));

    // Feature buttons - First row
    styleBootstrapButton(btnviewattractionsandevents, "🎫 Attractions & Events", "primary");
    styleBootstrapButton(btnQuery, "❓ Need Help?", "info");
    buttonPanel.add(btnviewattractionsandevents, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 420, 100));
    buttonPanel.add(btnQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 420, 100));

    // Feature buttons - Second row
    styleBootstrapButton(btnShowHospitality, "🏨 Restaurants & Hotels", "primary");
    styleBootstrapButton(btnWorkRequest, "📝 Work Request", "success");
    buttonPanel.add(btnShowHospitality, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 420, 100));
    buttonPanel.add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 420, 100));

    // Photo Gallery button
    styleBootstrapButton(btnShowHospitality2, "📸 Photo Gallery", "primary");
    buttonPanel.add(btnShowHospitality2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, 100));

    // Weather widget
    setupWeatherWidget();
    weatherPanel.setBackground(new Color(79, 70, 229, 200));
    weatherPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(67, 56, 202), 2),
        BorderFactory.createEmptyBorder(15, 20, 15, 20)
    ));
    buttonPanel.add(weatherPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 420, 200));

    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));
}

private void styleBootstrapButton(JButton button, String text, String style) {
    button.setText(text);
    Color bgColor;
    switch(style) {
        case "primary": bgColor = new Color(13, 110, 253); break;
        case "success": bgColor = new Color(25, 135, 84); break;
        case "info": bgColor = new Color(13, 202, 240); break;
        default: bgColor = new Color(13, 110, 253);
    }
    
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
private String[] weatherQuotes = {
    "☔ Check the weather or regret your outfit choice!",
    "🌞 Boston Weather: Dress accordingly or blame yourself",
    "🌈 Mother Nature's Mood Swing Report",
    "🌤 Before you leave: Weather Spoiler Alert!",
    "🌪 Boston's Weather Roulette",
    "🌡 Will you need a jacket? Let's find out!",
    "⛈ Weather Update: Because surprises aren't always fun",
    "🌦 Boston's Weather: Plot twist every hour",
    "❄ Dress for the weather you want? Nah, check this first!",
    "🌥 Today's Weather: Your outfit's best friend or worst enemy"
};    
private void setupWeatherWidget() {
     Random rand = new Random();
    String randomQuote = weatherQuotes[rand.nextInt(weatherQuotes.length)];
    
    // Set as panel title
    weatherPanel.setBorder(BorderFactory.createTitledBorder(randomQuote));
    // Set labels text
    locationLabel.setText("Boston, MA");
    temperatureLabel.setText("Loading...");
    conditionLabel.setText("");
    
    // Start weather updates
    weatherUpdateTimer = new Timer(300000, e -> updateWeatherDisplay());
    weatherUpdateTimer.start();
    updateWeatherDisplay();
}


private void updateWeatherDisplay() {
    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            try {
                String url = "https://api.open-meteo.com/v1/forecast?latitude=42.36&longitude=-71.06&current_weather=true&temperature_unit=fahrenheit";
                URL weatherUrl = new URL(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(weatherUrl.openStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                JSONObject weatherData = new JSONObject(response.toString());
                JSONObject currentWeather = weatherData.getJSONObject("current_weather");
                double temp = currentWeather.getDouble("temperature");
                
                SwingUtilities.invokeLater(() -> {
                    temperatureLabel.setText(String.format("%.1f°F", temp));
                });
            } catch (Exception e) {
                System.err.println("Weather update failed: " + e.getMessage());
            }
            return null;
        }
    };
    worker.execute();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnShowHospitality2 = new javax.swing.JButton();
        btnShowHospitality = new javax.swing.JButton();
        btnQuery = new javax.swing.JButton();
        btnviewattractionsandevents = new javax.swing.JButton();
        weatherPanel = new javax.swing.JPanel();
        locationLabel = new javax.swing.JLabel();
        temperatureLabel = new javax.swing.JLabel();
        conditionLabel = new javax.swing.JLabel();
        btnWorkRequest = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnShowHospitality2.setText("View Photo Gallery");
        btnShowHospitality2.setOpaque(true);
        btnShowHospitality2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowHospitality2ActionPerformed(evt);
            }
        });
        add(btnShowHospitality2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 199, 79));

        btnShowHospitality.setText("View Restaurants and Hotels");
        btnShowHospitality.setOpaque(true);
        btnShowHospitality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowHospitalityActionPerformed(evt);
            }
        });
        add(btnShowHospitality, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 79));

        btnQuery.setText("Need Help ?");
        btnQuery.setOpaque(true);
        btnQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });
        add(btnQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 199, 79));

        btnviewattractionsandevents.setText("View Attraction And Events");
        btnviewattractionsandevents.setOpaque(true);
        btnviewattractionsandevents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewattractionsandeventsActionPerformed(evt);
            }
        });
        add(btnviewattractionsandevents, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 199, 79));

        weatherPanel.setBackground(new java.awt.Color(102, 204, 255));
        weatherPanel.setForeground(new java.awt.Color(255, 255, 255));
        weatherPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        locationLabel.setBackground(new java.awt.Color(255, 255, 255));
        locationLabel.setForeground(new java.awt.Color(255, 255, 255));
        locationLabel.setText("Boston,Ma");
        weatherPanel.add(locationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        temperatureLabel.setBackground(new java.awt.Color(255, 255, 255));
        temperatureLabel.setForeground(new java.awt.Color(255, 255, 255));
        temperatureLabel.setText("Temperature: --°F");
        weatherPanel.add(temperatureLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        conditionLabel.setBackground(new java.awt.Color(255, 255, 255));
        conditionLabel.setForeground(new java.awt.Color(255, 255, 255));
        conditionLabel.setText("Condition: --");
        weatherPanel.add(conditionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        add(weatherPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 340, 520, 160));

        btnWorkRequest.setText("Work Request");
        btnWorkRequest.setOpaque(true);
        btnWorkRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkRequestActionPerformed(evt);
            }
        });
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 200, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowHospitality2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowHospitality2ActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new PhotoGalleryPanel());
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnShowHospitality2ActionPerformed

    private void btnShowHospitalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowHospitalityActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new ViewHospitalityPanel());
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnShowHospitalityActionPerformed

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new QueryJPanel());
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnQueryActionPerformed

    private void btnviewattractionsandeventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewattractionsandeventsActionPerformed
               Container parent = this.getParent();
        parent.removeAll();
        parent.add(new ViewAttractionAndEventPanel());
        parent.revalidate();
        parent.repaint(); // TODO add your handling code here:
    }//GEN-LAST:event_btnviewattractionsandeventsActionPerformed

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkRequestActionPerformed
  String username = UserSession.getInstance().getUsername();
    String organization = "TouristOrganization";
    String enterprise = "TourismEnterprise";
    
    Container parent = this.getParent();
    parent.removeAll();
    parent.add(new WorkRequestPanel(username, organization, enterprise,"Tourist"));
    parent.revalidate();
    parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnWorkRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuery;
    private javax.swing.JButton btnShowHospitality;
    private javax.swing.JButton btnShowHospitality2;
    private javax.swing.JButton btnWorkRequest;
    private javax.swing.JButton btnviewattractionsandevents;
    private javax.swing.JLabel conditionLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel temperatureLabel;
    private javax.swing.JPanel weatherPanel;
    // End of variables declaration//GEN-END:variables
}
