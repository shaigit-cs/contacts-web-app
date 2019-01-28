package com.wooky.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
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
    private static final String CONTENT_SAVE = "content_save";
    private static final String LIST_EMPTY = "list_empty";

    public String translate(String translationInput, String language) {

        Locale currentLocale = new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle("translations", currentLocale);
        String translationOutput = bundle.getString(translationInput);

        LOG.info("Returned translated text: {}", translationOutput);

        return translationOutput;
    }

    public String[] translationKeys() {

        String[] output = { MENU_LIST, MENU_SEARCH, MENU_ADD, MENU_LOGIN, CONTENT_NAME, CONTENT_SURNAME, CONTENT_SAVE, LIST_EMPTY };

        return output;
    }
}
