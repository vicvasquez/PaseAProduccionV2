package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.dao.UsuariosDao;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActualizarPerfilServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        try {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password1");          

            BigDecimal usuarioId = new BigDecimal(request.getParameter("usuarioId"));

            UsuariosDao dUsuario = new UsuariosDao();

            PpUsuarios usuario =  dUsuario.getUsuarioById(usuarioId);

            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setClave(password);
            Date date = new Date();
            usuario.setDateModified(date);

            dUsuario.updateUsuario(usuario);
            request.setAttribute("user", usuario);
            request.getSession().setAttribute("user", usuario);
            response.sendRedirect("perfil.jsp");
            
        } catch (Exception e) {
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
            
            response.sendRedirect("mensajeError.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
