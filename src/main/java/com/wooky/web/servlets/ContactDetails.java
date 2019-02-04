package com.wooky.web.servlets;

import com.wooky.core.Translator;
import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import com.wooky.web.freemaker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "details")
public class ContactDetails extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ContactDetails.class);
    private static final String TEMPLATE_INDEX = "contact-details";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

    @Inject
    private ContactDao contactDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        String idString;

        if (req.getParameterMap().containsKey("id")) {
            idString = req.getParameter("id");
            session.setAttribute("session_req", req);
            LOG.info("Request: {} stored in session: {}", req.hashCode(), session.getId());
        } else {
            LOG.info("Found session: {}", session.getId());
            req = (HttpServletRequest) session.getAttribute("session_req");
            LOG.info("Found request: {}", req.hashCode());
            idString = req.getParameter("id");
        }

        Long id = Long.parseLong(idString);

        boolean editStatus;

        if (req.getParameter("edit") == null) {
            editStatus = false;
        } else {
            editStatus = true;
        }

        String language = languageHandler.getLanguage(req);

        String[] translationKeys = translator.translationKeys();

        Map<String, Object> model = new HashMap<>();
        model.put("activeList", "");
        model.put("activeAdd", "");
        model.put("currentLanguage", language);
        model.put("referrer", "&referrer=details");
        model.put("editStatus", editStatus);

        for (String i : translationKeys) {
            model.put(i, translator.translate(i, language));
        }

        final Contact contactDetails = contactDao.findById(id);
        model.put("contactDetails", contactDetails);

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_INDEX);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
