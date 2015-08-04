package com.cis.paseaproduccionweb.hibernate;
// Generated 04/08/2015 01:00:43 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpSubmenus generated by hbm2java
 */
public class PpSubmenus  implements java.io.Serializable {


     private BigDecimal submenuId;
     private String descSubmenu;
     private String flagEstado;
     private String nombreSubmenu;
     private BigDecimal moduloModuloId;

    public PpSubmenus() {
    }

	
    public PpSubmenus(BigDecimal submenuId, BigDecimal moduloModuloId) {
        this.submenuId = submenuId;
        this.moduloModuloId = moduloModuloId;
    }
    public PpSubmenus(BigDecimal submenuId, String descSubmenu, String flagEstado, String nombreSubmenu, BigDecimal moduloModuloId) {
       this.submenuId = submenuId;
       this.descSubmenu = descSubmenu;
       this.flagEstado = flagEstado;
       this.nombreSubmenu = nombreSubmenu;
       this.moduloModuloId = moduloModuloId;
    }
   
    public BigDecimal getSubmenuId() {
        return this.submenuId;
    }
    
    public void setSubmenuId(BigDecimal submenuId) {
        this.submenuId = submenuId;
    }
    public String getDescSubmenu() {
        return this.descSubmenu;
    }
    
    public void setDescSubmenu(String descSubmenu) {
        this.descSubmenu = descSubmenu;
    }
    public String getFlagEstado() {
        return this.flagEstado;
    }
    
    public void setFlagEstado(String flagEstado) {
        this.flagEstado = flagEstado;
    }
    public String getNombreSubmenu() {
        return this.nombreSubmenu;
    }
    
    public void setNombreSubmenu(String nombreSubmenu) {
        this.nombreSubmenu = nombreSubmenu;
    }
    public BigDecimal getModuloModuloId() {
        return this.moduloModuloId;
    }
    
    public void setModuloModuloId(BigDecimal moduloModuloId) {
        this.moduloModuloId = moduloModuloId;
    }




}


