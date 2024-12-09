/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.View;
import Functional_Dependencies.QRCodeGenerator;
import User.UserSession;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import com.sun.jdi.connect.Transport;
import event.Event;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.Document;
import service.BookingService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONObject;
import tour.Attraction;
import tourist.Tourist;
import ui.Tourist.TouristJPanel;

/**
 *
 * @author anupbochare
 */
public class ViewAttractionAndEventPanel extends javax.swing.JPanel {
    private BookingService bookingService;
    private DefaultTableModel attractionTableModel;
    private DefaultTableModel eventTableModel;
    private DefaultTableModel hospitalityTableModel;

    /**
     * Creates new form TouristJPanel
     */
    public ViewAttractionAndEventPanel() {
    initComponents();
    bookingService = BookingService.getInstance();
    bookingService.registerTouristPanel(this);
    setupCustomLayout();

    attractionTableModel = (DefaultTableModel) tblAttractions.getModel();
    eventTableModel = (DefaultTableModel) tblEvents.getModel();
    setupTables();
    refreshData();

    }
public void refreshData() {
    populateTables();
    // Notify the UserManagementPanel to refresh
    if (bookingService != null) {
        bookingService.notifyUserManagementPanel();
    }}   
    private void setupTables() {
         attractionTableModel = (DefaultTableModel) tblAttractions.getModel();
    attractionTableModel.setColumnIdentifiers(new String[]{
        "Name", "Location", "Description", "Price", "Hours", "Available Capacity"
    });
    
    // Setup Events table
    eventTableModel = (DefaultTableModel) tblEvents.getModel();
    eventTableModel.setColumnIdentifiers(new String[]{
        "Name", "Location", "Description", "Price", "Date", "Time", "Available Capacity"
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

    // Header buttons
    styleBootstrapButton(btnBack, "← Back", "secondary");
    styleBootstrapButton(btnDownloadAttractionAndEventList, "Download List", "primary");
    headerPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 100, 35));
    headerPanel.add(btnDownloadAttractionAndEventList, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 12, 150, 35));

    // Main content card
    JPanel contentCard = new JPanel();
    contentCard.setBackground(Color.WHITE);
    contentCard.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(222, 226, 230)),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

    // Attractions section
    jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
    jLabel2.setText("Available Attractions");
    contentCard.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

    // Attractions table
    styleTable(tblAttractions);
    contentCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 920, 200));

    // Attraction buttons
    styleBootstrapButton(btnBookAttraction, "Book Attraction", "success");
    styleBootstrapButton(btnOpenMaps, "Open Maps", "info");
    
    contentCard.add(btnBookAttraction, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 35));
    contentCard.add(btnOpenMaps, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 120, 35));

    // Events section
    jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
    jLabel1.setText("Available Events");
    contentCard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

    // Events table
    styleTable(tblEvents);
    contentCard.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 920, 200));

    // Event button
    styleBootstrapButton(tbnBookEvent, "Book Event", "success");
    contentCard.add(tbnBookEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 150, 35));
}

