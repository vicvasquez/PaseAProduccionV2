/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpArchivosAprob;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eyomona
 */
public class ArchivoAprobDao {
    public void insertarArchivoUso(PpArchivosAprob archivoAprobInsert){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(archivoAprobInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
    }
}
