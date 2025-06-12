package admin;

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




public class HomeAdmin extends javax.swing.JFrame {
    
    private Connection conn;
    private String selectedImagePath = "";
    private JLabel selectedLabel = null;
     private DefaultTableModel tabmode;
    
    public HomeAdmin() {
        initComponents();
        conn = Koneksi.getConnection();
        
         if (Login.loggedUser != null) {
            t_userName.setText("Selamat Datang, " + Login.loggedUser + "!");
        } else {
            t_userName.setText("Selamat Datang, User!");
        }
         
         getDataProduk();
         loadCategoryToComboBox();
         kategori();
    }
    
    private void getDataProduk() {
        DefaultTableModel model = (DefaultTableModel) tbl_produk.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT * FROM products";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
       
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("category_id");
                String nama = rs.getString("nama");
                String harga = rs.getString("harga");
                String stok = rs.getString("stok");
                String desc = rs.getString("deskripsi");
                String gambarPath = rs.getString("gambar");

                // **Load gambar dari file**
                JLabel labelGambar = new JLabel();
                if (gambarPath != null && !gambarPath.isEmpty()) {
                    File imgFile = new File("images/products" + gambarPath); // Pastikan path benar
                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(new ImageIcon(imgFile.getAbsolutePath()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
                        labelGambar.setIcon(icon);
                    } else {
                        labelGambar.setText("Gambar tidak ditemukan");
                    }
                } else {
                    labelGambar.setText("No Image");
                }

                Object[] rowData = {id, id_category, nama, harga, stok, desc, labelGambar};
                model.addRow(new Object[]{id, id_category, nama, harga, stok, desc, gambarPath});
            }

            rs.close();
            st.close();
                    
        } catch (Exception e) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private HashMap<String, Integer> categoryMap = new HashMap<>();
    
