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

    public Gerente obtenerGerente(int codigo) throws SQLException {
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
    
    //usamos para verificar si esta en el horario definido
    private static final String inputFormat = "HH:mm"; 
    private Date date; 
    private Date dateCompareOne; 
    private Date dateCompareTwo; 
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US); 
    // turno matutino (6:00 a 14:30 hrs)
    //vespertino (13:00 a 22:00 hrs).
    public boolean verificarRequisitos(int codigo) throws SQLException{
        Gerente gerente = obtenerGerente(codigo);
        String turno  = gerente.getTurno();        
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
