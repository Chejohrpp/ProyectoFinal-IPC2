/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author sergi
 */
public class Cajero {
    public static final String CAJERO_DB_NAME = "cajero";
    public static final String CODIGO_DB = "codigo";
    public static final String NOMBRE_DB = "nombre";
    public static final String TRUNO_DB = "turno";
    public static final String DPI_DB = "dpi";
    public static final String DIRECCION_DB= "direccion";
    public static final String GENERO_DB = "genero";
    public static final String PASSWORD_DB = "password";
    
    private int codigo;
    private String nombre;
    private String turno;
    private String dpi;
    private String direccion;
    private String genero;
    private String password;

    //constructor para el ingrese del archivo xml
    public Cajero(int codigo, String nombre, String turno, String dpi, String direccion, String genero, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.turno = turno;
        this.dpi = dpi;
        this.direccion = direccion;
        this.genero = genero;
        this.password = password;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
