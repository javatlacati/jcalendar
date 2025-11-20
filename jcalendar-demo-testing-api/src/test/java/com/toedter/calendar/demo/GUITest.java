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
package com.toedter.calendar.demo;

import com.toedter.calendar.demo.pageobject.JCalendarDemoPageObject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Ruslan Lopez Carro
 */
public class GUITest {

    private static final int COMPONENTS_TO_TEST = 7;
    private JCalendarDemo jCalendarDemo;
    private final Locale englishLocale = Locale.US;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Setting up English locale");
        Locale.setDefault(englishLocale);
        jCalendarDemo = new JCalendarDemo("JCalendar Demo");
        jCalendarDemo.pack();
        jCalendarDemo.setResizable(true);
        jCalendarDemo.setVisible(true);
    }

    @AfterEach
    public void tearDown() throws Exception {
        jCalendarDemo.setVisible(false);
    }

    @Test
    public void checkAboutFromMenu() {
        JCalendarDemoPageObject demoPageObject = new JCalendarDemoPageObject("JCalendar Demo");
        assertEquals(3, demoPageObject.getMenuBarChildCount(), "There should be 3 menus in the menu bar");
        assertEquals(1, demoPageObject.getHelpMenuChildCount(), "There should be 1 menus in the help sub menu");
        assertEquals(COMPONENTS_TO_TEST, demoPageObject.getComponentsMenuChildCount(), "There should be " + COMPONENTS_TO_TEST + " menus in the components sub menu");
        assertEquals(COMPONENTS_TO_TEST, demoPageObject.getToolbarButtonsCount(), "There should be " + COMPONENTS_TO_TEST + " components in the component tool bar");

        assertEquals("JDateChooser", demoPageObject.getTitleOfComponentsPanel());
        assertEquals("Properties", demoPageObject.getTitleOfPropertiesPanel());

        demoPageObject.clickAboutMenu();
        String version = "1.3.9";
        String aboutText = "Version " +
                version;
        assertThat(demoPageObject.aboutMenuText(), CoreMatchers.containsString(aboutText));

        demoPageObject.closeAboutMenu();
    }
}
