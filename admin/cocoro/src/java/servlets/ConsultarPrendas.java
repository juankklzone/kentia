/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yeyo-chan
 */
public class ConsultarPrendas extends HttpServlet {

    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarPrendas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession respuesta = request.getSession(true);
        response.setContentType("text/html");
        
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        //response.getWriter().append("").append(request.getContextPath());
        PrintWriter out = response.getWriter(); 
        DAO obj=new DAO();
        String consultas[][];
        
        consultas=obj.hasConsulta();
        response.getWriter().print("<html>");
        response.getWriter().print("<head><title>Consulta de Datos - Administración de Datos</title></head>");         
        response.getWriter().print("<body>");
        response.getWriter().print("<h1>Prendas Almacenadas</h1>");
        response.getWriter().print("<hr color='red'>");
        response.getWriter().print("<br><br>");
        response.getWriter().print("<table border='1' style='width:100%'>");
        response.getWriter().print("<tr bgcolor='#99ccff'><th>ID</th><th>COLOR_ID</th><th>CLIMA_ID</th><th>TIPO_PRENDA_ID</th><th>OCASION_ID</th><th>BRILLO</th><th>USUARIO_ID</th><th>FOTO</th></tr>");
        for(int i=0;i<consultas.length;i++){
            response.getWriter().print("<tr>");
            for(int j=0;j<consultas[0].length;j++){
                response.getWriter().print("<td align='center'>"+consultas[i][j]+"</td>");
            }
            response.getWriter().print("</tr>");
        }
        response.getWriter().print("</table>");
        response.getWriter().print("<br>");
        response.getWriter().print("<hr color='red'>");
        response.getWriter().print("<br>");
        response.getWriter().print("<p><a href='registroPrendas.jsp'>Agregar prendas</a></p>");
        response.getWriter().print("<p><a href='menu.jsp'>Menu Principal</a></p>");
        response.getWriter().print("<p><a href='eliminarPrendas.jsp'>Eliminar prendas</a></p>");
        response.getWriter().print("<br>");
        response.getWriter().print("<form action='CerrarSesion' method='get'><p><input type='submit' value='Cerrar Sesion'></p></form>");
        response.getWriter().print("</body>");
        response.getWriter().print("</html>");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doGet(request, response);
    }
    
}
