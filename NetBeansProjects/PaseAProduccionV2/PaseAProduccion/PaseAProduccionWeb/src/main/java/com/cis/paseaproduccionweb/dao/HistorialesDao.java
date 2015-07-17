/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis.paseaproduccionweb.dao;

import com.cis.paseaproduccionweb.hibernate.HibernateUtil;
import com.cis.paseaproduccionweb.hibernate.PpHistoriales;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public Integer getLastVersion(String nombre){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Object resultado;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            Query q = session.getNamedQuery("SP_ULTIMA_VERSION_HISTORIAL");
            q.setString(0, nombre);
            resultado = q.uniqueResult();
            tx.commit();
            return (Integer)resultado;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        return 0;
    }
    
    public List<PpHistoriales> getHistorial(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<PpHistoriales> lstHistorial = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpHistoriales h order by h.nombre ASC, h.nroVersion ASC");
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
    
    public List<PpHistoriales> FiltrarHistorial(List<PpHistoriales> lstLista, String filtroNombreArchivo, 
            String filtroNombreUsuario, String filtroFechaInicio, String filtroFechaFin){
        
        ArrayList<PpHistoriales> lstHistorial = new ArrayList<>();
            UsuariosDao dUsuaio = new UsuariosDao();
        
        try {
            if(lstLista != null){
                for(PpHistoriales historial : lstLista){
                    String nombreUsuario = dUsuaio.getUsuarioById(historial.getUsuarioId()).getNombre();
                    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaInicio = new Date("01/01/1900");
                    Date fechaFin = new Date("12/31/9999");
                    if(filtroFechaInicio != null){
                        fechaInicio = formatDate.parse(filtroFechaInicio);
                        fechaInicio.setHours(23);
                        fechaInicio.setMinutes(59);
                        fechaInicio.setSeconds(59);
                    }
                    if(filtroFechaFin != null){
                        fechaFin = formatDate.parse(filtroFechaFin);
                        fechaFin.setHours(23);
                        fechaFin.setMinutes(59);
                        fechaFin.setSeconds(59);
                    }
                    if(filtroNombreArchivo == null)
                        filtroNombreArchivo = "";
                    if(filtroNombreUsuario == null)
                        filtroNombreUsuario = "";
                    
                    if(historial.getNombre().contains(filtroNombreArchivo) && nombreUsuario.contains(filtroNombreUsuario) 
                            && (historial.getFecha().after(fechaInicio) || historial.getFecha().equals(fechaInicio)) 
                            && (historial.getFecha().equals(fechaFin) || historial.getFecha().before(fechaFin))) 
                        lstHistorial.add(historial);
                }
                
            }
            
        } catch (Exception e) {
            return null;
            
        }
        return lstHistorial;
        
    }
    
    public PpHistoriales getHistorialByHistorialId(BigDecimal historialId){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        PpHistoriales historial = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from PpHistoriales where historialId = '" + historialId +"'");
            historial = (PpHistoriales)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally{
            session.close();
        }
        
        return historial;
    }
}
