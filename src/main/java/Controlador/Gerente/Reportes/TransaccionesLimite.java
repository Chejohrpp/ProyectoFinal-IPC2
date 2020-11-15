/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente.Reportes;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author sergi
 */
@WebServlet("/transaccionesLimite")
public class TransaccionesLimite extends HttpServlet {
    
    Connection connection = ConnectionDB.ConnectionDB.getInstance();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            String limite = request.getParameter("limite");
            
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ClientesTransaccionesLimite.pdf");//cambia
            
            File file = new File(request.getServletContext().getRealPath("/resources/ClientesTransaccionesLimite.jrxml"));//cambia
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("limite", Double.parseDouble(limite));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        }catch(Exception e){
            
        }
    }
}
