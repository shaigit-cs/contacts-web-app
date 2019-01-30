package com.wooky.web.servlets;

import com.wooky.core.Translator;
import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import com.wooky.web.freemaker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "list")
public class ContactList extends HttpServlet {

    private static final String TEMPLATE_INDEX = "contact-list";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

    @Inject
    private ContactDao contactDao;

//    @Override
//    public void init() {
//
//        Contact c1 = new Contact("Łukasz", "Marwitz");
//        contactDao.save(c1);
//        Contact c2 = new Contact("Ada", "Adamska");
//        contactDao.save(c2);
//        Contact c3 = new Contact("Zenek", "Martyniuk");
//        contactDao.save(c3);
//        Contact c4 = new Contact("Mirek", "Zakrzewski");
//        contactDao.save(c4);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        String language;

        if (req.getAttribute("language") != null) {
            language = req.getAttribute("language").toString();
        } else {
            language = languageHandler.getLanguage(req);
        }

        String[] translationKeys = translator.translationKeys();

        Map<String, Object> model = new HashMap<>();
        model.put("activeList", "active");
        model.put("activeAdd", "");
        model.put("currentLanguage", language);
        model.put("referrer", "&referrer=list");

        for (String i : translationKeys) {
            model.put(i, translator.translate(i, language));
        }

        if (req.getAttribute("searchResult") != null) {
            final List<Contact> searchResult = (ArrayList<Contact>) req.getAttribute("searchResult");
            model.put("contactList", searchResult);
            model.put("searchPhrase", "' " + req.getAttribute("searchPhrase") + " '");
            model.put("searchResultSize", req.getAttribute("searchResultSize"));
        } else {
            final List<Contact> contactList = contactDao.findAll();
            model.put("contactList", contactList);
        }

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_INDEX);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
