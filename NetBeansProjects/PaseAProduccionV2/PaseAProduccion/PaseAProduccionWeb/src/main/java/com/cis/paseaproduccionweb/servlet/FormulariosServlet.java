package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FormulariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String padreId = request.getParameter("padreId");
            String tipoPadre = request.getParameter("tipoPadre");
            String sistemaId = request.getParameter("sistemaId");
            String filtro = request.getParameter("filtro");
            String filtroEstado = request.getParameter("filtroEstado");
            request.setAttribute("padreId", padreId);
            request.setAttribute("tipoPadre", tipoPadre);
            request.setAttribute("sistemaId", sistemaId);
            if(filtro != null)
                request.setAttribute("filtro", filtro.trim());
            if(filtroEstado != null)
                request.setAttribute("filtroEstado", filtroEstado);
            RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/mostrarFormularios.jsp");
            rDispatcher.forward(request, response);
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
