package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpFormularios;
import com.cis.paseaproduccionweb.hibernate.PpSubmenus;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.util.ArrayList;
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
            Query query = session.createQuery("from PpFormularios where ppsubmenuSubmenuId='"+ submenuId +"'");
            lstFormularios = query.list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return lstFormularios;
    }
    public List<PpFormularios> getFormulariosByModuloId(BigDecimal moduloId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <PpFormularios> lstFormularios = null;
        ArrayList<PpFormularios> lstFormulariosFinal = new ArrayList<>();
        SubMenusDao dSubMenu = new SubMenusDao();
        List <PpSubmenus> lstSubMenus = dSubMenu.getSubMenuByModuloId(moduloId);
        
        for (PpSubmenus lstSubMenu : lstSubMenus) {
            try {
                if (lstSubMenus.size() > 0) {
                    lstFormularios = getFormulariosBySubmenuId(lstSubMenu.getSubmenuId());
                    for(int i=0; i<lstFormularios.size(); i++){
                        if(lstFormularios!=null && lstFormularios.size()>0){
                            lstFormulariosFinal.add(lstFormularios.get(i));
                        }
                    }
                }
            }catch (Exception e) {
            }
        }
        
        return lstFormulariosFinal;
    }
}
