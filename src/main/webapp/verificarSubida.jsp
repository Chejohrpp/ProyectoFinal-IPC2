<%-- 
    Document   : verificarSubida
    Created on : 15/11/2020, 12:31:40 PM
    Author     : sergi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="styleSheet" href="css/estilo.css">
    </head>
    <body>
        <c:if test="${success == 1}">
        <h1>Errores:</h1>
             <div class="informacion">
            <table>
            <tr>
                <th>Error</th>
            </tr>
                <c:forEach items="${errores}" var="error">
                    <tr>
                        <td>${error}</td>
                    </tr>                              
                </c:forEach>
            </table>             
        </div>
        <h1>Deseea subir otro archivo XML?</h1>        
        <div class="datos">
            <h2>Busque el archivo XML</h2>
            <form method="POST" enctype="multipart/form-data" action="leerXML">
                <label for="dataFile">Seleccione archivo:</label>
                <input type="file" name="dataFile" accept=".xml"/>
                <br>
                <br>
                <button type="submit" >aceptar</button>
            </form>  
        </div> 
        </c:if>
             <c:if test="${success == 0}">
                 <h1>No hay errores</h1>
             </c:if>
       <div class="datos">
            <h2>Ir al Login</h2>
            <form action="login.jsp">
                <button type="submit" >Login</button>
            </form>  
        </div>       
    </body>
</html>