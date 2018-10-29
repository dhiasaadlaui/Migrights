/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO
 */
@Cacheable(value = false)
@Entity
@Table(name = "reclamation", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamation.findAll", query = "SELECT r FROM Reclamation r")
    , @NamedQuery(name = "Reclamation.findById", query = "SELECT r FROM Reclamation r WHERE r.id = :id")
    , @NamedQuery(name = "Reclamation.findByType", query = "SELECT r FROM Reclamation r WHERE r.type = :type")
    , @NamedQuery(name = "Reclamation.findByContenu", query = "SELECT r FROM Reclamation r WHERE r.contenu = :contenu")
    , @NamedQuery(name = "Reclamation.findByEtat", query = "SELECT r FROM Reclamation r WHERE r.etat = :etat")
    , @NamedQuery(name = "Reclamation.findByDateHeure", query = "SELECT r FROM Reclamation r WHERE r.dateHeure = :dateHeure")})
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;
    @Size(max = 1000)
    @Column(name = "contenu", length = 1000)
    private String contenu;
    @Size(max = 50)
    @Column(name = "etat", length = 50)
    private String etat;
    @Column(name = "date_heure")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeure;
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    @ManyToOne
    private Admin idAdmin;
    @JoinColumn(name = "id_immigrant", referencedColumnName = "id")
    @ManyToOne
    private Immigrant idImmigrant;
    @OneToMany(mappedBy = "idReclamation")
    private List<Contenumedia> contenumediaList;

    public Reclamation() {
    }

    public Reclamation(Integer id) {
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Immigrant getIdImmigrant() {
        return idImmigrant;
    }

    public void setIdImmigrant(Immigrant idImmigrant) {
        this.idImmigrant = idImmigrant;
    }

    @XmlTransient
    public List<Contenumedia> getContenumediaList() {
        return contenumediaList;
    }

    public void setContenumediaList(List<Contenumedia> contenumediaList) {
        this.contenumediaList = contenumediaList;
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
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Reclamation[ id=" + id + " ]";
    }
    
}
