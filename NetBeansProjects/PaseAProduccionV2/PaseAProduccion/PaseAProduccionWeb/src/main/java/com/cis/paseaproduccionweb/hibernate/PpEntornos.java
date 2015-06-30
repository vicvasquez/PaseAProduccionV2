package com.cis.paseaproduccionweb.hibernate;
// Generated 30/06/2015 11:45:38 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpEntornos generated by hbm2java
 */
public class PpEntornos  implements java.io.Serializable {


     private BigDecimal entornoId;
     private String nombreEntorno;
     private String rutaEntorno;
     private BigDecimal ppsistemaSistemaId;

    public PpEntornos() {
    }

	
    public PpEntornos(BigDecimal entornoId, BigDecimal ppsistemaSistemaId) {
        this.entornoId = entornoId;
        this.ppsistemaSistemaId = ppsistemaSistemaId;
    }
    public PpEntornos(BigDecimal entornoId, String nombreEntorno, String rutaEntorno, BigDecimal ppsistemaSistemaId) {
       this.entornoId = entornoId;
       this.nombreEntorno = nombreEntorno;
       this.rutaEntorno = rutaEntorno;
       this.ppsistemaSistemaId = ppsistemaSistemaId;
    }
   
    public BigDecimal getEntornoId() {
        return this.entornoId;
    }
    
    public void setEntornoId(BigDecimal entornoId) {
        this.entornoId = entornoId;
    }
    public String getNombreEntorno() {
        return this.nombreEntorno;
    }
    
    public void setNombreEntorno(String nombreEntorno) {
        this.nombreEntorno = nombreEntorno;
    }
    public String getRutaEntorno() {
        return this.rutaEntorno;
    }
    
    public void setRutaEntorno(String rutaEntorno) {
        this.rutaEntorno = rutaEntorno;
    }
    public BigDecimal getPpsistemaSistemaId() {
        return this.ppsistemaSistemaId;
    }
    
    public void setPpsistemaSistemaId(BigDecimal ppsistemaSistemaId) {
        this.ppsistemaSistemaId = ppsistemaSistemaId;
    }




}


