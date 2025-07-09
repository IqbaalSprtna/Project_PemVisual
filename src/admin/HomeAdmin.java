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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;




public class HomeAdmin extends javax.swing.JFrame {
    
    private Connection conn;
    private String selectedImagePath = "";
    private JLabel selectedLabel = null;
    private DefaultTableModel tabmode;
    private String selectedFileName = "";

    
    public HomeAdmin() {
        initComponents();
        conn = Koneksi.getConnection();
        
        if (Login.loggedUser != null) {
            t_userName.setText("Selamat Datang, " + Login.loggedUser + "!");
        } else {
            t_userName.setText("Selamat Datang, User!");
        }
         
        Locale loc = new Locale ("id", "ID");
        Locale.setDefault(loc);
         
         getDataProduk();
         tablePelanggan();
         getDataKategori();
         loadCategoryToComboBox();
         loadPromo();
        loadProdukPromo();
        tableCustomer();
    }
    
    private void getDataProduk() {
        Object[] Baris = {"id", "id_category", "Nama", "Harga", "Stok", "Deskripsi", "Gambar", "Deleted_at"};
        tabmode = new DefaultTableModel(null, Baris);
        tbl_produk.setModel(tabmode);
        
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

                // Load gambar dari file
                JLabel labelGambar = new JLabel();
                if (gambarPath != null && !gambarPath.isEmpty()) {
                    File imgFile = new File("images/products" + gambarPath);
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
                tabmode.addRow(rowData);
            }

            rs.close();
            st.close();
                    
        } catch (Exception e) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void clearFormProduk() {
        t_idProduk.setText("");
        t_idProduk.setEnabled(true);
        t_catProduk.setSelectedIndex(0);
        t_namaProduk.setText("");
        t_hargaProduk.setText("");
        t_stokProduk.setText("");
        t_descProduk.setText("");
        btn_pilihGambar.setText("Pilih Gambar");
        t_idProduk.requestFocus();
    }
    
    private HashMap<String, Integer> categoryMap = new HashMap<>();
    
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
    
