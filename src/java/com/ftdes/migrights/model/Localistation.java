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

@Entity
@Table(name = "localistation", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localistation.findAll", query = "SELECT l FROM Localistation l")
    , @NamedQuery(name = "Localistation.findById", query = "SELECT l FROM Localistation l WHERE l.id = :id")
    , @NamedQuery(name = "Localistation.findByObjet", query = "SELECT l FROM Localistation l WHERE l.objet = :objet")
    , @NamedQuery(name = "Localistation.findByCouleur", query = "SELECT l FROM Localistation l WHERE l.couleur = :couleur")
    , @NamedQuery(name = "Localistation.findByTexte", query = "SELECT l FROM Localistation l WHERE l.texte = :texte")
    , @NamedQuery(name = "Localistation.findByX", query = "SELECT l FROM Localistation l WHERE l.x = :x")
    , @NamedQuery(name = "Localistation.findByY", query = "SELECT l FROM Localistation l WHERE l.y = :y")})
public class Localistation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(name = "objet", length = 200)
    private String objet;
    @Size(max = 100)
    @Column(name = "couleur", length = 100)
    private String couleur;
    @Size(max = 1000)
    @Column(name = "texte", length = 1000)
    private String texte;
    @Size(max = 50)
    @Column(name = "x", length = 50)
    private String x;
    @Size(max = 50)
    @Column(name = "y", length = 50)
    private String y;
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    @ManyToOne
    private Admin idAdmin;

    public Localistation() {
    }

    public Localistation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
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
        if (!(object instanceof Localistation)) {
            return false;
        }
        Localistation other = (Localistation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Localistation[ id=" + id + " ]";
    }
    
}
