package com.cis.paseaproduccionweb.hibernate;
// Generated 04/08/2015 01:00:43 PM by Hibernate Tools 4.3.1


import java.sql.Blob;

/**
 * PpReportes generated by hbm2java
 */
public class PpReportes  implements java.io.Serializable {


     private long reporteId;
     private PpUsuarios ppUsuarios;
     private PpFormularios ppFormularios;
     private String flagEstado;
     private String flagUso;
     private String descReporte;
     private String nombreReporte;
     private String flagTipo;
     private Blob archivo;

    public PpReportes() {
    }

	
    public PpReportes(long reporteId, PpUsuarios ppUsuarios, PpFormularios ppFormularios) {
        this.reporteId = reporteId;
        this.ppUsuarios = ppUsuarios;
        this.ppFormularios = ppFormularios;
    }
    public PpReportes(long reporteId, PpUsuarios ppUsuarios, PpFormularios ppFormularios, String flagEstado, String flagUso, String descReporte, String nombreReporte, String flagTipo, Blob archivo) {
       this.reporteId = reporteId;
       this.ppUsuarios = ppUsuarios;
       this.ppFormularios = ppFormularios;
       this.flagEstado = flagEstado;
       this.flagUso = flagUso;
       this.descReporte = descReporte;
       this.nombreReporte = nombreReporte;
       this.flagTipo = flagTipo;
       this.archivo = archivo;
    }
   
    public long getReporteId() {
        return this.reporteId;
    }
    
    public void setReporteId(long reporteId) {
        this.reporteId = reporteId;
    }
    public PpUsuarios getPpUsuarios() {
        return this.ppUsuarios;
    }
    
    public void setPpUsuarios(PpUsuarios ppUsuarios) {
        this.ppUsuarios = ppUsuarios;
    }
    public PpFormularios getPpFormularios() {
        return this.ppFormularios;
    }
    
    public void setPpFormularios(PpFormularios ppFormularios) {
        this.ppFormularios = ppFormularios;
    }
    public String getFlagEstado() {
        return this.flagEstado;
    }
    
    public void setFlagEstado(String flagEstado) {
        this.flagEstado = flagEstado;
    }
    public String getFlagUso() {
        return this.flagUso;
    }
    
    public void setFlagUso(String flagUso) {
        this.flagUso = flagUso;
    }
    public String getDescReporte() {
        return this.descReporte;
    }
    
    public void setDescReporte(String descReporte) {
        this.descReporte = descReporte;
    }
    public String getNombreReporte() {
        return this.nombreReporte;
    }
    
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
    public String getFlagTipo() {
        return this.flagTipo;
    }
    
    public void setFlagTipo(String flagTipo) {
        this.flagTipo = flagTipo;
    }
    public Blob getArchivo() {
        return this.archivo;
    }
    
    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }




}


