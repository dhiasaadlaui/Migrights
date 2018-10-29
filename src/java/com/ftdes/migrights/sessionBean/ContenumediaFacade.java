/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.sessionBean;

import com.ftdes.migrights.model.Contenumedia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO
 */
@Stateless
public class ContenumediaFacade extends AbstractFacade<Contenumedia> {

    @PersistenceContext(unitName = "MigrightsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenumediaFacade() {
        super(Contenumedia.class);
    }
    
}
