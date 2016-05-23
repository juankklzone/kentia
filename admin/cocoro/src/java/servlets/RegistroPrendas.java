/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.DAO;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Yeyo-chan
 */
public class RegistroPrendas extends HttpServlet {
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        HttpSession respuesta = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        String uploadPath = "C:"+"/"+"Carga";
        
        File destino = new File(uploadPath);
        if (!destino.exists()) {
            destino.mkdir();
        }
        
        ServletRequestContext src = new ServletRequestContext(request);
        if(ServletFileUpload.isMultipartContent(src)){
            DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            try {
                java.util.List lista = upload.parseRequest(src);
                File file = null;
                java.util.Iterator it = lista.iterator();
                
                while(it.hasNext()){
                    FileItem item=(FileItem)it.next();
                    if(item.isFormField())
                        out.println(item.getFieldName()+"<br>");
                    else{
                        file=new File(item.getName());
                        item.write(new File(destino,file.getName()));
                        out.println(item.getName()+" Fichero subido");
                        
                        String ruta=uploadPath+"/"+item.getName();
                        
                        DAO d = new DAO();
                        try {
                            d.conectar();
                            d.registrarPrendas(ruta);
                            respuesta.setAttribute("success", "Las prendas se han registrado exitosamente");
                            response.sendRedirect("registroPrendas.jsp");
                            d.desconectar();
                        }
                        catch(Exception e){
                            out.println("Excepcion: " +e);
                        }
                    }
                }
            } catch (org.apache.commons.fileupload.FileUploadException ex) {
                Logger.getLogger(RegistroPrendas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(RegistroPrendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
