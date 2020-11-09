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
public class GerenteModelo {
    private static String ATRIBUTOS= Gerente.CODIGO_DB +","+Gerente.NOMBRE_DB+","+Gerente.TRUNO_DB+","+Gerente.DPI_DB+","+Gerente.DIRECCION_DB+","+Gerente.GENERO_DB+","+Gerente.PASSWORD_DB;
    private static String ADD_CAJERO = "INSERT INTO " + Gerente.GERENTE_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    
    private Connection connection = ConnectionDB.getInstance();
    
    public GerenteModelo(){
        
    }
    public void addGerente(Gerente gerente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CAJERO);

        preSt.setInt(1, gerente.getCodigo());
        preSt.setString(2, gerente.getNombre());
        preSt.setString(3, gerente.getTurno());
        preSt.setString(4, gerente.getDpi());
        preSt.setString(5, gerente.getDireccion());
        preSt.setString(6, gerente.getGenero());
        preSt.setString(7, gerente.getPassword());              
        preSt.setString(8, Gerente.LLAVE);
        preSt.executeUpdate(); 
    }
}
