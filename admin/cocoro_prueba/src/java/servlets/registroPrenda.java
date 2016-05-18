/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.*;
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
public class registroPrenda extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        HttpSession respuesta = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        String idPrenda = request.getParameter("idPrenda");
        String idColor = request.getParameter("idColor");
        String idClima = request.getParameter("idClima");
        String idOcasion = request.getParameter("idOcasion");
        String idTipo_prenda = request.getParameter("idTipo_prenda");
        String brillo = request.getParameter("brillo");
        String foto = request.getParameter("foto");
        
        DAO d = new DAO();
        
        if(idPrenda.isEmpty() || idColor.isEmpty() || idClima.isEmpty() || idOcasion.isEmpty() || idTipo_prenda.isEmpty() || brillo.isEmpty() || foto.isEmpty()){
            respuesta.setAttribute("error", "Hay por lo menos un campo vacio");
            response.sendRedirect("index.jsp");
        }
        else{
            try {
                d.conectar();
                d.registrarPrenda(idPrenda, idColor, idClima, idOcasion, idTipo_prenda, brillo, foto);
                respuesta.setAttribute("error", null);
                response.sendRedirect("index.jsp");
                d.desconectar();
            }
            catch(Exception e){
                out.println("Excepcion: " +e);
            }
        }
    }

}
