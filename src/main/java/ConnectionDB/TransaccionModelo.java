/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.Gerente;
import objetos.Transaccion;

/**
 *
 * @author sergi
 */
public class TransaccionModelo {
    private static String ATRIBUTOS = Transaccion.CODIGO_DB +"," +Transaccion.MONTO_DB+","+Transaccion.TIPO_DB+","+Transaccion.HORA_DB+","+Transaccion.FECHA_DB+","+Transaccion.CAJERO_CODIGO_DB+","+Transaccion.CUENTA_CODIGO_DB;
    private static String ATRIBUTOS_CON_NUEVOSALDO = Transaccion.CODIGO_DB +"," +Transaccion.MONTO_DB+","+Transaccion.TIPO_DB+","+Transaccion.HORA_DB+","+Transaccion.FECHA_DB+","+Transaccion.NUEVO_SALDO_DB+","+Transaccion.CAJERO_CODIGO_DB+","+Transaccion.CUENTA_CODIGO_DB;
    private static String ADD_TRANSACCION = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,?)";
    private static String ADD_TRANSACCION_CON_NUEVOSALDO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME +"( " + ATRIBUTOS_CON_NUEVOSALDO +" ) VALUES(?,?,?,?,?,?,?,?)";
    
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
        PreparedStatement preSt = connection.prepareStatement(ADD_TRANSACCION);

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
    
    
}
