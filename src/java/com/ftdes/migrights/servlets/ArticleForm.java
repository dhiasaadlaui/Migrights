package com.ftdes.migrights.servlets;

import com.ftdes.migrights.managedBean.util.ImageDisplayBean;
import com.ftdes.migrights.model.Article;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class ArticleForm extends HttpServlet {

    @EJB
    private com.ftdes.migrights.sessionBean.ArticleFacade ejbFacade;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Article art = ejbFacade.find(Integer.parseInt(request.getParameter("id")));
            // Set response content type
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            String title = art.getTitre();
            String txt = art.getTexte();
            String Auteur = art.getIdAdmin().getPrenom() + " " + art.getIdAdmin().getNom();
            ImageDisplayBean imageDisplayBean = new ImageDisplayBean();
            String url = request.getRequestURL().toString();
            String docType
                    = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateart = sdf.format(art.getDateHeure());
            out.println(docType
                    + "<html>\n"
                    + "<head><title>" + title + "</title></head>\n"
                    + "<body bgcolor = \"#ffffff\">\n"
                    + "<h1 align = \"left\">" + title + "</h1>\n"
                    + "<a href=\"https://www.facebook.com/sharer/sharer.php?u="+"google.com"+" \" target=\"_blank\"><img src=\"http://www.tutorialstutor.com/uploads/facebook/Facebook-share-button.png\" width='100px'/></a>\n"
                    + "<img  src='data:image/png;base64," + imageDisplayBean.imageDisplay(art.getImage()) + "' width='100%' ></img>"
                    + txt + "<br/>"
                    + "<h3 align=\"right\"> Auteur: " + Auteur + "</h3>"
                    + "<h4 align=\"right\"> Le: " + dateart + "</h4>"
                    + "</body> </html>"
            );
            art.setNbrVue(art.getNbrVue() + 1);
            ejbFacade.edit(art);

        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String title = "404 Article not found";
            String txt = "Erreur : article non trouvable";
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            out.println(docType
                    + "<html>\n"
                    + "<head><title>" + title + "</title></head>\n"
                    + "<body bgcolor = \"#ffffff\">\n" + "<h1 align = \"center\">" + title + "</h1>\n"
                    + e.getLocalizedMessage() + txt
                    + "<ul>\n"
                    + "</body> </html>"
            );
        }

    }
}
