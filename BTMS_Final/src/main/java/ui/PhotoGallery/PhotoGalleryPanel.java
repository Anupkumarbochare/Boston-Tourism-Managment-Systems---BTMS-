/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.PhotoGallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author anupbochare
 */
public class PhotoGalleryPanel extends javax.swing.JPanel {
private JPanel galleryPanel;
    private static final String[] ATTRACTIONS = {
        "Charles River Esplanade",
        "Fenway Park",
        "Bunker Hill Monument",
        "Freedom Trail",
        "Boston Tea Party Ships",
        "Museum of Fine Arts",
        "Boston Commons",
        "Seaport",
        "Boston View",
        "Boston Public Gardern",
        "Boston Commons Circle",
        "Quincy Market"
        
    };
    /**
     * Creates new form PhotoGalleryPanel
     */
    public PhotoGalleryPanel() {
       initComponents();
                setupCustomLayout();
                revalidate();
                repaint();

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

        // Header title
        JLabel titleLabel = new JLabel("Boston Attractions Gallery");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, -1));

        // Main content card
        JPanel contentCard = new JPanel();
        contentCard.setBackground(Color.WHITE);
        contentCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contentCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(contentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 960, 600));

        // Gallery grid
        galleryPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add photos to gallery
        for (int i = 0; i < ATTRACTIONS.length; i++) {
            addPhotoCard(ATTRACTIONS[i], (i + 1) + ".jpg");
        }

        // Add gallery to scroll pane
        JScrollPane scrollPane = new JScrollPane(galleryPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentCard.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 940, 580));
    }

    private void addPhotoCard(String attractionName, String imageName) {
        // Create card panel with shadow effect
        JPanel photoCard = new JPanel();
        photoCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        photoCard.setBackground(Color.WHITE);
        photoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        // Image
        ImageIcon icon = loadImage("/images/" + imageName);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                photoCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(13, 110, 253)),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                photoCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(222, 226, 230)),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                showLargeImage(attractionName, icon);
            }
        });
        photoCard.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 250));

        // Caption
        JLabel captionLabel = new JLabel(attractionName);
        captionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        captionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoCard.add(captionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 380, -1));

        galleryPanel.add(photoCard);
    }

    private ImageIcon loadImage(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                BufferedImage img = ImageIO.read(imgURL);
                return new ImageIcon(img.getScaledInstance(400, 250, Image.SCALE_SMOOTH));
            }
            return createPlaceholderImage();
        } catch (IOException e) {
            return createPlaceholderImage();
        }
    }

    private ImageIcon createPlaceholderImage() {
        BufferedImage placeholder = new BufferedImage(400, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = placeholder.createGraphics();
        g2d.setColor(new Color(233, 236, 239));
        g2d.fillRect(0, 0, 400, 250);
        g2d.dispose();
        return new ImageIcon(placeholder);
    }

    private void showLargeImage(String attractionName, ImageIcon icon) {
        JDialog dialog = new JDialog();
        dialog.setTitle(attractionName);
        dialog.setModal(true);
        dialog.setBackground(new Color(248, 249, 250));

        JPanel dialogContent = new JPanel(new BorderLayout(0, 10));
        dialogContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialogContent.setBackground(Color.WHITE);

        Image img = icon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        dialogContent.add(imageLabel, BorderLayout.CENTER);

        dialog.add(dialogContent);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
