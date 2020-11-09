/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.CuentasAsociadas;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CuentasAsociadasModelo {
    private static String ATRIBUTOS_SIN_CODIGO = CuentasAsociadas.ESTADO_DB + "," + CuentasAsociadas.ESTADO_ENVIAR_DB + "," + CuentasAsociadas.NO_INTENTOS_DB + "," +CuentasAsociadas.CUENTA_CODIGO_DB +"," + CuentasAsociadas.CLIENTE_CODIGO_DB;
    private static String ADD_CUENTAS_ASOCIADAS_SIN_CODIGO = "INSSERT INTO " + CuentasAsociadas.CUENTAS_ASOCIADAS_DB_NAME + " ( " +ATRIBUTOS_SIN_CODIGO +" ) VALUES (?,?,?,?,?)";
    
    private Connection connection = ConnectionDB.getInstance();
    
    
    public CuentasAsociadasModelo(){
        
    }
    
    public void addCuentasAsociadasSinCodigo(CuentasAsociadas cuentasAsociadas) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CUENTAS_ASOCIADAS_SIN_CODIGO);

        preSt.setBoolean(1, cuentasAsociadas.getEstado());
        preSt.setBoolean(2, cuentasAsociadas.getEstado_enviar());
        preSt.setInt(3, cuentasAsociadas.getNo_intentos());
        preSt.setInt(4, cuentasAsociadas.getCuenta_codigo());
        preSt.setInt(5, cuentasAsociadas.getCliente_codigo());

        preSt.executeUpdate(); 
    }
    
}
