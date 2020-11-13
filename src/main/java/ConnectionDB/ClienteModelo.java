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
import objetos.Cliente;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class ClienteModelo {
    
    private static String ATRIBUTOS = Cliente.CODIGO_DB+","+Cliente.NOMBRE_DB+","+Cliente.BIRTH_DB+","+Cliente.DPI_DB +"," + Cliente.DIRECCION_DB+","+Cliente.GENERO_DB+","+Cliente.DPI_PDF_DB+","+ Cliente.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_PASSWORD =  Cliente.CODIGO_DB+","+Cliente.NOMBRE_DB+","+Cliente.BIRTH_DB+","+Cliente.DPI_DB +"," + Cliente.DIRECCION_DB+","+Cliente.GENERO_DB+","+Cliente.DPI_PDF_DB;
    private static String ADD_CLIENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + "( "+ ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String CLIENTES = "SELECT " + ATRIBUTOS_SIN_PASSWORD + ", cast(aes_decrypt("+Cliente.PASSWORD_DB +",?) as char) " + Cliente.PASSWORD_DB + " FROM " + Cliente.CLIENTE_DB_NAME;
    private static String OBTENER_CLIENTE = CLIENTES + " WHERE " + Cliente.CODIGO_DB + " = ? LIMIT 1";
    
    private Connection connection = ConnectionDB.getInstance();
    
    public ClienteModelo(){
        
    }
    public void addCliente(Cliente cliente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CLIENTE);

        preSt.setInt(1, cliente.getCodigo());
        preSt.setString(2, cliente.getNombre());
        preSt.setString(3, cliente.getBirth());
        preSt.setString(4, cliente.getDpi());
        preSt.setString(5, cliente.getDireccion());
        preSt.setString(6, cliente.getGenero());
        preSt.setBinaryStream(7, cliente.getDpi_pdf());
        preSt.setString(8, cliente.getPassword());              
        preSt.setString(9, Gerente.LLAVE);
        preSt.executeUpdate(); 
    }
    
    public Cliente verificarLogin(int codigo, String pass) throws SQLException {
        Cliente cliente = obtenerCliente(codigo);
        if (cliente != null && cliente.getPassword().equals(pass)) {
            return cliente;
        }
        return null;        
    }

    private Cliente obtenerCliente(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CLIENTE);
        preSt.setString(1, Gerente.LLAVE);
        preSt.setInt(2, codigo);        
        ResultSet result = preSt.executeQuery();
        Cliente cliente = null;
        while(result.next()){
            cliente = new Cliente(
                    result.getInt(Cliente.CODIGO_DB),
                    result.getString(Cliente.NOMBRE_DB),
                    result.getString(Cliente.BIRTH_DB),
                    result.getString(Cliente.DPI_DB),
                    result.getString(Cliente.DIRECCION_DB),
                    result.getString(Cliente.GENERO_DB),
                    result.getBinaryStream(Cliente.DPI_PDF_DB),
                    result.getString(Cliente.PASSWORD_DB)           
            );
        }
        return cliente;
    }
    public Cliente obtenerClienteGlobal(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(OBTENER_CLIENTE);
        preSt.setString(1, Gerente.LLAVE);
        preSt.setInt(2, codigo);        
        ResultSet result = preSt.executeQuery();
        Cliente cliente = null;
        while(result.next()){
            cliente = new Cliente(
                    result.getInt(Cliente.CODIGO_DB),
                    result.getString(Cliente.NOMBRE_DB),
                    result.getString(Cliente.BIRTH_DB),
                    result.getString(Cliente.DPI_DB),
                    result.getString(Cliente.DIRECCION_DB),
                    result.getString(Cliente.GENERO_DB),
                    result.getBinaryStream(Cliente.DPI_PDF_DB),
                    result.getString(Cliente.PASSWORD_DB)           
            );
        }
        return cliente;
    }
    
}
