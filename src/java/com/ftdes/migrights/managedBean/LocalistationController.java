package com.ftdes.migrights.managedBean;

import com.ftdes.migrights.model.Localistation;
import com.ftdes.migrights.managedBean.util.JsfUtil;
import com.ftdes.migrights.managedBean.util.JsfUtil.PersistAction;
import com.ftdes.migrights.sessionBean.LocalistationFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.event.map.OverlaySelectEvent;

@Named("localistationController")
@SessionScoped
public class LocalistationController implements Serializable {

    @EJB
    private com.ftdes.migrights.sessionBean.LocalistationFacade ejbFacade;
    private List<Localistation> items = null;
    private Localistation selected;
    
    @Inject
    private LoginController loginctrl;
    
        private MapModel emptyModel;
      
    private String title;
    private String description;
    private String color;
    private Marker markerClient;  
    
       public void onMarkerSelect(OverlaySelectEvent event) {
        markerClient = (Marker) event.getOverlay();
    }
    
    
    public Marker getMarkerClient() {
        return markerClient;
    }

    public void setMarkerClient(Marker markerClient) {
        this.markerClient = markerClient;
    }
    
    
    private double lat;
      
    private double lng;

    public LocalistationController() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Localistation getSelected() {
        return selected;
    }

    public void setSelected(Localistation selected) {
        this.selected = selected;
    }
     @PostConstruct
    public void init() {
         if (items == null) {
            items = getFacade().findAll();
        }
        emptyModel = new DefaultMapModel();
        items.forEach((item) -> {
            emptyModel.addOverlay(new Marker(new LatLng(Double.valueOf(item.getX()), Double.valueOf(item.getY())), item.getObjet(),item.getCouleur(),item.getCouleur()));
        });
    }
      
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    public String getTitle() {
        return title;
    }
  
    public void setTitle(String title) {
        this.title = title;
    }
  
    public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }
      
    public void addMarker() {
        prepareCreate();
        
        Marker marker = new Marker(new LatLng(lat, lng), title);
        selected.setX(String.valueOf(lat));
        selected.setY(String.valueOf(lng));
        selected.setObjet(title);
        selected.setTexte(description);
        selected.setCouleur(color);
        selected.setIdAdmin(loginctrl.getLogedAdmin());
        emptyModel.addOverlay(marker);
        create();
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LocalistationFacade getFacade() {
        return ejbFacade;
    }

    public Localistation prepareCreate() {
        selected = new Localistation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LocalistationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LocalistationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LocalistationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Localistation> getItems() {
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

    public Localistation getLocalistation(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Localistation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Localistation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Localistation.class)
    public static class LocalistationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LocalistationController controller = (LocalistationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "localistationController");
            return controller.getLocalistation(getKey(value));
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
            if (object instanceof Localistation) {
                Localistation o = (Localistation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Localistation.class.getName()});
                return null;
            }
        }

    }

}
