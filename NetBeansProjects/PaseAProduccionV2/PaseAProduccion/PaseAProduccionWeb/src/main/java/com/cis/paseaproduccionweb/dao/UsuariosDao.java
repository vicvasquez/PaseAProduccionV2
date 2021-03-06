package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpUsuarios;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UsuariosDao {
    
    public PpUsuarios getUsuarioByUsername(String username)
    {
        Session session = null; 
        Transaction tx = null;
        PpUsuarios usuario = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpUsuarios where nombreUsuario='"+username+"'");
            usuario = (PpUsuarios)query.uniqueResult();
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
        
        return usuario;
    }
    
    public PpUsuarios getUsuarioById(BigDecimal usuarioId)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpUsuarios usuario = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpUsuarios where usuarioId='"+usuarioId+"'");
            usuario = (PpUsuarios)query.uniqueResult();
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
        
        return usuario;
    }
    
    public void updateUsuario(PpUsuarios usuarioAct){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.update(usuarioAct);
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
    }
    
}
