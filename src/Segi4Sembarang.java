/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ari Satriya
 */
public class Segi4Sembarang implements Geometri{
    private String nama;
    private double SisiA, SisiB, SisiC, SisiD;
    private double diagonal;

    // Method Chaining / Fluent Setter
    public Segi4Sembarang setNama(String nama) { this.nama = nama; return this; }
    public Segi4Sembarang setSisiA(double a) { this.SisiA = a; return this; }
    public Segi4Sembarang setSisiB(double b) { this.SisiB = b; return this; }
    public Segi4Sembarang setSisiC(double c) { this.SisiC = c; return this; }
    public Segi4Sembarang setSisiD(double d) { this.SisiD = d; return this; }
    public Segi4Sembarang setDiagonal(double diag) { this.diagonal = diag; return this; }

    public double getSisiA() { 
        return this.SisiA; 
    }
    
    public String getNama() { return nama; }

    @Override
    public double menghitungKelilingSegi4() {
        return this.SisiA + this.SisiB + this.SisiC + this.SisiD;
    }

    @Override
    public double menghitungKelilingSegi4(double a, double b, double c, double d) {
        return a + b + c + d;
    }

    @Override
    public double menghitungLuasSegi4() throws InvalidDiagonalException {
        if (this.diagonal >= (this.SisiA + this.SisiB) || this.diagonal <= Math.abs(this.SisiA - this.SisiB)) {
            throw new InvalidDiagonalException("Diagonal tidak valid untuk Segitiga 1 (Sisi A & B)");
        }
        if (this.diagonal >= (this.SisiC + this.SisiD) || this.diagonal <= Math.abs(this.SisiC - this.SisiD)) {
            throw new InvalidDiagonalException("Diagonal tidak valid untuk Segitiga 2 (Sisi C & D)");
        }

        double s1 = (this.SisiA + this.SisiB + this.diagonal) / 2.0;
        double luas1 = Math.sqrt(s1 * (s1 - this.SisiA) * (s1 - this.SisiB) * (s1 - this.diagonal));

        double s2 = (this.SisiC + this.SisiD + this.diagonal) / 2.0;
        double luas2 = Math.sqrt(s2 * (s2 - this.SisiC) * (s2 - this.SisiD) * (s2 - this.diagonal));

        return luas1 + luas2;
    }

    @Override
    public double menghitungLuasSegi4(double a, double b, double c, double d, double diag) throws InvalidDiagonalException {
        double s1 = (a + b + diag) / 2.0;
        double s2 = (c + d + diag) / 2.0;
        return Math.sqrt(s1 * (s1 - a) * (s1 - b) * (s1 - diag)) + Math.sqrt(s2 * (s2 - c) * (s2 - d) * (s2 - diag));
    }

    @Override public double menghitungLuasPermukaan() { return 0; }
    @Override public double menghitungLuasPermukaan(double p) { return 0; }
    @Override public double menghitungVolume() { return 0; }
    @Override public double menghitungVolume(double t) { return 0; }
}