    private String getCategoryNameById(int id) {
        for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
            if (entry.getValue() == id) {
                return entry.getKey(); // nama kategori
            }
        }
        return null;
    }
    
    protected void tablePelanggan() {
        tablePelanggan(""); // Panggil versi dengan parameter, tapi kosong
    }
    private void tablePelanggan(String keyword) {
        Object[] Baris = {"ID Pelanggan", "Nama Pelanggan", "Email", "Alamat", "NO HP", "ID Order", "Status", "Tanggal Pemesanan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblPelanggan.setModel(tabmode);

        String sql = "SELECT u.id AS id_pelanggan, u.nama, u.email, u.alamat, u.no_hp, " +
                     "o.id AS id_order, o.status, o.tanggal_pemesanan " +
                     "FROM users u JOIN orders o ON u.id = o.user_id ";

        if (keyword != null && !keyword.isEmpty()) {
            sql += "WHERE CAST(o.id AS CHAR) LIKE ? OR u.nama LIKE ? OR o.tanggal_pemesanan LIKE ?";
        }

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            if (keyword != null && !keyword.isEmpty()) {
                String like = "%" + keyword + "%";
                stat.setString(1, like);
                stat.setString(2, like);
                stat.setString(3, like);
            }

            ResultSet hasil = stat.executeQuery();
            while (hasil.next()) {
                String[] data = {
                hasil.getString("id_pelanggan"),
                hasil.getString("nama"),
                hasil.getString("email"),
                hasil.getString("alamat"),
                hasil.getString("no_hp"),
                hasil.getString("id_order"),
                hasil.getString("status"),
                hasil.getString("tanggal_pemesanan")
                };
            tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal load data pelanggan: " + e.getMessage());
        }
    }
    
    protected void clearPelanggan() {
        tIdPesanan.setText("");
        tNamaPesanan.setText("");
        tAlamatPesanan.setText("");
        tNoHpPesanan.setText("");
        cbStatusPesanan.setSelectedIndex(0);
        tIdPesanan.setEnabled(true);
    }
    
    protected void getDataKategori() {
        Object[] Baris = {"id", "Nama Kategori", "Deleted_at", "Created_at", "Updated_at"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkategori.setModel(tabmode);
        
        try {
            String sql = "SELECT * FROM categories";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
       
            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String deleted_at = rs.getString("deleted_at");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");

                Object[] rowData = {id, nama, deleted_at, created_at, updated_at};
                tabmode.addRow(rowData);
            }

            rs.close();
            st.close();
                    
        } catch (Exception e) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void loadPromo() {
        Object[] baris = {
        "N0", "Nama", "Kode Promo", "Jenis Promo", 
        "Potongan Harga", "All Produk", "Tanggal Dimulai", 
        "Tanggal Berakhir", "Status"
        };
    
        tabmode = new DefaultTableModel(null, baris);
        tbl_promo.setModel(tabmode);
    
        String sql = "select * from promotions";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Iterasi data hasil query
            while(hasil.next()){
                String idpromo = hasil.getString("id");
                String namapromo = hasil.getString("nama_promo");
                String kodepromo = hasil.getString("kode_promo");
                String jenispromo = hasil.getString("jenis_promo");
                String potharga = hasil.getString("potongan_harga");
                String allproduk = hasil.getString("all_products");
                Date starDate = hasil.getTimestamp("start_date");
                Date endDate = hasil.getTimestamp("end_date");
                String stardate = starDate != null ? sdf.format(starDate): "";
                String enddate = endDate != null ? sdf.format(endDate): "";
                String status = hasil.getString("status");
            
                String[] data = {idpromo, namapromo, kodepromo, jenispromo, potharga, allproduk, stardate, enddate, status};
                tabmode.addRow(data);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
    }
     
     private void loadProdukPromo() {
        Object[] baris = {
            "Id", "Id Produk", "Id Promo"
        };
    
        tabmode = new DefaultTableModel(null, baris);
        table_pp.setModel(tabmode);
    
        String sql = "SELECT * from product_promotions";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
        
            // Iterasi data hasil query
            while(hasil.next()){
                String idProdukPromosi = hasil.getString("id");
                String idProduk = hasil.getString("product_id");
                String idPromo = hasil.getString("promo_id");
            
                String[] data = {idProdukPromosi, idProduk, idPromo};
                tabmode.addRow(data);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
     }
     
     protected void tableCustomer() {
         Object[] Baris = {"Id", "Nama Pelanggan", "Email Pelanggan", "Alamat", "No HP", "Role"};
        tabmode = new DefaultTableModel(null, Baris);
        tblCustomer.setModel(tabmode);
        
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
       
            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String email = rs.getString("email");
                String alamat = rs.getString("alamat");
                String noHp = rs.getString("no_hp");
                String role = rs.getString("role");

                Object[] rowData = {id, nama, email, alamat, noHp, role};
                tabmode.addRow(rowData);
            }

            rs.close();
            st.close();
                    
        } catch (Exception e) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
     }
     
     protected void kosongPelanggan() {
        tIdPelanggan.setText("");
        tNamaPelanggan.setText("");
        tEmailPelanggan.setText("");
        tNoHpPelanggan.setText("");
        tAlamatPelanggan.setText("");
        rolePelanggan.clearSelection();
        tPasswordPelanggan.setText("");
        tCPasswordPelanggan.setText("");
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rolePelanggan = new javax.swing.ButtonGroup();
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
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        c_produk = new javax.swing.JPanel();
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
        jLabel14 = new javax.swing.JLabel();
        t_idProduk = new javax.swing.JTextField();
        btn_addProduk = new javax.swing.JButton();
        btn_updetProduk = new javax.swing.JButton();
        btn_clearProduk = new javax.swing.JButton();
        btn_deletProduk = new javax.swing.JButton();
        btn_cetakProduk = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_produk = new javax.swing.JTable();
        c_kategori = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkategori = new javax.swing.JTable();
        tnamakategori = new javax.swing.JTextField();
        bsave = new javax.swing.JButton();
        bClearKat = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tidkategori = new javax.swing.JTextField();
        bDeleteKat = new javax.swing.JButton();
        c_pelanggan = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tNamaPelanggan = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tEmailPelanggan = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        tNoHpPelanggan = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tAlamatPelanggan = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        tPasswordPelanggan = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        tCPasswordPelanggan = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnEditCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        btnCetakCustomer = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        tCariCustomer = new javax.swing.JTextField();
        btnCariCustomer = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        rbRole1 = new javax.swing.JRadioButton();
        rbRole2 = new javax.swing.JRadioButton();
        tIdPelanggan = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        btnClearPelanggan = new javax.swing.JButton();
        c_pesanan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tAlamatPesanan = new javax.swing.JTextField();
        tNoHpPesanan = new javax.swing.JTextField();
        tNamaPesanan = new javax.swing.JTextField();
        tIdPesanan = new javax.swing.JTextField();
        bEditPelanggan = new javax.swing.JButton();
        bClearPelanggan = new javax.swing.JButton();
        tCariPesanan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbStatusPesanan = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        btnCariPesanan = new javax.swing.JButton();
        c_promo = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_promo = new javax.swing.JTable();
        bsave1 = new javax.swing.JButton();
        bupdate = new javax.swing.JButton();
        bclear = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tkode_promo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        tnama_promo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tpot_harga = new javax.swing.JTextField();
        semua_produk = new javax.swing.JCheckBox();
        cmbJenisPromo = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        tid_promo = new javax.swing.JTextField();
        ttd = new com.toedter.calendar.JDateChooser();
        ttb = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        tcari_promo = new javax.swing.JTextField();
        bcari_promo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tid_produk2 = new javax.swing.JTextField();
        tid_promo2 = new javax.swing.JTextField();
        tidpp = new javax.swing.JTextField();
        tnama_produk2 = new javax.swing.JTextField();
        tnama_promo2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        bcari_produk2 = new javax.swing.JButton();
        bcari_promo2 = new javax.swing.JButton();
        bsave_pp = new javax.swing.JButton();
        bupdate_pp = new javax.swing.JButton();
        bclear_pp = new javax.swing.JButton();
        bdelete_pp = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_pp = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        c_laporan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        btn_laporan.setText("LOGOUT");
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
                    .addComponent(btn_dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_pelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_promo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_kiriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
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
                .addContainerGap(981, Short.MAX_VALUE))
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
                .addContainerGap(2181, Short.MAX_VALUE))
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

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("SELAMAT DATANG DI MENU ADMIN");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("SILAKAN PILIH MENU DI SAMPING UNTUK MEMULAI");

        javax.swing.GroupLayout c_dashboardLayout = new javax.swing.GroupLayout(c_dashboard);
        c_dashboard.setLayout(c_dashboardLayout);
        c_dashboardLayout.setHorizontalGroup(
            c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
                .addContainerGap(1429, Short.MAX_VALUE))
        );
        c_dashboardLayout.setVerticalGroup(
            c_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_dashboardLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1207, Short.MAX_VALUE))
        );

        pn_utama.add(c_dashboard, "c_dashboard");

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
        btn_pilihGambar.setPreferredSize(new java.awt.Dimension(123, 30));
        btn_pilihGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihGambarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Id");

        t_idProduk.setEditable(false);
        t_idProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t_idProduk)
                            .addComponent(t_descProduk)
                            .addComponent(t_namaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(t_hargaProduk)
                            .addComponent(t_stokProduk)
                            .addComponent(btn_pilihGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(t_catProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_idProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(t_catProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_namaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_hargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_stokProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_descProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btn_pilihGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
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
        btn_clearProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearProdukActionPerformed(evt);
            }
        });

        btn_deletProduk.setText("DELETE");
        btn_deletProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletProdukActionPerformed(evt);
            }
        });

        btn_cetakProduk.setText("CETAK");
        btn_cetakProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakProdukActionPerformed(evt);
            }
        });

        tbl_produk.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_produkMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_produk);

        javax.swing.GroupLayout c_produkLayout = new javax.swing.GroupLayout(c_produk);
        c_produk.setLayout(c_produkLayout);
        c_produkLayout.setHorizontalGroup(
            c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_addProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_updetProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_clearProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_deletProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cetakProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(1399, Short.MAX_VALUE))
        );
        c_produkLayout.setVerticalGroup(
            c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(c_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_produkLayout.createSequentialGroup()
                        .addComponent(btn_addProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_updetProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_clearProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_deletProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cetakProduk))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(885, Short.MAX_VALUE))
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

        jLabel13.setText("Nama");

        jLabel20.setText("Id");

        tidkategori.setEnabled(false);

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
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(c_kategoriLayout.createSequentialGroup()
                                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel20))
                                .addGap(73, 73, 73)
                                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tidkategori)
                                    .addComponent(tnamakategori)))))
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClearKat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDeleteKat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1493, Short.MAX_VALUE))
        );
        c_kategoriLayout.setVerticalGroup(
            c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_kategoriLayout.createSequentialGroup()
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel15))
                    .addGroup(c_kategoriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tidkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(25, 25, 25)
                        .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tnamakategori, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGap(26, 26, 26)
                .addGroup(c_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClearKat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDeleteKat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1138, Short.MAX_VALUE))
        );

        pn_utama.add(c_kategori, "c_kategori");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Registrasi Pelanggan");

        jLabel35.setText("Nama");

        jLabel36.setText("Email");

        jLabel37.setText("No HP");

        jLabel38.setText("Alamat");

        tAlamatPelanggan.setColumns(20);
        tAlamatPelanggan.setRows(5);
        jScrollPane8.setViewportView(tAlamatPelanggan);

        jLabel39.setText("Password");

        jLabel40.setText("Confirm Password");

        btnRegister.setText("Registrasi");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnEditCustomer.setText("Edit");
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });

        btnDeleteCustomer.setText("Delete");
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });

        btnCetakCustomer.setText("Cetak");
        btnCetakCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakCustomerActionPerformed(evt);
            }
        });

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblCustomer);

        btnCariCustomer.setText("Cari");
        btnCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariCustomerActionPerformed(evt);
            }
        });

        jLabel41.setText("Role");

        rolePelanggan.add(rbRole1);
        rbRole1.setText("admin");
        rbRole1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRole1ActionPerformed(evt);
            }
        });

        rolePelanggan.add(rbRole2);
        rbRole2.setText("user");

        tIdPelanggan.setEnabled(false);

        jLabel42.setText("Id");

        btnClearPelanggan.setText("Clear");
        btnClearPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearPelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout c_pelangganLayout = new javax.swing.GroupLayout(c_pelanggan);
        c_pelanggan.setLayout(c_pelangganLayout);
        c_pelangganLayout.setHorizontalGroup(
            c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_pelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(c_pelangganLayout.createSequentialGroup()
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_pelangganLayout.createSequentialGroup()
                                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel42))
                                .addGap(18, 18, 18)
                                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tEmailPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tNoHpPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(c_pelangganLayout.createSequentialGroup()
                                        .addComponent(rbRole1)
                                        .addGap(42, 42, 42)
                                        .addComponent(rbRole2))
                                    .addComponent(tPasswordPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tCPasswordPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(c_pelangganLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_pelangganLayout.createSequentialGroup()
                                .addComponent(tCariCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCetakCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(1402, Short.MAX_VALUE))
        );
        c_pelangganLayout.setVerticalGroup(
            c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_pelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(tCariCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_pelangganLayout.createSequentialGroup()
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tEmailPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNoHpPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(rbRole1)
                            .addComponent(rbRole2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tPasswordPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addGap(11, 11, 11)
                        .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(tCPasswordPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClearPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCetakCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(901, 901, 901))
        );

        pn_utama.add(c_pelanggan, "c_pelanggan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID Pelanggan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Nama Pelanggan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Alamat");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("No HP");

        tNoHpPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNoHpPesananActionPerformed(evt);
            }
        });

        tNamaPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNamaPesananActionPerformed(evt);
            }
        });

        tIdPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIdPesananActionPerformed(evt);
            }
        });

        bEditPelanggan.setText("EDIT");
        bEditPelanggan.setPreferredSize(new java.awt.Dimension(150, 29));
        bEditPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditPelangganActionPerformed(evt);
            }
        });

        bClearPelanggan.setText("CLEAR");
        bClearPelanggan.setPreferredSize(new java.awt.Dimension(150, 29));
        bClearPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearPelangganActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Status");

        cbStatusPesanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Status Pemesanan --", "menunggu_pembayaran", "diproses", "dikirim", "selesai" }));

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPelangganMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblPelanggan);

        btnCariPesanan.setText("Cari");
        btnCariPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout c_pesananLayout = new javax.swing.GroupLayout(c_pesanan);
        c_pesanan.setLayout(c_pesananLayout);
        c_pesananLayout.setHorizontalGroup(
            c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_pesananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(c_pesananLayout.createSequentialGroup()
                        .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(c_pesananLayout.createSequentialGroup()
                                    .addComponent(tCariPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnCariPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                            .addGroup(c_pesananLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(30, 30, 30)
                                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tIdPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tNamaPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(c_pesananLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(113, 113, 113)
                                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(c_pesananLayout.createSequentialGroup()
                                        .addComponent(bEditPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(bClearPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbStatusPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(1563, Short.MAX_VALUE))
                    .addGroup(c_pesananLayout.createSequentialGroup()
                        .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_pesananLayout.createSequentialGroup()
                                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(108, 108, 108)
                                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tAlamatPesanan)
                                    .addComponent(tNoHpPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        c_pesananLayout.setVerticalGroup(
            c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_pesananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCariPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPesanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tIdPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tNamaPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tAlamatPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tNoHpPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbStatusPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(c_pesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEditPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClearPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pn_utama.add(c_pesanan, "c_pesanan");

        tbl_promo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "title1", "title2", "title3", "title4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_promo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_promoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_promo);

        bsave1.setText("SAVE");
        bsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsave1ActionPerformed(evt);
            }
        });

        bupdate.setText("UPDATE");
        bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdateActionPerformed(evt);
            }
        });

        bclear.setText("CLEAR");
        bclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclearActionPerformed(evt);
            }
        });

        bdelete.setText("DELETE");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setText("Nama Promo");

        jLabel22.setText("Jenis Promo");

        jLabel23.setText("True or False");

        jLabel24.setText("Tanggal Dimulai");

        jLabel25.setText("Tanggal Berakhir");

        jLabel26.setText("Potongan Harga");

        jLabel27.setText("Status");

        jLabel28.setText("Kode Promo");

        semua_produk.setText("Semua Produk");

        cmbJenisPromo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "persentase", "nominal" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "aktif", "nonaktif" }));

        jLabel29.setText("Id Promo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel23)
                            .addComponent(jLabel16)
                            .addComponent(jLabel27)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel22)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tid_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpot_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnama_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tkode_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semua_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbJenisPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tid_promo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnama_promo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tkode_promo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(cmbJenisPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpot_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(semua_produk))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel27))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ttb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        jLabel30.setText("Pencarian Promo");

        bcari_promo.setText("Cari");
        bcari_promo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_promoActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel18.setText("Id Produk Promo");

        jLabel31.setText("Id Produk");

        jLabel32.setText("Id Promo");

        tidpp.setEnabled(false);

        jLabel33.setText("Nama Produk");

        jLabel34.setText("Nama Promo");

        bcari_produk2.setText("Cari");
        bcari_produk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_produk2ActionPerformed(evt);
            }
        });

        bcari_promo2.setText("Cari");
        bcari_promo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_promo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34))
                .addGap(53, 53, 53)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tid_produk2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tidpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(bcari_produk2))
                            .addComponent(tnama_promo2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tid_promo2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bcari_promo2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(tnama_produk2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(tidpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tid_produk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(bcari_produk2))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnama_produk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tid_promo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_promo2)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(tnama_promo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        bsave_pp.setText("SAVE");
        bsave_pp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsave_ppActionPerformed(evt);
            }
        });

        bupdate_pp.setText("UPDATE");
        bupdate_pp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdate_ppActionPerformed(evt);
            }
        });

        bclear_pp.setText("CLEAR");
        bclear_pp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclear_ppActionPerformed(evt);
            }
        });

        bdelete_pp.setText("DELETE");
        bdelete_pp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdelete_ppActionPerformed(evt);
            }
        });

        table_pp.setModel(new javax.swing.table.DefaultTableModel(
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
        table_pp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ppMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table_pp);

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout c_promoLayout = new javax.swing.GroupLayout(c_promo);
        c_promo.setLayout(c_promoLayout);
        c_promoLayout.setHorizontalGroup(
            c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_promoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(c_promoLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bsave1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bclear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_promoLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bsave_pp)
                                    .addComponent(bupdate_pp)
                                    .addComponent(bclear_pp)
                                    .addComponent(bdelete_pp)))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, c_promoLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGap(0, 1311, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(c_promoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(tcari_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bcari_promo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c_promoLayout.setVerticalGroup(
            c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_promoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(c_promoLayout.createSequentialGroup()
                        .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(c_promoLayout.createSequentialGroup()
                                .addComponent(bsave1)
                                .addGap(18, 18, 18)
                                .addComponent(bupdate)
                                .addGap(18, 18, 18)
                                .addComponent(bclear)
                                .addGap(18, 18, 18)
                                .addComponent(bdelete))
                            .addGroup(c_promoLayout.createSequentialGroup()
                                .addComponent(bsave_pp)
                                .addGap(18, 18, 18)
                                .addComponent(bupdate_pp)
                                .addGap(18, 18, 18)
                                .addComponent(bclear_pp)
                                .addGap(18, 18, 18)
                                .addComponent(bdelete_pp))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(c_promoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(tcari_promo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_promo)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(806, Short.MAX_VALUE))
        );

        pn_utama.add(c_promo, "c_promo");

        jLabel19.setText("INI LAPORAN");

        javax.swing.GroupLayout c_laporanLayout = new javax.swing.GroupLayout(c_laporan);
        c_laporan.setLayout(c_laporanLayout);
        c_laporanLayout.setHorizontalGroup(
            c_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_laporanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel19)
                .addContainerGap(2206, Short.MAX_VALUE))
        );
        c_laporanLayout.setVerticalGroup(
            c_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(c_laporanLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel19)
                .addContainerGap(1417, Short.MAX_VALUE))
        );

        pn_utama.add(c_laporan, "c_laporan");

        javax.swing.GroupLayout pn_dasarLayout = new javax.swing.GroupLayout(pn_dasar);
        pn_dasar.setLayout(pn_dasarLayout);
        pn_dasarLayout.setHorizontalGroup(
            pn_dasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dasarLayout.createSequentialGroup()
                .addGap(31, 31, 31)
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(HomeAdmin.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_updetProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updetProdukActionPerformed
        try {
            // Ambil nilai dari input form
            int idProduk = Integer.parseInt(t_idProduk.getText());
            String namaProduk = t_namaProduk.getText();
            String hargaProduk = t_hargaProduk.getText();
            String stokProduk = t_stokProduk.getText();
            String deskripsi = t_descProduk.getText();
            String gambar = selectedFileName;
            
            // Validasi Gambar
            if (gambar == null || gambar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Silakan pilih gambar terlebih dahulu.");
            return;
            }
            
            // Ambil kategori dari combo box
            String namaKategori = (String) t_catProduk.getSelectedItem();
            if (namaKategori == null || namaKategori.equals("-- Pilih Kategori --")) {
                JOptionPane.showMessageDialog(this, "Silakan pilih kategori terlebih dahulu.");
                return;
            }

            // Ambil ID kategori dari Map
            Integer categoryId = categoryMap.get(namaKategori);
            if (categoryId == null) {
                JOptionPane.showMessageDialog(this, "Kategori tidak valid.");
                return;
            }

            // Query update produk
            String sql = "UPDATE products SET category_id = ?, nama = ?, harga = ?, stok = ?, deskripsi = ?, gambar = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, categoryId);
            pst.setString(2, namaProduk);
            pst.setString(3, hargaProduk);
            pst.setString(4, stokProduk);
            pst.setString(5, deskripsi);
            pst.setString(6, gambar);
            pst.setInt(7, idProduk);

            int hasil = pst.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Data produk berhasil diperbarui.");
                getDataProduk(); // refresh tabel
                clearFormProduk(); // kosongkan form input
            } else {
                JOptionPane.showMessageDialog(this, "Data produk gagal diperbarui.");
            }

            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_updetProdukActionPerformed

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
                clearFormProduk();
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
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileName = selectedFile.getName();  // ← hanya nama filenya
            btn_pilihGambar.setText(selectedFileName);  // tampilkan di tombol
        }
    }//GEN-LAST:event_btn_pilihGambarActionPerformed

    private void t_descProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_descProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_descProdukActionPerformed

    private void t_stokProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_stokProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_stokProdukActionPerformed

    private void t_hargaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_hargaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_hargaProdukActionPerformed

    private void t_namaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_namaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_namaProdukActionPerformed

    private void t_catProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_catProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_catProdukActionPerformed

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        if (selectedLabel != btn_laporan) {
            btn_laporan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_laporanMouseExited

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        btn_laporan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_laporan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
        // Hapus session
        Login.idUser = null;

        // Tampilkan kembali form login
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }
//        CardLayout ly = (CardLayout)pn_utama.getLayout();
//        ly.show(pn_utama, "c_laporan");
//
//        if (selectedLabel != null) {
//            selectedLabel.setBackground(new Color(240, 240, 240));
//        }
//
//        selectedLabel = btn_laporan;
//        btn_laporan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_promoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseExited
        if (selectedLabel != btn_promo) {
            btn_promo.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_promoMouseExited

    private void btn_promoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseEntered
        btn_promo.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_promo.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_promo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_promoMouseEntered

    private void btn_promoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_promoMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_promo");

        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }

        selectedLabel = btn_promo;
        btn_promo.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_promoMouseClicked

    private void btn_pelangganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseExited
        if (selectedLabel != btn_pelanggan) {
            btn_pelanggan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_pelangganMouseExited

    private void btn_pelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseEntered
        btn_pelanggan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_pelanggan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_pelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_pelangganMouseEntered

    private void btn_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pelangganMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_pelanggan");

        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }

        selectedLabel = btn_pelanggan;
        btn_pelanggan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_pelangganMouseClicked

    private void btn_pesananMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseExited
        if (selectedLabel != btn_pesanan) {
            btn_pesanan.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_pesananMouseExited

    private void btn_pesananMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseEntered
        btn_pesanan.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_pesanan.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_pesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_pesananMouseEntered

    private void btn_pesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesananMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_pesanan");

        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }

        selectedLabel = btn_pesanan;
        btn_pesanan.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_pesananMouseClicked

    private void btn_kategoriMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseExited
        if (selectedLabel != btn_kategori) {
            btn_kategori.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_kategoriMouseExited

    private void btn_kategoriMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseEntered
        btn_kategori.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_kategori.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_kategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_kategoriMouseEntered

    private void btn_kategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_kategori");

        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }

        selectedLabel = btn_kategori;
        btn_kategori.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_kategoriMouseClicked

    private void btn_produkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseExited
        if (selectedLabel != btn_produk) {
            btn_produk.setBackground(new Color(240, 240, 240));
        }
    }//GEN-LAST:event_btn_produkMouseExited

    private void btn_produkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseEntered
        btn_produk.setOpaque(true);
        btn_produk.setBackground(new Color(200, 200, 200));
        btn_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_produkMouseEntered

    private void btn_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produkMouseClicked
        CardLayout ly = (CardLayout)pn_utama.getLayout();
        ly.show(pn_utama, "c_produk");

        if (selectedLabel != null) {
            selectedLabel.setBackground(new Color(240, 240, 240));
        }

        selectedLabel = btn_produk;
        btn_produk.setBackground(new Color(150, 150, 150));
    }//GEN-LAST:event_btn_produkMouseClicked

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
        if (selectedLabel != btn_dashboard) {
            btn_dashboard.setBackground(new Color(240, 240, 240)); // Warna default
        }
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        btn_dashboard.setOpaque(true); // Pastikan warna latar belakang berubah
        btn_dashboard.setBackground(new Color(200, 200, 200)); // Warna hover
        btn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_dashboardMouseEntered

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

    private void bEditPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditPelangganActionPerformed
        try {
            // 1. Update tabel users berdasarkan nama pelanggan
            String sqlUsers = "UPDATE users SET nama = ?, alamat = ?, no_hp = ? " +
                              "WHERE id = (SELECT user_id FROM orders WHERE id = ?)";
            PreparedStatement statUsers = conn.prepareStatement(sqlUsers);
            statUsers.setString(1, tNamaPesanan.getText());
            statUsers.setString(2, tAlamatPesanan.getText());
            statUsers.setString(3, tNoHpPesanan.getText());
            statUsers.setString(4, tIdPesanan.getText()); // ID Order
            statUsers.executeUpdate();

            // 2. Update status pesanan di tabel orders berdasarkan ID Order
            String sqlOrders = "UPDATE orders SET status = ? WHERE id = ?";
            PreparedStatement statOrders = conn.prepareStatement(sqlOrders);
            statOrders.setString(1, cbStatusPesanan.getSelectedItem().toString());
            statOrders.setString(2, tIdPesanan.getText()); // ID Order
            statOrders.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data pelanggan dan status berhasil diubah!");

           clearPelanggan();
           tablePelanggan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah! " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bEditPelangganActionPerformed

    private void bClearPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearPelangganActionPerformed
        clearPelanggan();
    }//GEN-LAST:event_bClearPelangganActionPerformed

    private void t_idProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_idProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idProdukActionPerformed

    private void tbl_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_produkMouseClicked
        int bar = tbl_produk.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbl_produk.getModel();

        if (bar != -1 && model.getColumnCount() >= 7) {
            String a = model.getValueAt(bar, 0).toString(); // ID Produk
            String b = model.getValueAt(bar, 1).toString(); // Kategori
            String c = model.getValueAt(bar, 2).toString(); // Nama Produk
            String d = model.getValueAt(bar, 3).toString(); // Harga
            String e = model.getValueAt(bar, 4).toString(); // Stok
            String f = model.getValueAt(bar, 5).toString(); // Deskripsi
            String g = model.getValueAt(bar, 6).toString(); // Nama Gambar (file path)

            t_idProduk.setText(a);
            t_idProduk.setEnabled(true);
            int idCategory = Integer.parseInt(model.getValueAt(bar, 1).toString());
            String namaKategori = getCategoryNameById(idCategory);

            if (namaKategori != null) {
                t_catProduk.setSelectedItem(namaKategori);
            } else {
                t_catProduk.setSelectedIndex(0); // "-- Pilih Kategori --"
            }
            t_namaProduk.setText(c);
            t_hargaProduk.setText(d);
            t_stokProduk.setText(e);
            t_descProduk.setText(f);
            btn_pilihGambar.setText(g); // jika ingin menampilkan nama file gambar
            t_idProduk.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Data produk tidak lengkap atau tidak valid.");
        }
    }//GEN-LAST:event_tbl_produkMouseClicked

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {                                      
        String keyword = tCariPesanan.getText();

        String sql = "SELECT " +
                 "u.id AS id_pelanggan, " +
                 "u.nama, " +
                 "u.email, " +
                 "u.alamat, " +
                 "u.no_hp, " +
                 "o.id AS id_order, " +
                 "o.status, " +
                 "o.tanggal_pemesanan " +
                 "FROM users u " +
                 "JOIN orders o ON u.id = o.user_id " +
                 "WHERE u.id LIKE ? OR o.id LIKE ? OR o.tanggal_pemesanan LIKE ?";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, "%" + keyword + "%"); // cari ID Pelanggan
            stat.setString(2, "%" + keyword + "%"); // cari ID Order
            stat.setString(3, "%" + keyword + "%"); // cari Tanggal Pemesanan

            ResultSet rs = stat.executeQuery();
            tabmode.setRowCount(0); // Clear table

            while (rs.next()) {
                String a = rs.getString("id_pelanggan");
                String b = rs.getString("nama");
                String c = rs.getString("email");
                String d = rs.getString("alamat");
                String e = rs.getString("no_hp");
                String f = rs.getString("id_order");
                String g = rs.getString("status");
                String h = rs.getString("tanggal_pemesanan");

                String[] data = {a, b, c, d, e, f, g, h};
                tabmode.addRow(data);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat mencari data: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }                                
    }                                    

    private void bDeleteKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteKatActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            String sql = "UPDATE categories SET deleted_at = NOW() WHERE id = ?";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tidkategori.getText());
                int rowsUpdated = stat.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus (soft delete)!");
                    tidkategori.setText("");
                    tnamakategori.setText("");
                    tidkategori.requestFocus();
                    getDataKategori(); // pastikan hanya ambil data yang belum dihapus
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau sudah dihapus.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_bDeleteKatActionPerformed

    private void tblkategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkategoriMouseClicked
        int bar = tblkategori.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblkategori.getModel();

        if (bar != -1 && model.getColumnCount() >= 2) {
            String a = model.getValueAt(bar, 0).toString(); // ID Kategori
            String b = model.getValueAt(bar, 1).toString(); // Nama Kategori

            tidkategori.setText(a);
            tnamakategori.setText(b);
        } else {
            JOptionPane.showMessageDialog(this, "Data kategori tidak valid atau kolom kurang.");
        }
    }//GEN-LAST:event_tblkategoriMouseClicked

    private void tnamakategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamakategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamakategoriActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
        // TODO add your handling code here:
        String namaKategori = tnamakategori.getText ();
        try {
            String sql = "INSERT INTO categories (nama) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1,namaKategori);

            st.executeUpdate();
            JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
            getDataKategori();
        }   catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan Kategori!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bsaveActionPerformed

    private void bClearKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearKatActionPerformed
        tidkategori.setText("");
        tnamakategori.setText("");
    }//GEN-LAST:event_bClearKatActionPerformed

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

            getDataKategori();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah! " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_beditActionPerformed

    private void tbl_promoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_promoMouseClicked
        int row = tbl_promo.getSelectedRow();

        // Ambil model langsung dari tabel
        DefaultTableModel model = (DefaultTableModel) tbl_promo.getModel();

        if (row != -1 && model.getColumnCount() >= 9) {
            try {
                tid_promo.setText(model.getValueAt(row, 0).toString());
                tnama_promo.setText(model.getValueAt(row, 1).toString());
                tkode_promo.setText(model.getValueAt(row, 2).toString());
                cmbJenisPromo.setSelectedItem(model.getValueAt(row, 3).toString());
                tpot_harga.setText(model.getValueAt(row, 4).toString());
                semua_produk.setText(model.getValueAt(row, 5).toString());

                // Tanggal mulai dan tanggal berakhir
                String startDateStr = model.getValueAt(row, 6).toString();
                String endDateStr = model.getValueAt(row, 7).toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = sdf.parse(startDateStr);
                Date endDate = sdf.parse(endDateStr);

                ttd.setDate(startDate);
                ttb.setDate(endDate);

                // Status promo
                cmbStatus.setSelectedItem(model.getValueAt(row, 8).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Kesalahan saat mengambil data promo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Data promo tidak lengkap atau baris tidak valid.");
        }
    }//GEN-LAST:event_tbl_promoMouseClicked

    private void bsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsave1ActionPerformed
        String namaPromo =  tnama_promo.getText();
        String kodePromo =  tkode_promo.getText();
        String jenisPromo =  cmbJenisPromo.getSelectedItem().toString();
        String potonganHarga =  tpot_harga.getText();
        int allProducts =  semua_produk.isSelected()?1:0;
        java.util.Date tglMulai = ttd.getDate();
        java.util.Date tglAkhir = ttd.getDate();
        String status = cmbStatus.getSelectedItem().toString();

        if(namaPromo.isEmpty() || kodePromo.isEmpty() || jenisPromo.isEmpty() || potonganHarga.isEmpty() || tglMulai ==null || tglAkhir==null || status.isEmpty()){
            JOptionPane.showMessageDialog(this, "Semua field harus diisi","Peringatan",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String sql = "INSERT INTO promotions (nama_promo, kode_promo, jenis_promo, potongan_harga, all_products, start_date, end_date, status) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, namaPromo);
            st.setString(2, kodePromo);
            st.setString(3, jenisPromo);
            st.setDouble(4, Double.parseDouble(potonganHarga));
            st.setInt(5, allProducts);

            java.sql.Timestamp sqlStart = new java.sql.Timestamp(tglMulai.getTime());
            java.sql.Timestamp sqlEnd = new java.sql.Timestamp(tglAkhir.getTime());

            st.setTimestamp(6, sqlStart);
            st.setTimestamp(7, sqlEnd);
            st.setString(8, status);

            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Promo berhasil disimpan");

            loadPromo();

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data" + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_bsave1ActionPerformed

    private void bupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdateActionPerformed
        String namaPromo =  tnama_promo.getText();
        String kodePromo =  tkode_promo.getText();
        String jenisPromo =  cmbJenisPromo.getSelectedItem().toString();
        String potonganHarga =  tpot_harga.getText();
        int allProducts =  semua_produk.isSelected()?1:0;
        java.util.Date tglMulai = ttd.getDate();
        java.util.Date tglAkhir = ttd.getDate();
        String status =  cmbStatus.getSelectedItem().toString();

        if(namaPromo.isEmpty() || kodePromo.isEmpty() || jenisPromo.isEmpty() || potonganHarga.isEmpty() || tglMulai ==null || tglAkhir==null || status.isEmpty()){
            JOptionPane.showMessageDialog(this, "Semua field harus diisi","Peringatan",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String sql = "UPDATE promotions SET kode_promo=?, jenis_promo=?, potongan_harga=?, all_products=?, start_date=?, end_date=?, status=? WHERE nama_promo=?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, kodePromo);
            st.setString(2, jenisPromo);
            st.setDouble(3, Double.parseDouble(potonganHarga));
            st.setInt(4, allProducts);

            java.sql.Timestamp sqlStart = new java.sql.Timestamp(tglMulai.getTime());
            java.sql.Timestamp sqlEnd = new java.sql.Timestamp(tglAkhir.getTime());

            st.setTimestamp(5, sqlStart);
            st.setTimestamp(6, sqlEnd);
            st.setString(7, status);
            st.setString(8, namaPromo);

            int result = st.executeUpdate();
            if(result>0){
                JOptionPane.showMessageDialog(this, "Data Promo berhasil diupdate");
            }else{
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan atau gagal diupdate");
            }

            loadPromo();

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal update data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bupdateActionPerformed

    private void bclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclearActionPerformed
        tid_promo.setText("");
        tnama_promo.setText("");
        tkode_promo.setText("");
        cmbJenisPromo.setSelectedItem("");
        tpot_harga.setText("");

        ttd.setDate(null);
        ttd.setDate(null);
        cmbStatus.setSelectedItem("");
        // TODO add your handling code here:
    }//GEN-LAST:event_bclearActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus","KOnfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "delete from promotions where nama_promo='"+tnama_promo.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");

                tnama_promo.requestFocus();
                loadPromo();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Data gagal dihapus+e");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_bdeleteActionPerformed

    private void bcari_promoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_promoActionPerformed
        String keyword = tcari_promo.getText().trim();
       Object[] baris = {
        "N0", "Nama", "Kode Promo", "Jenis Promo",
        "Potongan Harga", "All Produk", "Tanggal Dimulai",
        "Tanggal Berakhir", "Status"
    };


        tabmode = new DefaultTableModel(null, baris);
    tbl_promo.setModel(tabmode);

    String sql = "SELECT * FROM promotions WHERE nama_promo LIKE ? OR kode_promo LIKE ?";

    try {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        ResultSet hasil = pst.executeQuery();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int no = 1;

        while (hasil.next()) {
            String idpromo = String.valueOf(no++);
            String namapromo = hasil.getString("nama_promo");
            String kodepromo = hasil.getString("kode_promo");
            String jenispromo = hasil.getString("jenis_promo");
            String potharga = hasil.getString("potongan_harga");
            String allproduk = hasil.getString("all_products");
            Date starDate = hasil.getTimestamp("start_date");
            Date endDate = hasil.getTimestamp("end_date");
            String stardate = starDate != null ? sdf.format(starDate) : "";
            String enddate = endDate != null ? sdf.format(endDate) : "";
            String status = hasil.getString("status");

            String[] data = {idpromo, namapromo, kodepromo, jenispromo, potharga, allproduk, stardate, enddate, status};
            tabmode.addRow(data);
        }

        hasil.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mencari data: " + e.getMessage());
    }
    }//GEN-LAST:event_bcari_promoActionPerformed

    private void bcari_produk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_produk2ActionPerformed
        String sql = "select * from products where id = '"+tid_produk2.getText()+"'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String nm = hasil.getString("nama");
                tnama_produk2.setText(nm);
                tnama_produk2.setEnabled(false);
            }
        }catch(Exception e){}
        // TODO add your handling code here:
    }//GEN-LAST:event_bcari_produk2ActionPerformed

    private void bcari_promo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_promo2ActionPerformed
        String sql = "select * from promotions where id = '"+tid_promo2.getText()+"'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String nm = hasil.getString("nama_promo");
                tnama_promo2.setText(nm);
                tnama_promo2.setEnabled(false);
            }
        }catch(Exception e){}
        // TODO add your handling code here:
    }//GEN-LAST:event_bcari_promo2ActionPerformed

    private void bsave_ppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsave_ppActionPerformed
        String idProdukPromo =  tidpp.getText();
        String idProduk2 =  tid_produk2.getText();
        String idPromo2 =  tid_promo2.getText();

        if(idProdukPromo.isEmpty() || idProduk2.isEmpty() || idPromo2.isEmpty()){
            JOptionPane.showMessageDialog(this, "Semua field harus diisi","Peringatan",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String sql = "INSERT INTO product_promotions (id, product_id, promo_id) VALUES (?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, idProdukPromo);
            st.setString(2, idProduk2);
            st.setString(3, idPromo2);

            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Produk Promo berhasil disimpan");

            loadProdukPromo();

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data" + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_bsave_ppActionPerformed

    private void bupdate_ppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdate_ppActionPerformed
        String idProdukPromo =  tidpp.getText();
        String idProduk2 =  tid_produk2.getText();
        String idPromo2 =  tid_promo2.getText();

        if(idProdukPromo.isEmpty() || idProduk2.isEmpty() || idPromo2.isEmpty()){
            JOptionPane.showMessageDialog(this, "Semua field harus diisi","Peringatan",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String sql = "update product_promotions set product_id=?,promo_id=? where id=?";
                    
            PreparedStatement st = conn.prepareStatement(sql);

            
            st.setString(1, idProduk2);
            st.setString(2, idPromo2);
            st.setString(3, idProdukPromo);

            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Produk promo berhasil diupdate");

            loadProdukPromo();

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data" + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_bupdate_ppActionPerformed

    private void table_ppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ppMouseClicked
        int bar = table_pp.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) table_pp.getModel();

        if (bar != -1 && model.getColumnCount() >= 3) {
            String a = model.getValueAt(bar, 0).toString();
            String b = model.getValueAt(bar, 1).toString();
            String c = model.getValueAt(bar, 2).toString();

            tidpp.setText(a);
            tidpp.setEnabled(false); // Mengunci ID agar tidak diubah
            tid_produk2.setText(b);
            tid_promo2.setText(c);
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap atau kolom kurang.");
        }
    }//GEN-LAST:event_table_ppMouseClicked

    private void bclear_ppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear_ppActionPerformed
        tidpp.setText("");
        tid_produk2.setText("");
        tnama_produk2.setText("");
        tnama_produk2.setEnabled(true);
        tid_promo2.setText("");
        tnama_promo2.setText("");
        tnama_promo2.setEnabled(true);
    }//GEN-LAST:event_bclear_ppActionPerformed

    private void bdelete_ppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdelete_ppActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus","KOnfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "delete from product_promotions where id='"+tidpp.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");

                tidpp.requestFocus();
                loadProdukPromo();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Data gagal dihapus+e");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_bdelete_ppActionPerformed

    private void rbRole1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRole1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbRole1ActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String nama = tNamaPelanggan.getText();
        String email = tEmailPelanggan.getText();
        String noHp = tNoHpPelanggan.getText();
        String alamat = tAlamatPelanggan.getText();
        String role;
        if (rbRole1.isSelected()) {
            role = rbRole1.getText();
        } else {
            role = rbRole2.getText();
        }
        String pass1 = tPasswordPelanggan.getText();
        String pass2 = tCPasswordPelanggan.getText();
        
        if (nama.isEmpty() || email.isEmpty() || noHp.isEmpty() || alamat.isEmpty() || role.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua Kolom Harus Terisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi panjang password maksimal 8 karakter
        if (pass1.length() > 8) {
            JOptionPane.showMessageDialog(this, "Password maksimal 8 karakter!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi password harus sama dengan konfirmasi password
        if (!pass1.equals(pass2)) {
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
            st.setString(3, pass2);
            st.setString(4, role);
            st.setString(5, alamat);
            st.setString(6, noHp);
            
            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");                
            }
            
            kosongPelanggan();
            tableCustomer();
            st.close();
        } catch (Exception e) {
           Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        int bar = tblCustomer.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        
        tIdPelanggan.setText(a);
        tNamaPelanggan.setText(b);
        tEmailPelanggan.setText(c);
        if (f.equals("admin")) {
            rbRole1.setSelected(true);
            rbRole2.setSelected(false);
        } else {
            rbRole1.setSelected(false);
            rbRole2.setSelected(true);
        }
        tNoHpPelanggan.setText(d);
        tAlamatPelanggan.setText(e);
        
        btnRegister.setEnabled(false);
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        String id = tIdPelanggan.getText();
        String nama = tNamaPelanggan.getText();
        String email = tEmailPelanggan.getText();
        String noHp = tNoHpPelanggan.getText();
        String alamat = tAlamatPelanggan.getText();
        String role;
        if (rbRole1.isSelected()) {
            role = rbRole1.getText();
        } else {
            role = rbRole2.getText();
        }
        String pass1 = tPasswordPelanggan.getText();
        String pass2 = tCPasswordPelanggan.getText();
        try {
            String sql = "UPDATE users SET nama=?, email=?, password=?, role=?, alamat=?, no_hp=? WHERE id=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, nama);
            stat.setString(2, email);
            stat.setString(3, pass2);
            stat.setString(4, role);
            stat.setString(5, alamat);
            stat.setString(6, noHp);
            stat.setString(7, id);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");

            kosongPelanggan();
            tableCustomer();
            btnRegister.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah! " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnClearPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPelangganActionPerformed
        kosongPelanggan();
        btnRegister.setEnabled(true);
    }//GEN-LAST:event_btnClearPelangganActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin untuk dihapus?", "Konfirmasi Dialgo", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM users WHERE id = '" + tIdPelanggan.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
                
                kosongPelanggan();
                tableCustomer();
                tIdPelanggan.requestFocus();
                btnRegister.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!" + e);
            } 
        }
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void btnCetakCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakCustomerActionPerformed
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tugasakhir_db", "root", "");

            String reportPath = "src/laporan/laporanpelanggan.jasper";
            Map<String, Object> params = new HashMap<>();
            params.put("PARAM_ID_TRANSAKSI", 123);

            JasperPrint jprint = JasperFillManager.fillReport(reportPath, params, conn);
            JasperViewer.viewReport(jprint, false);

        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak struk: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakCustomerActionPerformed

    private void btnCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariCustomerActionPerformed
        String keyword = tCariCustomer.getText();

        String sql = "SELECT * FROM users WHERE nama LIKE ? OR id LIKE ?";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, "%" + keyword + "%"); 
            stat.setString(2, "%" + keyword + "%");

            ResultSet rs = stat.executeQuery();
            tabmode.setRowCount(0);
            
            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String email = rs.getString("email");
                String alamat = rs.getString("alamat");
                String noHp = rs.getString("no_hp");
                String role = rs.getString("role");

                Object[] rowData = {id, nama, email, alamat, noHp, role};
                tabmode.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat mencari data: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void tNoHpPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNoHpPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNoHpPesananActionPerformed

    private void tNamaPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaPesananActionPerformed

    private void tIdPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIdPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tIdPesananActionPerformed

    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPelangganMouseClicked
       int bar = tblPelanggan.getSelectedRow();
       DefaultTableModel model = (DefaultTableModel) tblPelanggan.getModel();

        if (bar < 0 || model.getColumnCount() < 8) {
            JOptionPane.showMessageDialog(null, "Data tabel tidak sesuai!");
            return;
        }

        String idPelanggan = model.getValueAt(bar, 0).toString();
        String nama = model.getValueAt(bar, 1).toString();
        String email = model.getValueAt(bar, 2).toString();
        String alamat = model.getValueAt(bar, 3).toString();
        String noHp = model.getValueAt(bar, 4).toString();
        String idOrder = model.getValueAt(bar, 5).toString();
        String status = model.getValueAt(bar, 6).toString();
        String tanggal = model.getValueAt(bar, 7).toString();

        tIdPesanan.setText(idOrder);
        tIdPesanan.setEnabled(false);
        tNamaPesanan.setText(nama);
        tAlamatPesanan.setText(alamat);
        tNoHpPesanan.setText(noHp);
        cbStatusPesanan.setSelectedItem(status);
    }//GEN-LAST:event_tblPelangganMouseClicked

    private void btn_clearProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearProdukActionPerformed
        clearFormProduk();
        t_idProduk.setEnabled(false);
    }//GEN-LAST:event_btn_clearProdukActionPerformed

    private void btn_deletProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletProdukActionPerformed
        try {
            // Ambil ID produk dari form
            String idProdukStr = t_idProduk.getText();

            // Validasi jika kosong
            if (idProdukStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Silakan pilih produk yang ingin dihapus terlebih dahulu.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, 
                "Yakin ingin menghapus produk ini?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int idProduk = Integer.parseInt(idProdukStr);
                
                String sql = "DELETE FROM products WHERE id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, idProduk);

                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Produk berhasil dihapus.");
                    getDataProduk();     // refresh tabel
                    clearFormProduk();  // kosongkan form input
                } else {
                    JOptionPane.showMessageDialog(this, "Produk tidak ditemukan atau gagal dihapus.");
                }

                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus produk: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_deletProdukActionPerformed

    private void btn_cetakProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakProdukActionPerformed
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tugasakhir_db", "root", "");

            String reportPath = "src/laporan/laporanproduk.jasper";
            Map<String, Object> params = new HashMap<>();
            params.put("PARAM_ID_TRANSAKSI", 123);

            JasperPrint jprint = JasperFillManager.fillReport(reportPath, params, conn);
            JasperViewer.viewReport(jprint, false);

        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak struk: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_cetakProdukActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tugasakhir_db", "root", "");

            String reportPath = "src/laporan/laporanpromo.jasper";
            Map<String, Object> params = new HashMap<>();
            params.put("PARAM_ID_TRANSAKSI", 123);

            JasperPrint jprint = JasperFillManager.fillReport(reportPath, params, conn);
            JasperViewer.viewReport(jprint, false);

        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak struk: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCariPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananActionPerformed
        String keyword = tCariPesanan.getText().trim();
        tablePelanggan(keyword);
    }//GEN-LAST:event_btnCariPesananActionPerformed

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
    private javax.swing.JButton bClearPelanggan;
    private javax.swing.JButton bDeleteKat;
    private javax.swing.JButton bEditPelanggan;
    private javax.swing.JButton bcari_produk2;
    private javax.swing.JButton bcari_promo;
    private javax.swing.JButton bcari_promo2;
    private javax.swing.JButton bclear;
    private javax.swing.JButton bclear_pp;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bdelete_pp;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bsave;
    private javax.swing.JButton bsave1;
    private javax.swing.JButton bsave_pp;
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnCariPesanan;
    private javax.swing.JButton btnCetakCustomer;
    private javax.swing.JButton btnClearPelanggan;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnRegister;
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
    private javax.swing.JButton bupdate;
    private javax.swing.JButton bupdate_pp;
    private javax.swing.JPanel c_dashboard;
    private javax.swing.JPanel c_kategori;
    private javax.swing.JPanel c_laporan;
    private javax.swing.JPanel c_pelanggan;
    private javax.swing.JPanel c_pesanan;
    private javax.swing.JPanel c_produk;
    private javax.swing.JPanel c_promo;
    private javax.swing.JComboBox<String> cbStatusPesanan;
    private javax.swing.JComboBox<String> cmbJenisPromo;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pn_atas;
    private javax.swing.JPanel pn_dasar;
    private javax.swing.JPanel pn_kanan;
    private javax.swing.JPanel pn_kiri;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JRadioButton rbRole1;
    private javax.swing.JRadioButton rbRole2;
    private javax.swing.ButtonGroup rolePelanggan;
    private javax.swing.JCheckBox semua_produk;
    private javax.swing.JTextArea tAlamatPelanggan;
    private javax.swing.JTextField tAlamatPesanan;
    private javax.swing.JTextField tCPasswordPelanggan;
    private javax.swing.JTextField tCariCustomer;
    private javax.swing.JTextField tCariPesanan;
    private javax.swing.JTextField tEmailPelanggan;
    private javax.swing.JTextField tIdPelanggan;
    private javax.swing.JTextField tIdPesanan;
    private javax.swing.JTextField tNamaPelanggan;
    private javax.swing.JTextField tNamaPesanan;
    private javax.swing.JTextField tNoHpPelanggan;
    private javax.swing.JTextField tNoHpPesanan;
    private javax.swing.JTextField tPasswordPelanggan;
    private javax.swing.JComboBox<String> t_catProduk;
    private javax.swing.JTextField t_descProduk;
    private javax.swing.JTextField t_hargaProduk;
    private javax.swing.JTextField t_idProduk;
    private javax.swing.JTextField t_namaProduk;
    private javax.swing.JTextField t_stokProduk;
    private javax.swing.JLabel t_userName;
    private javax.swing.JTable table_pp;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTable tbl_produk;
    private javax.swing.JTable tbl_promo;
    private javax.swing.JTable tblkategori;
    private javax.swing.JTextField tcari_promo;
    private javax.swing.JTextField tid_produk2;
    private javax.swing.JTextField tid_promo;
    private javax.swing.JTextField tid_promo2;
    private javax.swing.JTextField tidkategori;
    private javax.swing.JTextField tidpp;
    private javax.swing.JTextField tkode_promo;
    private javax.swing.JTextField tnama_produk2;
    private javax.swing.JTextField tnama_promo;
    private javax.swing.JTextField tnama_promo2;
    private javax.swing.JTextField tnamakategori;
    private javax.swing.JTextField tpot_harga;
    private com.toedter.calendar.JDateChooser ttb;
    private com.toedter.calendar.JDateChooser ttd;
    // End of variables declaration//GEN-END:variables
}
