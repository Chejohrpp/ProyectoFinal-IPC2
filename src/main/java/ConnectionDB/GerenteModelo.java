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
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class GerenteModelo {
    private static String ATRIBUTOS= Gerente.CODIGO_DB +","+Gerente.NOMBRE_DB+","+Gerente.TRUNO_DB+","+Gerente.DPI_DB+","+Gerente.DIRECCION_DB+","+Gerente.GENERO_DB+","+Gerente.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_CODIGO= Gerente.NOMBRE_DB+","+Gerente.TRUNO_DB+","+Gerente.DPI_DB+","+Gerente.DIRECCION_DB+","+Gerente.GENERO_DB+","+Gerente.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_PASSWORD = Gerente.CODIGO_DB +","+Gerente.NOMBRE_DB+","+Gerente.TRUNO_DB+","+Gerente.DPI_DB+","+Gerente.DIRECCION_DB+","+Gerente.GENERO_DB;
    private static String ADD_GERENTE = "INSERT INTO " + Gerente.GERENTE_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String ADD_GERENTE_SIN_CODIGO = "INSERT INTO " + Gerente.GERENTE_DB_NAME +"( " + ATRIBUTOS_SIN_CODIGO +" ) VALUES(?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String CONT_GERENTE = "SELECT COUNT(*) cantidad FROM " + Gerente.GERENTE_DB_NAME + " LIMIT 1";
    private static String GERENTES = "SELECT " + ATRIBUTOS_SIN_PASSWORD + ", cast(aes_decrypt("+Gerente.PASSWORD_DB +",?) as char) " + Gerente.PASSWORD_DB + " FROM " + Gerente.GERENTE_DB_NAME;
    private static String OBTENER_GERENTE = GERENTES + "  WHERE " + Gerente.CODIGO_DB +" =? LIMIT 1";
    private static String MOD_GERENTE = "UPDATE " + Gerente.GERENTE_DB_NAME + " SET " + Gerente.NOMBRE_DB+"=?,"+Gerente.TRUNO_DB+"=?,"+
            Gerente.DPI_DB+"=?,"+Gerente.DIRECCION_DB+"=?,"+Gerente.GENERO_DB+"=?,"+Gerente.PASSWORD_DB+"=AES_ENCRYPT(?,?) WHERE "+Gerente.CODIGO_DB+"=?";
    
    private static Connection connection = ConnectionDB.getInstance();
    
    public GerenteModelo(){
        
    }
    public void addGerente(Gerente gerente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_GERENTE);

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
    public long addGerenteSinCodigo(Gerente gerente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_GERENTE_SIN_CODIGO,Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, gerente.getNombre());
        preSt.setString(2, gerente.getTurno());
        preSt.setString(3, gerente.getDpi());
        preSt.setString(4, gerente.getDireccion());
        preSt.setString(5, gerente.getGenero());
        preSt.setString(6, gerente.getPassword());              
        preSt.setString(7, Gerente.LLAVE);
        preSt.executeUpdate(); 
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }
    public void ModGerente(Gerente gerente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(MOD_GERENTE);
        
        preSt.setString(1, gerente.getNombre());
        preSt.setString(2, gerente.getTurno());
        preSt.setString(3, gerente.getDpi());
        preSt.setString(4, gerente.getDireccion());
        preSt.setString(5, gerente.getGenero());
        preSt.setString(6, gerente.getPassword());              
        preSt.setString(7, Gerente.LLAVE);
        preSt.setInt(8, gerente.getCodigo());
        preSt.executeUpdate(); 
    }
    
    public int cantGerentes() throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(CONT_GERENTE);        
        ResultSet result = preSt.executeQuery();        
        int cantGerentes = 0;
        if (result.next()) {
            cantGerentes = result.getInt(1);
        }        
        return cantGerentes;
    }
    
    public Gerente verificarLogin(int codigo, String pass) throws SQLException {
        Gerente gerente = obtenerGerente(codigo);
        if (gerente != null && gerente.getPassword().equals(pass)) {
            return gerente;
        }
        return null;        
    }

    private Gerente obtenerGerente(int codigo) throws SQLException {
         PreparedStatement preSt = connection.prepareStatement(OBTENER_GERENTE);
        preSt.setString(1, Gerente.LLAVE);
        preSt.setInt(2, codigo);        
        ResultSet result = preSt.executeQuery();
        Gerente gerente = null;
        while(result.next()){
            gerente = new Gerente(
                    result.getInt(Gerente.CODIGO_DB),
                    result.getString(Gerente.NOMBRE_DB),
                    result.getString(Gerente.TRUNO_DB),
                    result.getString(Gerente.DPI_DB),
                    result.getString(Gerente.DIRECCION_DB),
                    result.getString(Gerente.GENERO_DB),
                    result.getString(Gerente.PASSWORD_DB)           
            );
        }
        return gerente;
    }
    public List<Gerente> todosGerentes() throws SQLException {
         PreparedStatement preSt = connection.prepareStatement(GERENTES);
        preSt.setString(1, Gerente.LLAVE);        
        ResultSet result = preSt.executeQuery();
        List<Gerente> gerentes = new LinkedList<>();
        while(result.next()){
            gerentes.add( new Gerente(
                    result.getInt(Gerente.CODIGO_DB),
                    result.getString(Gerente.NOMBRE_DB),
                    result.getString(Gerente.TRUNO_DB),
                    result.getString(Gerente.DPI_DB),
                    result.getString(Gerente.DIRECCION_DB),
                    result.getString(Gerente.GENERO_DB),
                    result.getString(Gerente.PASSWORD_DB)           
            ));
        }
        return gerentes;
    }
}
