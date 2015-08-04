package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtilTDM;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpTempArchaprob;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ArchivoAprobDaoTDM {
    
    public void insertarArchivoUso(PpTempArchaprob archivoAprobInsert){
        
        Session session = HibernateUtilTDM.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(archivoAprobInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
        } finally{
            session.close();
        }
    }
    
    public PpTempArchaprob getArchivoAprobadoByNombre(String nombreArchivo){
        
        Session session = HibernateUtilTDM.getSessionFactory().openSession();
        Transaction tx = null;
        PpTempArchaprob archivoAprobado = null;
       
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpTempArchaprob where nombreArchivo='" + nombreArchivo +"'");
            archivoAprobado = (PpTempArchaprob)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
        } finally{
            session.close();
        }
        
        return archivoAprobado;
    }
    
    public void eliminarArchivoAprobado(PpTempArchaprob archivoAprobadoEliminar){
        
        Session session = HibernateUtilTDM.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.delete(archivoAprobadoEliminar);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
        } finally{
            session.close();
        }
    }
    
}
