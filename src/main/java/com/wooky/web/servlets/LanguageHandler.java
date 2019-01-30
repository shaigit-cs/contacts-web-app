package com.wooky.web.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("language")
public class LanguageHandler extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String referrer = req.getParameter("referrer");
        String language = req.getParameter("lang");

        Cookie cookie = new Cookie("language", language);
        resp.addCookie(cookie);
        LOG.info("The following language was stored and set: {}", language);
        req.setAttribute("language", language);

        LOG.info("Redirecting back to referrer: {}", referrer);
        resp.sendRedirect(referrer);
    }

    public String getLanguage(HttpServletRequest req) {

        Cookie[] cookie = req.getCookies();

        String result = "pl";

        if (cookie != null) {
            for (Cookie i : cookie) {
                if (i.getName().equals("language")) {
                    result = i.getValue();
                }
            }
        }

        LOG.info("Following language bundle in use: {}", result);
        return result;
    }
}
