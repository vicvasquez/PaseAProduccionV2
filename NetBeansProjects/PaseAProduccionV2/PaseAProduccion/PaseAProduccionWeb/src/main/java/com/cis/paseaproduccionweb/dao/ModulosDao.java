/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpModulos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vvasquez
 */
public class ModulosDao {
    
    public List<PpModulos> getModulosBySistemaId(BigDecimal sistemaId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpModulos> lstModulos = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpModulos where ppsistemaSistemaId='"+ sistemaId +"'");
            lstModulos = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstModulos;
        
    }
    
    public PpModulos getModuloByModuloId(BigDecimal moduloId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpModulos modulo = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpModulos where moduloId='"+ moduloId +"'");
            modulo = (PpModulos)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return modulo;
    }
    
    public int updateModulo(PpModulos moduloAct){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.update(moduloAct);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        return result;
    }
    
    public void insertarModulo(PpModulos moduloInsert){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(moduloInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
    }
    
}
