package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.HistorialesDao;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
                 is.close();
            }
            
            response.sendRedirect("Historial");
               
            
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
