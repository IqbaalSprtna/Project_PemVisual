package Form;

import Config.Koneksi;
import admin.HomeAdmin;
import Form.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HomeUser extends javax.swing.JFrame {
        
    public class Session {
    public int userId;
}
    
    private Connection conn;
    
    public HomeUser() {
        conn = Koneksi.getConnection();
        initComponents();
    }




    private void tambahKeKeranjang(Connection conn, String userId, String productId, JTextField fieldJumlah) throws SQLException {
        int jumlah = 0;
        try {
            jumlah = Integer.parseInt(fieldJumlah.getText());
        } catch (NumberFormatException e) {
            // Lewati jika kosong atau bukan angka
        }

        if (jumlah > 0) {
            String query = "INSERT INTO cart (user_id, product_id, jumlah) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userId);
            pst.setString(2, productId);
            pst.setInt(3, jumlah);
            pst.executeUpdate();
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnorder = new javax.swing.JButton();
        PutriSalju = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnPlus1 = new javax.swing.JButton();
        btnKurang1 = new javax.swing.JButton();
        tps = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btntambah = new javax.swing.JButton();
        PutriSalju1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btnKurang3 = new javax.swing.JButton();
        tk = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnPlus3 = new javax.swing.JButton();
        PutriSalju2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btnKurang4 = new javax.swing.JButton();
        tpc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnPlus4 = new javax.swing.JButton();
        PutriSalju3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        btnKurang6 = new javax.swing.JButton();
        tmc = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnPlus6 = new javax.swing.JButton();
        PutriSalju4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btnPlus2 = new javax.swing.JButton();
        btnKurang2 = new javax.swing.JButton();
        tdc = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        PutriSalju5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btnKurang5 = new javax.swing.JButton();
        tac = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnPlus5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuKeranjang = new javax.swing.JMenuItem();
        MenuRiwayat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuLogout = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(142, 173, 141));

        btnorder.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnorder.setText("Order");
        btnorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnorderActionPerformed(evt);
            }
        });

        PutriSalju.setBackground(new java.awt.Color(255, 255, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Putri Salju");

        btnPlus1.setText("+");
        btnPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus1ActionPerformed(evt);
            }
        });

        btnKurang1.setText("-");
        btnKurang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang1ActionPerformed(evt);
            }
        });

        tps.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tps.setText("0");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/putri salju.jpeg"))); // NOI18N

        javax.swing.GroupLayout PutriSaljuLayout = new javax.swing.GroupLayout(PutriSalju);
        PutriSalju.setLayout(PutriSaljuLayout);
        PutriSaljuLayout.setHorizontalGroup(
            PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSaljuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PutriSaljuLayout.createSequentialGroup()
                .addGroup(PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PutriSaljuLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel16)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(PutriSaljuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnKurang1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tps)
                        .addGap(10, 10, 10)
                        .addComponent(btnPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PutriSaljuLayout.setVerticalGroup(
            PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSaljuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tps))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btntambah.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btntambah.setText("Tambahkan Keranjang");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        PutriSalju1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Kastengel");

        btnKurang3.setText("-");
        btnKurang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang3ActionPerformed(evt);
            }
        });

        tk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tk.setText("0");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/kastengel 178x178.jpeg"))); // NOI18N

        btnPlus3.setText("+");
        btnPlus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PutriSalju1Layout = new javax.swing.GroupLayout(PutriSalju1);
        PutriSalju1.setLayout(PutriSalju1Layout);
        PutriSalju1Layout.setHorizontalGroup(
            PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PutriSalju1Layout.createSequentialGroup()
                        .addComponent(btnKurang3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlus3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(PutriSalju1Layout.createSequentialGroup()
                        .addGroup(PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PutriSalju1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel21)))
                        .addGap(0, 28, Short.MAX_VALUE))))
        );
        PutriSalju1Layout.setVerticalGroup(
            PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tk)
                    .addComponent(btnKurang3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PutriSalju2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Palm Cheese");

        btnKurang4.setText("-");
        btnKurang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang4ActionPerformed(evt);
            }
        });

        tpc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tpc.setText("0");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/palm cheese.jpeg"))); // NOI18N

        btnPlus4.setText("+");
        btnPlus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PutriSalju2Layout = new javax.swing.GroupLayout(PutriSalju2);
        PutriSalju2.setLayout(PutriSalju2Layout);
        PutriSalju2Layout.setHorizontalGroup(
            PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PutriSalju2Layout.createSequentialGroup()
                        .addComponent(btnKurang4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tpc, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlus4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(PutriSalju2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel23)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju2Layout.setVerticalGroup(
            PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tpc)
                    .addComponent(btnKurang4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PutriSalju3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Matcha Almond");

        btnKurang6.setText("-");
        btnKurang6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang6ActionPerformed(evt);
            }
        });

        tmc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tmc.setText("0");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/kue.jpeg"))); // NOI18N

        btnPlus6.setText("+");
        btnPlus6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PutriSalju3Layout = new javax.swing.GroupLayout(PutriSalju3);
        PutriSalju3.setLayout(PutriSalju3Layout);
        PutriSalju3Layout.setHorizontalGroup(
            PutriSalju3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PutriSalju3Layout.createSequentialGroup()
                        .addComponent(btnKurang6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tmc, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlus6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(PutriSalju3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel25)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju3Layout.setVerticalGroup(
            PutriSalju3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tmc)
                    .addComponent(btnKurang6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PutriSalju4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Dark Choco");

        btnPlus2.setText("+");
        btnPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus2ActionPerformed(evt);
            }
        });

        btnKurang2.setText("-");
        btnKurang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang2ActionPerformed(evt);
            }
        });

        tdc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tdc.setText("0");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/coklat  178x178.jpg"))); // NOI18N

        javax.swing.GroupLayout PutriSalju4Layout = new javax.swing.GroupLayout(PutriSalju4);
        PutriSalju4.setLayout(PutriSalju4Layout);
        PutriSalju4Layout.setHorizontalGroup(
            PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PutriSalju4Layout.createSequentialGroup()
                        .addComponent(btnKurang2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tdc, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlus2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(PutriSalju4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel27)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju4Layout.setVerticalGroup(
            PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tdc)
                    .addComponent(btnKurang2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PutriSalju5.setBackground(new java.awt.Color(255, 255, 204));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Almond Cheese");

        btnKurang5.setText("-");
        btnKurang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang5ActionPerformed(evt);
            }
        });

        tac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tac.setText("0");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/nastar.jpeg"))); // NOI18N

        btnPlus5.setText("+");
        btnPlus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlus5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PutriSalju5Layout = new javax.swing.GroupLayout(PutriSalju5);
        PutriSalju5.setLayout(PutriSalju5Layout);
        PutriSalju5Layout.setHorizontalGroup(
            PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PutriSalju5Layout.createSequentialGroup()
                        .addComponent(btnKurang5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlus5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))))
            .addGroup(PutriSalju5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel29)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju5Layout.setVerticalGroup(
            PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tac)
                    .addComponent(btnKurang5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PutriSalju, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PutriSalju5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PutriSalju4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PutriSalju1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PutriSalju2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PutriSalju3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btntambah)
                                    .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PutriSalju1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PutriSalju4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PutriSalju, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PutriSalju2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutriSalju3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(PutriSalju5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
        );

        jMenu1.setText("Menu");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        MenuKeranjang.setText("Keranjang");
        jMenu1.add(MenuKeranjang);

        MenuRiwayat.setText("Riwayat Transaksi");
        jMenu1.add(MenuRiwayat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Profile");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        MenuLogout.setText("Logout");
        MenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLogoutActionPerformed(evt);
            }
        });
        jMenu2.add(MenuLogout);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 536, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed

        try {
        Connection conn = Koneksi.getConnection();

        // Ambil user_id dari Session
        String userId = Login.idUser;

        tambahKeKeranjang(conn, userId, "1", tps); 
        tambahKeKeranjang(conn, userId, "2", tdc);
        tambahKeKeranjang(conn, userId, "3", tk);
        tambahKeKeranjang(conn, userId, "4", tpc);
        tambahKeKeranjang(conn, userId, "5", tac);
        tambahKeKeranjang(conn, userId, "6", tmc);

        JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan ke keranjang!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal menambahkan ke keranjang: " + e.getMessage());
    }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderActionPerformed
        
    }//GEN-LAST:event_btnorderActionPerformed

    private void btnPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus1ActionPerformed
        try {
        int total = Integer.parseInt(tps.getText());
        total++; // tambah 1
        tps.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tps.setText("1");
    }
    }//GEN-LAST:event_btnPlus1ActionPerformed

    private void btnKurang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang1ActionPerformed
        try {
        int total = Integer.parseInt(tps.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tps.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tps.setText("0");
    }
    }//GEN-LAST:event_btnKurang1ActionPerformed

    private void btnPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus2ActionPerformed
       try {
        int total = Integer.parseInt(tdc.getText());
        total++; // tambah 1
        tdc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tdc.setText("1");
    }                                     

    }//GEN-LAST:event_btnPlus2ActionPerformed

    private void btnKurang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang2ActionPerformed
         try {
        int total = Integer.parseInt(tdc.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tdc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tdc.setText("0");
    }
    }//GEN-LAST:event_btnKurang2ActionPerformed

    private void btnPlus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus3ActionPerformed
 try {
        int total = Integer.parseInt(tk.getText());
        total++; // tambah 1
        tk.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tk.setText("1");
    }      
        }//GEN-LAST:event_btnPlus3ActionPerformed

    private void btnKurang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang3ActionPerformed
