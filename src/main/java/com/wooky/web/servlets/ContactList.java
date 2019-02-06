package com.wooky.web.servlets;

import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import com.wooky.web.freemaker.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("list")
public class ContactList extends HttpServlet {

    private static final String TEMPLATE_LIST = "contact-list";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ContactDao contactDao;

//    DEMO

//    @Override
//    public void init() {
//
//        Date birthday = null;
//        try {
//            birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1986-04-18");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Contact c1 = new Contact("Łukasz", "Marwitz", "lukaszmarwitz@gmail.com", "48", "123456789", birthday);
//        contactDao.save(c1);
//        Contact c2 = new Contact("Ada", "Adamek", "ada89@wp.pl", "48", "123456789", birthday);
//        contactDao.save(c2);
//        Contact c3 = new Contact("Zenek", "Martyniuk", "zmartyniuk@onet.eu", "49", "111111111", birthday);
//        contactDao.save(c3);
//        Contact c4 = new Contact("Mirek", "Zakrzewski", "mirekz@me.com", "44", "999999999", birthday);
//        contactDao.save(c4);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> model = templateProvider.setTemplateProviderTop(req, resp);
        model.put("activeList", "active");
        model.put("activeAdd", "");
        model.put("referrer", "&referrer=list");

        if (req.getAttribute("searchResult") != null) {
            final List<Contact> searchResult = (ArrayList<Contact>) req.getAttribute("searchResult");
            model.put("contactList", searchResult);
            model.put("searchPhrase", "' " + req.getAttribute("searchPhrase") + " '");
            model.put("searchResultSize", req.getAttribute("searchResultSize"));
        } else {
            final List<Contact> contactList = contactDao.findAll();
            model.put("contactList", contactList);
        }

        templateProvider.setTemplateProviderBottom(model, req, resp, getServletContext(), TEMPLATE_LIST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
