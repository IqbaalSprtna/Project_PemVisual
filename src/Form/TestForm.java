package Form;

import Config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TestForm extends javax.swing.JFrame {

    private Connection conn;
    
    public TestForm() {
        initComponents();
        conn = Koneksi.getConnection();
        getData();
    }
    
    private void getData() {
        DefaultTableModel model = (DefaultTableModel) table_data.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String email = rs.getString("email");
                String pass = rs.getString("password");
                String role = rs.getString("role");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");
            
                Object[] rowData = {id, nama,email,pass,role,created_at,updated_at};
                model.addRow(rowData);
            }
            
            rs.close();
            st.close();
                    
        } catch (Exception e) {
            Logger.getLogger(TestForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_name = new javax.swing.JLabel();
        t_name = new javax.swing.JTextField();
        lb_email = new javax.swing.JLabel();
        t_email = new javax.swing.JTextField();
        lb_pass = new javax.swing.JLabel();
        t_add = new javax.swing.JButton();
        t_update = new javax.swing.JButton();
        t_riset = new javax.swing.JButton();
        t_delet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_data = new javax.swing.JTable();
        t_pass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        setSize(new java.awt.Dimension(0, 0));

        lb_name.setText("Name");

        lb_email.setText("Email");

        lb_pass.setText("Password");

        t_add.setText("Add");
        t_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_addActionPerformed(evt);
            }
        });

        t_update.setText("Update");
        t_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_updateActionPerformed(evt);
            }
        });

        t_riset.setText("Clear");
        t_riset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_risetActionPerformed(evt);
            }
        });

        t_delet.setText("Delet");
        t_delet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_deletActionPerformed(evt);
            }
        });

        table_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Email", "Password", "Role", "Created_at", "Updated_at"
            }
        ));
        table_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_dataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_data);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_pass)
                            .addComponent(lb_name)
                            .addComponent(lb_email))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_name, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t_email)
                            .addComponent(t_pass, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(t_add)
                        .addGap(18, 18, 18)
                        .addComponent(t_update)
                        .addGap(18, 18, 18)
                        .addComponent(t_riset)
                        .addGap(18, 18, 18)
                        .addComponent(t_delet)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_name)
                    .addComponent(t_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_email)
                    .addComponent(t_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_pass)
                    .addComponent(t_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_add)
                    .addComponent(t_update)
                    .addComponent(t_riset)
                    .addComponent(t_delet))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void t_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_addActionPerformed
        String name = t_name.getText();
        String email = t_email.getText();
        String pass = t_pass.getText();
        String role = "user";
        
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua Kolom Harus Terisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
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
            
            String sql = "INSERT into users (name, email, password, role) VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, pass);
            st.setString(4, role);
            
            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                resetForm();
                getData();
            }
            
            st.close();
            
        } catch (Exception e) {
           Logger.getLogger(TestForm.class.getName()).log(Level.SEVERE, null, e);

        } 
    }//GEN-LAST:event_t_addActionPerformed

    private void t_risetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_risetActionPerformed
        resetForm();
    }//GEN-LAST:event_t_risetActionPerformed

    private void t_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_updateActionPerformed
        int selectedRow = table_data.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih Baris yang Akan di Perbarui!");
            return;
        }
        
        String id = table_data.getValueAt(selectedRow, 0).toString();
        String name = t_name.getText();
        String email = t_email.getText();
        String password = t_pass.getText();
        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua Kolom Harus Terisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        try {   
            String sql = "UPDATE users SET nama=?, password=? WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, password);
            st.setString(3, id);
            
            int rowUpdated = st.executeUpdate();
            if (rowUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui!");
                resetForm();
                getData();
            }
            
            st.close();
        } catch (Exception e) {
            Logger.getLogger(TestForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_t_updateActionPerformed

    private void table_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_dataMouseClicked
        int selectedRow = table_data.getSelectedRow();
        if (selectedRow != -1) {
            String name = table_data.getValueAt(selectedRow, 1).toString();
            String email = table_data.getValueAt(selectedRow, 2).toString();
            String pass = table_data.getValueAt(selectedRow, 3).toString();
            
            t_name.setText(name);
            t_email.setText(email);
            t_pass.setText(pass);
        }
        
        t_email.setEditable(false);
    }//GEN-LAST:event_table_dataMouseClicked

    private void t_deletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_deletActionPerformed
        int selectedRow = table_data.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih Baris yang Akan di Perbarui!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda Yakin Ingin Menghapus Data Ini ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = table_data.getValueAt(selectedRow, 0).toString();
            
            try {
                String sql = "DELETE FROM users WHERE id=?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, id);
                
                int rowDeleted = st.executeUpdate();
                if (rowDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus!");
                resetForm();
                getData();
            }
            
            st.close();
            } catch (Exception e) {
                Logger.getLogger(TestForm.class.getName()).log(Level.SEVERE, null, e);

            }
        }
    }//GEN-LAST:event_t_deletActionPerformed

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
            java.util.logging.Logger.getLogger(TestForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_email;
    private javax.swing.JLabel lb_name;
    private javax.swing.JLabel lb_pass;
    private javax.swing.JButton t_add;
    private javax.swing.JButton t_delet;
    private javax.swing.JTextField t_email;
    private javax.swing.JTextField t_name;
    private javax.swing.JPasswordField t_pass;
    private javax.swing.JButton t_riset;
    private javax.swing.JButton t_update;
    private javax.swing.JTable table_data;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        t_name.setText("");
        t_email.setText("");
        t_pass.setText("");
    }

}

    
