/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

/**
 *
 * @author jmoscoso
 */
public class DownloadServlet extends HttpServlet {

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
    
       
         try
        {

            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", "jmoscoso", "intratego5%");
            //si la ruta y archivo del remotefile debe existir
            SmbFile remoteFile = new SmbFile("smb://192.168.185.30/saastest/LI05.RDF",auth); 
              InputStream is = remoteFile.getInputStream();  
              OutputStream os =  new FileOutputStream(new File("/home/jmoscoso/Escritorio/LI05.RDF"));    //new FileOutputStream(new File("/home/jmoscoso/Escritorio/2daversion.txt"));
              
                  int bufferSize = 5096;

                  byte[] b = new byte[bufferSize];
                  int noOfBytes = 0;
                         while( (noOfBytes = is.read(b)) != -1 )
                  {
                      os.write(b, 0, noOfBytes);
                  }
                    os.close();
                    is.close();

            
            

         response.sendRedirect("index.jsp");
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
