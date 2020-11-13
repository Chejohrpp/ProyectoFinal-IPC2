<%-- 
    Document   : reportesGerente
    Created on : 12/11/2020, 08:13:18 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>
        <div class="datos">
            <h2>Reportes</h2>
        </div>
        <div class="informacion"> 
            <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="" name=""> 
            </form>
             <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="" name=""> 
            </form>
            <form action="inicio.jsp">
                  <input type="submit" value="Regresar">
            </form> 
        </div>
    </body>
</html>
