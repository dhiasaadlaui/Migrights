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
@Table(name = "immigrant", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Immigrant.findAll", query = "SELECT i FROM Immigrant i")
    , @NamedQuery(name = "Immigrant.findById", query = "SELECT i FROM Immigrant i WHERE i.id = :id")
    , @NamedQuery(name = "Immigrant.findByNom", query = "SELECT i FROM Immigrant i WHERE i.nom = :nom")
    , @NamedQuery(name = "Immigrant.findByPrenom", query = "SELECT i FROM Immigrant i WHERE i.prenom = :prenom")
    , @NamedQuery(name = "Immigrant.findByEmail", query = "SELECT i FROM Immigrant i WHERE i.email = :email")
    , @NamedQuery(name = "Immigrant.findByNumtel", query = "SELECT i FROM Immigrant i WHERE i.numtel = :numtel")
    , @NamedQuery(name = "Immigrant.findBySexe", query = "SELECT i FROM Immigrant i WHERE i.sexe = :sexe")
    , @NamedQuery(name = "Immigrant.findByDateNaissance", query = "SELECT i FROM Immigrant i WHERE i.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Immigrant.findByNationalite", query = "SELECT i FROM Immigrant i WHERE i.nationalite = :nationalite")
    , @NamedQuery(name = "Immigrant.findByGouvernerat", query = "SELECT i FROM Immigrant i WHERE i.gouvernerat = :gouvernerat")
    , @NamedQuery(name = "Immigrant.findByVille", query = "SELECT i FROM Immigrant i WHERE i.ville = :ville")
    , @NamedQuery(name = "Immigrant.findBySituationSociale", query = "SELECT i FROM Immigrant i WHERE i.situationSociale = :situationSociale")
    , @NamedQuery(name = "Immigrant.findByEtatCivile", query = "SELECT i FROM Immigrant i WHERE i.etatCivile = :etatCivile")
    , @NamedQuery(name = "Immigrant.findByNbrEnfantsEnTn", query = "SELECT i FROM Immigrant i WHERE i.nbrEnfantsEnTn = :nbrEnfantsEnTn")})
public class Immigrant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;
    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
    @Size(max = 50)
    @Column(name = "numtel", length = 50)
    private String numtel;
    @Size(max = 10)
    @Column(name = "sexe", length = 10)
    private String sexe;
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Size(max = 20)
    @Column(name = "nationalite", length = 20)
    private String nationalite;
    @Size(max = 50)
    @Column(name = "gouvernerat", length = 50)
    private String gouvernerat;
    @Size(max = 50)
    @Column(name = "ville", length = 50)
    private String ville;
    @Size(max = 50)
    @Column(name = "situation_sociale", length = 50)
    private String situationSociale;
    @Size(max = 50)
    @Column(name = "etat_civile", length = 50)
    private String etatCivile;
    @Column(name = "nbr_enfants_en_tn")
    private Integer nbrEnfantsEnTn;
    @OneToMany(mappedBy = "idImmigrant")
    private List<Reclamation> reclamationList;

    public Immigrant() {
    }

    public Immigrant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getGouvernerat() {
        return gouvernerat;
    }

    public void setGouvernerat(String gouvernerat) {
        this.gouvernerat = gouvernerat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSituationSociale() {
        return situationSociale;
    }

    public void setSituationSociale(String situationSociale) {
        this.situationSociale = situationSociale;
    }

    public String getEtatCivile() {
        return etatCivile;
    }

    public void setEtatCivile(String etatCivile) {
        this.etatCivile = etatCivile;
    }

    public Integer getNbrEnfantsEnTn() {
        return nbrEnfantsEnTn;
    }

    public void setNbrEnfantsEnTn(Integer nbrEnfantsEnTn) {
        this.nbrEnfantsEnTn = nbrEnfantsEnTn;
    }

    @XmlTransient
    public List<Reclamation> getReclamationList() {
        return reclamationList;
    }

    public void setReclamationList(List<Reclamation> reclamationList) {
        this.reclamationList = reclamationList;
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
        if (!(object instanceof Immigrant)) {
            return false;
        }
        Immigrant other = (Immigrant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Immigrant[ id=" + id + " ]";
    }
    
}
