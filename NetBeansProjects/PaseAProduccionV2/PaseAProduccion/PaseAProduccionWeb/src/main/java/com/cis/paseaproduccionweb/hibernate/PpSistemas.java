package com.cis.paseaproduccionweb.hibernate;
// Generated 19/06/2015 11:30:11 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpSistemas generated by hbm2java
 */
public class PpSistemas  implements java.io.Serializable {


     private BigDecimal sistemaId;
     private String nombreSistema;

    public PpSistemas() {
    }

	
    public PpSistemas(BigDecimal sistemaId) {
        this.sistemaId = sistemaId;
    }
    public PpSistemas(BigDecimal sistemaId, String nombreSistema) {
       this.sistemaId = sistemaId;
       this.nombreSistema = nombreSistema;
    }
   
    public BigDecimal getSistemaId() {
        return this.sistemaId;
    }
    
    public void setSistemaId(BigDecimal sistemaId) {
        this.sistemaId = sistemaId;
    }
    public String getNombreSistema() {
        return this.nombreSistema;
    }
    
    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }




}


