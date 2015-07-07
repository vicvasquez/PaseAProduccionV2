package com.cis.paseaproduccionweb.hibernate;
// Generated 07/07/2015 11:10:09 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * PpUsuarios generated by hbm2java
 */
public class PpUsuarios  implements java.io.Serializable {


     private BigDecimal usuarioId;
     private String email;
     private String rutaLocal;
     private String clave;
     private String nombreUsuario;
     private String nombre;
     private Date dateModified;
     private String flagEstado;
     private BigDecimal pprolRolId;
     private String credUsuario;
     private String credPassword;

    public PpUsuarios() {
    }

	
    public PpUsuarios(BigDecimal usuarioId, BigDecimal pprolRolId) {
        this.usuarioId = usuarioId;
        this.pprolRolId = pprolRolId;
    }
    public PpUsuarios(BigDecimal usuarioId, String email, String rutaLocal, String clave, String nombreUsuario, String nombre, Date dateModified, String flagEstado, BigDecimal pprolRolId, String credUsuario, String credPassword) {
       this.usuarioId = usuarioId;
       this.email = email;
       this.rutaLocal = rutaLocal;
       this.clave = clave;
       this.nombreUsuario = nombreUsuario;
       this.nombre = nombre;
       this.dateModified = dateModified;
       this.flagEstado = flagEstado;
       this.pprolRolId = pprolRolId;
       this.credUsuario = credUsuario;
       this.credPassword = credPassword;
    }
   
    public BigDecimal getUsuarioId() {
        return this.usuarioId;
    }
    
    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
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
    public BigDecimal getPprolRolId() {
        return this.pprolRolId;
    }
    
    public void setPprolRolId(BigDecimal pprolRolId) {
        this.pprolRolId = pprolRolId;
    }
    public String getCredUsuario() {
        return this.credUsuario;
    }
    
    public void setCredUsuario(String credUsuario) {
        this.credUsuario = credUsuario;
    }
    public String getCredPassword() {
        return this.credPassword;
    }
    
    public void setCredPassword(String credPassword) {
        this.credPassword = credPassword;
    }




}


