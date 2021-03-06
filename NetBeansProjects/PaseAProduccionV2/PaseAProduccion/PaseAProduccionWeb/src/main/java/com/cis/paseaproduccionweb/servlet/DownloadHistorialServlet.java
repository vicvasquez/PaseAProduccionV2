package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ErroresDao;
import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadHistorialServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            String histId = request.getParameter("historialId");
            PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
            
            BigDecimal historialId = new BigDecimal(histId);
            
            HistorialesDao dHistorial = new HistorialesDao();
            
            PpHistoriales historial = dHistorial.getHistorialByHistorialId(historialId);
            
            if(historial != null && historial.getArchivo() != null){
                
                String nombre = historial.getNombre();
                String fileName = nombre;
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
                InputStream       inStream   = historial.getArchivo().getBinaryStream(); 

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
                
                /*
                InputStream is = historial.getArchivo().getBinaryStream();
                FileOutputStream fos = new FileOutputStream(usuario.getRutaLocal()+historial.getNombre()+"-v"+historial.getNroVersion().toString());

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
            
            response.sendRedirect("Historial");
               
            
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
