/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.dao.ModulosDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadModuloServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try
        {
            String formId = request.getParameter("moduloId");
            String tipoDescarga = request.getParameter("tipoDescarga");
            String sisId = request.getParameter("sistemaId");
            BigDecimal sistemaId = new BigDecimal(sisId);
            ModulosDao dModulo = new ModulosDao();
            BigDecimal moduloId = new BigDecimal(formId);
            PpModulos modulo = dModulo.getModuloByModuloId(moduloId);
            PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");

            if(tipoDescarga.equals("trabajo")){
                
                
                ArchivosUsoDao dArchivoUso = new ArchivosUsoDao();
                PpArchivosUso archivoUso = new PpArchivosUso();
                
                archivoUso.setArchivoId(moduloId);
                archivoUso.setDescArchivo(modulo.getDescModulo());
                archivoUso.setNombreArchivo(modulo.getNombreModulo());
                archivoUso.setSistemaId(sistemaId);
                archivoUso.setTipo("MOD");
                archivoUso.setFlagNoche("N");
                archivoUso.setUsuarioId(usuario.getUsuarioId());
                
                modulo.setFlagUso("S");
                modulo.setPpusuarioUsuarioId(usuario.getUsuarioId());
                
                dModulo.updateModulo(modulo);   

                dArchivoUso.insertarArchivoUso(archivoUso);
                             
            }
            
            if(modulo.getArchivo() != null){
                
                String fileName = modulo.getNombreModulo();
                String fileType = "application/pdf";
                // Find this file id in database to get file name, and file type

                // You must tell the browser the file type you are going to send
                // for example application/pdf, text/plain, text/html, image/jpg
                response.setContentType(fileType);

                // Make sure to show the download dialog
                response.setHeader("Content-disposition","attachment; filename=" + fileName);

                // Assume file name is retrieved from database
                // For example D:\\file\\test.pdf
                
                
                File              blobFile   = new File(fileName); 
                FileOutputStream  outStream  = new FileOutputStream(blobFile); 
                InputStream       inStream   = modulo.getArchivo().getBinaryStream(); 

                int     length  = -1; 
                int     size1    = 5096;
                byte[]  buffer2  = new byte[size1]; 

                while ((length = inStream.read(buffer2)) != -1) 
                { 
                  outStream.write(buffer2, 0, length); 
                  outStream.flush(); 
                } 

                inStream.close(); 
                outStream.close(); 
                

                // This should send the file to browser
                OutputStream out = response.getOutputStream();
                
                FileInputStream in = new FileInputStream(blobFile);
                byte[] buffer = new byte[5096];
                int length1;
                while ((length1 = in.read(buffer)) > 0){
                   out.write(buffer, 0, length1);
                }
                in.close();
                out.flush();
                
                /*InputStream is = modulo.getArchivo().getBinaryStream();
                FileOutputStream fos = new FileOutputStream(usuario.getRutaLocal()+modulo.getNombreModulo());

                //5 MEGAS
                int bufferSize = 5096;

                byte[] b = new byte[bufferSize];
                int noOfBytes = 0;
                while( (noOfBytes = is.read(b)) != -1 )
                {
                   fos.write(b, 0, noOfBytes);
                }
                fos.close();
                is.close();*/
            }
  
            request.setAttribute("tipoPadre", sistemaId);
            
            getServletContext().getRequestDispatcher("/mostrarEntornos.jsp").forward(request, response);
        }
        catch(Exception ex)
        {
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
