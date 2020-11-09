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
    public static final String ESTADO_ENVIAR_DB = "estado_enviar";
    public static final String NO_INTENTOS_DB = "no_intentos";
    public static final String CUENTA_CODIGO_DB = "cuenta_codigo";
    public static final String CLIENTE_CODIGO_DB= "cliente_codigo";
    
    
    private int codigo;
    private Boolean estado;
    private Boolean estado_enviar;
    private int no_intentos;
    private int cuenta_codigo;
    private int cliente_codigo;

    public CuentasAsociadas(Boolean estado, Boolean estado_enviar, int no_intentos, int cuenta_codigo, int cliente_codigo) {
        this.estado = estado;
        this.estado_enviar = estado_enviar;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getEstado_enviar() {
        return estado_enviar;
    }

    public void setEstado_enviar(Boolean estado_enviar) {
        this.estado_enviar = estado_enviar;
    }

    public int getNo_intentos() {
        return no_intentos;
    }

    public void setNo_intentos(int no_intentos) {
        this.no_intentos = no_intentos;
    }  

    public int getCuenta_codigo() {
        return cuenta_codigo;
    }

    public void setCuenta_codigo(int cuenta_codigo) {
        this.cuenta_codigo = cuenta_codigo;
    }

    public int getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCliente_codigo(int cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
    }


    
}
