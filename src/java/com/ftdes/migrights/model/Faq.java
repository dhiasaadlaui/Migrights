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
@Cacheable(value = false)
@Entity
@Table(name = "faq", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faq.findAll", query = "SELECT f FROM Faq f")
    , @NamedQuery(name = "Faq.findById", query = "SELECT f FROM Faq f WHERE f.id = :id")
    , @NamedQuery(name = "Faq.findByQuestion", query = "SELECT f FROM Faq f WHERE f.question = :question")
    , @NamedQuery(name = "Faq.findByReponse", query = "SELECT f FROM Faq f WHERE f.reponse = :reponse")
    , @NamedQuery(name = "Faq.findByNbrVue", query = "SELECT f FROM Faq f WHERE f.nbrVue = :nbrVue")
    , @NamedQuery(name = "Faq.findByNbrJaime", query = "SELECT f FROM Faq f WHERE f.nbrJaime = :nbrJaime")})
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 500)
    @Column(name = "question", length = 500)
    private String question;
    @Size(max = 1000)
    @Column(name = "reponse", length = 1000)
    private String reponse;
    @Column(name = "nbr_vue")
    private Integer nbrVue;
    @Column(name = "nbr_jaime")
    private Integer nbrJaime;
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    @ManyToOne
    private Admin idAdmin;

    public Faq() {
    }

    public Faq(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Integer getNbrVue() {
        return nbrVue;
    }

    public void setNbrVue(Integer nbrVue) {
        this.nbrVue = nbrVue;
    }

    public Integer getNbrJaime() {
        return nbrJaime;
    }

    public void setNbrJaime(Integer nbrJaime) {
        this.nbrJaime = nbrJaime;
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
        if (!(object instanceof Faq)) {
            return false;
        }
        Faq other = (Faq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Faq[ id=" + id + " ]";
    }
    
}
