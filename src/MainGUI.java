import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainGUI extends JFrame {
    private JTextField txtA, txtB, txtC, txtD, txtDiag, txtTinggi;
    private JTextArea txtRumus, txtLog;
    private JLabel lblTinggi, lblStatus;
    private JButton btnEksekusi;
    private JProgressBar progressBar;
    
    private int menuTerpilih = 1; 

    public MainGUI() {
        setTitle("Sistem Komputasi Geometri - Tugas PBO");
        setSize(900, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // --- 1. JMENUBAR ---
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGeometri = new JMenu("Pilih Modul Geometri");
        JMenuItem itemSegi4 = new JMenuItem("1. Kalkulator Segi4 Sembarang (2D)");
        JMenuItem itemLimas = new JMenuItem("2. Kalkulator Limas Segi4 (3D)");
        JMenuItem itemPrisma = new JMenuItem("3. Kalkulator Prisma Segi4 (3D)");
        JMenuItem itemThread = new JMenuItem("4. Demo Simulasi Multithreading (Massal)");
        
        itemSegi4.addActionListener(e -> gantiMenu(1));
        itemLimas.addActionListener(e -> gantiMenu(2));
        itemPrisma.addActionListener(e -> gantiMenu(3));
        itemThread.addActionListener(e -> gantiMenu(4));
        
        menuGeometri.add(itemSegi4);
        menuGeometri.add(itemLimas);
        menuGeometri.add(itemPrisma);
        menuGeometri.addSeparator();
        menuGeometri.add(itemThread);
        menuBar.add(menuGeometri);
        setJMenuBar(menuBar);

        // --- 2. HEADER PANEL ---
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(41, 128, 185));
        panelHeader.setBorder(new EmptyBorder(15, 20, 15, 20));
        JLabel lblTitle = new JLabel("Dashboard Analisis Geometri & Multithreading");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        panelHeader.add(lblTitle, BorderLayout.WEST);
        add(panelHeader, BorderLayout.NORTH);

        // --- 3. CENTRAL WORKSPACE ---
        JPanel panelTengah = new JPanel(new GridLayout(1, 2, 15, 0));
        panelTengah.setBorder(new EmptyBorder(0, 15, 0, 15));

        // Panel Form Input
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Parameter Dimensi / Sisi "),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.weightx = 1.0;

        txtA = new JTextField("5.0"); txtB = new JTextField("6.0");
        txtC = new JTextField("7.0"); txtD = new JTextField("8.0");
        txtDiag = new JTextField("9.0"); txtTinggi = new JTextField("12.0");

        buatBarisForm(panelInput, "Panjang Sisi A (cm)", txtA, gbc, 0);
        buatBarisForm(panelInput, "Panjang Sisi B (cm)", txtB, gbc, 1);
        buatBarisForm(panelInput, "Panjang Sisi C (cm)", txtC, gbc, 2);
        buatBarisForm(panelInput, "Panjang Sisi D (cm)", txtD, gbc, 3);
        buatBarisForm(panelInput, "Garis Diagonal (cm)", txtDiag, gbc, 4);
        
        lblTinggi = new JLabel("Tinggi Ruang 3D (cm)");
        gbc.gridx = 0; gbc.gridy = 5; panelInput.add(lblTinggi, gbc);
        gbc.gridx = 1; panelInput.add(txtTinggi, gbc);

        btnEksekusi = new JButton("Hitung Sekarang");
        btnEksekusi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEksekusi.setBackground(new Color(46, 204, 113));
        btnEksekusi.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 8, 8, 8);
        panelInput.add(btnEksekusi, gbc);

        // Panel Dokumen Informasi Rumus (KEMBALI DIADAKAN & LENGKAP)
        txtRumus = new JTextArea();
        txtRumus.setEditable(false);
        txtRumus.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtRumus.setBackground(Color.WHITE);
        txtRumus.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollRumus = new JScrollPane(txtRumus);
        scrollRumus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Formulasi Matematika "));

        panelTengah.add(panelInput);
        panelTengah.add(scrollRumus);
        add(panelTengah, BorderLayout.CENTER);

        // --- 4. LOWER TERMINAL LOG ---
        JPanel panelBawah = new JPanel(new BorderLayout(5, 5));
        panelBawah.setBorder(new EmptyBorder(0, 15, 10, 15));

        txtLog = new JTextArea(12, 50); // Dipertinggi sedikit agar aliran data terlihat puas
        txtLog.setEditable(false);
        txtLog.setBackground(new Color(23, 32, 42));
        txtLog.setForeground(new Color(46, 204, 113));
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(txtLog);
        
        JPanel panelStatus = new JPanel(new BorderLayout());
        lblStatus = new JLabel("Modul Siap.");
        progressBar = new JProgressBar();
        
        panelStatus.add(lblStatus, BorderLayout.WEST);
        panelStatus.add(progressBar, BorderLayout.EAST);

        panelBawah.add(scrollLog, BorderLayout.CENTER);
        panelBawah.add(panelStatus, BorderLayout.SOUTH);
        add(panelBawah, BorderLayout.SOUTH);

        btnEksekusi.addActionListener(e -> kelolaAksiTombol());
        gantiMenu(1);
    }

    private void buatBarisForm(JPanel p, String teksLabel, JTextField tf, GridBagConstraints gbc, int baris) {
        gbc.gridwidth = 1; gbc.gridx = 0; gbc.gridy = baris; p.add(new JLabel(teksLabel), gbc);
        gbc.gridx = 1; p.add(tf, gbc);
    }

    private void gantiMenu(int idMenu) {
        this.menuTerpilih = idMenu;
        txtLog.setText("");
        setFormFieldsEnabled(true);
        
        if (idMenu == 1) {
            lblTinggi.setVisible(false); txtTinggi.setVisible(false);
            btnEksekusi.setText("Hitung Objek Segi4 (2D)");
            lblStatus.setText("Menu Aktif: Segi4 Sembarang (2D)");
            
            // Pengembalian Rumus Gambar 2
            txtRumus.setText("=== FORMULA SEGI4 SEMBARANG ===\n\n"
                    + "-> Keliling Alas:\n"
                    + "   K = SisiA + SisiB + SisiC + SisiD\n\n"
                    + "-> Luas Alas (Teorema Ketaksamaan Heron):\n"
                    + "   s1 = (A + B + Diagonal) / 2\n"
                    + "   Luas_Sg1 = √(s1 * (s1-A) * (s1-B) * (s1-Diag))\n"
                    + "   s2 = (C + D + Diagonal) / 2\n"
                    + "   Luas_Sg2 = √(s2 * (s2-C) * (s2-D) * (s2-Diag))\n"
                    + "   Total Luas = Luas_Sg1 + Luas_Sg2");
        } else if (idMenu == 2) {
            lblTinggi.setVisible(true); txtTinggi.setVisible(true);
            btnEksekusi.setText("Hitung Objek Limas (3D)");
            lblStatus.setText("Menu Aktif: Limas Segi4 Sembarang (3D)");
            
            txtRumus.setText("=== FORMULA LIMAS SEGI4 SEMBARANG ===\n\n"
                    + "-> Luas Alas:\n"
                    + "   Dihitung dengan pembagian Segitiga Heron via Diagonal\n\n"
                    + "-> Volume Limas:\n"
                    + "   V = 1/3 * Luas Alas * Tinggi\n\n"
                    + "-> Luas Permukaan:\n"
                    + "   LP = Luas Alas + Luas Selubung Miring");
        } else if (idMenu == 3) {
            lblTinggi.setVisible(true); txtTinggi.setVisible(true);
            btnEksekusi.setText("Hitung Objek Prisma (3D)");
            lblStatus.setText("Menu Aktif: Prisma Segi4 Sembarang (3D)");
            
            txtRumus.setText("=== FORMULA PRISMA SEGI4 SEMBARANG ===\n\n"
                    + "-> Luas Alas:\n"
                    + "   Dihitung dengan pembagian Segitiga Heron\n\n"
                    + "-> Volume Prisma:\n"
                    + "   V = Luas Alas * Tinggi\n\n"
                    + "-> Luas Permukaan:\n"
                    + "   LP = (2 * Luas Alas) + (Keliling Alas * Tinggi)");
        } else if (idMenu == 4) {
            lblTinggi.setVisible(false); txtTinggi.setVisible(false);
            setFormFieldsEnabled(false);
            btnEksekusi.setText("Jalankan Simulasi Aliran Multithreading");
            lblStatus.setText("Menu Aktif: Demo Aliran Data Massal");
            
            txtRumus.setText("=== DEMO SIMULASI MULTITHREADING ===\n\n"
                    + "Untuk membuktikan kedua jalur komputer bekerja bersamaan,\n"
                    + "sistem akan menjalankan 2 Thread sekaligus:\n"
                    + "1. Worker-Alpha (Memproses indeks 0 s/d 100)\n"
                    + "2. Worker-Beta  (Memproses indeks 101 s/d 200)\n\n"
                    + "Perhatikan terminal bawah! Output akan keluar satu per satu\n"
                    + "secara berebutan/bergantian membuktikan paralelisme asli.");
        }
        revalidate(); repaint();
    }

    private void setFormFieldsEnabled(boolean status) {
        txtA.setEnabled(status); txtB.setEnabled(status);
        txtC.setEnabled(status); txtD.setEnabled(status);
        txtDiag.setEnabled(status); txtTinggi.setEnabled(status);
    }

    private void kelolaAksiTombol() {
        if (menuTerpilih == 4) {
            jalankanDemoMultithreadingSatuPerSatu();
            return;
        }

        // Jalankan Logika Hitung Biasa (Tetap presisi seperti sebelumnya)
        try {
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());
            double d = Double.parseDouble(txtD.getText());
            double diag = Double.parseDouble(txtDiag.getText());

            if (menuTerpilih == 1) {
                Segi4Sembarang s4 = new Segi4Sembarang().setSisiA(a).setSisiB(b).setSisiC(c).setSisiD(d).setDiagonal(diag);
                txtLog.setText("=== HASIL KALKULASI SINGLE OBJECT (2D) ===\n");
                txtLog.append(String.format("Keliling Bangun : %.2f cm\n", s4.menghitungKelilingSegi4()));
                txtLog.append(String.format("Luas Bangun     : %.2f cm²\n", s4.menghitungLuasSegi4()));
            } else if (menuTerpilih == 2) {
                double t = Double.parseDouble(txtTinggi.getText());
                LimasSegi4Sembarang limas = (LimasSegi4Sembarang) new LimasSegi4Sembarang().setTinggi(t).setSisiA(a).setSisiB(b).setSisiC(c).setSisiD(d).setDiagonal(diag);
                txtLog.setText("=== HASIL KALKULASI SINGLE OBJECT LIMAS (3D) ===\n");
                txtLog.append(String.format("Luas Alas       : %.2f cm²\n", limas.menghitungLuasSegi4()));
                txtLog.append(String.format("Luas Permukaan  : %.2f cm²\n", limas.menghitungLuasPermukaan()));
                txtLog.append(String.format("Volume Limas    : %.2f cm³\n", limas.menghitungVolume()));
            } else if (menuTerpilih == 3) {
                double t = Double.parseDouble(txtTinggi.getText());
                PrismaSegi4Sembarang prisma = (PrismaSegi4Sembarang) new PrismaSegi4Sembarang().setTinggi(t).setSisiA(a).setSisiB(b).setSisiC(c).setSisiD(d).setDiagonal(diag);
                txtLog.setText("=== HASIL KALKULASI SINGLE OBJECT PRISMA (3D) ===\n");
                txtLog.append(String.format("Luas Alas       : %.2f cm²\n", prisma.menghitungLuasSegi4()));
                txtLog.append(String.format("Luas Permukaan  : %.2f cm²\n", prisma.menghitungLuasPermukaan()));
                txtLog.append(String.format("Volume Prisma   : %.2f cm³\n", prisma.menghitungVolume()));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input harus berupa angka desimal!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidDiagonalException ide) {
            txtLog.setText("[ERROR GEOMETRI] " + ide.getMessage());
        }
    }

