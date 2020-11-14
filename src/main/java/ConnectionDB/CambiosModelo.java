/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import objetos.Cambios;

/**
 *
 * @author sergi
 */
public class CambiosModelo {
    private static String ATRIBUTOS = Cambios.CODIGO_TIPO_DB +","+Cambios.TIPO_DB +"," + Cambios.NOMBRE_DB +","+ Cambios.FECHA_DB+","+ Cambios.GERENTE_CODIGO_DB;
    private static String ADD_CAMBIOS = "INSERT INTO " + Cambios.CAMBIOS_DB_NAME + "( " +ATRIBUTOS +" ) VALUES(?,?,?,?,?)";
    
    private Connection connection = ConnectionDB.getInstance();
    
    public void addCambios( Cambios cambios) throws SQLException{
        cambios.setFecha(getFechaActual());
        PreparedStatement preSt = connection.prepareStatement(ADD_CAMBIOS);
        preSt.setInt(1, cambios.getCodigo_tipo());
        preSt.setString(2, cambios.getTipo());
        preSt.setString(3, cambios.getNombre());
        preSt.setString(4, cambios.getFecha());
        preSt.setInt(5, cambios.getGerente_codigo());            
        preSt.executeUpdate(); 
    }   
    
    
    private String getFechaActual(){
        Calendar now = Calendar.getInstance(); 
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month +"-" + day;
    }
}
