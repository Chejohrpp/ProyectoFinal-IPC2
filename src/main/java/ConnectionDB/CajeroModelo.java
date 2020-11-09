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
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CajeroModelo {
    
    private static String ATRIBUTOS= Cajero.CODIGO_DB +","+Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB+","+Cajero.PASSWORD_DB;
    private static String ADD_CAJERO = "INSERT INTO " + Cajero.CAJERO_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    
    private Connection connection = ConnectionDB.getInstance();
    
    public CajeroModelo(){
        
    }
    public void addCajero(Cajero cajero) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CAJERO);

        preSt.setInt(1, cajero.getCodigo());
        preSt.setString(2, cajero.getNombre());
        preSt.setString(3, cajero.getTurno());
        preSt.setString(4, cajero.getDpi());
        preSt.setString(5, cajero.getDireccion());
        preSt.setString(6, cajero.getGenero());
        preSt.setString(7, cajero.getPassword());              
        preSt.setString(8, Gerente.LLAVE);
        preSt.executeUpdate(); 
    }
}
