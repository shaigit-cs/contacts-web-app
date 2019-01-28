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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = req.getParameter("action");
        LOG.info("Requested action: {}", action);

        if (action.equals("add")) {
            addContact(req, resp);
        }
//        else if (action.equals("delete")) {
//            deleteContact(req, resp);
//        } else if (action.equals("update")) {
//            updateContact(req, resp);
//        } else if (action.equals("findAll")) {
//            findAll(req, resp);
//        }
    }

    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String languageFromCookie = languageHandler.getLanguage(req);
        String addStatus;

        final Contact c = new Contact();
        c.setName(req.getParameter("name"));
        c.setSurname(req.getParameter("surname"));

        try {
            contactDao.save(c);
            addStatus = translator.translate(INFO_SAVE, languageFromCookie);
            LOG.info("Saved a new contact object: {}", c);
        } catch (Exception e) {
            addStatus = translator.translate(INFO_SAVE_ISSUE, languageFromCookie);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add");
        req.setAttribute("addStatus", addStatus);
        dispatcher.forward(req, resp);
    }
}
