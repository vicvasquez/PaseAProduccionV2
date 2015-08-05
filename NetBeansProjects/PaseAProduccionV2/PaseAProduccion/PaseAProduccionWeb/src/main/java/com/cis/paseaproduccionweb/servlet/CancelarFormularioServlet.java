package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoAprobDao;
import com.cis.paseaproduccionweb.dao.ArchivoAprobDaoTDM;
import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.dao.SubMenusDao;
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

        try {
 
            String archId = request.getParameter("archivoId");
            String tipo = request.getParameter("archivoTipo");
            String tipoCancelacion = request.getParameter("tipoCancelacion");
            PpArchivosUso archivoUso = null;

            ArchivosUsoDao dArchivosUso = new ArchivosUsoDao();
            BigDecimal archivoId = new BigDecimal(archId);

            archivoUso = dArchivosUso.getArchivosUsoByArchivoIdYTipo(archivoId, tipo);

            if(archivoUso != null)
            {
                ArchivoAprobDao dArchivoAprobado = new ArchivoAprobDao();
                ArchivoAprobDaoTDM dArchivoAprobadoTDM = new ArchivoAprobDaoTDM();
                SubMenusDao dSubmenu = new SubMenusDao();
                ModulosDao dModulo = new ModulosDao();
                FormulariosDao dFormulario = new FormulariosDao();
                String sistemaId = null;
                
                if(tipo.equals("MOD")){
                    PpModulos modulo = dModulo.getModuloByModuloId(archivoId);
                    sistemaId = modulo.getPpsistemaSistemaId().toString();
                    switch(tipoCancelacion){
                        case "1":
                            dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "S");
                            modulo.setFlagUso("N");
                            modulo.setPpusuarioUsuarioId(null);
                            dModulo.updateModulo(modulo);
                            break;
                            
                        case "2":
                            archivoUso.setFlagNoche("N");
                            dArchivosUso.updateArchivoUso(archivoUso);
                            break;
                    }
                }
                else if(tipo.equals("FOR") || tipo.equals("REP")){
                    PpFormularios formulario = dFormulario.getFormularioByFormularioId(archivoId);
                    sistemaId = dModulo.getModuloByModuloId(dSubmenu.getSubMenuBySubMenuId(formulario.getPpsubmenuSubmenuId()).getModuloModuloId()).getPpsistemaSistemaId().toString();
                    switch(tipoCancelacion){
                        case "1":
                            dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "S");
                            formulario.setFlagUso("N");
                            formulario.setPpusuarioUsuarioId(null);
                            dFormulario.updateFormularios(formulario);
                            break;
                            
                        case "2":
                            archivoUso.setFlagNoche("N");
                            dArchivosUso.updateArchivoUso(archivoUso);
                            break;
                    }
                }
                
                if(sistemaId != null){
                    switch (sistemaId){
                            case "1":
                                dArchivoAprobado.eliminarArchivoAprobado(dArchivoAprobado.getArchivoAprobadoByNombre(archivoUso.getNombreArchivo()));   
                                break;
                            case "2":
                                dArchivoAprobadoTDM.eliminarArchivoAprobado(dArchivoAprobadoTDM.getArchivoAprobadoByNombre(archivoUso.getNombreArchivo()));
                                break;
                        }
                }
        }
        
        response.sendRedirect("index.jsp");
            
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
