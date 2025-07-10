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
import javax.swing.SpinnerNumberModel;

public class HomeUser extends javax.swing.JFrame {
        
    public class Session {
    public int userId;
}
    
    private Connection conn;
    
    public HomeUser() {
        conn = Koneksi.getConnection();
        initComponents();
        
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        tps.setModel(model);
        tdc.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        tk.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        tpc.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        tac.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        tmc.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        
        if (Login.loggedUser != null) {
            namaUser.setText("Selamat Datang, " + Login.loggedUser + "!");
        } else {
            namaUser.setText("Selamat Datang, User!");
        }
    }

    private void tambahKeKeranjang(Connection conn, String userId, String productId, int jumlah) throws SQLException {
    if (jumlah > 0) {
        String query = "INSERT INTO cart (user_id, product_id, jumlah) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, userId);
        pst.setString(2, productId);
        pst.setInt(3, jumlah);
        pst.executeUpdate();
    }
}


    protected void kosongkanProduk() {
        tps.setValue(0);
        tdc.setValue(0);
        tk.setValue(0);
        tpc.setValue(0);
        tmc.setValue(0);
        tac.setValue(0);
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
        jLabel16 = new javax.swing.JLabel();
        tps = new javax.swing.JSpinner();
        btntambah = new javax.swing.JButton();
        PutriSalju1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btnKurang3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btnPlus3 = new javax.swing.JButton();
        tk = new javax.swing.JSpinner();
        PutriSalju2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btnKurang4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        btnPlus4 = new javax.swing.JButton();
        tpc = new javax.swing.JSpinner();
        PutriSalju3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        btnKurang6 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        btnPlus6 = new javax.swing.JButton();
        tmc = new javax.swing.JSpinner();
        PutriSalju4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btnPlus2 = new javax.swing.JButton();
        btnKurang2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        tdc = new javax.swing.JSpinner();
        PutriSalju5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btnKurang5 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        btnPlus5 = new javax.swing.JButton();
        tac = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namaUser = new javax.swing.JLabel();
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
        btnorder.setText("Pesan Sekarang");
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
                .addContainerGap()
                .addComponent(btnKurang1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tps, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PutriSaljuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PutriSaljuLayout.setVerticalGroup(
            PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSaljuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PutriSaljuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(tk, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlus3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(PutriSalju1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(PutriSalju1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel21)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju1Layout.setVerticalGroup(
            PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlus4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(PutriSalju2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel23)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju2Layout.setVerticalGroup(
            PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tmc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tmc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products/coklat  178x178.jpg"))); // NOI18N

        javax.swing.GroupLayout PutriSalju4Layout = new javax.swing.GroupLayout(PutriSalju4);
        PutriSalju4.setLayout(PutriSalju4Layout);
        PutriSalju4Layout.setHorizontalGroup(
            PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(29, 29, 29))
            .addGroup(PutriSalju4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PutriSalju4Layout.createSequentialGroup()
                        .addComponent(btnKurang2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tdc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlus2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(PutriSalju4Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PutriSalju4Layout.setVerticalGroup(
            PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tac, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlus5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
            .addGroup(PutriSalju5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel29)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PutriSalju5Layout.setVerticalGroup(
            PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PutriSalju5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PutriSalju5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKurang5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/assets/banner.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        namaUser.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        namaUser.setText("Selamat Datang, User!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namaUser)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(PutriSalju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PutriSalju4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PutriSalju1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PutriSalju2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PutriSalju3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PutriSalju5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntambah, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(namaUser)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PutriSalju2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PutriSalju3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PutriSalju1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PutriSalju4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PutriSalju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PutriSalju5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        MenuKeranjang.setText("Keranjang");
        MenuKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuKeranjangActionPerformed(evt);
            }
        });
        jMenu1.add(MenuKeranjang);

        MenuRiwayat.setText("Riwayat Transaksi");
        MenuRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRiwayatActionPerformed(evt);
            }
        });
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        int a = (int) tps.getValue();
        int b = (int) tdc.getValue();
        int c = (int) tk.getValue();
        int d = (int) tpc.getValue();
        int e = (int) tac.getValue();
        int f = (int) tmc.getValue();

        // Validasi jika semua bernilai 0
        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0) {
            JOptionPane.showMessageDialog(this, "Tambahkan produk terlebih dahulu!");
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String userId = Login.idUser;

            if (a > 0) tambahKeKeranjang(conn, userId, "1", a);
            if (b > 0) tambahKeKeranjang(conn, userId, "2", b);
            if (c > 0) tambahKeKeranjang(conn, userId, "3", c);
            if (d > 0) tambahKeKeranjang(conn, userId, "4", d);
            if (e > 0) tambahKeKeranjang(conn, userId, "5", e);
            if (f > 0) tambahKeKeranjang(conn, userId, "6", f);

            kosongkanProduk();
            JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambahkan ke keranjang: " + ex.getMessage());
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderActionPerformed
        int a = (int) tps.getValue();
        int b = (int) tdc.getValue();
        int c = (int) tk.getValue();
        int d = (int) tpc.getValue();
        int e = (int) tac.getValue();
        int f = (int) tmc.getValue();

        // Validasi jika semua bernilai 0
        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0) {
            JOptionPane.showMessageDialog(this, "Tambahkan produk terlebih dahulu!");
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String userId = Login.idUser;

            if (a > 0) tambahKeKeranjang(conn, userId, "1", a);
            if (b > 0) tambahKeKeranjang(conn, userId, "2", b);
            if (c > 0) tambahKeKeranjang(conn, userId, "3", c);
            if (d > 0) tambahKeKeranjang(conn, userId, "4", d);
            if (e > 0) tambahKeKeranjang(conn, userId, "5", e);
            if (f > 0) tambahKeKeranjang(conn, userId, "6", f);

            kosongkanProduk();
            JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!");
            new OrderForm().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambahkan ke keranjang: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnorderActionPerformed

    private void btnPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus1ActionPerformed
        try {
            int total = (int) tps.getValue();
            total++; // tambah 1
            tps.setValue(total);
        } catch (NumberFormatException e) {
            tps.setValue(1);
        }
    }//GEN-LAST:event_btnPlus1ActionPerformed

    private void btnKurang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang1ActionPerformed
        try {
            int total = (int) tps.getValue();
            if (total > 0) {
                total--; 
            }
            tps.setValue(total); 
        } catch (Exception e) {
            tps.setValue(0); 
        }
    }//GEN-LAST:event_btnKurang1ActionPerformed

    private void btnPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus2ActionPerformed
       try {
            int total = (int) tdc.getValue();
            total++; // tambah 1
            tdc.setValue(total);
        } catch (NumberFormatException e) {
            tdc.setValue(1);
        }
    }//GEN-LAST:event_btnPlus2ActionPerformed

    private void btnKurang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang2ActionPerformed
        try {
            int total = (int) tdc.getValue();
            if (total > 0) {
                total--; 
            }
            tdc.setValue(total); 
        } catch (Exception e) {
            tdc.setValue(0); 
        }
    }//GEN-LAST:event_btnKurang2ActionPerformed

    private void btnPlus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus3ActionPerformed
        try {
            int total = (int) tk.getValue();
            total++; // tambah 1
            tk.setValue(total);
        } catch (NumberFormatException e) {
            tk.setValue(1);
        }
        }//GEN-LAST:event_btnPlus3ActionPerformed

    private void btnKurang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang3ActionPerformed
        try {
            int total = (int) tk.getValue();
            if (total > 0) {
                total--; 
            }
            tk.setValue(total); 
        } catch (Exception e) {
            tk.setValue(0); 
        }
    }//GEN-LAST:event_btnKurang3ActionPerformed

    private void btnKurang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang6ActionPerformed
        try {
            int total = (int) tmc.getValue();
            if (total > 0) {
                total--; 
            }
            tmc.setValue(total); 
        } catch (Exception e) {
            tmc.setValue(0); 
    }    }//GEN-LAST:event_btnKurang6ActionPerformed

