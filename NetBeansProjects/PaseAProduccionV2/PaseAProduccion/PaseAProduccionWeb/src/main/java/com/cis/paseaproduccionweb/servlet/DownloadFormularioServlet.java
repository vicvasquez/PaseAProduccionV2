package com.cis.paseaproduccionweb.servlet;

import com.cis.paseaproduccionweb.dao.ArchivosUsoDao;
import com.cis.paseaproduccionweb.dao.FormulariosDao;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFormularioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
       
         try
        {
            String formId = request.getParameter("formularioId");
            String tipoDescarga = request.getParameter("tipoDescarga");
            String tipoPadre = request.getParameter("tipoPadre");
            String padreId = request.getParameter("padreId");
            String sisId = request.getParameter("sistemaId");
            BigDecimal sistemaId = new BigDecimal(sisId);
            FormulariosDao dFormulario = new FormulariosDao();
            BigDecimal formularioId = new BigDecimal(formId);
            PpFormularios formulario = dFormulario.getFormularioByFormularioId(formularioId);
            
            if(tipoDescarga.equals("trabajo")){
                
                PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
                
                ArchivosUsoDao dArchivoUso = new ArchivosUsoDao();
                PpArchivosUso archivoUso = new PpArchivosUso();
                
                archivoUso.setArchivoId(formularioId);
                archivoUso.setDescArchivo(formulario.getDescFormulario());
                archivoUso.setNombreArchivo(formulario.getNombreFormulario());
                archivoUso.setSistemaId(sistemaId);
                archivoUso.setTipo("FOR");
                archivoUso.setUsuarioId(usuario.getUsuarioId());

                dArchivoUso.insertarArchivoUso(archivoUso);
                formulario.setFlagUso("S");
                formulario.setPpusuarioUsuarioId(usuario.getUsuarioId());
                
                dFormulario.actualizarEnUso(formulario);                
            }
            
             InputStream is = formulario.getArchivo().getBinaryStream();
             FileOutputStream fos = new FileOutputStream("home/eyomona/FORMULARIO.FMB");
            
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
  
            
            request.setAttribute("tipoPadre", tipoPadre);
            request.setAttribute("tipoPadre", padreId);
            request.setAttribute("tipoPadre", sistemaId);
            
            RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/mostrarFormularios.jsp");
            rDispatcher.forward(request, response);
        }
        catch(Exception ex)
        {
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
