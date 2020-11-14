<%-- 
    Document   : asociarCuenta
    Created on : 13/11/2020, 08:14:58 PM
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
        <link rel="styleSheet" href="css/estilo.css">
    </head>   
    <body>            
 
        <h1>Asociar una Cuenta</h1>           
       <div class="datos">

           <c:if test="${success == 0}">
               <label id="error" style="color:red;text-align: center">${mensajeError}</label>            
            </c:if>             
            <c:if test="${success == 1}">
               <label id="bien" style="color:green; text-align: center">Se envio la solicitud al cliente, si acepta aparecera su cuenta en transacciones</label>            
               <label id="bien" style="color:green; text-align: center">Puede ver el estado de esta cuenta en los reportes,cuentas asociadas realizadas</label>            
            </c:if>  

          <form action="asociarCuenta" method="POST">
                 <%--codigo del cliente--%>
                 <label for="codigoCliente">Codigo del cliente</label>
                 <input type="number" min="0" required="required" name="kingCuenta" placeholder="Codigo del cliente">
                 <%--dpi--%>
                 <label for="dpi">DPI del cliente</label>
                 <input type="number" min="0" required="required" name="dpi" placeholder="DPI del cliente">
                 <%--noCuenta--%>
                <label for="noCuenta">No. de Cuenta</label>
                 <input type="number" min="0" required="required" name="noCuenta" placeholder="No. de Cuenta">                 
                 <%--No intentos--%>
                 <label for="noIntentos">No. de intentos con esta cuenta: ${noIntentos}</label>
                 <br>
                 <%--boton--%>
                 <input type="submit" value="Asociar Cuenta">
            </form>
         <form action="cliente/inicio.jsp">
             <input type="submit" value="Regresar">
         </form>   
       </div>
      
    </body>
</html>