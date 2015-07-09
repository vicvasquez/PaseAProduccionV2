/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoAprobDao;
import com.cis.paseaproduccionweb.dao.ArchivoPaseDao;
import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosAprob;
import com.cis.paseaproduccionweb.hibernate.PpArchivosPase;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sun.misc.IOUtils;

/**
 *
 * @author eyomona
 */
public class PaseAProduccionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            String archId = request.getParameter("archivoId");
            String archivoTipo = request.getParameter("archivoTipo");
            
            BigDecimal archivoId = new BigDecimal(archId);

            String paseTip = request.getParameter("paseTipo");
            Integer paseTipo = new Integer(paseTip);
            
            FormulariosDao dFormulario = new FormulariosDao();
            ModulosDao dModulos = new ModulosDao();
            ArchivoAprobDao dArchivoAprob = new ArchivoAprobDao();
            ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
            HistorialesDao dHistorial = new HistorialesDao();
            
            PpArchivosPase archivoPaseForm = new PpArchivosPase();
            PpArchivosAprob archivoAprob = new PpArchivosAprob();
            PpHistoriales historial = new PpHistoriales();
            
            Date date = new Date();          
            
            ArchivosUsoDao dArchivosUso = new ArchivosUsoDao();
            PpArchivosUso archivoUso = dArchivosUso.getArchivosUsoByArchivoIdYTipo(archivoId, archivoTipo);
            
            Blob archivoBlobRDF = null;
            Blob archivoBlobFMB = null;
            Blob archivoBlobFMX = null;
            
            if(archivoTipo.equals("REP")){
                //COPIAR RDF
                Part filePartRDF = request.getPart("archivoRDF");
                InputStream fileContentRDF = filePartRDF.getInputStream();
                byte[] bytesRDF = new byte[(int)filePartRDF.getSize()];
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                for(int i = 0; (i = fileContentRDF.read(bytesRDF))>0;)
                    output.write(bytesRDF, 0, i);
                
                archivoBlobRDF = new javax.sql.rowset.serial.SerialBlob(bytesRDF);
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

                //COPIAR FMB
                Part filePartFMX = request.getPart("archivoFMX");
                InputStream fileContentFMX = filePartFMX.getInputStream();

                byte[] bytesFMX = new byte[(int)filePartFMX.getSize()];
                ByteArrayOutputStream outputFMX = new ByteArrayOutputStream();

                for(int i = 0; (i = fileContentFMX.read(bytesFMX))>0;)
                    outputFMX.write(bytesFMX, 0, i);

                archivoBlobFMX = new javax.sql.rowset.serial.SerialBlob(bytesFMX);
            }
            
            if(archivoTipo.equals("FOR"))
            {
                PpFormularios formulario = dFormulario.getFormularioByFormularioId(archivoId);
                if(formulario.getFlagTipo().equals("R")){
                    
                    archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                    archivoPaseForm.setArchivo(archivoBlobRDF);
                    
                    formulario.setFlagUso("N");
                    formulario.setPpusuarioUsuarioId(null);

                    formulario.setArchivo(archivoBlobRDF);
                    dFormulario.updateFormularios(formulario);
                    
                    if(archivoUso != null)
                        dArchivosUso.eliminarArchivoUso(archivoUso);

    /*              historial.setArchivo(archivoBlob);
                    historial.setFecha(date);
                    historial.setPpFormularios(formulario);*/
                    
                    dArchivoPase.insertarArchivoUso(archivoPaseForm);
                    dArchivoPase.PasarProduccion();
                    dArchivoPase.TruncarTabla();
                }
                else{
                    switch(paseTipo){
                        case 0:  //INTENTAR SIN BAJAR SERVICIOS
                                archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                                archivoPaseForm.setArchivo(archivoBlobFMX);

                                dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                dArchivoPase.PasarProduccion();
                                dArchivoPase.TruncarTabla();

                                formulario.setFlagUso("N");
                                formulario.setPpusuarioUsuarioId(null);

                                formulario.setArchivo(archivoBlobFMB);
                                dFormulario.updateFormularios(formulario);
                                
                                if(archivoUso != null)
                                    dArchivosUso.eliminarArchivoUso(archivoUso);

    /*                          historial.setArchivo(archivoBlob);
                                historial.setFecha(date);
                                historial.setPpFormularios(formulario);*/
                            break;

                        case 1:   //BAJANDO SERVICIOS
                                archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                                archivoPaseForm.setArchivo(archivoBlobFMX);

                                dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                dArchivoPase.PasarProduccionServicios();

                                dArchivoPase.TruncarTabla();

                                formulario.setFlagUso("N");
                                formulario.setPpusuarioUsuarioId(null);
                                
                                formulario.setArchivo(archivoBlobFMB);
                                dFormulario.updateFormularios(formulario);
                                
                                if(archivoUso != null)
                                    dArchivosUso.eliminarArchivoUso(archivoUso);

                               /* historial.setArchivo(archivoBlob);
                                historial.setFecha(date);
                                historial.setPpFormularios(formulario);

                                dHistorial.insertarHistorial(historial);*/
                            break;

                        case 2:    //PASE NOCTURNO
                                archivoAprob.setNombreArchivo(formulario.getNombreFormulario());
                                archivoAprob.setArchivo(archivoBlobFMX);

                                dArchivoAprob.insertarArchivoUso(archivoAprob);
                                
                                formulario.setArchivo(archivoBlobFMB);
                                dFormulario.updateFormularios(formulario);
                                
                                archivoUso.setFlagNoche("S");
                                dArchivosUso.updateArchivoUso(archivoUso);

                                /*historial.setArchivo(archivoBlob);
                                historial.setFecha(date);
                                historial.setPpFormularios(formulario);*/
                            break;       
                    }
                }
            }
            else
            {
                PpModulos modulo = dModulos.getModuloByModuloId(archivoId);
                switch(paseTipo){
                    case 0: //INTENTAR SIN BAJAR SERVICIOS
                            archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                            archivoPaseForm.setArchivo(archivoBlobFMX);

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccion();
                            dArchivoPase.TruncarTabla();
                            
                            modulo.setArchivo(archivoBlobFMB);
                            dModulos.updateModulo(modulo);
                            
                            if(archivoUso != null)
                                dArchivosUso.eliminarArchivoUso(archivoUso);
                            
                            modulo.setFlagUso("N");
                            modulo.setPpusuarioUsuarioId(null);

                   /*         historial.setArchivo(archivoBlob);
                            historial.setFecha(date);
                            historial.setPpModulos(modulo);*/
                        break;
                    
                    case 1: //BAJANDO SERVICIOS
                            archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                            archivoPaseForm.setArchivo(archivoBlobFMX);

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccionServicios();
                            dArchivoPase.TruncarTabla();
                            
                            modulo.setArchivo(archivoBlobFMB);
                            dModulos.updateModulo(modulo);
                            
                            if(archivoUso != null)
                                dArchivosUso.eliminarArchivoUso(archivoUso);
                            
                            modulo.setFlagUso("N");
                            modulo.setPpusuarioUsuarioId(null);

                     /*       historial.setArchivo(archivoBlob);
                            historial.setFecha(date);
                            historial.setPpModulos(modulo);*/
                        break;
                        
                    case 2: //PASE NOCTURNO
                            archivoAprob.setNombreArchivo(modulo.getNombreModulo());
                            archivoAprob.setArchivo(archivoBlobFMX);
                            
                            dArchivoAprob.insertarArchivoUso(archivoAprob);
                            
                            modulo.setArchivo(archivoBlobFMB);
                            dModulos.updateModulo(modulo);
                            
                            archivoUso.setFlagNoche("S");
                            dArchivosUso.updateArchivoUso(archivoUso);
                            
                    /*        historial.setArchivo(archivoBlob);
                            historial.setFecha(date);
                            historial.setPpModulos(modulo);*/
                        break;
                        
                }
            }
            response.sendRedirect("mensajePaseConfirmacion.jsp");
        }catch(Exception ex){
            response.sendRedirect("mensajePaseFalla.jsp");
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
