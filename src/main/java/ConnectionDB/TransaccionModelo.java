/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import objetos.Gerente;
import objetos.Transaccion;

/**
 *
 * @author sergi
 */
public class TransaccionModelo {
    private static String ATRIBUTOS = Transaccion.CODIGO_DB +"," +Transaccion.MONTO_DB+","+Transaccion.TIPO_DB+","+Transaccion.HORA_DB+","+Transaccion.FECHA_DB+","+Transaccion.CAJERO_CODIGO_DB+","+Transaccion.CUENTA_CODIGO_DB;
    
    private static String ATRIBUTOS_CON_NUEVOSALDO = Transaccion.CODIGO_DB +"," +Transaccion.MONTO_DB+","+Transaccion.TIPO_DB+","+Transaccion.HORA_DB+","+Transaccion.FECHA_DB+","+Transaccion.NUEVO_SALDO_DB+","+Transaccion.CAJERO_CODIGO_DB+","+Transaccion.CUENTA_CODIGO_DB;
    private static String ATRIBUTOS_CON_NUEVOSALDO_SIN_CODIGO = Transaccion.MONTO_DB+","+Transaccion.TIPO_DB+","+Transaccion.HORA_DB+","+Transaccion.FECHA_DB+","+Transaccion.NUEVO_SALDO_DB+","+Transaccion.CAJERO_CODIGO_DB+","+Transaccion.CUENTA_CODIGO_DB;
    private static String ADD_TRANSACCION = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,?)";
    private static String ADD_TRANSACCION_CON_NUEVOSALDO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME +"( " + ATRIBUTOS_CON_NUEVOSALDO +" ) VALUES(?,?,?,?,?,?,?,?)";
    private static String ADD_TRANSACCION_CON_NUEVOSALDO_SIN_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME +"( " + ATRIBUTOS_CON_NUEVOSALDO_SIN_CODIGO +" ) VALUES(?,?,?,?,?,?,?)";
    private static String TRANSACCIONES = "SELECT " + ATRIBUTOS_CON_NUEVOSALDO  +" FROM  "+Transaccion.TRANSACCION_DB_NAME;
    
    private Connection connection = ConnectionDB.getInstance();
    
    public TransaccionModelo(){
        
    }
    public void addTransaccion(Transaccion transaccion) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_TRANSACCION);

        preSt.setInt(1, transaccion.getCodigo());
        preSt.setDouble(2, transaccion.getMonto());
        preSt.setString(3, transaccion.getTipo());
        preSt.setString(4, transaccion.getHora());
        preSt.setString(5, transaccion.getFecha());
        preSt.setInt(6, transaccion.getCajero_codigo());
        preSt.setInt(7, transaccion.getCuenta_codigo());              
        preSt.executeUpdate(); 
    }
    
    public void addTransaccionConNuevoSaldo(Transaccion transaccion) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_TRANSACCION_CON_NUEVOSALDO);

        preSt.setInt(1, transaccion.getCodigo());
        preSt.setDouble(2, transaccion.getMonto());
        preSt.setString(3, transaccion.getTipo());
        preSt.setString(4, transaccion.getHora());
        preSt.setString(5, transaccion.getFecha());
        preSt.setDouble(6, transaccion.getNuevoSaldo());
        preSt.setInt(7, transaccion.getCajero_codigo());
        preSt.setInt(8, transaccion.getCuenta_codigo());              
        preSt.executeUpdate(); 
    }
    public void addTransaccionConNuevoSaldoSinCodigo(Transaccion transaccion) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_TRANSACCION_CON_NUEVOSALDO_SIN_CODIGO);
        preSt.setDouble(1, transaccion.getMonto());
        preSt.setString(2, transaccion.getTipo());
        preSt.setString(3, transaccion.getHora());
        preSt.setString(4, transaccion.getFecha());
        preSt.setDouble(5, transaccion.getNuevoSaldo());
        preSt.setInt(6, transaccion.getCajero_codigo());
        preSt.setInt(7, transaccion.getCuenta_codigo());              
        preSt.executeUpdate(); 
    }
    public List<Transaccion> todoasTransacciones() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(TRANSACCIONES); 
        ResultSet result = preSt.executeQuery();
        List<Transaccion> transacciones = new LinkedList<>();
        while(result.next()){
            transacciones.add( new Transaccion(
                    result.getInt(Transaccion.CODIGO_DB),
                    result.getDouble(Transaccion.MONTO_DB),
                    result.getString(Transaccion.TIPO_DB),
                    result.getString(Transaccion.HORA_DB),
                    result.getString(Transaccion.FECHA_DB),
                    result.getDouble(Transaccion.NUEVO_SALDO_DB),
                    result.getInt(Transaccion.CAJERO_CODIGO_DB),
                    result.getInt(Transaccion.CUENTA_CODIGO_DB)           
            ));
        }
        return transacciones;
    }
    
    
    public void crearTransaccionCredito(double monto, int cajeroCodigo, double nuevoSaldo, int cuentaCodigo) throws SQLException{
        
        Transaccion transaccion = new Transaccion(monto,"CREDITO",getHoraActual(),getFechaActual(),nuevoSaldo,cajeroCodigo,cuentaCodigo); 
        addTransaccionConNuevoSaldoSinCodigo(transaccion);
        
    } 
    public void crearTransaccionDebito(double monto, int cajeroCodigo, double nuevoSaldo, int cuentaCodigo) throws SQLException{
        
        Transaccion transaccion = new Transaccion(monto,"DEBITO",getHoraActual(),getFechaActual(),nuevoSaldo,cajeroCodigo,cuentaCodigo); 
        addTransaccionConNuevoSaldoSinCodigo(transaccion);
        
    } 
    
    private String getHoraActual(){
        Calendar now = Calendar.getInstance(); 

        int hour = now.get(Calendar.HOUR_OF_DAY); 
        int minute = now.get(Calendar.MINUTE); 
        int second = now.get(Calendar.SECOND);        
        return hour + ":" + minute +":" + second;
    }
    private String getFechaActual(){
        Calendar now = Calendar.getInstance(); 
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month +"-" + day;
    }
    
    
    
    
}
