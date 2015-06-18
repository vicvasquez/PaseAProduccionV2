package com.cis.paseaproduccionweb.beans;
// Generated 18/06/2015 11:24:36 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpRoles generated by hbm2java
 */
public class PpRoles  implements java.io.Serializable {


     private BigDecimal rolId;
     private String nombreRol;
     private String descRol;

    public PpRoles() {
    }

	
    public PpRoles(BigDecimal rolId) {
        this.rolId = rolId;
    }
    public PpRoles(BigDecimal rolId, String nombreRol, String descRol) {
       this.rolId = rolId;
       this.nombreRol = nombreRol;
       this.descRol = descRol;
    }
   
    public BigDecimal getRolId() {
        return this.rolId;
    }
    
    public void setRolId(BigDecimal rolId) {
        this.rolId = rolId;
    }
    public String getNombreRol() {
        return this.nombreRol;
    }
    
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public String getDescRol() {
        return this.descRol;
    }
    
    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }




}


