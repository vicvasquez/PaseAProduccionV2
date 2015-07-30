/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eyomona
 */
public class ErroresDao {
    
    public void insertarError(PpErrores error){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
     
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(error);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
    }
    
}
