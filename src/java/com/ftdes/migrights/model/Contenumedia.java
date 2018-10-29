/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Cacheable(value = false)
@Entity
@Table(name = "contenumedia", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenumedia.findAll", query = "SELECT c FROM Contenumedia c")
    , @NamedQuery(name = "Contenumedia.findById", query = "SELECT c FROM Contenumedia c WHERE c.id = :id")
    , @NamedQuery(name = "Contenumedia.findByType", query = "SELECT c FROM Contenumedia c WHERE c.type = :type")})
public class Contenumedia implements Serializable {

    @Lob
    @Column(name = "contenu")
    private byte[] contenu;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 20)
    @Column(name = "type", length = 20)
    private String type;
    @JoinColumn(name = "id_reclamation", referencedColumnName = "id")
    @ManyToOne
    private Reclamation idReclamation;

    public Contenumedia() {
    }

    public Contenumedia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Reclamation getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(Reclamation idReclamation) {
        this.idReclamation = idReclamation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenumedia)) {
            return false;
        }
        Contenumedia other = (Contenumedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Contenumedia[ id=" + id + " ]";
    }

    public byte[] getContenu() {
        return contenu;
    }

    public void setContenu(byte[] contenu) {
        this.contenu = contenu;
    }
    
}
