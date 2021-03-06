/*
 *  Copyright (C) 2006 Tolven Inc 
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU Lesser General Public License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 * 
 * Contact: info@tolvenhealth.com
 */
package org.tolven.app.el;

import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.tolven.app.AppLocaleText;

/**
 * This class exists to handle the fact that JSF's method binding is limited to Map property type behavior
 * @author Joseph Isaac
 *
 */
public class AppLocaleMap implements Map<ResourceBundle, String> {

    private AppLocaleText appLocaleText;
    private boolean defaultLocaleTextOnly;

    public AppLocaleMap(AppLocaleText appLocaleText) {
        this(appLocaleText, false);
    }

    public AppLocaleMap(AppLocaleText appLocaleText, boolean defaultLocalTextOnly) {
        this.appLocaleText = appLocaleText;
        this.defaultLocaleTextOnly = defaultLocalTextOnly;
    }

    public AppLocaleText getAppLocaleText() {
        return appLocaleText;
    }

    public void setAppLocaleText(AppLocaleText appLocaleText) {
        this.appLocaleText = appLocaleText;
    }

    public boolean isDefaultLocaleTextOnly() {
        return defaultLocaleTextOnly;
    }

    public void setDefaultLocaleTextOnly(boolean defaultLocaleTextOnly) {
        this.defaultLocaleTextOnly = defaultLocaleTextOnly;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<ResourceBundle, String>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String get(Object key) {
        if(isDefaultLocaleTextOnly()) {
            return getAppLocaleText().getDefaultLocaleText((ResourceBundle) key);
        } else {
            return getAppLocaleText().getLocaleText((ResourceBundle) key);
        }
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<ResourceBundle> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String put(ResourceBundle key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends ResourceBundle, ? extends String> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public String remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<String> values() {
        // TODO Auto-generated method stub
        return null;
    }

}
