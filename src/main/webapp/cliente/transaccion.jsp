<%-- 
    Document   : transaccion
    Created on : 13/11/2020, 05:54:23 PM
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
        <link rel="styleSheet" href="css/estilo.css">
    </head>   
    <body>            
 
        <h1>Transaccion</h1>           
       <div class="datos">

           <c:if test="${success == 0}">
               <label id="error" style="color:red;text-align: center">${mensajeError}</label>            
            </c:if>             
            <c:if test="${success == 1}">
               <label id="bien" style="color:green; text-align: center">Transaccion realizada con exito</label>            
            </c:if>  

          <form action="buscarCuenta" method="POST">
                <%--Datos de la cuenta --%>
                <h3>No. de cuenta: <%= session.getAttribute("noCuenta") %> </h3>
                <h3>Credito que posee: <%= session.getAttribute("credito") %> </h3>
                 <%--Monto--%>                   
                <label for="monto">Monto</label>
                 <input type="number" step="any" min="0" required="required" name="monto" placeholder="Monto">
                 <%--sus cuentas--%>
                 <label for="noCuentaEnviar">Cuenta a enviar la transaccion</label>
                 <select name="noCuentaEnviar">
                     <c:forEach items="${noCuentas}" var="noCuenta">
                         <option>${noCuenta}</option>
                    </c:forEach>                     
                 </select>
                 <%--boton--%>
                 <input type="submit" value="Realizar Transaccion">
            </form>
         <form action="cliente/inicio.jsp">
             <input type="submit" value="Regresar">
         </form>   
       </div>
      
    </body>
</html>
