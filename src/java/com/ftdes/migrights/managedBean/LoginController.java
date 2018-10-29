/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftdes.migrights.managedBean;

/**
 *
 * @author LENOVO
 */
import com.ftdes.migrights.model.Admin;
import com.ftdes.migrights.sessionBean.DataQuery;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("login")
@javax.enterprise.context.SessionScoped
public class LoginController implements Serializable {

    private String email;
    private String password;
    private DataQuery query = new DataQuery();
    private Admin logedAdmin = new Admin();
    private Boolean isLoged=false;
    private Boolean webmasterIsLoged=false;
        
    private String webmasterUserName;
    private String WebmasterPassword;

    public String getWebmasterUserName() {
        return webmasterUserName;
    }

    public void setWebmasterUserName(String webmasterUserName) {
        this.webmasterUserName = webmasterUserName;
    }

    public String getWebmasterPassword() {
        return WebmasterPassword;
    }

    public void setWebmasterPassword(String WebmasterPassword) {
        this.WebmasterPassword = WebmasterPassword;
    }
    
    
    public Boolean getWebmasterIsLoged() {
        return webmasterIsLoged;
    }

    public void setWebmasterIsLoged(Boolean webmasterIsLoged) {
        this.webmasterIsLoged = webmasterIsLoged;
    }
    
    
    public Boolean getIsLoged() {
        return isLoged;
    }

    public void setIsLoged(Boolean isLoged) {
        this.isLoged = isLoged;
    }
    
    
    
    public Admin getLogedAdmin() {
        return logedAdmin;
    }

    public void setLogedAdmin(Admin logedAdmin) {
        this.logedAdmin = logedAdmin;
    }

    public String onload() {
        return "index.xhtml";
    }
    public String logout(){
            setIsLoged(false);
            setLogedAdmin(null);
        
        return "/public/home.xhtml?faces-redirect=true";
    }
    
    public String loginControl() {
        if (query.loginControl(email, password) != null) {
            setIsLoged(true);
            logedAdmin = query.loginControl(email, password);
            System.out.println("logincontroller bolean" +getIsLoged());
            return "article/List.xhtml?faces-redirect=true";
            
        }
        
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Invalid username or password !! "));
        return "";

    }
      public String loginControlwebmaster() {
        if (webmasterUserName.equals("webmaster") && WebmasterPassword.equals("0000")) {
            setWebmasterIsLoged(true);
            
            return "admin/List.xhtml?faces-redirect=true";
            
        }
        
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Invalid username or password !! "));
        return "";

    }
         public String logoutWebMaster(){
             setWebmasterIsLoged(false);      
        return "/public/home.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
