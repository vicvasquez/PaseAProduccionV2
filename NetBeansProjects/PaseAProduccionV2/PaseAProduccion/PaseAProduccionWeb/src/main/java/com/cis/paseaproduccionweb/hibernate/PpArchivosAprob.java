package com.cis.paseaproduccionweb.hibernate;
// Generated 07/07/2015 11:10:09 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.sql.Blob;

/**
 * PpArchivosAprob generated by hbm2java
 */
public class PpArchivosAprob  implements java.io.Serializable {


     private BigDecimal aaprobId;
     private String nombreArchivo;
     private Blob archivo;

    public PpArchivosAprob() {
    }

	
    public PpArchivosAprob(BigDecimal aaprobId) {
        this.aaprobId = aaprobId;
    }
    public PpArchivosAprob(BigDecimal aaprobId, String nombreArchivo, Blob archivo) {
       this.aaprobId = aaprobId;
       this.nombreArchivo = nombreArchivo;
       this.archivo = archivo;
    }
   
    public BigDecimal getAaprobId() {
        return this.aaprobId;
    }
    
    public void setAaprobId(BigDecimal aaprobId) {
        this.aaprobId = aaprobId;
    }
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }
    
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public Blob getArchivo() {
        return this.archivo;
    }
    
    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }




}


