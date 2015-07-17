/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoPaseDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosPase;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
            String submId = request.getParameter("submenuId");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            
            if(nombre == null || nombre.equals("")){
                request.setAttribute("submenuId", submId);
                RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/insertarFormulario.jsp");
                rDispatcher.forward(request, response);
            }
            else{
                
                String tipo = request.getParameter("tipo");
                
                Blob archivoBlobRDF = null;
                Blob archivoBlobFMB = null;
                Blob archivoBlobFMX = null;
                
                HistorialesDao dHistorial = new HistorialesDao();
                PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
                FormulariosDao dFormulario = new FormulariosDao();
                
                if(tipo.equals("Reporte")){
                    //COPIAR RDF
                    
                    Part filePartRDF = request.getPart("archivoRDF");
                    InputStream fileContentRDF = filePartRDF.getInputStream();
                    byte[] bytesRDF = new byte[(int)filePartRDF.getSize()];
                    ByteArrayOutputStream output = new ByteArrayOutputStream();

                    for(int i = 0; (i = fileContentRDF.read(bytesRDF))>0;)
                        output.write(bytesRDF, 0, i);

                    archivoBlobRDF = new javax.sql.rowset.serial.SerialBlob(bytesRDF);
                    
                    //--------------------------------------------------------------
                    BigDecimal submenuId = new BigDecimal(submId);
                    PpFormularios reporte = new PpFormularios();
                    reporte.setNombreFormulario(nombre);
                    reporte.setDescFormulario(descripcion);
                    reporte.setArchivo(archivoBlobRDF);
                    reporte.setPpsubmenuSubmenuId(submenuId);
                    reporte.setFlagEstado("A");
                    reporte.setFlagUso("N");

                    dFormulario.insertarFormulario(reporte);

                    PpArchivosPase archivoPase = new PpArchivosPase();
                    ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
                    archivoPase.setArchivo(archivoBlobRDF);
                    archivoPase.setNombreArchivo(nombre);

                    dArchivoPase.insertarArchivoUso(archivoPase);
                    dArchivoPase.PasarProduccion();
                    dArchivoPase.TruncarTabla();

                    PpHistoriales historial = new PpHistoriales();
                    Date date = new Date();          
                    historial.setArchivo(archivoBlobRDF);
                    historial.setFecha(date);
                    historial.setPpFormularios(reporte);
                    historial.setComentarioPase("Primera versión del reporte generado con su creación");
                    historial.setUsuarioId(usuario.getUsuarioId());
                    historial.setNombre(nombre);
                    historial.setNroVersion((long)1);

                    dHistorial.insertarHistorial(historial);
                }
                else{
                    
                    //COPIAR FMB
                    Part filePartFMB = request.getPart("archivoFMB");
                    InputStream fileContentFMB = filePartFMB.getInputStream();

                    byte[] bytesFMB = new byte[(int)filePartFMB.getSize()];
                    ByteArrayOutputStream outputFMB = new ByteArrayOutputStream();

                    for(int i = 0; (i = fileContentFMB.read(bytesFMB))>0;)
                        outputFMB.write(bytesFMB, 0, i);

                    archivoBlobFMB = new javax.sql.rowset.serial.SerialBlob(bytesFMB);

                    //COPIAR FMX
                    Part filePartFMX = request.getPart("archivoFMX");
                    InputStream fileContentFMX = filePartFMX.getInputStream();

                    byte[] bytesFMX = new byte[(int)filePartFMX.getSize()];
                    ByteArrayOutputStream outputFMX = new ByteArrayOutputStream();

                    for(int i = 0; (i = fileContentFMX.read(bytesFMX))>0;)
                        outputFMX.write(bytesFMX, 0, i);

                    archivoBlobFMX = new javax.sql.rowset.serial.SerialBlob(bytesFMX);
                    
                    //--------------------------------------------------------------
                    BigDecimal submenuId = new BigDecimal(submId);
                    PpFormularios formulario = new PpFormularios();
                    formulario.setNombreFormulario(nombre);
                    formulario.setDescFormulario(descripcion);
                    formulario.setArchivo(archivoBlobFMB);
                    formulario.setPpsubmenuSubmenuId(submenuId);
                    formulario.setFlagEstado("A");
                    formulario.setFlagUso("N");

                    dFormulario.insertarFormulario(formulario);

                    PpArchivosPase archivoPase = new PpArchivosPase();
                    ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
                    archivoPase.setArchivo(archivoBlobFMX);
                    archivoPase.setNombreArchivo(nombre);

                    dArchivoPase.insertarArchivoUso(archivoPase);
                    dArchivoPase.PasarProduccion();
                    dArchivoPase.TruncarTabla();

                    PpHistoriales historial = new PpHistoriales();
                    Date date = new Date();          
                    historial.setArchivo(archivoBlobFMB);
                    historial.setFecha(date);
                    historial.setPpFormularios(formulario);
                    historial.setComentarioPase("Primera versión del formulario generado con su creación");
                    historial.setUsuarioId(usuario.getUsuarioId());
                    historial.setNombre(nombre);
                    historial.setNroVersion((long)1);

                    dHistorial.insertarHistorial(historial);
                }
            }
            
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
