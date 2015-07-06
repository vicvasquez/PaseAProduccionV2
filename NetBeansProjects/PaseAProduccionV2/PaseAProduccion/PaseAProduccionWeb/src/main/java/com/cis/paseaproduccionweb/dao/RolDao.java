package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpRoles;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RolDao {
    
    public PpRoles getRolByRolId(BigDecimal rolId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpRoles rol = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpRoles where rolId='"+ rolId +"'");
            rol = (PpRoles)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        return rol;
    }
    
}
