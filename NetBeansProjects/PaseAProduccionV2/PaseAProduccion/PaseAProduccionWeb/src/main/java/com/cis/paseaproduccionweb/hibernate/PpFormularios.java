package com.cis.paseaproduccionweb.hibernate;
// Generated 07/07/2015 02:14:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * PpFormularios generated by hbm2java
 */
public class PpFormularios  implements java.io.Serializable {


     private BigDecimal formularioId;
     private String flagEstado;
     private String flagUso;
     private String descFormulario;
     private String nombreFormulario;
     private BigDecimal ppsubmenuSubmenuId;
     private BigDecimal ppusuarioUsuarioId;
     private String flagTipo;
     private Blob archivo;
     private Set ppHistorialeses = new HashSet(0);

    public PpFormularios() {
    }

	
    public PpFormularios(BigDecimal formularioId, BigDecimal ppsubmenuSubmenuId) {
        this.formularioId = formularioId;
        this.ppsubmenuSubmenuId = ppsubmenuSubmenuId;
    }
    public PpFormularios(BigDecimal formularioId, String flagEstado, String flagUso, String descFormulario, String nombreFormulario, BigDecimal ppsubmenuSubmenuId, BigDecimal ppusuarioUsuarioId, String flagTipo, Blob archivo, Set ppHistorialeses) {
       this.formularioId = formularioId;
       this.flagEstado = flagEstado;
       this.flagUso = flagUso;
       this.descFormulario = descFormulario;
       this.nombreFormulario = nombreFormulario;
       this.ppsubmenuSubmenuId = ppsubmenuSubmenuId;
       this.ppusuarioUsuarioId = ppusuarioUsuarioId;
       this.flagTipo = flagTipo;
       this.archivo = archivo;
       this.ppHistorialeses = ppHistorialeses;
    }
   
    public BigDecimal getFormularioId() {
        return this.formularioId;
    }
    
    public void setFormularioId(BigDecimal formularioId) {
        this.formularioId = formularioId;
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
    public String getDescFormulario() {
        return this.descFormulario;
    }
    
    public void setDescFormulario(String descFormulario) {
        this.descFormulario = descFormulario;
    }
    public String getNombreFormulario() {
        return this.nombreFormulario;
    }
    
    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }
    public BigDecimal getPpsubmenuSubmenuId() {
        return this.ppsubmenuSubmenuId;
    }
    
    public void setPpsubmenuSubmenuId(BigDecimal ppsubmenuSubmenuId) {
        this.ppsubmenuSubmenuId = ppsubmenuSubmenuId;
    }
    public BigDecimal getPpusuarioUsuarioId() {
        return this.ppusuarioUsuarioId;
    }
    
    public void setPpusuarioUsuarioId(BigDecimal ppusuarioUsuarioId) {
        this.ppusuarioUsuarioId = ppusuarioUsuarioId;
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
    public Set getPpHistorialeses() {
        return this.ppHistorialeses;
    }
    
    public void setPpHistorialeses(Set ppHistorialeses) {
        this.ppHistorialeses = ppHistorialeses;
    }




}


