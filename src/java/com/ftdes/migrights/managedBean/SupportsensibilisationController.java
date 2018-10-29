package com.ftdes.migrights.managedBean;

import com.ftdes.migrights.model.Supportsensibilisation;
import com.ftdes.migrights.managedBean.util.JsfUtil;
import com.ftdes.migrights.managedBean.util.JsfUtil.PersistAction;
import com.ftdes.migrights.sessionBean.SupportsensibilisationFacade;
import com.google.common.io.ByteStreams;
import java.io.IOException;

import java.io.Serializable;
import java.util.Base64;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("supportsensibilisationController")
@SessionScoped
public class SupportsensibilisationController implements Serializable {

    @EJB
    private com.ftdes.migrights.sessionBean.SupportsensibilisationFacade ejbFacade;
    private List<Supportsensibilisation> items = null;
    private Supportsensibilisation selected;

    @Inject
    private LoginController loginctrl;

    private UploadedFile file;

    public void fileUploadListener(FileUploadEvent e) throws IOException {

        this.file = e.getFile();
        selected.setContenuImg(ByteStreams.toByteArray(this.file.getInputstream()));

        // Print out the information of the file
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize() + " selected size");
        // System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
        System.out.println("imaaage " + selected.getContenuImg());

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String imageDisplay(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    public SupportsensibilisationController() {
    }

    public Supportsensibilisation getSelected() {
        return selected;
    }

    public void setSelected(Supportsensibilisation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SupportsensibilisationFacade getFacade() {
        return ejbFacade;
    }

    public Supportsensibilisation prepareCreate() {
        selected = new Supportsensibilisation();
        initializeEmbeddableKey();
        return selected;
    }

    public void createImage() {

        selected.setType("image");
        selected.setNbrJaime(0);
        selected.setNbrVue(0);
        selected.setIdAdmin(loginctrl.getLogedAdmin());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SupportsensibilisationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createText() {
        selected.setType("texte");
        selected.setNbrJaime(0);
        selected.setNbrVue(0);
        selected.setIdAdmin(loginctrl.getLogedAdmin());

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SupportsensibilisationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createVideo() {
        selected.setType("video");
        selected.setNbrJaime(0);
        selected.setNbrVue(0);
        selected.setIdAdmin(loginctrl.getLogedAdmin());

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SupportsensibilisationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SupportsensibilisationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SupportsensibilisationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Supportsensibilisation> getItems() {
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

    public Supportsensibilisation getSupportsensibilisation(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Supportsensibilisation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Supportsensibilisation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Supportsensibilisation.class)
    public static class SupportsensibilisationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SupportsensibilisationController controller = (SupportsensibilisationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "supportsensibilisationController");
            return controller.getSupportsensibilisation(getKey(value));
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
            if (object instanceof Supportsensibilisation) {
                Supportsensibilisation o = (Supportsensibilisation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Supportsensibilisation.class.getName()});
                return null;
            }
        }

    }

}
