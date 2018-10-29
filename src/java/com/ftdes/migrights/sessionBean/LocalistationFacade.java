/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.sessionBean;

import com.ftdes.migrights.model.Localistation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO
 */
@Stateless
public class LocalistationFacade extends AbstractFacade<Localistation> {

    @PersistenceContext(unitName = "MigrightsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalistationFacade() {
        super(Localistation.class);
    }
    
}
