package com.wooky.web.servlets;

import com.wooky.web.freemaker.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("add")
public class ContactAdd extends HttpServlet {

    private static final String TEMPLATE_ADD = "contact-add";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> model = templateProvider.setTemplateProviderTop(req, resp);
        model.put("activeList", "");
        model.put("activeAdd", "active");
        model.put("referrer", "&referrer=add");
        templateProvider.setTemplateProviderBottom(model, req, resp, getServletContext(), TEMPLATE_ADD);
    }
}
