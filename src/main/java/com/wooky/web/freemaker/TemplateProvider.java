package com.wooky.web.freemaker;

import com.wooky.core.StaticFields;
import com.wooky.core.Translator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TemplateProvider {

    private static final String TEMPLATE_DIRECTORY_PATH = "ftlh";
    private static final String TEMPLATE_EXT = ".ftlh";
    private static final String NOTIFICATION = "notification";
    private static final String NOTIFICATION_CONTACT = "notificationContact";

    @Inject
    private Translator translator;

    public Template getTemplate(ServletContext servletContext, String templateName) throws IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setServletContextForTemplateLoading(servletContext, TEMPLATE_DIRECTORY_PATH);

        return cfg.getTemplate(templateName + TEMPLATE_EXT);
    }

    public Map<String, Object> setTemplateProviderTop(HttpServletRequest req, HttpServletResponse resp) {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        String[] translationKeys = translator.translationKeys();

        Map<String, Object> model = new HashMap<>();
        model.put("currentLanguage", translator.getLanguage(req));
        model.put("staticFields", StaticFields.getStaticFields());

        for (String i : translationKeys) {
            model.put(i, translator.translate(i, req));
        }

        return model;
    }

    public void setTemplateProviderBottom(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext, String templateName) throws IOException {

        if (req.getAttribute(NOTIFICATION) == null) {
            model.put(NOTIFICATION, "");
            model.put(NOTIFICATION_CONTACT, "");
        } else {
            String notification = req.getAttribute(NOTIFICATION).toString();
            String notificationContact = req.getAttribute(NOTIFICATION_CONTACT).toString();
            model.put(NOTIFICATION, notification);
            model.put(NOTIFICATION_CONTACT, notificationContact);
        }

        setTemplateProviderBottom(model, resp, servletContext, templateName);
    }

    public void setTemplateProviderBottom(Map<String, Object> model, HttpServletResponse resp, ServletContext servletContext, String templateName) throws IOException {

        Template template = getTemplate(servletContext, templateName);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
