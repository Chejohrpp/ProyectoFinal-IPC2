/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.Cajero;
import objetos.Cuenta;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CuentaModelo {
    private static String ATRIBUTOS = Cuenta.CODIGO_DB +"," +Cuenta.CREDITO_DB +","+ Cuenta.CREACION_DB + ","+  Cuenta.CLIENTE_CODIGO_DB;
    private static String ADD_CUENTA = "INSERT INTO " + Cuenta.CUENTA_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?)";
    
    private Connection connection = ConnectionDB.getInstance();
    
    public void addCuenta(Cuenta cuenta) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CUENTA);

        preSt.setInt(1, cuenta.getCodigo());
        preSt.setDouble(2, cuenta.getCredito());
        preSt.setString(3, cuenta.getCreacion());
        preSt.setInt(4, cuenta.getCliente_codigo());
        preSt.executeUpdate(); 
    }
}
