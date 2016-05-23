<%-- 
    Document   : index
    Created on : 18/05/2016, 12:09:12 AM
    Author     : Yeyo-chan
--%>
<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:if test="${sessionScope['sessionUsuario']!=null}">
    <% response.sendRedirect("menu.jsp");%>
</t:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesión - Administración de Datos</title>
    </head>
    <body>
        <h1>Inicia Sesión</h1>
        <hr color="red"/>
        <br><br>
        <p style="color: #ff0000">${sessionScope['error']}</p>
        <form action="IniciarSesion" method="post">
            <p> Usuario: <input type="text" name="usuario"></p>
            <p> Contraseña: <input type="password" name="pass"></p>
            <p><input type="submit" value="Iniciar Sesion"></p>
        </form>
        <br>
    </body>
</html>