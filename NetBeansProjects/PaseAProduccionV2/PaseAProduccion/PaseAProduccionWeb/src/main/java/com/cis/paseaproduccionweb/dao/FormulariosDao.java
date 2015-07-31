package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpErrores;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpSubmenus;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class FormulariosDao {
    
    public List<PpFormularios> getFormulariosBySubmenuId(BigDecimal submenuId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpFormularios> lstFormularios = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpFormularios f where f.ppsubmenuSubmenuId='"+ submenuId +"' order by f.nombreFormulario");
            lstFormularios = query.list();
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
        
        return lstFormularios;
    }
    public List<PpFormularios> getFormulariosByModuloId(BigDecimal moduloId){
        
        List <PpFormularios> lstFormularios = null;
        ArrayList<PpFormularios> lstFormulariosFinal = new ArrayList<>();
        SubMenusDao dSubMenu = new SubMenusDao();
        List <PpSubmenus> lstSubMenus = dSubMenu.getSubMenuByModuloId(moduloId);
        
        for (PpSubmenus submenu : lstSubMenus) {
            try {
                if (lstSubMenus.size() > 0) {
                    lstFormularios = getFormulariosBySubmenuId(submenu.getSubmenuId());
                    for(int i=0; i<lstFormularios.size(); i++){
                        if(lstFormularios!=null && lstFormularios.size()>0){
                            lstFormulariosFinal.add(lstFormularios.get(i));
                        }
                    }
                }
            }catch (Exception e) {
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
            }
        }
        
        return lstFormulariosFinal;
    }
    public List<PpFormularios> filtrarFormularios(List<PpFormularios> lstLista, String filtro, String uso){
        
        ArrayList<PpFormularios> lstFormularios = new ArrayList<>();
        
        try {
            if(lstLista != null)
            {
                if(uso.equals("Todos")){
                    for (PpFormularios formulario : lstLista) {
                        if(formulario.getNombreFormulario().toUpperCase().contains(filtro.toUpperCase()))
                            lstFormularios.add(formulario);
                    }
                }
                else{
                    if(uso.equals("Disponibles")){
                        for (PpFormularios formulario : lstLista) {
                        if(formulario.getNombreFormulario().toUpperCase().contains(filtro.toUpperCase()) &&
                                formulario.getFlagUso().equals("N"))
                            lstFormularios.add(formulario);
                        }
                    }
                    else if(uso.equals("Ocupados")){
                        for (PpFormularios formulario : lstLista) {
                        if(formulario.getNombreFormulario().toUpperCase().contains(filtro.toUpperCase()) &&
                                formulario.getFlagUso().equals("S"))
                            lstFormularios.add(formulario);
                        }
                    }
                }
            }   
            
        } catch (Exception e) {
            ErroresDao dError = new ErroresDao();
            PpErrores error = new PpErrores();
            Date date = new Date();
            
            error.setStacktrace(e.toString());
            error.setFecha(date);
            dError.insertarError(error);
        }
        
        return lstFormularios;
    }
    
    public PpFormularios getFormularioByFormularioId(BigDecimal formularioId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpFormularios formulario = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpFormularios where formularioId='"+ formularioId +"'");
            formulario = (PpFormularios)query.uniqueResult();
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
        
        return formulario;
    }
    
    public int updateFormularios(PpFormularios formularioAct){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.update(formularioAct);
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
        return result;
    }
    
    public void insertarFormulario(PpFormularios formularioIns){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(formularioIns);
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