    private void btnKurang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang5ActionPerformed
try {
            int total = (int) tac.getValue();
            if (total > 0) {
                total--; 
            }
            tac.setValue(total); 
        } catch (Exception e) {
            tac.setValue(0); 
    }    }//GEN-LAST:event_btnKurang5ActionPerformed

    private void btnKurang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang4ActionPerformed
        try {
            int total = (int) tpc.getValue();
            if (total > 0) {
                total--; 
            }
            tpc.setValue(total); 
        } catch (Exception e) {
            tpc.setValue(0); 
    }    }//GEN-LAST:event_btnKurang4ActionPerformed

    private void btnPlus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus4ActionPerformed
        try {
            int total = (int) tpc.getValue();
            total++; // tambah 1
            tpc.setValue(total);
        } catch (NumberFormatException e) {
            tpc.setValue(1);
    }              }//GEN-LAST:event_btnPlus4ActionPerformed

    private void btnPlus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus5ActionPerformed
        try {
            int total = (int) tac.getValue();
            total++; // tambah 1
            tac.setValue(total);
        } catch (NumberFormatException e) {
            tac.setValue(1);
        }
     }//GEN-LAST:event_btnPlus5ActionPerformed

    private void btnPlus6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlus6ActionPerformed
 try {
            int total = (int) tmc.getValue();
            total++; // tambah 1
            tmc.setValue(total);
        } catch (NumberFormatException e) {
            tmc.setValue(1);
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

    private void MenuKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuKeranjangActionPerformed
        new keranjang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuKeranjangActionPerformed

    private void MenuRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRiwayatActionPerformed
        new RiwayatTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuRiwayatActionPerformed

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
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel namaUser;
    private javax.swing.JSpinner tac;
    private javax.swing.JSpinner tdc;
    private javax.swing.JSpinner tk;
    private javax.swing.JSpinner tmc;
    private javax.swing.JSpinner tpc;
    private javax.swing.JSpinner tps;
    // End of variables declaration//GEN-END:variables
}
