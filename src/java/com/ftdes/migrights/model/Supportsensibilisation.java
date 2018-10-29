/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Entity
@Table(name = "supportsensibilisation", catalog = "migrights", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supportsensibilisation.findAll", query = "SELECT s FROM Supportsensibilisation s")
    , @NamedQuery(name = "Supportsensibilisation.findById", query = "SELECT s FROM Supportsensibilisation s WHERE s.id = :id")
    , @NamedQuery(name = "Supportsensibilisation.findByType", query = "SELECT s FROM Supportsensibilisation s WHERE s.type = :type")
    , @NamedQuery(name = "Supportsensibilisation.findByTitre", query = "SELECT s FROM Supportsensibilisation s WHERE s.titre = :titre")
    , @NamedQuery(name = "Supportsensibilisation.findByOrdre", query = "SELECT s FROM Supportsensibilisation s WHERE s.ordre = :ordre")
    , @NamedQuery(name = "Supportsensibilisation.findByDescription", query = "SELECT s FROM Supportsensibilisation s WHERE s.description = :description")
    , @NamedQuery(name = "Supportsensibilisation.findByVideo", query = "SELECT s FROM Supportsensibilisation s WHERE s.video = :video")
    , @NamedQuery(name = "Supportsensibilisation.findByTexte", query = "SELECT s FROM Supportsensibilisation s WHERE s.texte = :texte")
    , @NamedQuery(name = "Supportsensibilisation.findByNbrVue", query = "SELECT s FROM Supportsensibilisation s WHERE s.nbrVue = :nbrVue")
    , @NamedQuery(name = "Supportsensibilisation.findByNbrJaime", query = "SELECT s FROM Supportsensibilisation s WHERE s.nbrJaime = :nbrJaime")})
public class Supportsensibilisation implements Serializable {

    @Lob
    @Column(name = "contenu_img")
    private byte[] contenuImg;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;
    @Size(max = 100)
    @Column(name = "titre", length = 100)
    private String titre;
    @Column(name = "ordre")
    private Integer ordre;
    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;
    @Size(max = 20)
    @Column(name = "video", length = 20)
    private String video;
    @Size(max = 1000)
    @Column(name = "texte", length = 1000)
    private String texte;
    @Column(name = "nbr_vue")
    private Integer nbrVue;
    @Column(name = "nbr_jaime")
    private Integer nbrJaime;
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    @ManyToOne
    private Admin idAdmin;

    public Supportsensibilisation() {
    }

    public Supportsensibilisation(Integer id) {
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContenuImg() {
        return contenuImg;
    }

    public void setContenuImg(byte[] contenuImg) {
        this.contenuImg = contenuImg;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
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
        if (!(object instanceof Supportsensibilisation)) {
            return false;
        }
        Supportsensibilisation other = (Supportsensibilisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ftdes.migrights.model.Supportsensibilisation[ id=" + id + " ]";
    }



    
}
