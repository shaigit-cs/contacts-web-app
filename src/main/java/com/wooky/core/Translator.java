package com.wooky.core;

import com.wooky.dao.SettingDao;
import com.wooky.model.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
public class Translator {

    private static final Logger LOG = LoggerFactory.getLogger(Translator.class);
    private static final String DEFAULT_LANGUAGE = "pl";

    @Inject
    private SettingDao settingDao;

    public String translate(String translationInput) {

        Locale currentLocale = new Locale(getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("translations", currentLocale);
        String translationOutput = bundle.getString(translationInput);

        LOG.info("Returned translated text: {}", translationOutput);

        return translationOutput;
    }

    public String getLanguage() {

        String language;

        if (settingDao.findById(1) == null) {
            language = DEFAULT_LANGUAGE;
            LOG.info("No language settings found, used default language: {}", language);
        } else {
            language = settingDao.findById(1).getLanguage();
            LOG.info("Language settings found, following language in use: {}", language);
        }

        return language;
    }

    public void setLanguage(String newLanguage) {

        final Setting existingLanguage;

        if (settingDao.findById(1) == null) {
            existingLanguage = new Setting();
            existingLanguage.setLanguage(newLanguage);
            settingDao.save(existingLanguage);
            LOG.info("The following language was stored: {}", existingLanguage.getLanguage());
        } else {
            existingLanguage = settingDao.findById(1);
            existingLanguage.setLanguage(newLanguage);
            settingDao.update(existingLanguage);
            LOG.info("The language was set to: {}", existingLanguage.getLanguage());
        }
    }
}
