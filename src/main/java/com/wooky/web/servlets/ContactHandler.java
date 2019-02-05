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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@WebServlet(urlPatterns = "handler")
public class ContactHandler extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ContactHandler.class);
    private static final String NOTIFICATION = "notification";
    private static final String NOTIFICATION_CONTACT = "notificationContact";
    private static final String NOTIFICATION_SAVE = "notification_save";
    private static final String NOTIFICATION_UPDATE = "notification_update";
    private static final String NOTIFICATION_DELETE = "notification_delete";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String SPACE = " ";

    @Inject
    private ContactDao contactDao;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = req.getParameter("action");
        LOG.info("Requested POST action: {}", action);

        if (action.equals("add")) {
            addContact(req, resp);
        } else if (action.equals("search")) {
            searchContact(req, resp);
        } else if (action.equals("update")) {
            updateContact(req, resp);
        } else if (action.equals("delete")) {
            deleteContact(req, resp);
        } else {
            LOG.warn("Requested POST action not identified: {}", action);
        }
    }

    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final Contact newContact = new Contact();
        setContact(newContact, req);
        contactDao.save(newContact);
        LOG.info("Saved a new contact: {}", newContact);

        setNotifications(req, newContact, NOTIFICATION_SAVE);
        req.getRequestDispatcher("/list").forward(req, resp);
    }

    private void updateContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final Long id = Long.parseLong(req.getParameter("id"));
        final Contact existingContact = contactDao.findById(id);
        LOG.info("Updating contact: {}", existingContact);

        if (existingContact == null) {
            LOG.info("No contact found with id = {}, nothing to be updated", id);
        } else {
            setContact(existingContact, req);
            contactDao.update(existingContact);
            LOG.info("Contact updated as follows: {}", existingContact);
        }

        setNotifications(req, existingContact, NOTIFICATION_UPDATE);
        req.getRequestDispatcher("/list").forward(req, resp);
    }

    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final Long id = Long.parseLong(req.getParameter("id"));
        final Contact deleteContact = contactDao.findById(id);
        LOG.info("Deleting contact: {}", deleteContact);

        setNotifications(req, deleteContact, NOTIFICATION_DELETE);

        contactDao.delete(id);

        LOG.info("Contact deleted");
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

        String output;

        if (input.contains("@")) {
            output = input.toLowerCase();
        } else {
            output = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        return output;
    }

    private Date dateParser(String input) {

        Date output = null;

        try {
            output = new SimpleDateFormat(DATE_FORMAT).parse(input);
        } catch (ParseException e) {
            LOG.error("Unable to parse input String date: {} to the following Date format: {}", input, DATE_FORMAT);
        }

        return output;
    }

    private Contact setContact(Contact contact, HttpServletRequest req) {

        contact.setName(caseCorrection(req.getParameter("name")));
        contact.setSurname(caseCorrection(req.getParameter("surname")));
        contact.setEmail(caseCorrection(req.getParameter("email")));
        contact.setPhoneCode(req.getParameter("phone_code"));
        contact.setPhone(req.getParameter("phone"));
        contact.setBirthdate(dateParser(req.getParameter("birthdate")));

        return contact;
    }

    private void setNotifications(HttpServletRequest req, Contact contact, String notificationInfo) {

        String language = languageHandler.getLanguage(req);

        req.setAttribute(NOTIFICATION, translator.translate(notificationInfo, language));
        req.setAttribute(NOTIFICATION_CONTACT, contact.getName() + SPACE + contact.getSurname());

        LOG.info("Notifications set for: {}", contact);
    }
}
