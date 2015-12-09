package com.cis.paseaproduccionweb.hibernate;
// Generated 03/08/2015 12:00:45 PM by Hibernate Tools 4.3.1


import java.sql.Blob;

/**
 * PpTempArchpase generated by hbm2java
 */
public class PpTempArchpase  implements java.io.Serializable {


     private long tempapaseId;
     private String nombreArchivo;
     private Blob archivo;

    public PpTempArchpase() {
    }

	
    public PpTempArchpase(long tempapaseId) {
        this.tempapaseId = tempapaseId;
    }
    public PpTempArchpase(long tempapaseId, String nombreArchivo, Blob archivo) {
       this.tempapaseId = tempapaseId;
       this.nombreArchivo = nombreArchivo;
       this.archivo = archivo;
    }
   
    public long getTempapaseId() {
        return this.tempapaseId;
    }
    
    public void setTempapaseId(long tempapaseId) {
        this.tempapaseId = tempapaseId;
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

