/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente.Reportes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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
@WebServlet("/cuentaMasDineroTransaccionesIntervalo")
public class CuentaMasDineroTransaccionesIntervalo extends HttpServlet{
    Connection connection = ConnectionDB.ConnectionDB.getInstance();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            String codigo = (String) request.getSession().getAttribute("id");
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFinal = request.getParameter("fechaFinal");          
            
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=CuentaDineroIntervaloTiempo.pdf");//cambia
            
            File file = new File(request.getServletContext().getRealPath("/resources/CuentaDineroIntervaloTiempo.jrxml"));//cambia
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());            
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("codigoCliente", Integer.parseInt(codigo));
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
}
