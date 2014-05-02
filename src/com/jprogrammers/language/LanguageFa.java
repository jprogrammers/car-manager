package com.jprogrammers.language;

import javax.faces.context.FacesContext;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Created by EN20 on 2/13/14.
 */
public class LanguageFa extends ResourceBundle {

    public LanguageFa() {
        setParent(ResourceBundle.getBundle("com.jprogrammers.language.language-fa",
                FacesContext.getCurrentInstance().getViewRoot().getLocale(), new UTF8Control()));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }
}