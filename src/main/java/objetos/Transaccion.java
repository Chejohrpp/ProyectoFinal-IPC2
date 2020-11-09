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
public class Transaccion {
    public static final String TRANSACCION_DB_NAME = "transaccion";
    public static final String CODIGO_DB = "codigo";
    public static final String MONTO_DB = "monto";
    public static final String TIPO_DB = "tipo";
    public static final String HORA_DB = "hora";
    public static final String FECHA_DB= "fecha";
    public static final String CAJERO_CODIGO_DB = "cajero_codigo";
    public static final String CUENTA_CODIGO_DB = "cuenta_codigo";
    
    private int codigo;
    private double monto;
    private String tipo;
    private String hora;
    private String fecha;
    private int cajero_codigo;
    private int cuenta_codigo;

    //constructor para el ingrese del archivo xml

    public Transaccion(int codigo, double monto, String tipo, String hora, String fecha, int cajero_codigo, int cuenta_codigo) {
        this.codigo = codigo;
        this.monto = monto;
        this.tipo = tipo;
        this.hora = hora;
        this.fecha = fecha;
        this.cajero_codigo = cajero_codigo;
        this.cuenta_codigo = cuenta_codigo;
    }

    public Transaccion(double monto, String tipo, String hora, String fecha, int cajero_codigo, int cuenta_codigo) {
        this.monto = monto;
        this.tipo = tipo;
        this.hora = hora;
        this.fecha = fecha;
        this.cajero_codigo = cajero_codigo;
        this.cuenta_codigo = cuenta_codigo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCajero_codigo() {
        return cajero_codigo;
    }

    public void setCajero_codigo(int cajero_codigo) {
        this.cajero_codigo = cajero_codigo;
    }

    public int getCuenta_codigo() {
        return cuenta_codigo;
    }

    public void setCuenta_codigo(int cuenta_codigo) {
        this.cuenta_codigo = cuenta_codigo;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
