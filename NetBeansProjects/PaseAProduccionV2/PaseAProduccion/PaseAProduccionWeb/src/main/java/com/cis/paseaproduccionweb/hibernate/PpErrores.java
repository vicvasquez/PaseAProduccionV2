package com.cis.paseaproduccionweb.hibernate;
// Generated 23/07/2015 11:54:39 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * PpErrores generated by hbm2java
 */
public class PpErrores  implements java.io.Serializable {


     private long idError;
     private String stacktrace;
     private Date fecha;

    public PpErrores() {
    }

	
    public PpErrores(long idError) {
        this.idError = idError;
    }
    public PpErrores(long idError, String stacktrace, Date fecha) {
       this.idError = idError;
       this.stacktrace = stacktrace;
       this.fecha = fecha;
    }
   
    public long getIdError() {
        return this.idError;
    }
    
    public void setIdError(long idError) {
        this.idError = idError;
    }
    public String getStacktrace() {
        return this.stacktrace;
    }
    
    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}

