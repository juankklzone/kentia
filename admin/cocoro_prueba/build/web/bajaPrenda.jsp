<%--
    Document   : eliminarRegistro
    Author     : LarzRS
--%>

<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    Connection conexion = null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/cocoro";
    String user="root";
    String pwd="LarzRS";
%>

<%  
    PreparedStatement ps = null;
    ResultSet rs = null;
    //se inicia la conexion
    if(conexion == null){
        Class.forName(driver).newInstance();
        conexion = DriverManager.getConnection(url,user,pwd);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar prenda</title>
    </head>
    <body>
        <h1>Eliminar prenda</h1>
        <form action="bajaPrenda" method="post">
            ID: <input type="text" name="idPrenda"/>
            <input type="submit" value="Eliminar" />
        </form>
    </body>
</html>
