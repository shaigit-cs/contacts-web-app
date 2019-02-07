package com.wooky.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
public class Translator {

    private static final Logger LOG = LoggerFactory.getLogger(Translator.class);
    private static final String MENU_LIST = "menu_list";
    private static final String MENU_SEARCH = "menu_search";
    private static final String MENU_ADD = "menu_add";
    private static final String MENU_LOGIN = "menu_login";
    private static final String CONTENT_NAME = "content_name";
    private static final String CONTENT_SURNAME = "content_surname";
    private static final String CONTENT_EMAIL = "content_email";
    private static final String CONTENT_PASSWORD = "content_password";
    private static final String CONTENT_LOGIN = "content_login";
    private static final String CONTENT_SIGNUP = "content_signup";
    private static final String CONTENT_PHONE_CODE = "content_phone_code";
    private static final String CONTENT_PHONE = "content_phone";
    private static final String CONTENT_BIRTHDATE = "content_birthdate";
    private static final String CONTENT_SAVE = "content_save";
    private static final String CONTENT_CLOSE = "content_close";
    private static final String CONTENT_EDIT = "content_edit";
    private static final String CONTENT_UPDATE = "content_update";
    private static final String CONTENT_DELETE = "content_delete";
    private static final String LIST_EMPTY = "list_empty";
    private static final String SEARCH_INFO_PART_1 = "search_info_part_1";
    private static final String SEARCH_INFO_PART_2 = "search_info_part_2";
    private static final String ERROR_INFO = "error_info";
    private static final String ERROR_RETURN = "error_return";

    public String translate(String translationInput, HttpServletRequest req) {

        Locale currentLocale = new Locale(getLanguage(req));
        ResourceBundle bundle = ResourceBundle.getBundle("translations", currentLocale);
        String translationOutput = bundle.getString(translationInput);

        LOG.info("Returned translated text: {}", translationOutput);

        return translationOutput;
    }

    public String getLanguage(HttpServletRequest req) {

        HttpSession session = req.getSession();

        String language;

        if (session.getAttribute("session_language") == null) {
            language = "pl";
        } else {
            language = session.getAttribute("session_language").toString();
        }

        return language;
    }

    public String[] translationKeys() {

        String[] output = { MENU_LIST, MENU_SEARCH, MENU_ADD, MENU_LOGIN, CONTENT_NAME, CONTENT_SURNAME, CONTENT_EMAIL,
                CONTENT_PASSWORD, CONTENT_LOGIN, CONTENT_SIGNUP, CONTENT_PHONE_CODE, CONTENT_PHONE, CONTENT_BIRTHDATE,
                CONTENT_SAVE, CONTENT_CLOSE, CONTENT_EDIT, CONTENT_UPDATE, CONTENT_DELETE, LIST_EMPTY, ERROR_INFO,
                ERROR_RETURN, SEARCH_INFO_PART_1, SEARCH_INFO_PART_2 };

        return output;
    }
}
