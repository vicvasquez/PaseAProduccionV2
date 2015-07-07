package com.cis.paseaproduccionweb.hibernate;
// Generated 07/07/2015 11:10:09 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpRolPermisos generated by hbm2java
 */
public class PpRolPermisos  implements java.io.Serializable {


     private long rolperId;
     private BigDecimal permisoPermisoId;
     private BigDecimal pprolRolId;

    public PpRolPermisos() {
    }

    public PpRolPermisos(long rolperId, BigDecimal permisoPermisoId, BigDecimal pprolRolId) {
       this.rolperId = rolperId;
       this.permisoPermisoId = permisoPermisoId;
       this.pprolRolId = pprolRolId;
    }
   
    public long getRolperId() {
        return this.rolperId;
    }
    
    public void setRolperId(long rolperId) {
        this.rolperId = rolperId;
    }
    public BigDecimal getPermisoPermisoId() {
        return this.permisoPermisoId;
    }
    
    public void setPermisoPermisoId(BigDecimal permisoPermisoId) {
        this.permisoPermisoId = permisoPermisoId;
    }
    public BigDecimal getPprolRolId() {
        return this.pprolRolId;
    }
    
    public void setPprolRolId(BigDecimal pprolRolId) {
        this.pprolRolId = pprolRolId;
    }




}


