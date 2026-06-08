/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ari Satriya
 */
public class LimasSegi4Sembarang extends Segi4Sembarang{
private double tinggi;
    private double luasSelubungMiring;

    public LimasSegi4Sembarang setTinggi(double tinggi) { this.tinggi = tinggi; return this; }
    public LimasSegi4Sembarang setLuasSelubungMiring(double luas) { this.luasSelubungMiring = luas; return this; }

    @Override
    public double menghitungLuasPermukaan() {
        try { return super.menghitungLuasSegi4() + this.luasSelubungMiring; 
        } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungLuasPermukaan(double luasCustom) {
        try { return super.menghitungLuasSegi4() + luasCustom; 
        } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungVolume() {
        try { return (1.0 / 3.0) * super.menghitungLuasSegi4() * this.tinggi; 
        } catch (Exception e) { return 0; }
    }

    @Override
    public double menghitungVolume(double t) {
        try { return (1.0 / 3.0) * super.menghitungLuasSegi4() * t; 
        } catch (Exception e) { return 0; }
    }
}
