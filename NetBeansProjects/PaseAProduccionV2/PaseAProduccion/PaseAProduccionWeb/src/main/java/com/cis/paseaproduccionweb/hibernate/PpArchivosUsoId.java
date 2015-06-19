package com.cis.paseaproduccionweb.hibernate;
// Generated 19/06/2015 11:30:11 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PpArchivosUsoId generated by hbm2java
 */
public class PpArchivosUsoId  implements java.io.Serializable {


     private BigDecimal archivoId;
     private String tipo;

    public PpArchivosUsoId() {
    }

    public PpArchivosUsoId(BigDecimal archivoId, String tipo) {
       this.archivoId = archivoId;
       this.tipo = tipo;
    }
   
    public BigDecimal getArchivoId() {
        return this.archivoId;
    }
    
    public void setArchivoId(BigDecimal archivoId) {
        this.archivoId = archivoId;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PpArchivosUsoId) ) return false;
		 PpArchivosUsoId castOther = ( PpArchivosUsoId ) other; 
         
		 return ( (this.getArchivoId()==castOther.getArchivoId()) || ( this.getArchivoId()!=null && castOther.getArchivoId()!=null && this.getArchivoId().equals(castOther.getArchivoId()) ) )
 && ( (this.getTipo()==castOther.getTipo()) || ( this.getTipo()!=null && castOther.getTipo()!=null && this.getTipo().equals(castOther.getTipo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getArchivoId() == null ? 0 : this.getArchivoId().hashCode() );
         result = 37 * result + ( getTipo() == null ? 0 : this.getTipo().hashCode() );
         return result;
   }   


}


