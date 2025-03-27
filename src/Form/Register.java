package Form;

import Config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {
    
    private Connection conn;

    public Register() {
        initComponents();
        conn = Koneksi.getConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        t_email = new javax.swing.JTextField();
        btn_login = new javax.swing.JButton();
        btn_register = new javax.swing.JButton();
        t_username = new javax.swing.JTextField();
        t_pass = new javax.swing.JTextField();
        t_alamat = new javax.swing.JTextField();
        t_noHp = new javax.swing.JTextField();
        t_pass2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/assets/foto register.jpg"))); // NOI18N
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(142, 173, 141));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Register to");

        t_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_email.setText("EMAIL");
        t_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_emailFocusLost(evt);
            }
        });
        t_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_emailActionPerformed(evt);
            }
        });

        btn_login.setText("LOGIN");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        btn_register.setText("REGISTER");
        btn_register.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        t_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_username.setText("USERNAME");
        t_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_usernameFocusLost(evt);
            }
        });
        t_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_usernameActionPerformed(evt);
            }
        });

        t_pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_pass.setText("PASSWORD");
        t_pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_passFocusLost(evt);
            }
        });
        t_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_passActionPerformed(evt);
            }
        });

        t_alamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_alamat.setText("ALAMAT");
        t_alamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_alamatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_alamatFocusLost(evt);
            }
        });
        t_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_alamatActionPerformed(evt);
            }
        });

        t_noHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_noHp.setText("NO HP");
        t_noHp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_noHpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_noHpFocusLost(evt);
            }
        });
        t_noHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_noHpActionPerformed(evt);
            }
        });

        t_pass2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_pass2.setText("CONFIRM PASSWORD");
        t_pass2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_pass2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_pass2FocusLost(evt);
            }
        });
        t_pass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_pass2ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/assets/text_labites.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(136, 136, 136))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(t_username, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t_noHp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t_email, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(t_alamat, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(t_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(34, 34, 34)
                .addComponent(t_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_noHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(t_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_login))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 300, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 500, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void t_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_emailFocusGained
        String email = t_email.getText();
        if (email.equals("EMAIL")) {
            t_email.setText("");
        }
    }//GEN-LAST:event_t_emailFocusGained

    private void t_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_emailFocusLost
        String email = t_email.getText();
        if (email.equals("") || email.equals("EMAIL")) {
            t_email.setText("EMAIL");
        }
    }//GEN-LAST:event_t_emailFocusLost

    private void t_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_emailActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // Redirect ke Login
        Login login = new Login();
        login.setVisible(true);

        // Tutup halaman Register
        this.dispose();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        String nama = t_username.getText();
        String email = t_email.getText();
        String pass = t_pass.getText();
        String pass2 = t_pass2.getText();
        String alamat = t_alamat.getText();
        String no_hp = t_noHp.getText();
        String role = "user";
        
        if (nama.isEmpty() || nama.equals("USERNAME") || email.isEmpty() || email.equals("EMAIL") || pass.isEmpty() || pass.equals("PASSWORD") || pass2.isEmpty() || pass2.equals("CONFIRM PASSWORD") || alamat.isEmpty() || alamat.equals("ALAMAT") || no_hp.isEmpty() || no_hp.equals("NO HP")) {
            JOptionPane.showMessageDialog(this, "Semua Kolom Harus Terisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi panjang password maksimal 8 karakter
        if (pass.length() > 8) {
            JOptionPane.showMessageDialog(this, "Password maksimal 8 karakter!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi password harus sama dengan konfirmasi password
        if (!pass.equals(pass2)) {
            JOptionPane.showMessageDialog(this, "Password tidak sesuai!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String checkUser = "SELECT * FROM users WHERE email = ?";
            PreparedStatement checkEmail = conn.prepareStatement(checkUser);
            checkEmail.setString(1, email);
            ResultSet rs = checkEmail.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Email sudah digunakan!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String sql = "INSERT into users (nama, email, password, role, alamat, no_hp) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nama);
            st.setString(2, email);
            st.setString(3, pass);
            st.setString(4, role);
            st.setString(5, alamat);
            st.setString(6, no_hp);
            
            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                // Redirect ke Login
                Login login = new Login();
                login.setVisible(true);
                // Tutup halaman Register
                this.dispose();
            }
            
            st.close();
            
        } catch (Exception e) {
           Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e);

        }
    }//GEN-LAST:event_btn_registerActionPerformed

    private void t_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_usernameFocusGained
        String username = t_username.getText();
        if (username.equals("USERNAME")) {
            t_username.setText("");
        }
    }//GEN-LAST:event_t_usernameFocusGained

    private void t_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_usernameFocusLost
        String username = t_username.getText();
        if (username.equals("") || username.equals("USERNAME")) {
            t_username.setText("USERNAME");
        }
    }//GEN-LAST:event_t_usernameFocusLost

    private void t_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_usernameActionPerformed

    }//GEN-LAST:event_t_usernameActionPerformed

    private void t_passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_passFocusGained
        String pass = t_pass.getText();
        if (pass.equals("PASSWORD")) {
            t_pass.setText("");
        }
    }//GEN-LAST:event_t_passFocusGained

    private void t_passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_passFocusLost
        String pass = t_pass.getText();
        if (pass.equals("") || pass.equals("PASSWORD")) {
            t_pass.setText("PASSWORD");
        }
    }//GEN-LAST:event_t_passFocusLost

    private void t_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_passActionPerformed

    private void t_alamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_alamatFocusGained
        String alamat = t_alamat.getText();
        if (alamat.equals("ALAMAT")) {
            t_alamat.setText("");
        }
    }//GEN-LAST:event_t_alamatFocusGained

    private void t_alamatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_alamatFocusLost
        String alamat = t_alamat.getText();
        if (alamat.equals("") || alamat.equals("ALAMAT")) {
            t_alamat.setText("ALAMAT");
        }
    }//GEN-LAST:event_t_alamatFocusLost

    private void t_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_alamatActionPerformed

    private void t_noHpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_noHpFocusGained
        String no_hp = t_noHp.getText();
        if (no_hp.equals("NO HP")) {
            t_noHp.setText("");
        }
    }//GEN-LAST:event_t_noHpFocusGained

    private void t_noHpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_noHpFocusLost
        String no_hp = t_noHp.getText();
        if (no_hp.equals("") || no_hp.equals("NO HP")) {
            t_noHp.setText("NO HP");
        }
    }//GEN-LAST:event_t_noHpFocusLost

    private void t_noHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_noHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_noHpActionPerformed

    private void t_pass2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_pass2FocusGained
        String pass = t_pass2.getText();
        if (pass.equals("CONFIRM PASSWORD")) {
            t_pass2.setText("");
        }
    }//GEN-LAST:event_t_pass2FocusGained

    private void t_pass2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_pass2FocusLost
        String pass = t_pass2.getText();
        if (pass.equals("") || pass.equals("PASSWORD")) {
            t_pass2.setText("CONFIRM PASSWORD");
        }
    }//GEN-LAST:event_t_pass2FocusLost

    private void t_pass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_pass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_pass2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField t_alamat;
    private javax.swing.JTextField t_email;
    private javax.swing.JTextField t_noHp;
    private javax.swing.JTextField t_pass;
    private javax.swing.JTextField t_pass2;
    private javax.swing.JTextField t_username;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        t_username.setText("");
        t_email.setText("");
        t_pass.setText("");
        t_pass2.setText("");
        t_alamat.setText("");
        t_noHp.setText("");
    }
    
}
