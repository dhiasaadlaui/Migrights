package com.ftdes.migrights.managedBean;

import com.ftdes.migrights.model.Article;
import com.ftdes.migrights.managedBean.util.JsfUtil;
import com.ftdes.migrights.managedBean.util.JsfUtil.PersistAction;
import com.ftdes.migrights.sessionBean.ArticleFacade;
import com.ftdes.migrights.sessionBean.DataQuery;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;

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
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;


@Named("articleController")
@SessionScoped
public class ArticleController implements Serializable {

    @EJB
    private com.ftdes.migrights.sessionBean.ArticleFacade ejbFacade;
    private List<Article> items = null;
    private Article selected;
    private DataQuery query = new DataQuery();
    @Inject
    private LoginController loginctrl;

    private UploadedFile file;
    private List<Article> itemscaro = new ArrayList<Article>();

    public List<Article> getItemscaro() {
        getItems();
        if (items.size()<=5){
            itemscaro=items;
            return itemscaro;
        }else{
            itemscaro.add(items.get(0));
            itemscaro.add(items.get(1));
            itemscaro.add(items.get(2));
            itemscaro.add(items.get(3));
            itemscaro.add(items.get(4));
            return itemscaro;
        }
        
    }

    public void setItemscaro(List<Article> itemscaro) {
        this.itemscaro = itemscaro;
    }
 
    
    public String imageDisplay (byte[] b){
        return Base64.getEncoder().encodeToString(b);
    }

    public void fileUploadListener(FileUploadEvent e) throws IOException {

        this.file = e.getFile();
        selected.setImage(ByteStreams.toByteArray(this.file.getInputstream()));
         
        // Print out the information of the file
        System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize()+" selected size");
        // System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
        System.out.println("imaaage " +selected.getImage());
      
      
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        System.out.println("taille du fichier" + file.toString());
    }

    public ArticleController() {
    }

    public Article getSelected() {
        return selected;
    }

    public void setSelected(Article selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArticleFacade getFacade() {
        return ejbFacade;
    }

    public Article prepareCreate() {
        selected = new Article();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

        selected.setDateHeure(new Date());
        selected.setNbrJaime(0);
        selected.setNbrVue(0);
        selected.setIdAdmin(loginctrl.getLogedAdmin());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArticleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void updatvue() {
        selected.setNbrVue(selected.getNbrVue()+1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArticleUpdated"));
    }
    
        public void updatejaime() {
            System.out.println("to do");
            selected.setNbrJaime(50);
        persist(PersistAction.UPDATE, "j'aime éffectué");
            System.out.println("done");
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArticleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArticleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Article> getItems() {
        
       
            items = getFacade().findAll();
            items.sort(Comparator.comparing(Article::getId).reversed());
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

    public Article getArticle(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Article> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Article> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Article.class)
    public static class ArticleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticleController controller = (ArticleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articleController");
            return controller.getArticle(getKey(value));
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
            if (object instanceof Article) {
                Article o = (Article) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Article.class.getName()});
                return null;
            }
        }

    }

}
