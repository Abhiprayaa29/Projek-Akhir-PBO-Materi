/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
/**
 *
 * @author Ari Satriya
 */
public class ProsesGeometriThread implements Runnable{
private List<LimasSegi4Sembarang> listData;
    private int indeksMulai;
    private int indeksSelesai;
    private String namaThread;
    private JTextArea areaLog;

    public ProsesGeometriThread(List<LimasSegi4Sembarang> listData, int indeksMulai, int indeksSelesai, String namaThread, JTextArea areaLog) {
        this.listData = listData;
        this.indeksMulai = indeksMulai;
        this.indeksSelesai = indeksSelesai;
        this.namaThread = namaThread;
        this.areaLog = areaLog;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> areaLog.append("[" + namaThread + "] Memulai komputasi data...\n"));

        for (int i = indeksMulai; i < indeksSelesai; i++) {
            LimasSegi4Sembarang geometri = listData.get(i);

            try {
                double luasAlas = geometri.menghitungLuasSegi4();
                double volume = geometri.menghitungVolume();

                // Mengambil salah satu sampel sisi (misal Sisi A) untuk membuktikan angkanya bertambah
                double sisiA_SaatIni = geometri.getSisiA();

                // Format output menampilkan indeks, ukuran sisi yang membesar, dan hasil hitung yang membesar
                String barisOutput = String.format("[%s] Indeks %d (Sisi A: %.1f cm) -> Luas Alas: %.1f cm², Vol: %.1f cm³\n", 
                        namaThread, i, sisiA_SaatIni, luasAlas, volume);

                SwingUtilities.invokeLater(() -> {
                    areaLog.append(barisOutput);
                    areaLog.setCaretPosition(areaLog.getDocument().getLength());
                });

                // Jeda sangat tipis agar aliran data mulus meluncur ke bawah
                Thread.sleep(1); 

            } catch (Exception e) {
                // Berjaga-jaga jika ada ketaksamaan geometri
            }
        }

        SwingUtilities.invokeLater(() -> areaLog.append("=== [" + namaThread + "] SELESAI ===\n"));
    }
}
