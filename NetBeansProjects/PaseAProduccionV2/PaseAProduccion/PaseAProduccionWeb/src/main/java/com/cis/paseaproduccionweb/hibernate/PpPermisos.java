package com.cis.paseaproduccionweb.hibernate;
// Generated 19/06/2015 11:30:11 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpPermisos generated by hbm2java
 */
public class PpPermisos  implements java.io.Serializable {


     private BigDecimal permisoId;
     private String nombrePermiso;
     private String descPermiso;

    public PpPermisos() {
    }

	
    public PpPermisos(BigDecimal permisoId) {
        this.permisoId = permisoId;
    }
    public PpPermisos(BigDecimal permisoId, String nombrePermiso, String descPermiso) {
       this.permisoId = permisoId;
       this.nombrePermiso = nombrePermiso;
       this.descPermiso = descPermiso;
    }
   
    public BigDecimal getPermisoId() {
        return this.permisoId;
    }
    
    public void setPermisoId(BigDecimal permisoId) {
        this.permisoId = permisoId;
    }
    public String getNombrePermiso() {
        return this.nombrePermiso;
    }
    
    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }
    public String getDescPermiso() {
        return this.descPermiso;
    }
    
    public void setDescPermiso(String descPermiso) {
        this.descPermiso = descPermiso;
    }




}

