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
package com.toedter.plaf;

import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * The JCalendar theme. The colors are based on the theme "Experience Bue" of
 * the great Plastic3D Look and feel by JGoddies (www.jgoodies.com)
 *
 * @author Kai Toedter
 * @version $LastChangedRevision: 85 $
 * @version $LastChangedDate: 2006-04-28 13:50:52 +0200 (Fr, 28 Apr 2006) $
 */
public class JCalendarTheme extends DefaultMetalTheme {

    private static final ColorUIResource secondary1 = new ColorUIResource(128, 128, 128);
    private static final ColorUIResource secondary2 = new ColorUIResource(189, 190, 176);
    private static final ColorUIResource secondary3 = new ColorUIResource(236, 233, 216);
    private static final ColorUIResource BLUE_LOW_MEDIUM = new ColorUIResource(166, 202, 240);
    private static final ColorUIResource BLUE_LOW_LIGHTEST = new ColorUIResource(195, 212, 232);
    private static final ColorUIResource BLUE_MEDIUM_DARKEST = new ColorUIResource(44, 73, 135);
    private static final ColorUIResource BLUE_MEDIUM_DARK = new ColorUIResource(49, 106, 196);
    private static final ColorUIResource BLUE_MEDIUM_MEDIUM = new ColorUIResource(85, 115, 170);
    private static final ColorUIResource ORANGE_FOCUS = new ColorUIResource(245, 165, 16);
    private static final ColorUIResource GREEN_CHECK = new ColorUIResource(33, 161, 33);
    private static final ColorUIResource WHITE = new ColorUIResource(255, 255, 255);
    private static final FontUIResource standardFont = new FontUIResource(
            new Font("Tahoma", Font.PLAIN, 11));

    @Override
    public String getName() {
        return "JCalendar Theme";
    }

    @Override
    public FontUIResource getSystemTextFont() {
        return standardFont;
    }

    @Override
    public FontUIResource getUserTextFont() {
        return standardFont;
    }

    @Override
    public FontUIResource getControlTextFont() {
        return standardFont;
    }

    @Override
    public FontUIResource getMenuTextFont() {
        return standardFont;
    }

    @Override
    protected ColorUIResource getPrimary1() {
        return BLUE_MEDIUM_DARK;
    }

    @Override
    protected ColorUIResource getPrimary2() {
        return BLUE_LOW_MEDIUM;
    }

    @Override
    protected ColorUIResource getPrimary3() {
        return BLUE_LOW_LIGHTEST;
    }

    @Override
    protected ColorUIResource getSecondary1() {
        return secondary1;
    }

    @Override
    protected ColorUIResource getSecondary2() {
        return secondary2;
    }

    @Override
    protected ColorUIResource getSecondary3() {
        return secondary3;
    }

    @Override
    public ColorUIResource getFocusColor() {
        return ORANGE_FOCUS;
    }

    @Override
    public ColorUIResource getPrimaryControlShadow() {
        return getPrimary3();
    }

    @Override
    public ColorUIResource getMenuSelectedBackground() {
        return getPrimary1();
    }

    @Override
    public ColorUIResource getMenuSelectedForeground() {
        return WHITE;
    }

    public ColorUIResource getMenuItemBackground() {
        return WHITE;
    }

    public ColorUIResource getToggleButtonCheckColor() {
        return GREEN_CHECK;
    }
}
