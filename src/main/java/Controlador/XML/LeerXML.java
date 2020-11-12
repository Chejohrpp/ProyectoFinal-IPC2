/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.XML;

import ConnectionDB.SubirXML;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author sergi
 */
@WebServlet("/leerXML")
@MultipartConfig(location="C:/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class LeerXML extends HttpServlet{
    
    SubirXML subirXML = new SubirXML();
    public static final String BASE_PATH = "C:/tmp";
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("dataFile");
        String fileName = getFileName(filePart);
        String path = BASE_PATH + "/" + fileName;
        System.out.println(path);
        filePart.write(path);
        subirXML.subirDB(BASE_PATH, fileName);
        
    }

    //sirve para conocer el nombre del archivo que se ingreso
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
    }
}
