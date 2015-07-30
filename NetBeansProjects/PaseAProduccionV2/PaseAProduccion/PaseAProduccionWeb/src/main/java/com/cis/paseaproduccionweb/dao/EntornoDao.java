package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpEntornos;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import java.math.BigDecimal;
import java.util.Date;
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
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
        } finally{
            session.close();
        }
        
        return lstEntornos;
    }
    
}
