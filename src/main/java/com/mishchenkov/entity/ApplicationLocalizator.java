package com.mishchenkov.entity;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApplicationLocalizator {

    private final List<Locale> availableLocale;
    private final String bundleName;
    private Locale currentLocale;
    private ResourceBundle bundle;

    public ApplicationLocalizator(String bundleName, List<Locale> availableLocale) {
        this.bundleName = bundleName;
        this.availableLocale = availableLocale;
        this.currentLocale = Locale.getDefault();
        this.bundle = ResourceBundle.getBundle(bundleName, currentLocale);
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        this.bundle = ResourceBundle.getBundle(bundleName, currentLocale);
    }

    public List<Locale> getAvailableLocale() {
        return availableLocale;
    }

    public String getBundleString(String key) {
        return bundle.getString(key);
    }

    public int localeSize() {
        return availableLocale.size();
    }
}
