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
public class Cuenta {
    public static final String CUENTA_DB_NAME = "cuenta";
    public static final String CODIGO_DB = "codigo";
    public static final String CREDITO_DB = "credito";
    public static final String CREACION_DB = "creacion";
    public static final String CLIENTE_CODIGO_DB = "cliente_codigo";
    
    private int codigo;
    private double credito;
    private String creacion;
    private int cliente_codigo;
    
    //constructor para el ingrese del archivo xml

    public Cuenta(int codigo, double credito, String creacion, int cliente_codigo) {
        this.codigo = codigo;
        this.credito = credito;
        this.creacion = creacion;
        this.cliente_codigo = cliente_codigo;
    }

    public Cuenta(double credito, String creacion, int cliente_codigo) {
        this.credito = credito;
        this.creacion = creacion;
        this.cliente_codigo = cliente_codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public int getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCliente_codigo(int cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
    }
    
    
}
