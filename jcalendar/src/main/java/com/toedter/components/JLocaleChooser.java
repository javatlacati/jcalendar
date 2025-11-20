/* 
 * Copyright (C) 2019 Ruslan Lopez Carro.
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
package com.toedter.components;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Locale;

/**
 * JLocaleChooser is a bean for choosing locales.
 *
 * @author Kai Toedter
 * @version $LastChangedRevision: 85 $
 * @version $LastChangedDate: 2006-04-28 13:50:52 +0200 (Fr, 28 Apr 2006) $
 */
public class JLocaleChooser extends JComboBox<String> implements ItemListener {

    private static final long serialVersionUID = 8152430789764877431L;
    
    protected JComponent component;
    private Locale[] locales;
    private Locale locale;
    private int localeCount;

    /**
     * Default JLocaleChooser constructor.
     */
    public JLocaleChooser() {
        this(null);
    }

    /**
     * Default JLocaleChooser constructor.
     *
     * @param component
     */
    public JLocaleChooser(JComponent component) {
        super();
        this.component = component;
        initLocales();
    }

    private void initLocales() {
        addItemListener(this);
        locales = Calendar.getAvailableLocales();
        localeCount = locales.length;

        for (int i = 0; i < localeCount; i++) {
            if (!locales[i].getCountry().isEmpty()) {
                addItem(locales[i].getDisplayName());
            }
        }

        setLocale(Locale.getDefault());
    }

    /**
     * Returns "JLocaleChoose".
     *
     * @return the name value
     */
    @Override
    public String getName() {
        return "JLocaleChoose";
    }

    /**
     * The ItemListener for the locales.
     *
     * @param iEvt event from the item
     */
    @Override
    public void itemStateChanged(ItemEvent iEvt) {
        String item = (String) iEvt.getItem();
        int i;

        for (i = 0; i < localeCount; i++) {
            if (locales[i].getDisplayName().equals(item)) {
                break;
            }
        }
        setLocale(locales[i], false);
    }

    /**
     * Sets the locale.
     *
     * @see #getLocale
     */
    private void setLocale(Locale l, boolean select) {
        Locale oldLocale = locale;
        locale = l;
        int n = 0;

        if (select) {
            for (int i = 0; i < localeCount; i++) {
                if (!locales[i].getCountry().isEmpty()) {
                    if (locales[i].equals(locale)) {
                        setSelectedIndex(n);
                    }
                    n += 1;
                }
            }
        }

        firePropertyChange("locale", oldLocale, locale);
        if (component != null) {
            component.setLocale(l);
        }
    }

    /**
     * Sets the locale. This is a bound property.
     *
     * @see #getLocale
     */
    @Override
    public void setLocale(Locale l) {
        setLocale(l, true);
    }

    /**
     * Returns the locale.
     */
    @Override
    public Locale getLocale() {
        return locale;
    }

}
