/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eyomona
 */
public class HistorialesDao {
    public void insertarHistorial(PpHistoriales historialInsert){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(historialInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
    }
    
    public Integer getLastVersion(String tipoArchivo,BigDecimal idArchivo ){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query q = session.getNamedQuery("SP_ULTIMO_HISTORIAL");
            q.setString(0, tipoArchivo);
            q.setBigDecimal(1, idArchivo);
            Integer resultado = (Integer) q.uniqueResult();
            tx.commit();
            return resultado;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            return 0;
        } finally{
            session.close();
        }
    }
    
    public List<PpHistoriales> getHistorial(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<PpHistoriales> lstHistorial = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpHistoriales h order by h.nroVersion DESC");
            lstHistorial = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstHistorial;
    }
}
