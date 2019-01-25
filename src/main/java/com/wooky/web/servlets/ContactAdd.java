package com.wooky.web.servlets;

import com.wooky.core.Translator;
import com.wooky.web.freemaker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "add")
public class ContactAdd extends HttpServlet {

    private static final String TEMPLATE_INDEX = "contact-add";
    private static final String ADD_STATUS = "addStatus";
    private static final String MENU_LIST = "menu_list";
    private static final String MENU_SEARCH = "menu_search";
    private static final String MENU_ADD = "menu_add";
    private static final String MENU_LOGIN = "menu_login";
    private static final String CONTENT_NAME = "content_name";
    private static final String CONTENT_SURNAME = "content_surname";
    private static final String CONTENT_SAVE = "content_save";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private Translator translator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        Map<String, Object> model = new HashMap<>();
        model.put("activeList", "");
        model.put("activeSearch", "");
        model.put("activeAdd", "active");
        model.put("referrer", "&referrer=add");
        modelHandler(model, MENU_LIST);
        modelHandler(model, MENU_SEARCH);
        modelHandler(model, MENU_ADD);
        modelHandler(model, MENU_LOGIN);
        modelHandler(model, CONTENT_NAME);
        modelHandler(model, CONTENT_SURNAME);
        modelHandler(model, CONTENT_SAVE);

        if (req.getAttribute(ADD_STATUS) == null) {
            model.put(ADD_STATUS, "");
        } else {
            String addStatus = req.getAttribute(ADD_STATUS).toString();
            model.put(ADD_STATUS, addStatus);
        }

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_INDEX);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void modelHandler(Map<String, Object> model, String keyName) {
        model.put(keyName, translator.translate(keyName));
    }
}

