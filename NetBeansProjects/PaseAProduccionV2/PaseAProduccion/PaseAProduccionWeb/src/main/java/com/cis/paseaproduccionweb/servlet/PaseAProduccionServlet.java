    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivoAprobDao;
import com.cis.paseaproduccionweb.dao.ArchivoAprobDaoTDM;
import com.cis.paseaproduccionweb.dao.ArchivoPaseDao;
import com.cis.paseaproduccionweb.dao.ArchivoPaseDaoTDM;
import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.dao.SistemasDao;
import com.cis.paseaproduccionweb.dao.SubMenusDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosAprob;
import com.cis.paseaproduccionweb.hibernate.PpArchivosPase;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import com.cis.paseaproduccionweb.hibernate.PpTempArchaprob;
import com.cis.paseaproduccionweb.hibernate.PpTempArchpase;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class PaseAProduccionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            request.setCharacterEncoding("UTF-8");
            String archId = request.getParameter("archivoId");
            String archivoTipo = request.getParameter("archivoTipo");
            String comentarioPase = request.getParameter("comentarioPase");
            String comentarioServicios = request.getParameter("comentarioServicios");
            PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
            
            BigDecimal archivoId = new BigDecimal(archId);

            String paseTip = request.getParameter("paseTipo");
            Integer paseTipo = new Integer(paseTip);
            
            FormulariosDao dFormulario = new FormulariosDao();
            ModulosDao dModulos = new ModulosDao();
            ArchivoAprobDao dArchivoAprob = new ArchivoAprobDao();
            ArchivoAprobDaoTDM dArchivoAprobTDM = new ArchivoAprobDaoTDM();
            ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
            ArchivoPaseDaoTDM dArchivoPaseTDM = new ArchivoPaseDaoTDM();
            HistorialesDao dHistorial = new HistorialesDao();
            SubMenusDao dSubmenu = new SubMenusDao();
            
            PpArchivosPase archivoPaseForm = new PpArchivosPase();
            PpTempArchpase archivoPaseFormTDM = new PpTempArchpase();
            PpArchivosAprob archivoAprob = new PpArchivosAprob();
            PpTempArchaprob archivoAprobTDM = new PpTempArchaprob();
            PpHistoriales historial = new PpHistoriales();
            
            SistemasDao dSistema = new SistemasDao();
            
            Date date = new Date();
            
            ArchivosUsoDao dArchivosUso = new ArchivosUsoDao();
            PpArchivosUso archivoUso = dArchivosUso.getArchivosUsoByArchivoIdYTipo(archivoId, archivoTipo);
            
            Blob archivoBlobRDF = null;
            Blob archivoBlobFMB = null;
            Blob archivoBlobFMX = null;
            Integer resultado = null;
            
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

                //COPIAR FMX
                Part filePartFMX = request.getPart("archivoFMX");
                InputStream fileContentFMX = filePartFMX.getInputStream();

                byte[] bytesFMX = new byte[(int)filePartFMX.getSize()];
                ByteArrayOutputStream outputFMX = new ByteArrayOutputStream();

                for(int i = 0; (i = fileContentFMX.read(bytesFMX))>0;)
                    outputFMX.write(bytesFMX, 0, i);

                archivoBlobFMX = new javax.sql.rowset.serial.SerialBlob(bytesFMX);
            }
            
            if(archivoTipo.equals("FOR") || archivoTipo.equals("REP"))
            {
                PpFormularios formulario = dFormulario.getFormularioByFormularioId(archivoId);
                BigDecimal sistemaId = dModulos.getModuloByModuloId(dSubmenu.getSubMenuBySubMenuId(formulario.getPpsubmenuSubmenuId()).getModuloModuloId()).getPpsistemaSistemaId();
                if(formulario.getFlagTipo().equals("R")){
                    
                    switch(sistemaId.toString()){
                        case "1":
                            archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                            archivoPaseForm.setArchivo(archivoBlobRDF);

                            dArchivoPase.insertarArchivoUso(archivoPaseForm);
                            resultado = dArchivoPase.PasarProduccion();
                            dArchivoPase.TruncarTabla();
                            break;
                            
                        case "2":
                            archivoPaseFormTDM.setNombreArchivo(formulario.getNombreFormulario());
                            archivoPaseFormTDM.setArchivo(archivoBlobRDF);

                            dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                            resultado = dArchivoPaseTDM.PasarProduccion();
                            dArchivoPaseTDM.TruncarTabla();
                            break;
                    }
                    
                    if(resultado == 0 || resultado == -1){
                        response.sendRedirect("mensajePaseFalla.jsp");
                        return;
                    }
                    
                    formulario.setFlagUso("N");
                    formulario.setPpusuarioUsuarioId(null);

                    formulario.setArchivo(archivoBlobRDF);
                    dFormulario.updateFormularios(formulario);
                    
                    if(archivoUso != null)
                        dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");

                    historial.setArchivo(archivoBlobRDF);
                    historial.setFecha(date);
                    historial.setPpFormularios(formulario);
                    historial.setComentarioPase(comentarioPase);
                    historial.setComentarioServicios(comentarioServicios);
                    historial.setUsuarioId(usuario.getUsuarioId());
                    historial.setNombre(formulario.getNombreFormulario());
                    historial.setNroVersion((long)dHistorial.getLastVersion(formulario.getNombreFormulario(), sistemaId));
                    historial.setPpSistemas(dSistema.getSistemaBySistemaId(sistemaId));
                    
                    dHistorial.insertarHistorial(historial);
                }
                else{
                    switch(paseTipo){
                        case 0:  //INTENTAR SIN BAJAR SERVICIOS
                            switch(sistemaId.toString()){
                                case "1":
                                    archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoPaseForm.setArchivo(archivoBlobFMX);

                                    dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                    resultado = dArchivoPase.PasarProduccion();
                                    dArchivoPase.TruncarTabla();
                                    break;
                                    
                                case "2":
                                    archivoPaseFormTDM.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                    dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                    resultado = dArchivoPaseTDM.PasarProduccion();
                                    dArchivoPaseTDM.TruncarTabla();
                                    break; 
                            }   
                            
                            if(resultado == 0 || resultado == -1){
                                response.sendRedirect("mensajePaseFalla.jsp");
                                return;
                            } 

                            formulario.setFlagUso("N");
                            formulario.setPpusuarioUsuarioId(null);

                            formulario.setArchivo(archivoBlobFMB);
                            dFormulario.updateFormularios(formulario);
                                
                            if(archivoUso != null)
                                dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                                
                            historial.setArchivo(archivoBlobFMB);
                            historial.setFecha(date);
                            historial.setPpFormularios(formulario);
                            historial.setComentarioPase(comentarioPase);
                            historial.setComentarioServicios(comentarioServicios);
                            historial.setUsuarioId(usuario.getUsuarioId());
                            historial.setNombre(formulario.getNombreFormulario());
                                historial.setNroVersion((long)dHistorial.getLastVersion(formulario.getNombreFormulario(), sistemaId));
                            historial.setPpSistemas(dSistema.getSistemaBySistemaId(sistemaId));

                            dHistorial.insertarHistorial(historial);
                            break;

                        case 1:   //BAJANDO SERVICIOS
                            
                            switch(sistemaId.toString()){
                                case "1":
                                    archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoPaseForm.setArchivo(archivoBlobFMX);

                                    dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                    dArchivoPase.PasarProduccionServicios();
                                    dArchivoPase.TruncarTabla();
                                    break;
                                    
                                case "2":
                                    archivoPaseFormTDM.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                    dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                    dArchivoPaseTDM.PasarProduccionServicios();
                                    dArchivoPaseTDM.TruncarTabla();
                                    break;
                            }
                            formulario.setFlagUso("N");
                            formulario.setPpusuarioUsuarioId(null);
                                
                            formulario.setArchivo(archivoBlobFMB);
                            dFormulario.updateFormularios(formulario);
                                
                            if(archivoUso != null)
                                dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");

                            historial.setArchivo(archivoBlobFMB);
                            historial.setFecha(date);
                            historial.setPpFormularios(formulario);
                            historial.setComentarioPase(comentarioPase);
                            historial.setComentarioServicios(comentarioServicios);
                            historial.setUsuarioId(usuario.getUsuarioId());
                            historial.setNombre(formulario.getNombreFormulario());
                            historial.setNroVersion((long)dHistorial.getLastVersion(formulario.getNombreFormulario(), sistemaId));
                            historial.setPpSistemas(dSistema.getSistemaBySistemaId(sistemaId));

                            dHistorial.insertarHistorial(historial);
                            break;

                        case 2:    //PASE NOCTURNO
                            
                            switch(sistemaId.toString()){
                                case "1":
                                    archivoAprob.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoAprob.setArchivo(archivoBlobFMX);
                                    archivoAprob.setArchivoFuente(archivoBlobFMB);
                                    archivoAprob.setTipo("FOR");
                                    archivoAprob.setArchivoId(archivoId);
                                    archivoAprob.setUsuarioId(usuario.getUsuarioId());
                                    archivoAprob.setComentarioPase(comentarioPase);
                                    archivoAprob.setComentarioServicios(comentarioServicios);

                                    dArchivoAprob.insertarArchivoUso(archivoAprob);
                                    break;
                                    
                                case "2":
                                    archivoAprobTDM.setNombreArchivo(formulario.getNombreFormulario());
                                    archivoAprobTDM.setArchivo(archivoBlobFMX);
                                    archivoAprobTDM.setArchivoFuente(archivoBlobFMB);
                                    archivoAprobTDM.setTipo("FOR");
                                    archivoAprobTDM.setArchivoId(archivoId);
                                    archivoAprobTDM.setUsuarioId(usuario.getUsuarioId());
                                    archivoAprobTDM.setComentarioPase(comentarioPase);
                                    archivoAprobTDM.setComentarioServicios(comentarioServicios);

                                    dArchivoAprobTDM.insertarArchivoUso(archivoAprobTDM);
                                    break;
                            }   
                            archivoUso.setFlagNoche("S");
                            dArchivosUso.updateArchivoUso(archivoUso);
                            break;       
                    }
                }
            }
            else
            {
                PpModulos modulo = dModulos.getModuloByModuloId(archivoId);
                BigDecimal sistemaId = dModulos.getModuloByModuloId(modulo.getModuloId()).getPpsistemaSistemaId();
                switch(paseTipo){
                    case 0: //INTENTAR SIN BAJAR SERVICIOS
                        
                        switch(sistemaId.toString()){
                            case "1":
                                archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                                archivoPaseForm.setArchivo(archivoBlobFMX);

                                dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                resultado = dArchivoPase.PasarProduccion();
                                dArchivoPase.TruncarTabla();
                                break;
                                
                            case "2":
                                archivoPaseFormTDM.setNombreArchivo(modulo.getNombreModulo());
                                archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                resultado = dArchivoPaseTDM.PasarProduccion();
                                dArchivoPaseTDM.TruncarTabla();
                                break;
                        }
                        if(resultado == 0 || resultado == -1){
                            response.sendRedirect("mensajePaseFalla.jsp");
                            return;
                        }
                            
                        modulo.setArchivo(archivoBlobFMB);
                        dModulos.updateModulo(modulo);
                            
                        if(archivoUso != null)
                            dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                           
                        modulo.setFlagUso("N");
                        modulo.setPpusuarioUsuarioId(null);

                        historial.setArchivo(archivoBlobFMB);
                        historial.setFecha(date);
                        historial.setPpModulos(modulo);
                        historial.setComentarioPase(comentarioPase);
                        historial.setComentarioServicios(comentarioServicios);
                        historial.setUsuarioId(usuario.getUsuarioId());
                        historial.setNombre(modulo.getNombreModulo());
                        historial.setNroVersion((long)dHistorial.getLastVersion(modulo.getNombreModulo(), sistemaId));
                        historial.setPpSistemas(dSistema.getSistemaBySistemaId(sistemaId));

                        dHistorial.insertarHistorial(historial);
                        break;
                    
                    case 1: //BAJANDO SERVICIOS
                        
                        switch(sistemaId.toString()){
                            case "1":
                                archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                                archivoPaseForm.setArchivo(archivoBlobFMX);

                                dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                dArchivoPase.PasarProduccionServicios();
                                dArchivoPase.TruncarTabla();
                                break;
                            case "2":
                                archivoPaseFormTDM.setNombreArchivo(modulo.getNombreModulo());
                                archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                dArchivoPaseTDM.PasarProduccionServicios();
                                dArchivoPaseTDM.TruncarTabla();
                                break;
                        }
                        
                        modulo.setArchivo(archivoBlobFMB);
                        dModulos.updateModulo(modulo);
                            
                        if(archivoUso != null)
                            dArchivosUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                            
                        modulo.setFlagUso("N");
                        modulo.setPpusuarioUsuarioId(null);
                            
                        historial.setArchivo(archivoBlobFMB);
                        historial.setFecha(date);
                        historial.setPpModulos(modulo);
                        historial.setComentarioPase(comentarioPase);
                        historial.setComentarioServicios(comentarioServicios);
                        historial.setUsuarioId(usuario.getUsuarioId());
                        historial.setNombre(modulo.getNombreModulo());
                        historial.setNroVersion((long)dHistorial.getLastVersion(modulo.getNombreModulo(), sistemaId));
                        historial.setPpSistemas(dSistema.getSistemaBySistemaId(sistemaId));

                        dHistorial.insertarHistorial(historial);
                        break;
                        
                    case 2: //PASE NOCTURNO
                        switch(sistemaId.toString()){
                            case "1":
                                archivoAprob.setNombreArchivo(modulo.getNombreModulo());
                                archivoAprob.setArchivo(archivoBlobFMX);
                                archivoAprob.setArchivoFuente(archivoBlobFMB);
                                archivoAprob.setTipo("MOD");
                                archivoAprob.setArchivoId(archivoId);
                                archivoAprob.setUsuarioId(usuario.getUsuarioId());
                                archivoAprob.setComentarioPase(comentarioPase);
                                archivoAprob.setComentarioServicios(comentarioServicios);

                                dArchivoAprob.insertarArchivoUso(archivoAprob);
                                break;
                                
                            case "2":
                                archivoAprobTDM.setNombreArchivo(modulo.getNombreModulo());
                                archivoAprobTDM.setArchivo(archivoBlobFMX);
                                archivoAprobTDM.setArchivoFuente(archivoBlobFMB);
                                archivoAprobTDM.setTipo("MOD");
                                archivoAprobTDM.setArchivoId(archivoId);
                                archivoAprobTDM.setUsuarioId(usuario.getUsuarioId());
                                archivoAprobTDM.setComentarioPase(comentarioPase);
                                archivoAprobTDM.setComentarioServicios(comentarioServicios);

                                dArchivoAprobTDM.insertarArchivoUso(archivoAprobTDM);
                                break;
                        }    
                        archivoUso.setFlagNoche("S");
                        dArchivosUso.updateArchivoUso(archivoUso);
                        break;
                        
                }
            }
            response.sendRedirect("mensajePaseConfirmacion.jsp");
        }catch(Exception ex){
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(ex.toString());
            error.setFecha(date);
            dError.insertarError(error);
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
