package com.wooky.web.freemarker;

import com.wooky.core.StaticFields;
import com.wooky.core.Translator;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TemplateBuilder {

    private static final String NOTIFICATION = "notification";
    private static final String NOTIFICATION_CONTACT = "notificationContact";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private Translator translator;

    public Map<String, Object> setTemplateTop(HttpServletRequest req, HttpServletResponse resp) {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        String[] translationKeys = translator.translationKeys();

        Map<String, Object> model = new HashMap<>();
        model.put("currentLanguage", translator.getLanguage(req));
        model.put("staticFields", StaticFields.getInstance());

        for (String i : translationKeys) {
            model.put(i, translator.translate(i, req));
        }

        return model;
    }

    public void setTemplateBottom(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext, String templateName) throws IOException {

        if (req.getAttribute(NOTIFICATION) == null) {
            model.put(NOTIFICATION, "");
            model.put(NOTIFICATION_CONTACT, "");
        } else {
            String notification = req.getAttribute(NOTIFICATION).toString();
            String notificationContact = req.getAttribute(NOTIFICATION_CONTACT).toString();
            model.put(NOTIFICATION, notification);
            model.put(NOTIFICATION_CONTACT, notificationContact);
        }

        setTemplateBottom(model, resp, servletContext, templateName, req);
    }

    public void setTemplateBottom(Map<String, Object> model, HttpServletResponse resp, ServletContext servletContext, String templateName, HttpServletRequest req) throws IOException {

        Template template = templateProvider.getTemplate(servletContext, templateName, translator.getLanguage(req));

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
