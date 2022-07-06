/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbl3;

import view.MainPage;

/**
 *
 * @author Admin
 */
public class PBL3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }
    
}
