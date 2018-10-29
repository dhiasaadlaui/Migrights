package com.ftdes.migrights.managedBean;

import com.ftdes.migrights.model.Reclamation;
import com.ftdes.migrights.managedBean.util.JsfUtil;
import com.ftdes.migrights.managedBean.util.JsfUtil.PersistAction;
import com.ftdes.migrights.sessionBean.ReclamationFacade;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
import javax.inject.Inject;

@Named("reclamationController")
@SessionScoped
public class ReclamationController implements Serializable {

    @EJB
    private com.ftdes.migrights.sessionBean.ReclamationFacade ejbFacade;
    private List<Reclamation> items = null;
    private Reclamation selected;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    @Inject
    private LoginController loginctrl;
    
    //vars for stats

    int nbrstat;
    public int count_filter_sexe_type(String typerec,String sexerec){
              
            items = getFacade().findAll();
        
        nbrstat = 0;
        items.forEach((item) -> {
            if (item.getType().equals(typerec)&&item.getIdImmigrant().getSexe().equals(sexerec)&&item.getEtat().equals("Traité")) {
                nbrstat++;
            }
        });
        int nbr=nbrstat;
        return nbr;
    }
    public int count_filter_age(int x,int y){
              
            items = getFacade().findAll();
            LocalDate today = LocalDate.now();
          
        nbrstat = 0;
        items.forEach((item) -> {
            if(item.getIdImmigrant()!=null){
              if(item.getIdImmigrant().getDateNaissance()!=null){
                  
               
            
            int age = Period.between(item.getIdImmigrant().getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today).getYears();
            if (age>=x && age<=y &&item.getEtat().equals("Traité")) {
                nbrstat++;
            }}} 
        });
        int nbr=nbrstat;
        return nbr;
    }

    public ReclamationController() {
    }

    public Reclamation getSelected() {
        return selected;
    }

    public void setSelected(Reclamation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReclamationFacade getFacade() {
        return ejbFacade;
    }

    public Reclamation prepareCreate() {
        selected = new Reclamation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setDateHeure(new Date());
        selected.setContenu("<b>NB:</b><i> Cette réclamation a était ajouté manuelement par l'admin <b>"+loginctrl.getLogedAdmin().getPrenom()+" "+loginctrl.getLogedAdmin().getNom()+"</b></i><br/>**********<br/>"+selected.getContenu());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReclamationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setContenu(selected.getContenu() + "<br/>-------------------------<br/><i> L'admin: "+loginctrl.getLogedAdmin().getPrenom()+" "+loginctrl.getLogedAdmin().getNom()+" a changé l'état à </i><b>"+selected.getEtat()+
        "</b><br/><b>Note de mise à jour :</b> <br/>"+ getNote());
        selected.setIdAdmin(loginctrl.getLogedAdmin());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReclamationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReclamationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reclamation> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Reclamation getReclamation(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Reclamation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reclamation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reclamation.class)
    public static class ReclamationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReclamationController controller = (ReclamationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reclamationController");
            return controller.getReclamation(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reclamation) {
                Reclamation o = (Reclamation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reclamation.class.getName()});
                return null;
            }
        }

    }

}
