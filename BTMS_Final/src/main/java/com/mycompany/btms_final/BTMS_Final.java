/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.btms_final;

import javax.swing.SwingUtilities;
import ui.MainJFrame;

/**
 *
 * @author anupbochare
 */
public class BTMS_Final {

    public static void main(String[] args) {
 SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainJFrame frame = new MainJFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
