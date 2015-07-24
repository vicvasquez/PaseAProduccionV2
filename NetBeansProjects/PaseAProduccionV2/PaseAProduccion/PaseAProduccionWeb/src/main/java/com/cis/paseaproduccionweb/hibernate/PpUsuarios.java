package com.cis.paseaproduccionweb.hibernate;
// Generated 23/07/2015 11:54:39 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PpUsuarios generated by hbm2java
 */
public class PpUsuarios  implements java.io.Serializable {


     private BigDecimal usuarioId;
     private PpRoles ppRoles;
     private String email;
     private String rutaLocal;
     private String clave;
     private String nombreUsuario;
     private String nombre;
     private Date dateModified;
     private String flagEstado;
     private Set ppSentenciasSqls = new HashSet(0);

    public PpUsuarios() {
    }

	
    public PpUsuarios(BigDecimal usuarioId, PpRoles ppRoles) {
        this.usuarioId = usuarioId;
        this.ppRoles = ppRoles;
    }
    public PpUsuarios(BigDecimal usuarioId, PpRoles ppRoles, String email, String rutaLocal, String clave, String nombreUsuario, String nombre, Date dateModified, String flagEstado, Set ppSentenciasSqls) {
       this.usuarioId = usuarioId;
       this.ppRoles = ppRoles;
       this.email = email;
       this.rutaLocal = rutaLocal;
       this.clave = clave;
       this.nombreUsuario = nombreUsuario;
       this.nombre = nombre;
       this.dateModified = dateModified;
       this.flagEstado = flagEstado;
       this.ppSentenciasSqls = ppSentenciasSqls;
    }
   
    public BigDecimal getUsuarioId() {
        return this.usuarioId;
    }
    
    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }
    public PpRoles getPpRoles() {
        return this.ppRoles;
    }
    
    public void setPpRoles(PpRoles ppRoles) {
        this.ppRoles = ppRoles;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRutaLocal() {
        return this.rutaLocal;
    }
    
    public void setRutaLocal(String rutaLocal) {
        this.rutaLocal = rutaLocal;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getDateModified() {
        return this.dateModified;
    }
    
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
    public String getFlagEstado() {
        return this.flagEstado;
    }
    
    public void setFlagEstado(String flagEstado) {
        this.flagEstado = flagEstado;
    }
    public Set getPpSentenciasSqls() {
        return this.ppSentenciasSqls;
    }
    
    public void setPpSentenciasSqls(Set ppSentenciasSqls) {
        this.ppSentenciasSqls = ppSentenciasSqls;
    }




}


