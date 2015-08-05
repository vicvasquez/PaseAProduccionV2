package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpSistemas;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class SistemasDao {
    
    public PpSistemas getSistemaBySistemaId(BigDecimal sistemaId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpSistemas sistema = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpSistemas where sistemaId='"+ sistemaId +"'");
            sistema = (PpSistemas)query.uniqueResult();
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
        return sistema;
    }
    
}
