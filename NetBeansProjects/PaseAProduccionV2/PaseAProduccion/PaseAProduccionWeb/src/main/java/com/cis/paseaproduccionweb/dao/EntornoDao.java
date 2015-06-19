package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpEntornos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntornoDao {
    
    public List<PpEntornos> getEntornosBySistemaId(BigDecimal sistemaId)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpEntornos> lstEntornos = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpEntornos where ppsistemaSistemaId='"+ sistemaId +"'");
            lstEntornos = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstEntornos;
    }
    
}