private void jalankanDemoMultithreadingSatuPerSatu() {
    txtLog.setText("[Sistem] Menginisialisasi Thread Pekerja untuk 10.000 data...\n");
    progressBar.setIndeterminate(true);
    btnEksekusi.setEnabled(false);

    // Kita buat 10.000 data dengan dimensi yang bertambah besar setiap indeksnya
    List<LimasSegi4Sembarang> listBebanMassa = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
        // Sisi dasar misal 5.0, lalu setiap indeks kita tambahkan i agar nilainya membesar
        double pertambahan = i; 
        double sisiA = 5.0 + pertambahan;
        double sisiB = 6.0 + pertambahan;
        double sisiC = 7.0 + pertambahan;
        double sisiD = 8.0 + pertambahan;
        double diagonal = 9.0 + pertambahan; // Tetap penuhi syarat ketaksamaan segitiga
        double tinggi = 12.0 + (pertambahan * 0.5);

        LimasSegi4Sembarang limas = new LimasSegi4Sembarang();
        limas.setSisiA(sisiA);
        limas.setSisiB(sisiB);
        limas.setSisiC(sisiC);
        limas.setSisiD(sisiD);
        limas.setDiagonal(diagonal);
        limas.setTinggi(tinggi);

       listBebanMassa.add(limas);
    }

    // 2. Jalankan pengawas thread
    new Thread(() -> {
        long waktuMulai = System.currentTimeMillis();

        // Alpha mengerjakan indeks 0 s/d 5.000
        // Beta mengerjakan indeks 5.000 s/d 10.000
        Thread t1 = new Thread(new ProsesGeometriThread(listBebanMassa, 0, 5000, "Worker-Alpha", txtLog));
        Thread t2 = new Thread(new ProsesGeometriThread(listBebanMassa, 5000, 10000, "Worker-Beta", txtLog));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        long waktuSelesai = System.currentTimeMillis();

        SwingUtilities.invokeLater(() -> {
            txtLog.append(String.format("\n[SUKSES] Seluruh 10.000 Data Selesai Diproses dalam %d ms.\n", (waktuSelesai - waktuMulai)));
            progressBar.setIndeterminate(false);
            btnEksekusi.setEnabled(true);
            lblStatus.setText("Simulasi 10.000 Data Selesai.");
        });
    }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}