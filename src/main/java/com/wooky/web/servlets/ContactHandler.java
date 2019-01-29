package com.wooky.web.servlets;

import com.wooky.core.Translator;
import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@WebServlet(urlPatterns = "handler")
public class ContactHandler extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ContactHandler.class);
    private static final String INFO_SAVE = "info_save";
    private static final String INFO_SAVE_ISSUE = "info_save_issue";

    @Inject
    private ContactDao contactDao;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String action = req.getParameter("action");
        LOG.info("Requested action: {}", action);

        if (action.equals("add")) {
            addContact(req, resp);
        } else if (action.equals("update")) {
            updateContact(req, resp);
        } else if (action.equals("delete")) {
            deleteContact(req, resp);
        }
    }

    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String languageFromCookie = languageHandler.getLanguage(req);
        String addStatus;

        final Contact c = new Contact();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        c.setName(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
        c.setSurname(surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase());

        try {
            contactDao.save(c);
            addStatus = translator.translate(INFO_SAVE, languageFromCookie);
            LOG.info("Saved a new contact object: {}", c);
        } catch (Exception e) {
            addStatus = translator.translate(INFO_SAVE_ISSUE, languageFromCookie);
        }

        req.setAttribute("addStatus", addStatus);
        resp.sendRedirect("add");
    }

    private void updateContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final Long id = Long.parseLong(req.getParameter("id"));
        LOG.info("Updating contact with id: {}", id);

        final Contact existingContact = contactDao.findById(id);

        if (existingContact == null) {
            LOG.info("No contact found with id = {}, nothing to be updated", id);
        } else {
            existingContact.setName(req.getParameter("name"));
            existingContact.setSurname(req.getParameter("surname"));

            contactDao.update(existingContact);
            LOG.info("Contact object updated: {}", existingContact);
        }

        resp.sendRedirect("list");
    }

    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final Long id = Long.parseLong(req.getParameter("id"));
        LOG.info("Deleting contact with id: {}", id);

        contactDao.delete(id);

        resp.sendRedirect("list");
    }
}
