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
public class CuentasAsociadas {
    public static final String CUENTAS_ASOCIADAS_DB_NAME = "cuentas_asociadas";
    public static final String CODIGO_DB = "codigo";
    public static final String ESTADO_DB = "estado";
    public static final String NO_INTENTOS_DB = "no_intentos";
    public static final String CUENTA_CODIGO_DB = "cuenta_codigo";
    public static final String CLIENTE_CODIGO_DB= "cliente_codigo";
    
    
    private int codigo;
    private String estado;
    private String no_intentos;
    private String cuenta_codigo;
    private String cliente_codigo;

    //constructor para el ingrese del archivo xml
    public CuentasAsociadas(int codigo, String estado, String no_intentos, String cuenta_codigo, String cliente_codigo) {
        this.codigo = codigo;
        this.estado = estado;
        this.no_intentos = no_intentos;
        this.cuenta_codigo = cuenta_codigo;
        this.cliente_codigo = cliente_codigo;
    }

    public CuentasAsociadas(String estado, String no_intentos, String cuenta_codigo, String cliente_codigo) {
        this.estado = estado;
        this.no_intentos = no_intentos;
        this.cuenta_codigo = cuenta_codigo;
        this.cliente_codigo = cliente_codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNo_intentos() {
        return no_intentos;
    }

    public void setNo_intentos(String no_intentos) {
        this.no_intentos = no_intentos;
    }

    public String getCuenta_codigo() {
        return cuenta_codigo;
    }

    public void setCuenta_codigo(String cuenta_codigo) {
        this.cuenta_codigo = cuenta_codigo;
    }

    public String getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCliente_codigo(String cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
    }
    
}