private void styleTable(JTable table) {
    table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    table.setRowHeight(40);
    table.setShowGrid(false);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setBackground(Color.WHITE);
    
    JTableHeader header = table.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
    header.setBackground(new Color(33, 37, 41));
    header.setForeground(Color.WHITE);
    header.setBorder(BorderFactory.createEmptyBorder());
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    
    table.setSelectionBackground(new Color(237, 240, 255));
    table.setSelectionForeground(new Color(88, 101, 242));
    
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        case "success": bgColor = new Color(25, 135, 84); break;
        case "info": bgColor = new Color(13, 202, 240); break;
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
private void openGoogleMaps(String location) {
    try {
        String encodedLocation = java.net.URLEncoder.encode(location, "UTF-8");
        String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + encodedLocation;
        java.awt.Desktop.getDesktop().browse(new java.net.URI(mapsUrl));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error opening maps: " + ex.getMessage());
    }}
private void populateTables() {
    // Clear existing data
    attractionTableModel.setRowCount(0);
    eventTableModel.setRowCount(0);
    
    // Repopulate with fresh data
    for(Attraction attraction : bookingService.getAvailableAttractions()) {
        if(attraction.isIsActive()) {
            attractionTableModel.addRow(new Object[]{
                attraction.getName(),
                attraction.getLocation(),
                attraction.getDescription(),
                attraction.getTicketPrice(),
                attraction.getHours(),
                attraction.getCapacity()
            });
        }
    }
    
    for(Event event : bookingService.getAvailableEvents()) {
        if(event.isActive()) {
            eventTableModel.addRow(new Object[]{
                event.getName(),
                event.getLocation(),
                event.getDescription(),
                event.getTicketPrice(),
                event.getDate(),
                event.getTime(),
                event.getCapacity()
            });
        }
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
        tblAttractions = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEvents = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBookAttraction = new javax.swing.JButton();
        tbnBookEvent = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnDownloadAttractionAndEventList = new javax.swing.JButton();
        btnOpenMaps = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAttractions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Location", "Description", "Price", "Hours", "Available Capacity"
            }
        ));
        jScrollPane1.setViewportView(tblAttractions);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 870, 234));

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Location", "Description", "Price", "Date", "Time", "Available Capacity"
            }
        ));
        jScrollPane2.setViewportView(tblEvents);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 411, 850, 230));

        jLabel1.setText("Available Events");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 388, -1, -1));

        jLabel2.setText("Available Attractions");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 78, -1, -1));

        btnBookAttraction.setText("Book Attractions");
        btnBookAttraction.setOpaque(true);
        btnBookAttraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookAttractionActionPerformed(evt);
            }
        });
        add(btnBookAttraction, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 347, -1, -1));

        tbnBookEvent.setText("Book Event");
        tbnBookEvent.setOpaque(true);
        tbnBookEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnBookEventActionPerformed(evt);
            }
        });
        add(tbnBookEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 680, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        btnDownloadAttractionAndEventList.setText("Download");
        btnDownloadAttractionAndEventList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadAttractionAndEventListActionPerformed(evt);
            }
        });
        add(btnDownloadAttractionAndEventList, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 100, 30));

        btnOpenMaps.setText("Open Maps");
        btnOpenMaps.setOpaque(true);
        btnOpenMaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenMapsActionPerformed(evt);
            }
        });
        add(btnOpenMaps, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBookAttractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookAttractionActionPerformed
    int selectedRow = tblAttractions.getSelectedRow();
    if(selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select an attraction");
        return;
    }
    
    try {
        int currentCapacity = Integer.parseInt(tblAttractions.getValueAt(selectedRow, 5).toString());
        if(currentCapacity <= 0) {
            JOptionPane.showMessageDialog(this, "Sorry, this attraction is fully booked!");
            return;
        }
        
        // Get attraction and current user
        String attractionName = tblAttractions.getValueAt(selectedRow, 0).toString();
        Attraction attraction = bookingService.getAvailableAttractions().get(selectedRow);
String username = UserSession.getInstance().getUsername();
            Tourist tourist = bookingService.getTouristByUsername(username);

    if (username != null && tourist != null) {
        bookingService.bookAttraction(attraction, username);
        refreshData();

    }        
        // Book attraction and update capacity
        tblAttractions.setValueAt(currentCapacity - 1, selectedRow, 5);
        
        // Generate booking ID and QR code
        String bookingId = "ATT-" + System.currentTimeMillis();
        String bookingDetails = "Dear " + tourist.getName() + ",\n\n" +
                           "Your booking is confirmed!\n" +
                           "Attraction: " + attractionName + "\n" +
                           "Booking ID: " + bookingId + "\n" +
                           "Date: " + java.time.LocalDate.now() + "\n\n" +
                           "Thank you for booking with us!";
        EmailService.sendBookingConfirmation(tourist.getEmail(), bookingDetails);
        
        // Generate and show QR code
        BufferedImage qrImage = QRCodeGenerator.generateQRCodeImage(attractionName, bookingId);
        
        // Display QR in dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Booking Confirmation");
        dialog.setLayout(new BorderLayout());
        
        // Add QR code
        dialog.add(new JLabel(new ImageIcon(qrImage)), BorderLayout.CENTER);
        
        // Add booking details
        JLabel detailsLabel = new JLabel("<html>" + bookingDetails.replace("\n", "<br/>") + "</html>");
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(detailsLabel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
            
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Error in booking: " + e.getMessage());
    }
    
    }//GEN-LAST:event_btnBookAttractionActionPerformed

    private void tbnBookEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnBookEventActionPerformed
int selectedRow = tblEvents.getSelectedRow();
    if(selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select an event");
        return;
    }
    
    try {
        int currentCapacity = Integer.parseInt(tblEvents.getValueAt(selectedRow, 6).toString());
        if(currentCapacity <= 0) {
            JOptionPane.showMessageDialog(this, "Sorry, this event is fully booked!");
            return;
        }
        
        // Get event and current user
        String eventName = tblEvents.getValueAt(selectedRow, 0).toString();
        Event event = bookingService.getAvailableEvents().get(selectedRow);
    String username = UserSession.getInstance().getUsername();
    Tourist tourist = bookingService.getTouristByUsername(username);
    if (username != null && tourist != null) {
        bookingService.bookEvent(event, username);
        refreshData();
    }        
        // Book event and update capacity
        tblEvents.setValueAt(currentCapacity - 1, selectedRow, 6);
        
        // Generate booking ID and QR code
        String bookingId = "EVENT-" + System.currentTimeMillis();
       String bookingDetails = "Dear " + tourist.getName() + ",\n\n" +
                           "Your booking is confirmed!\n" +
                           "Event: " + eventName + "\n" +
                           "Booking ID: " + bookingId + "\n" +
                           "Date: " + java.time.LocalDate.now() + "\n\n" +
                           "Thank you for booking with us!";
    
    // Send email confirmation
    EmailService.sendBookingConfirmation(tourist.getEmail(), bookingDetails);
        
        // Generate and show QR code
        BufferedImage qrImage = QRCodeGenerator.generateQRCodeImage(eventName, bookingId);
        
        // Display QR in dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Event Booking Confirmation");
        dialog.setLayout(new BorderLayout());
        
        // Add QR code
        dialog.add(new JLabel(new ImageIcon(qrImage)), BorderLayout.CENTER);
        
        // Add booking details
        JLabel detailsLabel = new JLabel("<html>" + bookingDetails.replace("\n", "<br/>") + "</html>");
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(detailsLabel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
            
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Error in booking: " + e.getMessage());
    }
    }//GEN-LAST:event_tbnBookEventActionPerformed

    private void btnDownloadAttractionAndEventListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadAttractionAndEventListActionPerformed
    try {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Title
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(180, 750);
        contentStream.showText("Boston Tourism Guide");
        contentStream.endText();
        
        // Attractions Section
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Available Attractions");
        contentStream.endText();
        
        // Attractions Content
        float yPosition = 670;
        for (int i = 0; i < tblAttractions.getRowCount(); i++) {
            String name = tblAttractions.getValueAt(i, 0).toString();
            String location = tblAttractions.getValueAt(i, 1).toString();
            String capacity = tblAttractions.getValueAt(i, 5).toString();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("• " + name);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(70, yPosition - 20);
            contentStream.showText("Location: " + location);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(70, yPosition - 40);
            contentStream.showText("Available Capacity: " + capacity);
            contentStream.endText();
            
            yPosition -= 70;
            
            if (yPosition < 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = 750;
            }
        }
        
        // Events Section
        if (yPosition < 150) {
            contentStream.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            contentStream = new PDPageContentStream(document, page);
            yPosition = 750;
        }
        
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.newLineAtOffset(50, yPosition);
        contentStream.showText("Available Events");
        contentStream.endText();
        
        yPosition -= 40;
        
        // Events Content
        for (int i = 0; i < tblEvents.getRowCount(); i++) {
            String name = tblEvents.getValueAt(i, 0).toString();
            String location = tblEvents.getValueAt(i, 1).toString();
            String date = tblEvents.getValueAt(i, 4).toString();
            String capacity = tblEvents.getValueAt(i, 6).toString();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("• " + name);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(70, yPosition - 20);
            contentStream.showText("Location: " + location);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(70, yPosition - 40);
            contentStream.showText("Date: " + date);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(70, yPosition - 60);
            contentStream.showText("Available Capacity: " + capacity);
            contentStream.endText();
            
            yPosition -= 90;
            
            if (yPosition < 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = 750;
            }
        }
        
        contentStream.close();
        
        String filePath = System.getProperty("user.home") + "/Downloads/BostonAttractionsList.pdf";
        document.save(filePath);
        document.close();
        
        JOptionPane.showMessageDialog(this, "PDF has been downloaded to Downloads folder!");
        
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error generating PDF: " + e.getMessage());
    }
    }//GEN-LAST:event_btnDownloadAttractionAndEventListActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Container parent = this.getParent();
        parent.removeAll();
        parent.add(new TouristJPanel());
        parent.revalidate();
        parent.repaint();       // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnOpenMapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenMapsActionPerformed
  int attractionRow = tblAttractions.getSelectedRow();
    int eventRow = tblEvents.getSelectedRow();
    
    try {
        String name = "";
        if (attractionRow >= 0) {
            name = tblAttractions.getValueAt(attractionRow, 0).toString();
        } else if (eventRow >= 0) {
            name = tblEvents.getValueAt(eventRow, 0).toString();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an attraction or event");
            return;
        }
        
        String encodedLocation = java.net.URLEncoder.encode(name + ", Boston, MA", "UTF-8");
        String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + encodedLocation;
        java.awt.Desktop.getDesktop().browse(new java.net.URI(mapsUrl));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error opening maps: " + ex.getMessage());
    }       // TODO add your handling code here:
    }//GEN-LAST:event_btnOpenMapsActionPerformed
public class EmailService {
 private static final String SENDER_EMAIL = "anup.bochare.7@gmail.com";
    private static final String APP_PASSWORD = "focuslotkwsfgtyy"; // Your Gmail app-specific password
    public static void sendBookingConfirmation(String email, String bookingDetails) {
        Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Booking Confirmation");
            message.setText(bookingDetails);

            javax.mail.Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBookAttraction;
    private javax.swing.JButton btnDownloadAttractionAndEventList;
    private javax.swing.JButton btnOpenMaps;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAttractions;
    private javax.swing.JTable tblEvents;
    private javax.swing.JButton tbnBookEvent;
    // End of variables declaration//GEN-END:variables
 
}
