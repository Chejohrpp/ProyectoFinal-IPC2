/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente.Reportes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author sergi
 */
@WebServlet("/transaccionesCuenta15")
public class TransaccionesCuenta15 extends HttpServlet {
    
    Connection connection = ConnectionDB.ConnectionDB.getInstance();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            String codigoCuenta = (String) request.getParameter("noCuenta");
            String fechaInicio = obtenerFechaInicial();
            String fechaFinal = obtenerFechaFinal();          
            
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=TransaccionesMasGrandes15Cuenta.pdf");//cambia
            
            File file = new File(request.getServletContext().getRealPath("/resources/15TransaccionesCuentaMax.jrxml"));//cambia
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());            
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("codigoCuenta", Integer.parseInt(codigoCuenta));
            parameters.put("fechaFinal", fechaFinal);
            parameters.put("fechaInicio", fechaInicio);
            //System.out.println(codigoCuenta +"-" + fechaInicio +"-"+ fechaFinal );
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        }catch(IOException | NumberFormatException | JRException e){
            System.out.println("error " + e.getMessage());
        }
    }
    
    private String obtenerFechaInicial(){
        Calendar now = Calendar.getInstance(); 
        int year = now.get(Calendar.YEAR);
        return year + "-01-01";
    }
    
    private String obtenerFechaFinal(){
        Calendar now = Calendar.getInstance(); 
        int year = now.get(Calendar.YEAR);
        return year + "-12-31";
    }
}
