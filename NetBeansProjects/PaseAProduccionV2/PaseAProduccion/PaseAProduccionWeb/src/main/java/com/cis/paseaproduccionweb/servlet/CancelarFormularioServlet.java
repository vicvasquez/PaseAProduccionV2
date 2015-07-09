package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelarFormularioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String archId = request.getParameter("archivoId");
        String tipo = request.getParameter("archivoTipo");
        String tipoCancelacion = request.getParameter("tipoCancelacion");
        PpArchivosUso archivoUso = null;
        
        ArchivosUsoDao dArchivosUso = new ArchivosUsoDao();
        BigDecimal archivoId = new BigDecimal(archId);
        
        archivoUso = dArchivosUso.getArchivosUsoByArchivoIdYTipo(archivoId, tipo);
        
        if(archivoUso != null)
        {
            if(tipoCancelacion.equals("1")){
                dArchivosUso.eliminarArchivoUso(archivoUso);
                if(tipo.equals("MOD")){
                    ModulosDao dModulo = new ModulosDao();
                    PpModulos modulo = dModulo.getModuloByModuloId(archivoId);
                    modulo.setFlagUso("N");
                    modulo.setPpusuarioUsuarioId(null);
                    dModulo.updateModulo(modulo);
                }

                else if(tipo.equals("FOR") || tipo.equals("REP")){
                    FormulariosDao dFormulario = new FormulariosDao();
                    PpFormularios formulario = dFormulario.getFormularioByFormularioId(archivoId);
                    formulario.setFlagUso("N");
                    formulario.setPpusuarioUsuarioId(null);
                    dFormulario.updateFormularios(formulario);
                }
            }
                
            else if(tipoCancelacion.equals("2")){
                archivoUso.setFlagNoche("S");
                dArchivosUso.updateArchivoUso(archivoUso);
            }
                
        }
        
        response.sendRedirect("index.jsp");
        
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
