/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vvasquez
 */
public class HistorialServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String filtroNombreArchivo = request.getParameter("filtroNombreArchivo");
            String filtroNombreUsuario = request.getParameter("filtroNombreUsuario");
            String filtroFechaInicio = request.getParameter("filtroFechaInicio");
            String filtroFechaFin = request.getParameter("filtroFechaFin");
            request.setAttribute("filtroNombreArchivo", filtroNombreArchivo);
            request.setAttribute("filtroNombreUsuario", filtroNombreUsuario);
            request.setAttribute("filtroFechaInicio", filtroFechaInicio);
            request.setAttribute("filtroFechaFin", filtroFechaFin);
            
            RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/historial.jsp");
            rDispatcher.forward(request, response);
            
        } catch (Exception e) {           
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
