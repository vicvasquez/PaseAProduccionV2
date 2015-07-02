/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoAprobDao;
import com.cis.paseaproduccionweb.dao.ArchivoPaseDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosAprob;
import com.cis.paseaproduccionweb.hibernate.PpArchivosPase;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date = new Date();
            
            if(archivoTipo.equals("REP"))
            {
                PpFormularios reporte = new PpFormularios();
                reporte = dFormulario.getFormularioByFormularioId(archivoId);
                PpArchivosPase archivoPase = new PpArchivosPase();
                
                archivoPase.setNombreArchivo(reporte.getNombreFormulario());
                archivoPase.setArchivo(reporte.getArchivo());
                
                dArchivoPase.insertarArchivoUso(archivoPase);
                dArchivoPase.PasarProduccion();
                dArchivoPase.TruncarTabla();
                
                historial.setArchivo(reporte.getArchivo());
                
                historial.setFecha(date);
                historial.setPpformFormularioId(archivoId);
                
                
            }
            if(archivoTipo.equals("FOR"))
            {   
                PpFormularios formulario = new PpFormularios();
                formulario = dFormulario.getFormularioByFormularioId(archivoId);
                switch(paseTipo){
                    case 0:
                            archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                            archivoPaseForm.setArchivo(formulario.getArchivo());

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccion();
                            dArchivoPase.TruncarTabla();

                            historial.setArchivo(formulario.getArchivo());
                            historial.setFecha(date);
                            historial.setPpformFormularioId(archivoId);
                        break;
                            
                    case 1:
                            archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                            archivoPaseForm.setArchivo(formulario.getArchivo());

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccionServicios();
                            dArchivoPase.TruncarTabla();

                            historial.setArchivo(formulario.getArchivo());
                            historial.setFecha(date);
                            historial.setPpformFormularioId(archivoId);
                        break;
                        
                    case 2:
                            archivoAprob.setNombreArchivo(formulario.getNombreFormulario());
                            archivoAprob.setArchivo(formulario.getArchivo());
                            
                            dArchivoAprob.insertarArchivoUso(archivoAprob);
                            
                            historial.setArchivo(formulario.getArchivo());
                            historial.setFecha(date);
                            historial.setPpformFormularioId(archivoId);
                        break;
                        
                }
            }
            if(archivoTipo.equals("MOD"))
            {
                PpModulos modulo = new PpModulos();
                modulo = dModulos.getModuloByModuloId(archivoId);
                switch(paseTipo){
                    case 0:
                            archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                            archivoPaseForm.setArchivo(modulo.getArchivo());

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccion();
                            dArchivoPase.TruncarTabla();

                            historial.setArchivo(modulo.getArchivo());
                            historial.setFecha(date);
                            historial.setModuloModuloId(archivoId);
                        break;
                    
                    case 1:
                            archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                            archivoPaseForm.setArchivo(modulo.getArchivo());

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            dArchivoPase.PasarProduccionServicios();
                            dArchivoPase.TruncarTabla();

                            historial.setArchivo(modulo.getArchivo());
                            historial.setFecha(date);
                            historial.setModuloModuloId(archivoId);
                        break;
                        
                    case 2:
                            archivoAprob.setNombreArchivo(modulo.getNombreModulo());
                            archivoAprob.setArchivo(modulo.getArchivo());
                            
                            dArchivoAprob.insertarArchivoUso(archivoAprob);
                            
                            historial.setArchivo(modulo.getArchivo());
                            historial.setFecha(date);
                            historial.setModuloModuloId(archivoId);
                        break;
                        
                }
            }
            
        }catch(Exception ex){
            
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
