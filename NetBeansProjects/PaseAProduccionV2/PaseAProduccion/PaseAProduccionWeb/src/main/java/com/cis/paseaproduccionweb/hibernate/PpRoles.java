package com.cis.paseaproduccionweb.hibernate;
// Generated 23/07/2015 11:54:39 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PpRoles generated by hbm2java
 */
public class PpRoles  implements java.io.Serializable {


     private BigDecimal rolId;
     private String nombreRol;
     private String descRol;
     private Set ppUsuarioses = new HashSet(0);

    public PpRoles() {
    }

	
    public PpRoles(BigDecimal rolId) {
        this.rolId = rolId;
    }
    public PpRoles(BigDecimal rolId, String nombreRol, String descRol, Set ppUsuarioses) {
       this.rolId = rolId;
       this.nombreRol = nombreRol;
       this.descRol = descRol;
       this.ppUsuarioses = ppUsuarioses;
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
    public Set getPpUsuarioses() {
        return this.ppUsuarioses;
    }
    
    public void setPpUsuarioses(Set ppUsuarioses) {
        this.ppUsuarioses = ppUsuarioses;
    }




}


