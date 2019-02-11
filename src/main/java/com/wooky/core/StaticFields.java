package com.wooky.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StaticFields {

    private static StaticFields staticFields;

    private static final Logger LOG = LoggerFactory.getLogger(StaticFields.class);
    private static final String PATTERN_NAME = "[a-zA-ZąćęłńóśżźĄĆĘŁŃÓŚŻŹäöüÄÖÜß]+";
    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9._%+-]{0,63}@(?:[a-zA-Z0-9-]{1,63}\\.){1,8}[a-zA-Z]{2,4}$";
    private static final String PATTERN_PHONE = "[\\d]+";
    private static final String PATTERN_BIRTHDATE = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private StaticFields() {
        LOG.info("Constructor initialized.");
    }

    public static StaticFields getInstance() {
        if (staticFields == null) {
            staticFields = new StaticFields();
        }
        return staticFields;
    }

    public static String getPatternName() {
        return PATTERN_NAME;
    }

    public static String getPatternEmail() {
        return PATTERN_EMAIL;
    }

    public static String getPatternPhone() {
        return PATTERN_PHONE;
    }

    public static String getPatternBirthdate() {
        return PATTERN_BIRTHDATE;
    }

    public static String getDateFormat() {
        return DATE_FORMAT;
    }
}
