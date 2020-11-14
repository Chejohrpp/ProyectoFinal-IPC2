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
import java.util.LinkedList;
import java.util.List;
import objetos.Cuenta;
import objetos.CuentasAsociadas;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CuentasAsociadasModelo {
    private static String ATRIBUTOS = CuentasAsociadas.CODIGO_DB +","+ CuentasAsociadas.ESTADO_DB + "," + CuentasAsociadas.ESTADO_ENVIAR_DB + "," + CuentasAsociadas.NO_INTENTOS_DB + "," +
            CuentasAsociadas.CUENTA_CODIGO_DB +"," + CuentasAsociadas.CLIENTE_CODIGO_DB + "," + CuentasAsociadas.KING_CUENTA_DB;
    
    private static String ATRIBUTOS_SIN_CODIGO = CuentasAsociadas.ESTADO_DB + "," + CuentasAsociadas.ESTADO_ENVIAR_DB + "," + CuentasAsociadas.NO_INTENTOS_DB + "," +
            CuentasAsociadas.CUENTA_CODIGO_DB +"," + CuentasAsociadas.CLIENTE_CODIGO_DB + "," + CuentasAsociadas.KING_CUENTA_DB;
    
    private static String ADD_CUENTAS_ASOCIADAS_SIN_CODIGO = "INSSERT INTO " + CuentasAsociadas.CUENTAS_ASOCIADAS_DB_NAME + " ( " +ATRIBUTOS_SIN_CODIGO +" ) VALUES (?,?,?,?,?,?)";
    
    private static String MOD_CUENTAS_ASOCIADAS = "UPDATE " + CuentasAsociadas.CUENTAS_ASOCIADAS_DB_NAME +" SET " + CuentasAsociadas.ESTADO_DB + "=?," + CuentasAsociadas.ESTADO_ENVIAR_DB + "=?," + 
            CuentasAsociadas.NO_INTENTOS_DB + "=? WHERE " + CuentasAsociadas.CLIENTE_CODIGO_DB +"=? AND " + CuentasAsociadas.CUENTA_CODIGO_DB + "=?";
    
    private static String CUENTAS_ASOCIADAS = " SELECT " + ATRIBUTOS + " FROM " + CuentasAsociadas.CUENTAS_ASOCIADAS_DB_NAME;
    private static String OBTENER_CUENTAS_CLIENTE = CUENTAS_ASOCIADAS  +" WHERE " + CuentasAsociadas.CLIENTE_CODIGO_DB + "=?  AND " + CuentasAsociadas.ESTADO_DB + "= 1";
    
    
    
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
        preSt.setInt(6, cuentasAsociadas.getKing_cuenta());
        preSt.executeUpdate(); 
    }
    public void modCuentasAsociadas(CuentasAsociadas cuentasAsociadas) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(MOD_CUENTAS_ASOCIADAS);
        preSt.setBoolean(1, cuentasAsociadas.getEstado());
        preSt.setBoolean(2, cuentasAsociadas.getEstado_enviar());
        preSt.setInt(3, cuentasAsociadas.getNo_intentos());
        preSt.setInt(4, cuentasAsociadas.getCuenta_codigo());
        preSt.setInt(5, cuentasAsociadas.getCliente_codigo());
        preSt.executeUpdate(); 
    }
    
    public List<CuentasAsociadas> cuentasAsociadasCliente(int codigo) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTAS_CLIENTE);
        preSt.setInt(1, codigo);
        List<CuentasAsociadas> cuentas = new LinkedList<>();
        ResultSet result = preSt.executeQuery();
        while(result.next()){
            cuentas.add(new CuentasAsociadas(result.getInt(CuentasAsociadas.CODIGO_DB), 
                    result.getBoolean(CuentasAsociadas.ESTADO_DB),
                    result.getBoolean(CuentasAsociadas.ESTADO_ENVIAR_DB),
                    result.getInt(CuentasAsociadas.NO_INTENTOS_DB),
                    result.getInt(CuentasAsociadas.CUENTA_CODIGO_DB),
                    result.getInt(CuentasAsociadas.CLIENTE_CODIGO_DB),
                    result.getInt(CuentasAsociadas.KING_CUENTA_DB)
            ));
        }
        return cuentas;        
    }
}
