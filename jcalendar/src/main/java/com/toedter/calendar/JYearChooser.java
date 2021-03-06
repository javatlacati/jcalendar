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
package com.toedter.calendar;

import com.toedter.components.JSpinField;

import java.util.Calendar;

/**
 * JYearChooser is a bean for choosing a year.
 *
 * @version $LastChangedRevision: 85 $
 * @version $LastChangedDate: 2006-04-28 13:50:52 +0200 (Fr, 28 Apr 2006) $
 */
public class JYearChooser extends JSpinField {

    private static final long serialVersionUID = 2648810220491090064L;
    protected JDayChooser dayChooser;
    protected int oldYear;
    protected int startYear;
    protected int endYear;

    /**
     * Default JCalendar constructor.
     */
    public JYearChooser() {
        dayChooser = null;
        initJYearChooser();
    }

    private void initJYearChooser() {
        setName("JYearChooser");
        Calendar calendar = Calendar.getInstance();
        setMinimum(calendar.getMinimum(Calendar.YEAR));
        setMaximum(calendar.getMaximum(Calendar.YEAR));
        setValue(calendar.get(Calendar.YEAR));
    }

    /**
     * Sets the year. This is a bound property.
     *
     * @param aYear the new year
     *
     * @see #getYear
     */
    public void setYear(int aYear) {
        super.setValue(aYear, true, false);

        if (dayChooser != null) {
            dayChooser.setYear(value);
        }

        spinner.setValue(value);
        firePropertyChange("year", oldYear, value);
        oldYear = value;
    }

    /**
     * Sets the year value.
     *
     * @param value the year value
     */
    @Override
    public void setValue(int value) {
        setYear(value);
    }

    /**
     * Returns the year.
     *
     * @return the year
     */
    public int getYear() {
        return super.getValue();
    }

    /**
     * Convenience method set a day chooser that might be updated directly.
     *
     * @param dayChooser the day chooser
     */
    public void setDayChooser(JDayChooser dayChooser) {
        this.dayChooser = dayChooser;
    }

    /**
     * Returns the end year.
     *
     * @return the end year
     */
    public int getEndYear() {
        return getMaximum();
    }

    /**
     * Sets the end ear.
     *
     * @param endYear the end year
     */
    public void setEndYear(int endYear) {
        setMaximum(endYear);
    }

    /**
     * Returns the start year.
     *
     * @return the start year.
     */
    public int getStartYear() {
        return getMinimum();
    }

    /**
     * Sets the start year.
     *
     * @param startYear the start year
     */
    public void setStartYear(int startYear) {
        setMinimum(startYear);
    }
}