try {
        int total = Integer.parseInt(tk.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tk.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tk.setText("0");
    }
    }//GEN-LAST:event_btnKurang3ActionPerformed

    private void btnKurang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang6ActionPerformed
try {
        int total = Integer.parseInt(tmc.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tmc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tmc.setText("0");
    }    }//GEN-LAST:event_btnKurang6ActionPerformed

    private void btnKurang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang5ActionPerformed
try {
        int total = Integer.parseInt(tac.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tac.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tac.setText("0");
    }    }//GEN-LAST:event_btnKurang5ActionPerformed

    private void btnKurang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang4ActionPerformed
try {
        int total = Integer.parseInt(tpc.getText());
        if (total > 0) {
            total--; // kurang 1 jika lebih dari 0
        }
        tpc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 0
        tpc.setText("0");
    }    }//GEN-LAST:event_btnKurang4ActionPerformed

    private void btnPlus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus4ActionPerformed
 try {
        int total = Integer.parseInt(tpc.getText());
        total++; // tambah 1
        tpc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tpc.setText("1");
    }              }//GEN-LAST:event_btnPlus4ActionPerformed

    private void btnPlus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus5ActionPerformed
 try {
        int total = Integer.parseInt(tac.getText());
        total++; // tambah 1
        tac.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tac.setText("1");
    }         
     }//GEN-LAST:event_btnPlus5ActionPerformed

    private void btnPlus6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus6ActionPerformed
 try {
        int total = Integer.parseInt(tmc.getText());
        total++; // tambah 1
        tmc.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        // Jika teks tidak valid, set default ke 1
        tmc.setText("1");
    }              }//GEN-LAST:event_btnPlus6ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        new HomeUser().setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void MenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLogoutActionPerformed
       int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        // Hapus session
        Login.idUser = null;

        // Tampilkan kembali form login
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_MenuLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuKeranjang;
    private javax.swing.JMenuItem MenuLogout;
    private javax.swing.JMenuItem MenuRiwayat;
    private javax.swing.JPanel PutriSalju;
    private javax.swing.JPanel PutriSalju1;
    private javax.swing.JPanel PutriSalju2;
    private javax.swing.JPanel PutriSalju3;
    private javax.swing.JPanel PutriSalju4;
    private javax.swing.JPanel PutriSalju5;
    private javax.swing.JButton btnKurang1;
    private javax.swing.JButton btnKurang2;
    private javax.swing.JButton btnKurang3;
    private javax.swing.JButton btnKurang4;
    private javax.swing.JButton btnKurang5;
    private javax.swing.JButton btnKurang6;
    private javax.swing.JButton btnPlus1;
    private javax.swing.JButton btnPlus2;
    private javax.swing.JButton btnPlus3;
    private javax.swing.JButton btnPlus4;
    private javax.swing.JButton btnPlus5;
    private javax.swing.JButton btnPlus6;
    private javax.swing.JButton btnorder;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tac;
    private javax.swing.JTextField tdc;
    private javax.swing.JTextField tk;
    private javax.swing.JTextField tmc;
    private javax.swing.JTextField tpc;
    private javax.swing.JTextField tps;
    // End of variables declaration//GEN-END:variables
}
