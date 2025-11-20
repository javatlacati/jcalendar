/*
 * Copyright (C) 2021 RuslanLopez.
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
package com.toedter.calendar;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author RuslanLopez
 */
public class MinMaxDateEvaluator implements IDateEvaluator{
    private final DateUtil dateUtil = new DateUtil();

    @Override
    public boolean isSpecial(Date date) {
        return false;
    }

    @Override
    public Color getSpecialForegroundColor() {
        return null;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return null;
    }

    @Override
    public String getSpecialTooltip() {
        return null;
    }

    @Override
    public boolean isInvalid(Date date) {
        return !dateUtil.checkDate(date);
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }

    /**
     * Sets the maximum selectable date. If null, the date 01\01\9999 will be
     * set instead.
     *
     * @param max the maximum selectable date
     *
     * @return the maximum selectable date
     */
    public Date setMaxSelectableDate(Date max) {
        return dateUtil.setMaxSelectableDate(max);
    }

    /**
     * Sets the minimum selectable date. If null, the date 01\01\0001 will be
     * set instead.
     *
     * @param min the minimum selectable date
     *
     * @return the minimum selectable date
     */
    public Date setMinSelectableDate(Date min) {
        return dateUtil.setMinSelectableDate(min);
    }

    /**
     * Gets the maximum selectable date.
     *
     * @return the maximum selectable date
     */
    public Date getMaxSelectableDate() {
        return dateUtil.getMaxSelectableDate();
    }

    /**
     * Gets the minimum selectable date.
     *
     * @return the minimum selectable date
     */
    public Date getMinSelectableDate() {
        return dateUtil.getMinSelectableDate();
    }
}
