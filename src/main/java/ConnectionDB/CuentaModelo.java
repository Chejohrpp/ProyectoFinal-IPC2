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
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import objetos.Cajero;
import objetos.Cuenta;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
 public class CuentaModelo {
    private static String ATRIBUTOS = Cuenta.CODIGO_DB +"," +Cuenta.CREDITO_DB +","+ Cuenta.CREACION_DB + ","+  Cuenta.CLIENTE_CODIGO_DB;
    private static String ATRIBUTOS_SIN_CODIGO = Cuenta.CREDITO_DB +","+ Cuenta.CREACION_DB + ","+  Cuenta.CLIENTE_CODIGO_DB;
    private static String ADD_CUENTA = "INSERT INTO " + Cuenta.CUENTA_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?)";
    private static String ADD_CUENTA_SIN_CODIGO = "INSERT INTO " + Cuenta.CUENTA_DB_NAME +"( " + ATRIBUTOS_SIN_CODIGO +" ) VALUES(?,?,?)";
    private static String CUENTAS = "SELECT " + ATRIBUTOS + " FROM " + Cuenta.CUENTA_DB_NAME;
    private static String OBTENER_CUENTA = CUENTAS + " WHERE " + Cuenta.CODIGO_DB + " = ? LIMIT 1";
    private static String OBTENER_CUENTAS_CLIENTE = CUENTAS + " WHERE " + Cuenta.CLIENTE_CODIGO_DB + " =?";
    private static String OBTENER_CUENTAS_CLIENTE_SIN_CUENTA_ACTUAL = CUENTAS + " WHERE " + Cuenta.CLIENTE_CODIGO_DB + " =? AND " + Cuenta.CODIGO_DB + " != ?";
    private static String CAMBIAR_MONTO = "UPDATE " + Cuenta.CUENTA_DB_NAME + " SET " + Cuenta.CREDITO_DB + " = ? WHERE " +Cuenta.CODIGO_DB +" = ?"; 
    
    private Connection connection = ConnectionDB.getInstance();
    
    public void addCuenta(Cuenta cuenta) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CUENTA);

        preSt.setInt(1, cuenta.getCodigo());
        preSt.setDouble(2, cuenta.getCredito());
        preSt.setString(3, cuenta.getCreacion());
        preSt.setInt(4, cuenta.getCliente_codigo());
        preSt.executeUpdate(); 
    }
    public long addCuentaSinCodigo(Cuenta cuenta) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CUENTA_SIN_CODIGO,Statement.RETURN_GENERATED_KEYS);

        preSt.setDouble(1, cuenta.getCredito());
        preSt.setString(2, cuenta.getCreacion());
        preSt.setInt(3, cuenta.getCliente_codigo());
        preSt.executeUpdate();
        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;         
    }
    
    public List<Cuenta> cuentasCliente(int codigo) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTAS_CLIENTE);
        preSt.setInt(1, codigo);
        List<Cuenta> cuentas = new LinkedList<>();
        ResultSet result = preSt.executeQuery();
        while(result.next()){
            cuentas.add(new Cuenta(result.getInt(Cuenta.CODIGO_DB), 
                    result.getDouble(Cuenta.CREDITO_DB),
                    result.getString(Cuenta.CREACION_DB),
                    result.getInt(Cuenta.CLIENTE_CODIGO_DB)));
        }
        return cuentas;        
    }
    public List<Cuenta> cuentasClienteSinCuentaActual(int codigo,int noCuenta) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTAS_CLIENTE_SIN_CUENTA_ACTUAL);
        preSt.setInt(1, codigo);
        preSt.setInt(2, noCuenta);
        List<Cuenta> cuentas = new LinkedList<>();
        ResultSet result = preSt.executeQuery();
        while(result.next()){
            cuentas.add(new Cuenta(result.getInt(Cuenta.CODIGO_DB), 
                    result.getDouble(Cuenta.CREDITO_DB),
                    result.getString(Cuenta.CREACION_DB),
                    result.getInt(Cuenta.CLIENTE_CODIGO_DB)));
        }
        return cuentas;        
    }
    public Cuenta cuentaCliente(int codigo) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTA);
        preSt.setInt(1, codigo);
        Cuenta cuenta = null;
        ResultSet result = preSt.executeQuery();
        while(result.next()){
            cuenta = new Cuenta(result.getInt(Cuenta.CODIGO_DB), 
                    result.getDouble(Cuenta.CREDITO_DB),
                    result.getString(Cuenta.CREACION_DB),
                    result.getInt(Cuenta.CLIENTE_CODIGO_DB));
        }
        return cuenta;        
    }
    
     public void cambiarMonto(Cuenta cuenta) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(CAMBIAR_MONTO);         
        preSt.setDouble(1, cuenta.getCredito());
        preSt.setInt(2, cuenta.getCodigo());        
        preSt.executeUpdate(); 
    }
    
}
