/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.DAO;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yeyo-chan
 */
public class IniciarSesion extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession respuesta = request.getSession(true);
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        DAO d = new DAO();
        
        if(usuario.isEmpty() || pass.isEmpty()) {
            respuesta.setAttribute("error", "No se ha ingresado el usuario o la contraseña");
            response.sendRedirect("index.jsp");
        }
        else{
            try{
                d.conectar();
                if (d.siExisteCuenta(usuario, pass)) {
                    //OBTENGO EL USUARIO Y LO GUARDO EN UNA SESION
                    respuesta.setAttribute("sessionUsuario", usuario);
                    response.sendRedirect("menu.jsp");
                }
                else{
                    respuesta.setAttribute("error", "El usuario o contraseña no existen");
                    response.sendRedirect("index.jsp");
                }
                d.desconectar();
            }
            catch (Exception e){
                out.println("Excepcion: " +e);
            }
        }
    }

}
