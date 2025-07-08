package Form;

import Config.Koneksi;
import Form.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author moham
 */
public class OrderForm extends javax.swing.JFrame {
    
    private DefaultTableModel tabmode;
    private Connection conn;

    /**
     * Creates new form OrderForm
     */
    public OrderForm() {
        initComponents();
        conn = Koneksi.getConnection();
        tablekeranjang();
        hitungTotalPesanan();
        hitungTotalNet();
    }

    protected void tablekeranjang() {
        Object[] Baris = {"ID", "User ID", "Nama Produk", "Harga Satuan", "Jumlah"};
        tabmode = new DefaultTableModel(null, Baris);
        tbjual.setModel(tabmode);

        String sql = "SELECT cart.id, cart.user_id, cart.product_id, products.nama, products.harga, cart.jumlah " +
                     "FROM cart JOIN products ON cart.product_id = products.id";

        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while (hasil.next()) {
                String id = String.valueOf(hasil.getInt("id"));
                String userId = String.valueOf(hasil.getInt("user_id"));
                String namaProduk = String.valueOf(hasil.getString("nama"));
                String hargaSatuan = String.valueOf(hasil.getInt("harga"));
                String jumlah = String.valueOf(hasil.getInt("jumlah"));

                String[] data = {id, userId, namaProduk, hargaSatuan, jumlah};
                tabmode.addRow(data);
            }

            hasil.close();
            stat.close();
       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data keranjang: " + e.getMessage());
        }
    }
    
    protected void hitungTotalPesanan() {
        try {
            String sql = "SELECT SUM(cart.jumlah * products.harga) AS total " +
                         "FROM cart JOIN products ON cart.product_id = products.id";

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            if (rs.next()) {
                int total = rs.getInt("total");
                tTotalPesanan.setText(String.valueOf(total));
            } else {
                tTotalPesanan.setText("0");
            }

            rs.close();
            stat.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghitung total cart: " + e.getMessage());
        }
    }
    
    protected void hitungTotalNet() {
        try {
            double totalPesanan = Double.parseDouble(tTotalPesanan.getText());

            // Default tanpa promo
            tPotongan.setText("0");
            tTotalNet.setText(String.format("%.0f", totalPesanan));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Total pesanan tidak valid: " + e.getMessage());
            tPotongan.setText("0");
            tTotalNet.setText("0");
        }
    }

    private void kosongkanFormPesanan() {
        tTotalPesanan.setText("0");
        tKodePromo.setText("");
        tPotongan.setText("0");
        tTotalNet.setText("0");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbjual = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tTotalNet = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        btnPesan = new javax.swing.JButton();
        Total1 = new javax.swing.JLabel();
        btnPromo = new javax.swing.JButton();
        Total2 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        tTotalPesanan = new javax.swing.JTextField();
        tKodePromo = new javax.swing.JTextField();
        tPotongan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbjual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbjual.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbjualAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tbjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbjualMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbjual);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Detail Produk");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel6.setBackground(new java.awt.Color(142, 173, 141));

        jLabel6.setFont(new java.awt.Font("Marcellus SC", 1, 29)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Form Pemesanan");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/assets/text_labites.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        tTotalNet.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        tTotalNet.setEnabled(false);
        tTotalNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTotalNetActionPerformed(evt);
            }
        });
        tTotalNet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTotalNetKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Total Net");

        Total.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Total.setText("Total");

        btnPesan.setText("Pesan");
        btnPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesanActionPerformed(evt);
            }
        });

        Total1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Total1.setText("Kode Promo");

        btnPromo.setText("Pakai");
        btnPromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromoActionPerformed(evt);
            }
        });

        Total2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Total2.setText("Potongan");

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        tTotalPesanan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tTotalPesanan.setEnabled(false);
        tTotalPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTotalPesananActionPerformed(evt);
            }
        });
        tTotalPesanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTotalPesananKeyReleased(evt);
            }
        });

        tKodePromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodePromoActionPerformed(evt);
            }
        });
        tKodePromo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tKodePromoKeyReleased(evt);
            }
        });

        tPotongan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tPotongan.setEnabled(false);
        tPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPotonganActionPerformed(evt);
            }
        });
        tPotongan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tPotonganKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Total2)
                            .addComponent(Total1)
                            .addComponent(Total))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tTotalPesanan)
                            .addComponent(tKodePromo)
                            .addComponent(tPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tTotalNet, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPromo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total)
                    .addComponent(tTotalPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPromo)
                    .addComponent(Total1)
                    .addComponent(tKodePromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total2)
                    .addComponent(tPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tTotalNet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 600, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbjualMouseClicked
        
    }//GEN-LAST:event_tbjualMouseClicked

    private void tbjualAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbjualAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbjualAncestorAdded

    private void tTotalNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTotalNetActionPerformed
        
    }//GEN-LAST:event_tTotalNetActionPerformed

    private void tTotalNetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTotalNetKeyReleased
       
    }//GEN-LAST:event_tTotalNetKeyReleased

    private void btnPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesanActionPerformed
        try {
            // Ambil nilai-nilai dari form
            int userId = Integer.parseInt(Login.idUser);
            String kodePromo = tKodePromo.getText().trim();
            double totalHarga = Double.parseDouble(tTotalPesanan.getText());
            double potongan = Double.parseDouble(tPotongan.getText());
            double totalNet = Double.parseDouble(tTotalNet.getText());

            Integer promoId = null; // default null jika tidak ada kode promo

            // Cek apakah ada kode promo valid yang sedang digunakan
            if (!kodePromo.isEmpty()) {
                String sqlPromo = "SELECT id FROM promotions WHERE kode_promo = ?";
                PreparedStatement psPromo = conn.prepareStatement(sqlPromo);
                psPromo.setString(1, kodePromo);
                ResultSet rsPromo = psPromo.executeQuery();

                if (rsPromo.next()) {
                    promoId = rsPromo.getInt("id");
                }

                rsPromo.close();
                psPromo.close();
            }

            // Masukkan ke tabel orders
            String sqlInsert = "INSERT INTO orders (user_id, promo_id, total_harga, harga_potongan, harga_net, status) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psOrder = conn.prepareStatement(sqlInsert);

            psOrder.setInt(1, userId);
            if (promoId != null) {
                psOrder.setInt(2, promoId);
            } else {
                psOrder.setNull(2, java.sql.Types.INTEGER);
            }
            psOrder.setDouble(3, totalHarga);
            psOrder.setDouble(4, potongan);
            psOrder.setDouble(5, totalNet);
            psOrder.setString(6, "diproses"); // status default

            int hasil = psOrder.executeUpdate();
            psOrder.close();

            if (hasil > 0) {
            // Kosongkan tabel cart
                String sqlClearCart = "DELETE FROM cart WHERE user_id = ?";
                PreparedStatement psClear = conn.prepareStatement(sqlClearCart);
                psClear.setInt(1, userId);
                psClear.executeUpdate();
                psClear.close();

                // Kosongkan form
                kosongkanFormPesanan();

                JOptionPane.showMessageDialog(null, "Pesanan berhasil dibuat!");
                tablekeranjang();   
                hitungTotalPesanan();  
                hitungTotalNet();
                
                new HomeUser().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan pesanan.");
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memproses pesanan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnPesanActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new keranjang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void tTotalPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTotalPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTotalPesananActionPerformed

    private void tTotalPesananKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTotalPesananKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tTotalPesananKeyReleased

    private void tKodePromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodePromoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodePromoActionPerformed

    private void tKodePromoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tKodePromoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodePromoKeyReleased

    private void tPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPotonganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPotonganActionPerformed

    private void tPotonganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPotonganKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tPotonganKeyReleased

    private void btnPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromoActionPerformed
        String kodePromo = tKodePromo.getText().trim();

        try {
            String sql = "SELECT * FROM promotions WHERE kode_promo = ? AND status = 'aktif'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kodePromo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Ambil info tanggal promo
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                java.util.Date today = new java.util.Date();

                if (today.before(startDate)) {
                    JOptionPane.showMessageDialog(null, "Promo belum berlaku.");
                    return;
                }

                if (today.after(endDate)) {
                    JOptionPane.showMessageDialog(null, "Promo sudah kadaluarsa.");
                    return;
                }
            
                String jenisPromo = rs.getString("jenis_promo");
                double potongan = rs.getDouble("potongan_harga");

                double totalPesanan = Double.parseDouble(tTotalPesanan.getText());
                double totalPotongan = 0;

                if (jenisPromo.equalsIgnoreCase("persentase")) {
                    totalPotongan = (potongan / 100.0) * totalPesanan;
                } else if (jenisPromo.equalsIgnoreCase("nominal")) {
                    totalPotongan = potongan;
                }

                // Pastikan potongan tidak melebihi total pesanan
                if (totalPotongan > totalPesanan) {
                    totalPotongan = totalPesanan;
                }

                double totalNet = totalPesanan - totalPotongan;

                // Set hasil ke TextField
                tPotongan.setText(String.format("%.0f", totalPotongan));
                tTotalNet.setText(String.format("%.0f", totalNet));

                JOptionPane.showMessageDialog(null, "Kode promo berhasil diterapkan!");
            } else {
                JOptionPane.showMessageDialog(null, "Kode promo tidak ditemukan atau tidak berlaku.");
                tPotongan.setText("0");
                tTotalNet.setText(tTotalPesanan.getText()); // tidak berubah
            }

            rs.close();
            ps.close();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memproses promo: " + e.getMessage());
        }
    }//GEN-LAST:event_btnPromoActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Total1;
    private javax.swing.JLabel Total2;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPesan;
    private javax.swing.JButton btnPromo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tKodePromo;
    private javax.swing.JTextField tPotongan;
    private javax.swing.JTextField tTotalNet;
    private javax.swing.JTextField tTotalPesanan;
    private javax.swing.JTable tbjual;
    // End of variables declaration//GEN-END:variables
}
