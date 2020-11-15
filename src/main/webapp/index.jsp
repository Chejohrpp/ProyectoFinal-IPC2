<%@page import="ConnectionDB.GerenteModelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="styleSheet" href="css/estilo.css">
    </head>
    <body>
        
        <% GerenteModelo gerenteModelo = new GerenteModelo(); 
        if (gerenteModelo.cantGerentes() == 0 ) {%>
         
        <h1>La base de datos esta vacia</h1>        
        <div class="datos">
            <h2>Busque el archivo XML</h2>
            <form method="POST" enctype="multipart/form-data" action="leerXML">
                <label for="dataFile">Seleccione archivo:</label>
                <input type="file" name="dataFile" accept=".xml" required="required" />
                <br>
                <br>
                <button type="submit" >aceptar</button>
            </form>  
        </div>        
         <%  }else{
            response.sendRedirect("login");      
         }%>         
    </body>
</html>
