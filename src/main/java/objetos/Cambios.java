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
public class Cambios {
     public static final String CAMBIOS_DB_NAME = "cambios";
     public static final String CODIGO_DB = "codigo";
     public static final String TIPO_DB = "tipo";
     public static final String CODIGO_TIPO_DB = "codigo_tipo";
     public static final String NOMBRE_DB = "nombre";
     public static final String FECHA_DB = "fecha";
     public static final String GERENTE_CODIGO_DB = "gerente_codigo";
     
     private int codigo;
     private String tipo;
     private int codigo_tipo;
     private String nombre;
     private String fecha;
     private int gerente_codigo;

    public Cambios(String tipo, int codigo_tipo, String nombre, String fecha, int gerente_codigo) {
        this.tipo = tipo;
        this.codigo_tipo = codigo_tipo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.gerente_codigo = gerente_codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo_tipo() {
        return codigo_tipo;
    }

    public void setCodigo_tipo(int codigo_tipo) {
        this.codigo_tipo = codigo_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getGerente_codigo() {
        return gerente_codigo;
    }

    public void setGerente_codigo(int gerente_codigo) {
        this.gerente_codigo = gerente_codigo;
    }
     
     
     
     
}
