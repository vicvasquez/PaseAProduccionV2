package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpSubmenus;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class SubMenusDao {
    
    public List<PpSubmenus> getSubMenuByModuloId(BigDecimal moduloId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpSubmenus> lstSubMenus = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpSubmenus where moduloModuloId='"+ moduloId +"'");
            lstSubMenus = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstSubMenus;
        
    }
    
    public PpSubmenus getSubMenuBySubMenuId(BigDecimal submenuId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpSubmenus submenu = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpSubmenus where submenuId='"+ submenuId +"'");
            submenu = (PpSubmenus)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return submenu;
    }
    
    public void insertarSubMenu(PpSubmenus submenuInsert){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(submenuInsert);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
    }
    
}
