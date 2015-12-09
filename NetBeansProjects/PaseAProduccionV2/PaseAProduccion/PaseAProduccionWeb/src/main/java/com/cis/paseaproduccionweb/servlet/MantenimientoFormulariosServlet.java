/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoPaseDao;
import com.cis.paseaproduccionweb.dao.ArchivoPaseDaoTDM;
import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.dao.SistemasDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosPase;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpTempArchpase;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class MantenimientoFormulariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            request.setCharacterEncoding("UTF-8");
            String submId = request.getParameter("submenuId");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            
            if(nombre == null || nombre.equals("") || nombre.trim().equals("null")){
                request.setAttribute("submenuId", submId);
                RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/insertarFormulario.jsp");
                rDispatcher.forward(request, response);
            }
            else{
                nombre = nombre.trim();
                String tipo = request.getParameter("tipo");
                String sistemaId = request.getParameter("sistemaId");
                
                Blob archivoBlobRDF = null;
                Blob archivoBlobFMB = null;
                Blob archivoBlobFMX = null;
                
                HistorialesDao dHistorial = new HistorialesDao();
                PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
                FormulariosDao dFormulario = new FormulariosDao();
                
                BigDecimal submenuId = new BigDecimal(submId);
                PpFormularios reporte = new PpFormularios();
                SistemasDao dSistema = new SistemasDao();
                
                PpArchivosPase archivoPase = new PpArchivosPase();
                ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
                PpTempArchpase archivoPaseTDM = new PpTempArchpase();
                ArchivoPaseDaoTDM dArchivoPaseTDM = new ArchivoPaseDaoTDM();
                
                BigDecimal sisId = new BigDecimal(sistemaId);
                
                if(tipo.equals("R")){
                    
                    
                    //COPIAR RDF
                    Part filePartRDF = request.getPart("archivoRDF");
                    if(filePartRDF.getSize() != 0){
                        InputStream fileContentRDF = filePartRDF.getInputStream();
                        byte[] bytesRDF = new byte[(int)filePartRDF.getSize()];
                        ByteArrayOutputStream output = new ByteArrayOutputStream();

                        for(int i = 0; (i = fileContentRDF.read(bytesRDF))>0;)
                            output.write(bytesRDF, 0, i);

                        archivoBlobRDF = new javax.sql.rowset.serial.SerialBlob(bytesRDF);
                        
                        //--------------------------------------------------------------
                        reporte.setNombreFormulario(nombre);
                        reporte.setDescFormulario(descripcion);
                        reporte.setArchivo(archivoBlobRDF);
                        reporte.setPpsubmenuSubmenuId(submenuId);
                        reporte.setFlagEstado("A");
                        reporte.setFlagUso("N");
                        reporte.setFlagTipo("R");

                        dFormulario.insertarFormulario(reporte);
                        
                        switch(sistemaId){
                            case "1":
                                archivoPase.setArchivo(archivoBlobRDF);
                                archivoPase.setNombreArchivo(nombre);

                                dArchivoPase.insertarArchivoUso(archivoPase);
                                dArchivoPase.PasarProduccion();
                                dArchivoPase.TruncarTabla();
                                break;
                                
                            case "2":
                                archivoPaseTDM.setArchivo(archivoBlobRDF);
                                archivoPaseTDM.setNombreArchivo(nombre);

                                dArchivoPaseTDM.insertarArchivoUso(archivoPaseTDM);
                                dArchivoPaseTDM.PasarProduccion();
                                dArchivoPaseTDM.TruncarTabla();
                                break;
                        }
                        
                        PpHistoriales historial = new PpHistoriales();
                        Date date = new Date();
                        
                        historial.setArchivo(archivoBlobRDF);
                        historial.setFecha(date);
                        historial.setPpFormularios(reporte);
                        historial.setComentarioPase("Primera versión del reporte generado con su creación");
                        historial.setUsuarioId(usuario.getUsuarioId());
                        historial.setNombre(nombre);
                        historial.setNroVersion((long)1);
                        historial.setPpSistemas(dSistema.getSistemaBySistemaId(sisId));
                        
                        dHistorial.insertarHistorial(historial);
                    }
                    else
                    {
                        //--------------------------------------------------------------
                        reporte.setNombreFormulario(nombre);
                        reporte.setDescFormulario(descripcion);
                        reporte.setArchivo(archivoBlobRDF);
                        reporte.setPpsubmenuSubmenuId(submenuId);
                        reporte.setFlagEstado("A");
                        reporte.setFlagUso("N");
                        reporte.setFlagTipo("R");

                        dFormulario.insertarFormulario(reporte);
                    }
                }
                else{
                    
                    PpFormularios formulario = new PpFormularios();
                    Part filePartFMB = request.getPart("archivoFMB");
                    Part filePartFMX = request.getPart("archivoFMX");
                    
                    if(filePartFMB.getSize() != 0 && filePartFMX.getSize() != 0)
                    {
                        //COPIAR FMB
                        InputStream fileContentFMB = filePartFMB.getInputStream();

                        byte[] bytesFMB = new byte[(int)filePartFMB.getSize()];
                        ByteArrayOutputStream outputFMB = new ByteArrayOutputStream();

                        for(int i = 0; (i = fileContentFMB.read(bytesFMB))>0;)
                            outputFMB.write(bytesFMB, 0, i);

                        archivoBlobFMB = new javax.sql.rowset.serial.SerialBlob(bytesFMB);
                        
                        //COPIAR FMX
                        InputStream fileContentFMX = filePartFMX.getInputStream();

                        byte[] bytesFMX = new byte[(int)filePartFMX.getSize()];
                        ByteArrayOutputStream outputFMX = new ByteArrayOutputStream();

                        for(int i = 0; (i = fileContentFMX.read(bytesFMX))>0;)
                            outputFMX.write(bytesFMX, 0, i);

                        archivoBlobFMX = new javax.sql.rowset.serial.SerialBlob(bytesFMX);
                        
                        //--------------------------------------------------------------
                        
                        formulario.setNombreFormulario(nombre);
                        formulario.setDescFormulario(descripcion);
                        formulario.setArchivo(archivoBlobFMB);
                        formulario.setPpsubmenuSubmenuId(submenuId);
                        formulario.setFlagEstado("A");
                        formulario.setFlagUso("N");
                        formulario.setFlagTipo("F");

                        dFormulario.insertarFormulario(formulario);
                        
                        switch(sistemaId){
                            case "1":
                                archivoPase.setArchivo(archivoBlobFMX);
                                archivoPase.setNombreArchivo(nombre);

                                dArchivoPase.insertarArchivoUso(archivoPase);
                                dArchivoPase.PasarProduccion();
                                dArchivoPase.TruncarTabla();
                                break;
                                
                            case "2":
                                archivoPaseTDM.setArchivo(archivoBlobFMX);
                                archivoPaseTDM.setNombreArchivo(nombre);

                                dArchivoPaseTDM.insertarArchivoUso(archivoPaseTDM);
                                dArchivoPaseTDM.PasarProduccion();
                                dArchivoPaseTDM.TruncarTabla();
                                break;
                        }
                        
                        PpHistoriales historial = new PpHistoriales();
                        Date date = new Date();          
                        historial.setArchivo(archivoBlobFMB);
                        historial.setFecha(date);
                        historial.setPpFormularios(formulario);
                        historial.setComentarioPase("Primera versión del formulario generado con su creación");
                        historial.setUsuarioId(usuario.getUsuarioId());
                        historial.setNombre(nombre);
                        historial.setNroVersion((long)1);
                        historial.setPpSistemas(dSistema.getSistemaBySistemaId(sisId));

                        dHistorial.insertarHistorial(historial);
                    }
                    else{
                        formulario.setNombreFormulario(nombre);
                        formulario.setDescFormulario(descripcion);
                        formulario.setArchivo(archivoBlobFMB);
                        formulario.setPpsubmenuSubmenuId(submenuId);
                        formulario.setFlagEstado("A");
                        formulario.setFlagUso("N");
                        formulario.setFlagTipo("F");

                        dFormulario.insertarFormulario(formulario);
                    }
                }
                request.setAttribute("submenuId", submId);
                RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/mantenimientoFormulario.jsp");
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
