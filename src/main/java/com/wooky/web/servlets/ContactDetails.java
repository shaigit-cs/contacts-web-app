package com.wooky.web.servlets;

import com.wooky.dao.ContactDao;
import com.wooky.model.Contact;
import com.wooky.web.freemaker.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("details")
public class ContactDetails extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ContactDetails.class);
    private static final String TEMPLATE_DETAILS = "contact-details";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ContactDao contactDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> model = templateProvider.setTemplateProviderTop(req, resp);
        model.put("activeList", "");
        model.put("activeAdd", "");
        model.put("referrer", "&referrer=details");

        String idString;
        HttpSession session = req.getSession();

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

        boolean editStatus;
        if (req.getParameter("edit") == null) {
            editStatus = false;
        } else {
            editStatus = true;
        }
        model.put("editStatus", editStatus);

        Long id = Long.parseLong(idString);
        final Contact contactDetails = contactDao.findById(id);
        model.put("contactDetails", contactDetails);

        templateProvider.setTemplateProviderBottom(model, resp, getServletContext(), TEMPLATE_DETAILS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
