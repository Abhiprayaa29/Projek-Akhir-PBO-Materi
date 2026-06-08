/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ari Satriya
 */
public class PrismaSegi4Sembarang extends Segi4Sembarang{
    private double tinggi;

    public PrismaSegi4Sembarang setTinggi(double tinggi) { this.tinggi = tinggi; return this; }

    @Override
    public double menghitungLuasPermukaan() {
        try { return (2 * super.menghitungLuasSegi4()) + (super.menghitungKelilingSegi4() * this.tinggi); } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungLuasPermukaan(double kelilingCustom) {
        try { return (2 * super.menghitungLuasSegi4()) + (kelilingCustom * this.tinggi); } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungVolume() {
        try { return super.menghitungLuasSegi4() * this.tinggi; } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungVolume(double t) {
        try { return super.menghitungLuasSegi4() * t; } catch (Exception e) { return 0; }
    }
}
