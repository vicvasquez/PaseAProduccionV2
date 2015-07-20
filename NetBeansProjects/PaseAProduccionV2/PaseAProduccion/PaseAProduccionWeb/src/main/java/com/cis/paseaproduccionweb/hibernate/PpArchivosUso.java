package com.cis.paseaproduccionweb.hibernate;
// Generated 20/07/2015 11:30:52 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpArchivosUso generated by hbm2java
 */
public class PpArchivosUso  implements java.io.Serializable {


     private BigDecimal archivoUsoId;
     private BigDecimal archivoId;
     private BigDecimal sistemaId;
     private BigDecimal usuarioId;
     private String nombreArchivo;
     private String descArchivo;
     private String tipo;
     private String flagNoche;

    public PpArchivosUso() {
    }

	
    public PpArchivosUso(BigDecimal archivoUsoId, BigDecimal archivoId, BigDecimal sistemaId, BigDecimal usuarioId, String tipo) {
        this.archivoUsoId = archivoUsoId;
        this.archivoId = archivoId;
        this.sistemaId = sistemaId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
    }
    public PpArchivosUso(BigDecimal archivoUsoId, BigDecimal archivoId, BigDecimal sistemaId, BigDecimal usuarioId, String nombreArchivo, String descArchivo, String tipo, String flagNoche) {
       this.archivoUsoId = archivoUsoId;
       this.archivoId = archivoId;
       this.sistemaId = sistemaId;
       this.usuarioId = usuarioId;
       this.nombreArchivo = nombreArchivo;
       this.descArchivo = descArchivo;
       this.tipo = tipo;
       this.flagNoche = flagNoche;
    }
   
    public BigDecimal getArchivoUsoId() {
        return this.archivoUsoId;
    }
    
    public void setArchivoUsoId(BigDecimal archivoUsoId) {
        this.archivoUsoId = archivoUsoId;
    }
    public BigDecimal getArchivoId() {
        return this.archivoId;
    }
    
    public void setArchivoId(BigDecimal archivoId) {
        this.archivoId = archivoId;
    }
    public BigDecimal getSistemaId() {
        return this.sistemaId;
    }
    
    public void setSistemaId(BigDecimal sistemaId) {
        this.sistemaId = sistemaId;
    }
    public BigDecimal getUsuarioId() {
        return this.usuarioId;
    }
    
    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }
    
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getDescArchivo() {
        return this.descArchivo;
    }
    
    public void setDescArchivo(String descArchivo) {
        this.descArchivo = descArchivo;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getFlagNoche() {
        return this.flagNoche;
    }
    
    public void setFlagNoche(String flagNoche) {
        this.flagNoche = flagNoche;
    }




}


