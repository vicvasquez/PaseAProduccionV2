/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.dao.SubMenusDao;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpSubmenus;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vvasquez
 */
public class MantenimientoSubmenusServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            request.setCharacterEncoding("UTF-8");
            String modId = request.getParameter("moduloId");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            nombre = nombre.trim();
            
            if(nombre == null || nombre.equals("")){
                request.setAttribute("moduloId", modId);
                RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/insertarSubmenu.jsp");
                rDispatcher.forward(request, response);
            }
            else{
                BigDecimal moduloId = new BigDecimal(modId);
                PpSubmenus submenu = new PpSubmenus();
                SubMenusDao dSubmenu = new SubMenusDao();
                
                submenu.setNombreSubmenu(nombre);
                submenu.setDescSubmenu(descripcion);
                submenu.setModuloModuloId(moduloId);
                submenu.setFlagEstado("A");
                
                dSubmenu.insertarSubMenu(submenu);
                
                request.setAttribute("moduloId", modId);
                RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/mantenimientoSubmenu.jsp");
                rDispatcher.forward(request, response);
            }
               
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
