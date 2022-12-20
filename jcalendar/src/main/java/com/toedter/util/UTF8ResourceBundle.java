/*
 * Copyright (C) 2020 RuslanLopez.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.toedter.util;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * This class is a hack to read UTF-8 encoded property files. The implementation
 * is based on http://www.thoughtsabout.net/blog/archives/000044.html
 *
 * @author Kai Toedter
 *
 */
public abstract class UTF8ResourceBundle {

    public static final ResourceBundle getBundle(String baseName, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        if (!(bundle instanceof PropertyResourceBundle)) {
            return bundle;
        }

        return new UTF8PropertyResourceBundle((PropertyResourceBundle) bundle);
    }

    private static class UTF8PropertyResourceBundle extends ResourceBundle {

        private PropertyResourceBundle propertyResourceBundle;

        private UTF8PropertyResourceBundle(PropertyResourceBundle bundle) {
            this.propertyResourceBundle = bundle;
        }

        @Override
        public Enumeration<String> getKeys() {
            return propertyResourceBundle.getKeys();
        }

        @Override
        protected Object handleGetObject(String key) {
            String value = (String) propertyResourceBundle.handleGetObject(key);
            if (value != null) {
                return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
            return null;
        }
    }
}
