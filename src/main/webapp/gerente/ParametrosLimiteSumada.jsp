<%-- 
    Document   : ParametrosLimiteSumada
    Created on : 15/11/2020, 12:05:26 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Billeton</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>   
               <h1>Limite Sumada Establecido</h1>
               <div class="datos">               
                   <form action="../transaccionesLimiteSumada" method="GET">
                     <%--limite--%> 
                     <label for="usuario">Limite Sumada</label>
                    <input type="number" name="limite" step="any" min="0" value="1000.0" placeholder="Limite Sumada" required="required"> 
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="reportesGerente.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>              
     </body>
</html>
