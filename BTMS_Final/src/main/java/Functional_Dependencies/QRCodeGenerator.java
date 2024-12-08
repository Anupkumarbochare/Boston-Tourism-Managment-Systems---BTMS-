/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functional_Dependencies;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author anupbochare
 */
public class QRCodeGenerator {
      public static BufferedImage generateQRCodeImage(String eventName, String bookingId) {
        try {
            // Format date for Google Calendar
            LocalDateTime eventDate = LocalDateTime.now();
            String startDate = eventDate.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
            String endDate = eventDate.plusHours(2).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
            
            // Create Google Calendar URL
            String calendarUrl = String.format(
                "https://calendar.google.com/calendar/render?" +
                "action=TEMPLATE" +
                "&text=%s" +
                "&details=Booking ID: %s" +
                "&dates=%s/%s",
                URLEncoder.encode(eventName, "UTF-8"),
                URLEncoder.encode(bookingId, "UTF-8"),
                startDate,
                endDate
            );
            
            // Generate QR code
            BitMatrix matrix = new MultiFormatWriter().encode(
                calendarUrl,
                BarcodeFormat.QR_CODE,
                300, 300
            );
            
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }

    public static void generateAndShowQRCode(String bookingDetails) {
        try {
            // Create meaningful QR content
            String qrContent = "Boston Tourism Management System\n" +
                             "------------------------\n" +
                             bookingDetails + "\n" +
                             "Booking Time: " + java.time.LocalDateTime.now() + "\n" +
                             "Status: Confirmed";
                             
            BitMatrix matrix = new MultiFormatWriter().encode(
                qrContent,
                BarcodeFormat.QR_CODE,
                200, 200
            );
            
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
            
            // Display QR code in dialog
            JDialog dialog = new JDialog();
            dialog.setTitle("Booking QR Code");
            dialog.add(new JLabel(new ImageIcon(qrImage)));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
