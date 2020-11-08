/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

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
    public static final String DPI_PDF_DB = "dpi-pdf";
    public static final String PASSWORD_DB = "password";
    
    private int codigo;
    private String nombre;
    private String birth;
    private String dpi;
    private String direccion;
    private String genero;
    private Binary dpi_pdf;
    private String password;
    
    
    
}
