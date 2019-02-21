package com.wooky.web.servlets;

import com.wooky.web.freemaker.TemplateBuilder;

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
    private TemplateBuilder templateBuilder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> model = templateBuilder.setTemplateTop(req, resp);
        model.put("activeList", "");
        model.put("activeAdd", "active");
        model.put("referrer", "&referrer=add");
        templateBuilder.setTemplateBottom(model, req, resp, getServletContext(), TEMPLATE_ADD);
    }
}
