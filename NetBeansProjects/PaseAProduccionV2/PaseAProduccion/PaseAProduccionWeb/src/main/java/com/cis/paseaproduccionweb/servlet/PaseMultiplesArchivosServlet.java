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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PaseMultiplesArchivosServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            request.setCharacterEncoding("UTF-8");  
           
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            
            PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
            
            FormulariosDao dFormulario = new FormulariosDao();
            ModulosDao dModulos = new ModulosDao();
            ArchivoAprobDao dArchivoAprob = new ArchivoAprobDao();
            ArchivoAprobDaoTDM dArchivoAprobTDM = new ArchivoAprobDaoTDM();
            ArchivoPaseDao dArchivoPase = new ArchivoPaseDao();
            ArchivoPaseDaoTDM dArchivoPaseTDM = new ArchivoPaseDaoTDM();
            HistorialesDao dHistorial = new HistorialesDao();
            SubMenusDao dSubmenu = new SubMenusDao();
            ArchivosUsoDao dArchivoUso = new ArchivosUsoDao();
            SistemasDao dSistema = new SistemasDao();
            
            Blob archivoBlobRDF = null;
            Blob archivoBlobFMB = null;
            Blob archivoBlobFMX = null;
            
            PpArchivosUso archivoUso = null;
            
            Integer resultado = null;
            
            Date date = new Date();
            
            String comentarioPase = null;
            String comentarioServicios = null;
            
            PpArchivosPase archivoPaseForm = new PpArchivosPase();
            PpTempArchpase archivoPaseFormTDM = new PpTempArchpase();
            PpArchivosAprob archivoAprob = new PpArchivosAprob();
            PpTempArchaprob archivoAprobTDM = new PpTempArchaprob();
            PpHistoriales historial = new PpHistoriales();
            
            BigDecimal sistemaId = new BigDecimal("0");
            
            for(FileItem item : items){
                
                String filedName = item.getFieldName();
                String value = item.getString();
                
                if(filedName.equals("sistemaId")){
                    sistemaId = new BigDecimal(value);
                    continue;
                }
                
                else if(filedName.equals("comentarioBajada")){
                    comentarioServicios = value;
                    continue;
                }
                
                else if(filedName.equals("archivoUsoId")){
                    BigDecimal archivoUsoId = new BigDecimal(value);
                    archivoUso = dArchivoUso.getArchivoUsoByArchivoUsoId(archivoUsoId);
                }
                
                else if(filedName.equals("ArchivoFMB")){
                    InputStream fileContentFMB = item.getInputStream();
                    byte[] bytesFMB = new byte[(int)item.getSize()];
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    for(int i = 0; (i = fileContentFMB.read(bytesFMB))>0;)
                        output.write(bytesFMB, 0, i);
                    
                    archivoBlobFMB = new javax.sql.rowset.serial.SerialBlob(bytesFMB);
                }
                
                else if(filedName.equals("ArchivoFMX")){
                    InputStream fileContentFMX = item.getInputStream();
                    byte[] bytesFMX = new byte[(int)item.getSize()];
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    for(int i = 0; (i = fileContentFMX.read(bytesFMX))>0;)
                        output.write(bytesFMX, 0, i);
                    
                    archivoBlobFMX = new javax.sql.rowset.serial.SerialBlob(bytesFMX);
                }
                
                else if(filedName.equals("ArchivoRDF")){
                    InputStream fileContentRDF = item.getInputStream();
                    byte[] bytesRDF = new byte[(int)item.getSize()];
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    for(int i = 0; (i = fileContentRDF.read(bytesRDF))>0;)
                        output.write(bytesRDF, 0, i);
                    
                    archivoBlobRDF = new javax.sql.rowset.serial.SerialBlob(bytesRDF);
                }
                
                else if(filedName.equals("comentarioPase")){
                    comentarioPase = value;
                }
                
                if(archivoUso != null){
                    switch(archivoUso.getTipo()){
                        case "FOR":
                            if(archivoBlobFMB != null && archivoBlobFMX != null && comentarioPase != null){
                                
                                PpFormularios formulario = dFormulario.getFormularioByFormularioId(archivoUso.getArchivoId());
                                switch(archivoUso.getSistemaId().toString()){
                                    case "1":
                                        archivoPaseForm.setNombreArchivo(formulario.getNombreFormulario());
                                        archivoPaseForm.setArchivo(archivoBlobFMX);

                                        dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                        break;
                                    case "2":
                                        archivoPaseFormTDM.setNombreArchivo(formulario.getNombreFormulario());
                                        archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                        dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                        break;
                                }
                                
                                formulario.setFlagUso("N");
                                formulario.setPpusuarioUsuarioId(null);

                                formulario.setArchivo(archivoBlobFMB);
                                dFormulario.updateFormularios(formulario);

                                historial.setArchivo(archivoBlobFMB);
                                historial.setFecha(date);
                                historial.setPpFormularios(formulario);
                                historial.setComentarioPase(comentarioPase);
                                historial.setComentarioServicios(comentarioServicios);
                                historial.setUsuarioId(usuario.getUsuarioId());
                                historial.setNombre(formulario.getNombreFormulario());
                                historial.setNroVersion((long)dHistorial.getLastVersion(formulario.getNombreFormulario(), archivoUso.getSistemaId()));
                                historial.setPpSistemas(dSistema.getSistemaBySistemaId(archivoUso.getSistemaId()));

                                dHistorial.insertarHistorial(historial);
                                
                                dArchivoUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                                
                                archivoUso = null;
                                archivoBlobFMB = null;
                                archivoBlobFMX = null;
                                comentarioPase = null;
                            }
                            break;
                        case "MOD":
                            if(archivoBlobFMB != null && archivoBlobFMX != null && comentarioPase != null){
                                PpModulos modulo = dModulos.getModuloByModuloId(archivoUso.getArchivoId());
                                switch(archivoUso.getSistemaId().toString()){
                                    case "1":
                                        archivoPaseForm.setNombreArchivo(modulo.getNombreModulo());
                                        archivoPaseForm.setArchivo(archivoBlobFMX);

                                        dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                        break;
                                    case "2":
                                        archivoPaseFormTDM.setNombreArchivo(modulo.getNombreModulo());
                                        archivoPaseFormTDM.setArchivo(archivoBlobFMX);

                                        dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                        break;
                                }
                                
                                modulo.setFlagUso("N");
                                modulo.setPpusuarioUsuarioId(null);

                                modulo.setArchivo(archivoBlobFMB);
                                dModulos.updateModulo(modulo);

                                historial.setArchivo(archivoBlobFMB);
                                historial.setFecha(date);
                                historial.setPpModulos(modulo);
                                historial.setComentarioPase(comentarioPase);
                                historial.setComentarioServicios(comentarioServicios);
                                historial.setUsuarioId(usuario.getUsuarioId());
                                historial.setNombre(modulo.getNombreModulo());
                                historial.setNroVersion((long)dHistorial.getLastVersion(modulo.getNombreModulo(), archivoUso.getSistemaId()));
                                historial.setPpSistemas(dSistema.getSistemaBySistemaId(archivoUso.getSistemaId()));

                                dHistorial.insertarHistorial(historial);
                                
                                dArchivoUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                                
                                archivoUso = null;
                                archivoBlobFMB = null;
                                archivoBlobFMX = null;
                                comentarioPase = null;
                            }
                            break;
                    
                        case "REP":
                            if(archivoBlobRDF != null && comentarioPase != null){
                                PpFormularios reporte = dFormulario.getFormularioByFormularioId(archivoUso.getArchivoId());
                                switch(archivoUso.getSistemaId().toString()){
                                    case "1":
                                        archivoPaseForm.setNombreArchivo(reporte.getNombreFormulario());
                                        archivoPaseForm.setArchivo(archivoBlobRDF);

                                        dArchivoPase.insertarArchivoUso(archivoPaseForm);
                                        break;
                                    case "2":
                                        archivoPaseFormTDM.setNombreArchivo(reporte.getNombreFormulario());
                                        archivoPaseFormTDM.setArchivo(archivoBlobRDF);

                                        dArchivoPaseTDM.insertarArchivoUso(archivoPaseFormTDM);
                                        break;
                                }
                                
                                reporte.setFlagUso("N");
                                reporte.setPpusuarioUsuarioId(null);

                                reporte.setArchivo(archivoBlobRDF);
                                dFormulario.updateFormularios(reporte);

                                historial.setArchivo(archivoBlobRDF);
                                historial.setFecha(date);
                                historial.setPpFormularios(reporte);
                                historial.setComentarioPase(comentarioPase);
                                historial.setComentarioServicios(comentarioServicios);
                                historial.setUsuarioId(usuario.getUsuarioId());
                                historial.setNombre(reporte.getNombreFormulario());
                                historial.setNroVersion((long)dHistorial.getLastVersion(reporte.getNombreFormulario(), archivoUso.getSistemaId()));
                                historial.setPpSistemas(dSistema.getSistemaBySistemaId(archivoUso.getSistemaId()));

                                dHistorial.insertarHistorial(historial);
                                
                                dArchivoUso.eliminarArchivoUso(archivoUso.getArchivoUsoId(), "N");
                                
                                archivoUso = null;
                                archivoBlobRDF = null;
                                comentarioPase = null;
                            }
                            break;
                    }
                }
                
                
            }
            
            if(sistemaId.toString().equals("1")){
                dArchivoPase.PasarProduccionServicios();
                dArchivoPase.TruncarTabla();
            }
            if(sistemaId.toString().equals("2")){
                dArchivoPaseTDM.PasarProduccionServicios();
                dArchivoPaseTDM.TruncarTabla();
            }
            
        response.sendRedirect("mensajePaseConfirmacion.jsp");
            
            
        } catch (Exception e) {
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
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
