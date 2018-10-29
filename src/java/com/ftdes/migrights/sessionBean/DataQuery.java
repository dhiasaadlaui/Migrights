/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.sessionBean;

import com.ftdes.migrights.model.Admin;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LENOVO
 */
public class DataQuery {
    
    EntityManagerFactory emf;
    EntityManager em;
    public DataQuery(){
        emf = Persistence.createEntityManagerFactory("MigrightsPU");
        em = emf.createEntityManager();
    }
    public Admin loginControl(String email, String password){
        
        try{
           
       
        Admin a = em.createNamedQuery("Login.control", Admin.class).setParameter("username", email).setParameter("password", password).getSingleResult();
        if (a!=null){
            return a;
        }
        return a;
       } catch (Exception e) {
           return null;
       } 
    }
        public void refreshData(){
            emf.getCache().evictAll();
        }
}
