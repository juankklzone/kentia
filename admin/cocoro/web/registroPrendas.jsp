<%-- 
    Document   : registroPrendas
    Created on : 20/05/2016, 08:50:14 PM
    Author     : Yeyo-chan
--%>
<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:if test="${sessionScope['sessionUsuario']==null}">
    <% response.sendRedirect("index.jsp");%>
</t:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de prendas - Administración de Datos</title>
    </head>
    <body>
        <h1>Registro de prendas</h1>
        <hr color="red"/>
        <br><br>
        <p style="color: #00ff00">${sessionScope['success']}</p>
        <form action="RegistroPrendas" method="post" enctype="multipart/form-data">
            Cargue el archivo .csv correspondiente:
            <br><br>
            <input type="file" name="file">
            <br><br>
            <input type="submit" value="Registrar">
        </form>
        <br><br>
        <hr color="red"/>
        <br>
        <p><a href="menu.jsp">Menu Principal</a></p>
        <p><a href="consultaPrendas.jsp">Consultar prendas</a></p>
        <p><a href="eliminarPrendas.jsp">Eliminar prendas</a></p>
        <br>
        <form action="CerrarSesion" method="get">
            <p><input type="submit" value="Cerrar Sesion"></p>
        </form>
        
    </body>
</html>
