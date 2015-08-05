package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpSubmenus;
import java.math.BigDecimal;
import java.util.Date;
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
            Query query = session.createQuery("from PpSubmenus sm where sm.moduloModuloId='"+ moduloId +"' order by sm.nombreSubmenu");
            lstSubMenus = query.list();
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
        
        return lstSubMenus;
        
    }
    
    public PpSubmenus getSubMenuBySubMenuId(BigDecimal submenuId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpSubmenus submenu = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpSubmenus sm where sm.submenuId='"+ submenuId +"' order by sm.nombreSubmenu");
            submenu = (PpSubmenus)query.uniqueResult();
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
        
        return submenu;
    }
    
    public void insertarSubMenu(PpSubmenus submenuIns){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(submenuIns);
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
        }    finally{
            session.close();
        }
    }
    
}
