/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class ConnectionDB {
    private static Connection connection = null;
    private static ConnectionDB connectionDB;
    //hacemos conexion con la base de datos
    private ConnectionDB(){
        String url = "jdbc:mysql://localhost:3306/billeton?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "user";
        String password = "user";
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           connection = DriverManager.getConnection(url, user, password);     
            //System.out.println("se conceto");
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //utilizaremoo esto para extraer la conexion de la base de datos a cualquier otra clase de java o incluso en jsp
    public static Connection getInstance(){
        if (connectionDB == null) {
            connectionDB = new ConnectionDB();
        }
        return connection;        
    }
}
