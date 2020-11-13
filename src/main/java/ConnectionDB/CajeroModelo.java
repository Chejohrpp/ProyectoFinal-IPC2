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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import objetos.Cajero;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
public class CajeroModelo {
    
    private static String ATRIBUTOS= Cajero.CODIGO_DB +","+Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB+","+Cajero.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_CODIGO= Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB+","+Cajero.PASSWORD_DB;
    private static String ATRIBUTOS_SIN_PASSWORD = Cajero.CODIGO_DB +","+Cajero.NOMBRE_DB+","+Cajero.TRUNO_DB+","+Cajero.DPI_DB+","+Cajero.DIRECCION_DB+","+Cajero.GENERO_DB;
    private static String ADD_CAJERO = "INSERT INTO " + Cajero.CAJERO_DB_NAME +"( " + ATRIBUTOS +" ) VALUES(?,?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String ADD_CAJERO_SIN_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME +"( " + ATRIBUTOS_SIN_CODIGO +" ) VALUES(?,?,?,?,?,AES_ENCRYPT(?,?))";
    private static String CAJEROS = "SELECT " + ATRIBUTOS_SIN_PASSWORD + ", cast(aes_decrypt("+Cajero.PASSWORD_DB +",?) as char) " + Cajero.PASSWORD_DB + " FROM " + Cajero.CAJERO_DB_NAME;
    private static String OBTENER_CAJERO = CAJEROS + "  WHERE " + Cajero.CODIGO_DB +" =? LIMIT 1";
    private static String MOD_CAJERO = "UPDATE " + Cajero.CAJERO_DB_NAME + " SET " + Cajero.NOMBRE_DB+"=?, "+Cajero.TRUNO_DB+"=?,"+Cajero.DPI_DB+"=?,"
            +Cajero.DIRECCION_DB+"=?,"+Cajero.GENERO_DB+"=?,"+Cajero.PASSWORD_DB +"=AES_ENCRYPT(?,?) WHERE "+Cajero.CODIGO_DB+"=?";
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
    
    public long addCajeroSinCodigo(Cajero cajero) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(ADD_CAJERO_SIN_CODIGO,Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDpi());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getGenero());
        preSt.setString(6, cajero.getPassword());              
        preSt.setString(7, Gerente.LLAVE);
        preSt.executeUpdate(); 
        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }
    
    public void ModCajero(Cajero cajero) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(MOD_CAJERO);      
        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDpi());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getGenero());
        preSt.setString(6, cajero.getPassword());              
        preSt.setString(7, Gerente.LLAVE);
        preSt.setInt(8, cajero.getCodigo());
        preSt.executeUpdate();        
    }
    
    public Cajero verificarLogin(int codigo, String pass) throws SQLException {
        Cajero cajero = obtenerCajero(codigo);
        if (cajero != null && cajero.getPassword().equals(pass)) {
            return cajero;
        }
        return null;        
    }

    public Cajero obtenerCajero(int codigo) throws SQLException {
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
    public List<Cajero> todosCajeros() throws SQLException {
         PreparedStatement preSt = connection.prepareStatement(CAJEROS);
        preSt.setString(1, Gerente.LLAVE);    
        ResultSet result = preSt.executeQuery();
        List<Cajero> cajeros = new LinkedList<>();
        while(result.next()){
            cajeros.add(new Cajero(
                    result.getInt(Cajero.CODIGO_DB),
                    result.getString(Cajero.NOMBRE_DB),
                    result.getString(Cajero.TRUNO_DB),
                    result.getString(Cajero.DPI_DB),
                    result.getString(Cajero.DIRECCION_DB),
                    result.getString(Cajero.GENERO_DB),
                    result.getString(Cajero.PASSWORD_DB)           
            ));
        }
        return cajeros;
    }
    
    //usamos para verificar si esta en el horario definido
    private static final String inputFormat = "HH:mm"; 
    private Date date; 
    private Date dateCompareOne; 
    private Date dateCompareTwo; 
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US); 
    // turno matutino (6:00 a 14:30 hrs)
    //vespertino (13:00 a 22:00 hrs).
    public boolean verificarRequisitos(int codigo) throws SQLException{
        Cajero cajero = obtenerCajero(codigo);
        String turno  = cajero.getTurno();        
        String compareStringOne = "13:00"; 
        String compareStringTwo = "22:00";        
        
        if (turno.equalsIgnoreCase("MATUTINO")) {
            compareStringOne = "6:00"; 
            compareStringTwo = "14:30";
            return compareDates(compareStringOne,compareStringTwo);
        } 
        
        return compareDates(compareStringOne,compareStringTwo);
    }
    //camparamos la hora
    private boolean compareDates(String compareStringOne, String compareStringTwo){ 
        Calendar now = Calendar.getInstance(); 

        int hour = now.get(Calendar.HOUR_OF_DAY); 
        int minute = now.get(Calendar.MINUTE); 

        date = parseDate(hour + ":" + minute); 
        //System.out.println(date.toString());
        dateCompareOne = parseDate(compareStringOne); 
        //System.out.println(dateCompareOne);
        dateCompareTwo = parseDate(compareStringTwo); 
        //System.out.println(dateCompareTwo);
        if (dateCompareOne.before(date) && dateCompareTwo.after(date)) { 
            return true;
        } 
            return false;
    } 
    //transformamos la hora en formato simpleDateFormat
    private Date parseDate(String date) { 
        try { 
            return inputParser.parse(date); 
        } catch (java.text.ParseException e) { 
            return new Date(0); 
        } 
    } 
}
