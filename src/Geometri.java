/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Ari Satriya
 */
interface Geometri {
    public double menghitungKelilingSegi4();
    public double menghitungKelilingSegi4(double a, double b, double c, double d);

    public double menghitungLuasSegi4() throws InvalidDiagonalException;
    public double menghitungLuasSegi4(double a, double b, double c, double d, double diag) throws InvalidDiagonalException;

    public double menghitungLuasPermukaan();
    public double menghitungLuasPermukaan(double parameterCustom);

    public double menghitungVolume();
    public double menghitungVolume(double tinggi);
}
