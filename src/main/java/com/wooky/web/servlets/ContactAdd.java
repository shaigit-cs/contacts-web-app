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

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private Translator translator;

    @Inject
    private LanguageHandler languageHandler;

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
        model.put("activeList", "");
        model.put("activeAdd", "active");
        model.put("currentLanguage", language);
        model.put("referrer", "&referrer=add");

        for (String i : translationKeys) {
            model.put(i, translator.translate(i, language));
        }

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
}
