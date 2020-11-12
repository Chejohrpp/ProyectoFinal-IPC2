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
import objetos.Cajero;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CajeroModelo {
    
    private static String ATRIBUTOS= Cajero.CODIGO_DB +","+Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB+","+Cajero.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_PASSWORD = Cajero.CODIGO_DB +","+Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB;
    private static String ADD_CAJERO = "INSERT INTO " + Cajero.CAJERO_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String CAJEROS = "SELECT " + ATRIBUTOS_SIN_PASSWORD + ", cast(aes_decrypt("+Cajero.PASSWORD_DB +",?) as char) " + Cajero.PASSWORD_DB + " FROM " + Cajero.CAJERO_DB_NAME;
    private static String OBTENER_CAJERO = CAJEROS + "  WHERE " + Cajero.CODIGO_DB +" =? LIMIT 1";
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
    
    public Cajero verificarLogin(int codigo, String pass) throws SQLException {
        Cajero cajero = obtenerCajero(codigo);
        if (cajero != null && cajero.getPassword().equals(pass)) {
            return cajero;
        }
        return null;        
    }

    private Cajero obtenerCajero(int codigo) throws SQLException {
         PreparedStatement preSt = connection.prepareStatement(OBTENER_CAJERO);
        preSt.setString(1, Gerente.LLAVE);
        preSt.setInt(2, codigo);        
        ResultSet result = preSt.executeQuery();
        Cajero cajero = null;
        while(result.next()){
            cajero = new Cajero(
                    result.getInt(Cajero.CODIGO_DB),
                    result.getString(Cajero.NOMBRE_DB),
                    result.getString(Cajero.TRUNO_DB),
                    result.getString(Cajero.DPI_DB),
                    result.getString(Cajero.DIRECCION_DB),
                    result.getString(Cajero.GENERO_DB),
                    result.getString(Cajero.PASSWORD_DB)           
            );
        }
        return cajero;
    }
}
