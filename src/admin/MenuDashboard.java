/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

/**
 *
 * @author User
 */
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MenuDashboard extends JPanel {
    public MenuDashboard() {
        // Set layout dan tambahkan komponen ke panel
        setLayout(null); // Bisa diubah sesuai kebutuhan
        JLabel label = new JLabel("Ini adalah Menu Dashboard");
        label.setBounds(50, 50, 200, 30);
        add(label);
    }
}