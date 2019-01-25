package com.wooky.web.servlets;

import com.wooky.core.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/language")
public class LanguageHandler extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageHandler.class);

    @Inject
    private Translator translator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String referrer = req.getParameter("referrer");
        String language = req.getParameter("lang");

        translator.setLanguage(language);

        LOG.info("Referrer: {}", referrer);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + referrer);
        dispatcher.forward(req, resp);
    }
}
