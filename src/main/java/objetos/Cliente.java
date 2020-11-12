/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.InputStream;
import javax.websocket.Decoder.Binary;

/**
 *
 * @author sergi
 */
public class Cliente {
    public static final String CLIENTE_DB_NAME = "cliente";
    public static final String CODIGO_DB = "codigo";
    public static final String NOMBRE_DB = "nombre";
    public static final String BIRTH_DB = "birth";
    public static final String DPI_DB = "dpi";
    public static final String DIRECCION_DB= "direccion";
    public static final String GENERO_DB = "genero";
    public static final String DPI_PDF_DB = "dpi_pdf";
    public static final String PASSWORD_DB = "password";
    
    private int codigo;
    private String nombre;
    private String birth;
    private String dpi;
    private String direccion;
    private String genero;
    private InputStream dpi_pdf;
    private String password;
    
    //constructor para el ingrese del archivo xml

    public Cliente(int codigo, String nombre, String birth, String dpi, String direccion, String genero, InputStream dpi_pdf, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.birth = birth;
        this.dpi = dpi;
        this.direccion = direccion;
        this.genero = genero;
        this.dpi_pdf = dpi_pdf;
        this.password = password;
    }

    public Cliente(String nombre, String birth, String dpi, String direccion, String genero, InputStream dpi_pdf, String password) {
        this.nombre = nombre;
        this.birth = birth;
        this.dpi = dpi;
        this.direccion = direccion;
        this.genero = genero;
        this.dpi_pdf = dpi_pdf;
        this.password = password;
    }

    public InputStream getDpi_pdf() {
        return dpi_pdf;
    }

    public void setDpi_pdf(InputStream dpi_pdf) {
        this.dpi_pdf = dpi_pdf;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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
