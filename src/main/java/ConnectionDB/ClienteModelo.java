/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.Cliente;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class ClienteModelo {
    
    private static String ATRIBUTOS = Cliente.CODIGO_DB+","+Cliente.NOMBRE_DB+","+Cliente.BIRTH_DB+","+Cliente.DPI_DB +"," + Cliente.DIRECCION_DB+","+Cliente.GENERO_DB+","+Cliente.DPI_PDF_DB+","+ Cliente.PASSWORD_DB+",";
    private static String ADD_CLIENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + "( "+ ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,?,AES_ENCRYPT(?,?)";
    
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
    
}
