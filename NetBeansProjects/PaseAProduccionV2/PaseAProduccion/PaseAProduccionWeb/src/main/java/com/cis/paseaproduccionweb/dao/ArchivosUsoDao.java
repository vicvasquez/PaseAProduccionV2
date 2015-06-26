package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpArchivosUso;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ArchivosUsoDao {
    
    public List<PpArchivosUso> getArchivosUso(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpArchivosUso> lstArchivos = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpArchivosUso");
            lstArchivos = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstArchivos;
    }
    
    public List<PpArchivosUso> getArchivosUsoPorUsuario(BigDecimal usuarioId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpArchivosUso> lstArchivos = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpArchivosUso where usuarioId='"+ usuarioId +"'");
            lstArchivos = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstArchivos;
    }
    
    public PpArchivosUso getArchivosUsoByArchivoIdYTipo(BigDecimal archivoId, String tipo){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpArchivosUso archivoUso = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpArchivosUso where archivoId='"+ archivoId +"' and tipo='"+ tipo +"'");
            archivoUso = (PpArchivosUso)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        return archivoUso;
    }
    
    public void eliminarArchivoUso(PpArchivosUso archivosUsoDelete){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.delete(archivosUsoDelete);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
    }
    
    public void insertarArchivoUso(PpArchivosUso archivoUsoInsert){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(archivoUsoInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
    }
    
}
