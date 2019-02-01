package com.wooky.web.servlets;

import com.wooky.core.Translator;
import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@WebServlet(urlPatterns = "handler")
public class ContactHandler extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ContactHandler.class);
    private static final String NOTIFICATION_SAVE = "notification_save";
    private static final String NOTIFICATION_SAVE_ISSUE = "notification_save_issue";
    private static final String NOTIFICATION_UPDATE = "notification_update";
    private static final String NOTIFICATION_DELETE = "notification_delete";
    private static final String SPACE = " ";

    @Inject
    private ContactDao contactDao;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final String action = req.getParameter("action");
        LOG.info("Requested action: {}", action);

        if (action.equals("add")) {
            addContact(req, resp);
        } else if (action.equals("update")) {
            updateContact(req, resp);
        } else if (action.equals("delete")) {
            deleteContact(req, resp);
        } else if (action.equals("search")) {
            searchContact(req, resp);
        }
    }

    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String language = languageHandler.getLanguage(req);
        String notification;
        String notificationContact;

        final Contact c = new Contact();
        c.setName(caseCorrection(req.getParameter("name")));
        c.setSurname(caseCorrection(req.getParameter("surname")));

        contactDao.save(c);
        LOG.info("Saved a new contact object: {}", c);

        notification = translator.translate(NOTIFICATION_SAVE, language);
        notificationContact = c.getName() + SPACE + c.getSurname();

        req.setAttribute("notification", notification);
        req.setAttribute("notificationContact", notificationContact);
        req.getRequestDispatcher("/add").forward(req, resp);
    }

    private void updateContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String language = languageHandler.getLanguage(req);

        final Long id = Long.parseLong(req.getParameter("id"));
        LOG.info("Updating contact with id: {}", id);

        final Contact existingContact = contactDao.findById(id);

        if (existingContact == null) {
            LOG.info("No contact found with id = {}, nothing to be updated", id);
        } else {
            existingContact.setName(caseCorrection(req.getParameter("name")));
            existingContact.setSurname(caseCorrection(req.getParameter("surname")));

            contactDao.update(existingContact);
            LOG.info("Contact object updated: {}", existingContact);
        }

        String notification = translator.translate(NOTIFICATION_UPDATE, language);
        String notificationContact = existingContact.getName() + SPACE + existingContact.getSurname();
        req.setAttribute("notification", notification);
        req.setAttribute("notificationContact", notificationContact);
        req.getRequestDispatcher("/list").forward(req, resp);
    }

    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String language = languageHandler.getLanguage(req);

        final Long id = Long.parseLong(req.getParameter("id"));

        String notification = translator.translate(NOTIFICATION_DELETE, language);
        String notificationContact = contactDao.findById(id).getName() + SPACE + contactDao.findById(id).getSurname();

        LOG.info("Deleting contact with id: {}", id);
        contactDao.delete(id);

        req.setAttribute("notification", notification);
        req.setAttribute("notificationContact", notificationContact);
        req.getRequestDispatcher("/list").forward(req, resp);
    }

    private void searchContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final String searchPhrase = req.getParameter("phrase");
        LOG.info("Searching contacts to contain following phrase: {}", searchPhrase);

        List<Contact> searchResult = contactDao.search(searchPhrase);

        req.setAttribute("searchResult", searchResult);
        req.setAttribute("searchPhrase", searchPhrase);
        req.setAttribute("searchResultSize", searchResult.size());
        req.getRequestDispatcher("/list").forward(req, resp);
    }

    private String caseCorrection(String input) {
        String output = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        return output;
    }
}
