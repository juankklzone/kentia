/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yeyo-chan
 */
public class EliminarPrendas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("bajaPrenda.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        HttpSession respuesta = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        String idPrenda = request.getParameter("idPrenda");
        
        DAO d = new DAO();
        
        if(idPrenda.isEmpty()){
            respuesta.setAttribute("error", "ID vacio");
            response.sendRedirect("eliminarPrendas.jsp");
        }
        else{
            try {
                d.conectar();
                d.borrarPrendas(idPrenda);
                respuesta.setAttribute("success", "Prenda eliminada");
                response.sendRedirect("eliminarPrendas.jsp");
                d.desconectar();
            }
            catch(Exception e){
                out.println("Excepcion: " +e);
            }
        }
    }

}
