package com.wooky.web.servlets;

import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import com.wooky.web.freemaker.TemplateBuilder;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("list")
public class ContactList extends HttpServlet {

    private static final String TEMPLATE_LIST = "contact-list";

    @Inject
    private TemplateBuilder templateBuilder;

    @Inject
    private ContactDao contactDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> model = templateBuilder.setTemplateTop(req, resp);
        model.put("activeList", "active");
        model.put("activeAdd", "");
        model.put("activeLogin", "");
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

        templateBuilder.setTemplateBottom(model, req, resp, getServletContext(), TEMPLATE_LIST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