    private void kategori(){
      Object[] Baris = {"NO", "Nama Kategori"};
      tabmode = new DefaultTableModel(null,Baris);
      tblkategori.setModel(tabmode);
      
      String sql = "SELECT * from categories";      
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("nama");
                
                 String[] data={a,b};
                tabmode.addRow(data);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
           
                }
            }
    
    private void loadCategoryToComboBox() {
        try {
            String query = "SELECT id, nama FROM categories"; 
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("-- Pilih Kategori --"); // Tambahkan opsi default
            categoryMap.clear();

            while (rs.next()) {
               int id = rs.getInt("id");
               String nama = rs.getString("nama");
               model.addElement(nama);
               categoryMap.put(nama, id);
            }

            t_catProduk.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_kiri = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JLabel();
        btn_produk = new javax.swing.JLabel();
        btn_kategori = new javax.swing.JLabel();
        btn_pesanan = new javax.swing.JLabel();
        btn_pelanggan = new javax.swing.JLabel();
        btn_promo = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JLabel();
        pn_kanan = new javax.swing.JPanel();
        pn_atas = new javax.swing.JPanel();
        t_userName = new javax.swing.JLabel();
        pn_dasar = new javax.swing.JPanel();
        pn_utama = new javax.swing.JPanel();
        c_dashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ttl_produk = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ttl_pesanan = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        ttl_income = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_produk2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_order = new javax.swing.JTable();
        c_produk = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_produk = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        t_catProduk = new javax.swing.JComboBox<>();
        t_namaProduk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        t_hargaProduk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        t_stokProduk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        t_descProduk = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_pilihGambar = new javax.swing.JButton();
        btn_addProduk = new javax.swing.JButton();
        btn_updetProduk = new javax.swing.JButton();
        btn_clearProduk = new javax.swing.JButton();
        btn_deletProduk = new javax.swing.JButton();
        btn_cetakProduk = new javax.swing.JButton();
        c_kategori = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkategori = new javax.swing.JTable();
        tnamakategori = new javax.swing.JTextField();
        bsave = new javax.swing.JButton();
        bClearKat = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tidkategori = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tcarikategori = new javax.swing.JTextField();
        bcarikategori = new javax.swing.JButton();
        bDeleteKat = new javax.swing.JButton();
        c_pesanan = new javax.swing.JPanel();
        c_pelanggan = new javax.swing.JPanel();
        c_promo = new javax.swing.JPanel();
        c_laporan = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pn_kiri.setBackground(new java.awt.Color(255, 255, 255));
        pn_kiri.setPreferredSize(new java.awt.Dimension(300, 768));

        logo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 48)); // NOI18N
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("ADMIN");
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_dashboard.setText("DASHBOARD");
        btn_dashboard.setPreferredSize(new java.awt.Dimension(285, 20));
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseExited(evt);
            }
        });

        btn_produk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_produk.setText("PRODUK");
        btn_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_produkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_produkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_produkMouseExited(evt);
            }
        });

        btn_kategori.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_kategori.setText("KATEGORI");
        btn_kategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kategoriMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kategoriMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kategoriMouseExited(evt);
            }
        });

        btn_pesanan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_pesanan.setText("PESANAN");
        btn_pesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pesananMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pesananMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pesananMouseExited(evt);
            }
        });

        btn_pelanggan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_pelanggan.setText("PELANGGAN");
        btn_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pelangganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pelangganMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pelangganMouseExited(evt);
            }
        });

        btn_promo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_promo.setText("PROMO & DISKON");
        btn_promo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_promoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_promoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_promoMouseExited(evt);
            }
        });

        btn_laporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_laporan.setText("LAPORAN PENJUALAN");
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_kiriLayout = new javax.swing.GroupLayout(pn_kiri);
        pn_kiri.setLayout(pn_kiriLayout);
        pn_kiriLayout.setHorizontalGroup(
            pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kiriLayout.createSequentialGroup()
                .addGroup(pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_produk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_kategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_promo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_kiriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn_kiriLayout.setVerticalGroup(
            pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(647, Short.MAX_VALUE))
        );

        getContentPane().add(pn_kiri, java.awt.BorderLayout.LINE_START);

        pn_kanan.setBackground(new java.awt.Color(255, 255, 255));
        pn_kanan.setLayout(new java.awt.BorderLayout());

        pn_atas.setPreferredSize(new java.awt.Dimension(1066, 70));

        t_userName.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        t_userName.setText("Selamat Datang, User");

        javax.swing.GroupLayout pn_atasLayout = new javax.swing.GroupLayout(pn_atas);
        pn_atas.setLayout(pn_atasLayout);
        pn_atasLayout.setHorizontalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(t_userName)
                .addContainerGap(1642, Short.MAX_VALUE))
        );
        pn_atasLayout.setVerticalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(t_userName)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pn_kanan.add(pn_atas, java.awt.BorderLayout.PAGE_START);

        pn_dasar.setBackground(new java.awt.Color(255, 102, 102));

        pn_utama.setLayout(new java.awt.CardLayout());

        c_dashboard.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Produk", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        ttl_produk.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(ttl_produk)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(ttl_produk)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Pesanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        ttl_pesanan.setText("10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(ttl_pesanan)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(ttl_pesanan)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pendapatan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        ttl_income.setText("10");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(ttl_income)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(ttl_income)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tbl_produk2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_produk2);

        tbl_order.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_order);

        javax.swing.GroupLayout c_dashboardLayout = new javax.swing.GroupLayout(c_dashboard);
        c_dashboard.setLayout(c_dashboardLayout);
        c_dashboardLayout.setHorizontalGroup(
            c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_dashboardLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, c_dashboardLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(c_dashboardLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1054, Short.MAX_VALUE))
        );
        c_dashboardLayout.setVerticalGroup(
            c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(693, Short.MAX_VALUE))
        );

        pn_utama.add(c_dashboard, "c_dashboard");

        tbl_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "id_category", "Nama", "Harga", "Stok", "Deskripsi", "Gambar", "Deleted_at"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_produk);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Category");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nama");

        t_catProduk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        t_catProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_catProdukActionPerformed(evt);
            }
        });

        t_namaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_namaProdukActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Harga");

        t_hargaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_hargaProdukActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Stok");

        t_stokProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_stokProdukActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Deskripsi");

        t_descProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_descProdukActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Gambar");

        btn_pilihGambar.setText("Pilih Gambar");
        btn_pilihGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihGambarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(t_descProduk, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(t_namaProduk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(t_catProduk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(t_hargaProduk, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(t_stokProduk, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(btn_pilihGambar))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(t_catProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_namaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_hargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_stokProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_descProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btn_pilihGambar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_addProduk.setText("TAMBAH");
        btn_addProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addProdukActionPerformed(evt);
            }
        });

        btn_updetProduk.setText("UPDATE");
        btn_updetProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updetProdukActionPerformed(evt);
            }
        });

        btn_clearProduk.setText("CLEAR");

        btn_deletProduk.setText("DELETE");

        btn_cetakProduk.setText("CETAK");

        javax.swing.GroupLayout c_produkLayout = new javax.swing.GroupLayout(c_produk);
        c_produk.setLayout(c_produkLayout);
        c_produkLayout.setHorizontalGroup(
            c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_addProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_updetProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_clearProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_deletProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cetakProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1194, Short.MAX_VALUE))
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        c_produkLayout.setVerticalGroup(
            c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btn_addProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_updetProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_clearProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_deletProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cetakProduk)))
                .addContainerGap(532, Short.MAX_VALUE))
        );

        pn_utama.add(c_produk, "c_produk");

        tblkategori.setModel(new javax.swing.table.DefaultTableModel(
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
        tblkategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkategoriMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblkategori);

        tnamakategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamakategoriActionPerformed(evt);
            }
        });

        bsave.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bsave.setText("Save");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });

        bClearKat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bClearKat.setText("Clear");
        bClearKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearKatActionPerformed(evt);
            }
        });

        bedit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        jLabel9.setText("Nama");

        jLabel1.setText("Id");

        tidkategori.setEnabled(false);

        jLabel8.setText("pencarian kategori");

        bcarikategori.setText("cari");
        bcarikategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcarikategoriActionPerformed(evt);
            }
        });

        bDeleteKat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bDeleteKat.setText("Delete");
        bDeleteKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteKatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout c_kategoriLayout = new javax.swing.GroupLayout(c_kategori);
        c_kategori.setLayout(c_kategoriLayout);
        c_kategoriLayout.setHorizontalGroup(
            c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_kategoriLayout.createSequentialGroup()
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(c_kategoriLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(114, 114, 114)
                                .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bClearKat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bDeleteKat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8)
                        .addGap(50, 50, 50)
                        .addComponent(tcarikategori, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bcarikategori))
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1))
                        .addGap(73, 73, 73)
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(tnamakategori, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tidkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(953, Short.MAX_VALUE))
        );
        c_kategoriLayout.setVerticalGroup(
            c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_kategoriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tcarikategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcarikategori))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tidkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnamakategori, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(46, 46, 46)
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClearKat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDeleteKat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(699, Short.MAX_VALUE))
        );

        pn_utama.add(c_kategori, "c_kategori");

        javax.swing.GroupLayout c_pesananLayout = new javax.swing.GroupLayout(c_pesanan);
        c_pesanan.setLayout(c_pesananLayout);
        c_pesananLayout.setHorizontalGroup(
            c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1795, Short.MAX_VALUE)
        );
        c_pesananLayout.setVerticalGroup(
            c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
        );

        pn_utama.add(c_pesanan, "c_pesanan");

        javax.swing.GroupLayout c_pelangganLayout = new javax.swing.GroupLayout(c_pelanggan);
        c_pelanggan.setLayout(c_pelangganLayout);
        c_pelangganLayout.setHorizontalGroup(
            c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1795, Short.MAX_VALUE)
        );
        c_pelangganLayout.setVerticalGroup(
            c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
        );

        pn_utama.add(c_pelanggan, "c_pelanggan");

        javax.swing.GroupLayout c_promoLayout = new javax.swing.GroupLayout(c_promo);
        c_promo.setLayout(c_promoLayout);
        c_promoLayout.setHorizontalGroup(
            c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1795, Short.MAX_VALUE)
        );
        c_promoLayout.setVerticalGroup(
            c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
        );

        pn_utama.add(c_promo, "c_promo");

        javax.swing.GroupLayout c_laporanLayout = new javax.swing.GroupLayout(c_laporan);
        c_laporan.setLayout(c_laporanLayout);
        c_laporanLayout.setHorizontalGroup(
            c_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1795, Short.MAX_VALUE)
        );
        c_laporanLayout.setVerticalGroup(
            c_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
        );

        pn_utama.add(c_laporan, "c_laporan");

        javax.swing.GroupLayout pn_dasarLayout = new javax.swing.GroupLayout(pn_dasar);
        pn_dasar.setLayout(pn_dasarLayout);
        pn_dasarLayout.setHorizontalGroup(
            pn_dasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dasarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(61, 61, 61))
        );
        pn_dasarLayout.setVerticalGroup(
            pn_dasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dasarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_kanan.add(pn_dasar, java.awt.BorderLayout.CENTER);

        getContentPane().add(pn_kanan, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
       if (selectedLabel != btn_dashboard) { 
            btn_dashboard.setBackground(new Color(240, 240, 240)); // Warna default
        }
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_dashboard");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        // Set label yang baru dipilih
        selectedLabel = btn_dashboard;
        btn_dashboard.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(HomeAdmin.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_produk");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_produk;
        btn_produk.setBackground(new Color(150, 150, 150));      
    }//GEN-LAST:event_btn_produkMouseClicked

    private void t_namaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_namaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_namaProdukActionPerformed

    private void t_hargaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_hargaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_hargaProdukActionPerformed

    private void t_stokProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_stokProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_stokProdukActionPerformed

    private void t_descProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_descProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_descProdukActionPerformed

    private void btn_updetProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updetProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updetProdukActionPerformed

    private void t_catProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_catProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_catProdukActionPerformed

    private void btn_addProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addProdukActionPerformed
        try {
            String selectedCategory = (String) t_catProduk.getSelectedItem(); 
            if (selectedCategory.equals("-- Pilih Kategori --")) {
                JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int category_id = categoryMap.get(selectedCategory); 
            String nama = t_namaProduk.getText().trim();
            double harga = Double.parseDouble(t_hargaProduk.getText().trim());
            int stok = Integer.parseInt(t_stokProduk.getText().trim());
            String desc = t_descProduk.getText().trim();

            if (nama.isEmpty() || t_hargaProduk.getText().trim().isEmpty() || t_stokProduk.getText().trim().isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua Kolom Harus Terisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedImagePath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Silakan pilih gambar terlebih dahulu.");
                return;
            }

            // **Folder tujuan penyimpanan gambar**
            File targetDir = new File("src/images/products");
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // Buat folder jika belum ada
            }

            // **Salin file ke folder tujuan**
            File selectedFile = new File(selectedImagePath);
            String fileName = selectedFile.getName(); // Ambil nama file asli
            File destinationFile = new File(targetDir, fileName);

            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // **Simpan path relatif dalam database**
            String filePath = "images/products/" + fileName;

            String sql = "INSERT INTO products (category_id, nama, harga, stok, deskripsi, gambar) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, category_id);
            st.setString(2, nama);
            st.setDouble(3, harga);
            st.setInt(4, stok);
            st.setString(5, desc);
            st.setString(6, filePath); // Simpan path relatif

            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                resetForm();
                getDataProduk();
            }

            st.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan gambar!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btn_addProdukActionPerformed

    private void btn_pilihGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihGambarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Gambar Produk");

        // Filter hanya file gambar
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Gambar (JPG, PNG, JPEG)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath(); // Simpan path gambar ke variabel global
        
            JOptionPane.showMessageDialog(this, "Gambar dipilih: " + selectedImagePath);
        }
    }//GEN-LAST:event_btn_pilihGambarActionPerformed

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        btn_dashboard.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_dashboard.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_dashboardMouseEntered

    private void btn_produkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseEntered
        btn_produk.setOpaque(true);
        btn_produk.setBackground(new Color(200, 200, 200));
        btn_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_produkMouseEntered

    private void btn_produkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseExited
        if (selectedLabel != btn_produk) { 
            btn_produk.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_produkMouseExited

    private void btn_kategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_kategori");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_kategori;
        btn_kategori.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_kategoriMouseClicked

    private void btn_kategoriMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseEntered
        btn_kategori.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_kategori.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_kategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_kategoriMouseEntered

    private void btn_kategoriMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseExited
        if (selectedLabel != btn_kategori) { 
            btn_kategori.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_kategoriMouseExited

    private void btn_pesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_pesanan");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_pesanan;
        btn_pesanan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_pesananMouseClicked

    private void btn_pesananMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseEntered
        btn_pesanan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_pesanan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_pesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_pesananMouseEntered

    private void btn_pesananMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseExited
        if (selectedLabel != btn_pesanan) { 
            btn_pesanan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_pesananMouseExited

    private void btn_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_pelanggan");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_pelanggan;
        btn_pelanggan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_pelangganMouseClicked

    private void btn_pelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseEntered
        btn_pelanggan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_pelanggan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_pelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_pelangganMouseEntered

    private void btn_pelangganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseExited
        if (selectedLabel != btn_pelanggan) { 
            btn_pelanggan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_pelangganMouseExited

    private void btn_promoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_pelanggan");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_promo;
        btn_promo.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_promoMouseClicked

    private void btn_promoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseEntered
        btn_promo.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_promo.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_promo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_promoMouseEntered

    private void btn_promoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseExited
        if (selectedLabel != btn_promo) { 
            btn_promo.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_promoMouseExited

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_laporan");
        
        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }
        
        selectedLabel = btn_laporan;
        btn_laporan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        btn_laporan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_laporan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        if (selectedLabel != btn_laporan) { 
            btn_laporan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_laporanMouseExited

    private void tnamakategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamakategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamakategoriActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
      String namaKategori = tnamakategori.getText ();
      String idKategori = tidkategori.getText ();
        try {
            String sql = "UPDATE categories SET nama=? WHERE id=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, namaKategori);
            stat.setString(2, idKategori);
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");

           kategori();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah! " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_beditActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
        // TODO add your handling code here:
    String namaKategori = tnamakategori.getText ();
    String idKategori = tidkategori.getText ();
   try {
       String sql = "INSERT INTO categories (nama, id )VALUES (?,?)";
       PreparedStatement st = conn.prepareStatement(sql);
       
       st.setString(1,namaKategori);
       st.setString(2,idKategori);
       
       st.executeUpdate();
JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
 kategori();

   }    catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan Kategori!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_bsaveActionPerformed

    private void bClearKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearKatActionPerformed
        tidkategori.setText("");
        tnamakategori.setText("");

    }//GEN-LAST:event_bClearKatActionPerformed

    private void tblkategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkategoriMouseClicked
        // TODO add your handling code here:
         int bar = tblkategori.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        
        tidkategori.setText(a);
        tnamakategori.setText(b);
        
    }//GEN-LAST:event_tblkategoriMouseClicked

    private void bcarikategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcarikategoriActionPerformed
        // TODO add your handling code here:
         String keyword = tcarikategori.getText();

        String sql = "SELECT * FROM categories WHERE nama LIKE ? OR id LIKE ?";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, "%" + keyword + "%"); 
            stat.setString(2, "%" + keyword + "%");

            ResultSet rs = stat.executeQuery();
            tabmode.setRowCount(0);
            
            while (rs.next()) {
                String a = rs.getString("id");
                String b = rs.getString("nama");
              
                String[] data={a,b};
                tabmode.addRow(data);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error saat mencari data: " + e, "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_bcarikategoriActionPerformed

    private void bDeleteKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteKatActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialgo", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM categories WHERE id = '" + tidkategori.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
                
                kategori();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!" + e);
            } 
        }
    }//GEN-LAST:event_bDeleteKatActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClearKat;
    private javax.swing.JButton bDeleteKat;
    private javax.swing.JButton bcarikategori;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bsave;
    private javax.swing.JButton btn_addProduk;
    private javax.swing.JButton btn_cetakProduk;
    private javax.swing.JButton btn_clearProduk;
    private javax.swing.JLabel btn_dashboard;
    private javax.swing.JButton btn_deletProduk;
    private javax.swing.JLabel btn_kategori;
    private javax.swing.JLabel btn_laporan;
    private javax.swing.JLabel btn_pelanggan;
    private javax.swing.JLabel btn_pesanan;
    private javax.swing.JButton btn_pilihGambar;
    private javax.swing.JLabel btn_produk;
    private javax.swing.JLabel btn_promo;
    private javax.swing.JButton btn_updetProduk;
    private javax.swing.JPanel c_dashboard;
    private javax.swing.JPanel c_kategori;
    private javax.swing.JPanel c_laporan;
    private javax.swing.JPanel c_pelanggan;
    private javax.swing.JPanel c_pesanan;
    private javax.swing.JPanel c_produk;
    private javax.swing.JPanel c_promo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pn_atas;
    private javax.swing.JPanel pn_dasar;
    private javax.swing.JPanel pn_kanan;
    private javax.swing.JPanel pn_kiri;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JComboBox<String> t_catProduk;
    private javax.swing.JTextField t_descProduk;
    private javax.swing.JTextField t_hargaProduk;
    private javax.swing.JTextField t_namaProduk;
    private javax.swing.JTextField t_stokProduk;
    private javax.swing.JLabel t_userName;
    private javax.swing.JTable tbl_order;
    private javax.swing.JTable tbl_produk;
    private javax.swing.JTable tbl_produk2;
    private javax.swing.JTable tblkategori;
    private javax.swing.JTextField tcarikategori;
    private javax.swing.JTextField tidkategori;
    private javax.swing.JTextField tnamakategori;
    private javax.swing.JLabel ttl_income;
    private javax.swing.JLabel ttl_pesanan;
    private javax.swing.JLabel ttl_produk;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        t_namaProduk.setText("");
        t_hargaProduk.setText("");
        t_stokProduk.setText("");
        t_descProduk.setText("");
        btn_pilihGambar.setText("");
    }
}
