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

import com.toedter.util.UTF8ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Calendar.getInstance;

/**
 * JCalendar is a bean for entering a date by choosing the year, month and day.
 *
 * @author Ruslan Lopez Carro
 * @author Kai Toedter
 * @version $LastChangedRevision: 95 $
 * @version $LastChangedDate: 2006-05-05 18:43:15 +0200 (Fr, 05 Mai 2006) $
 */
public class JCalendar extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 8913369762644440133L;

    private Calendar calendar;

    private boolean initialized;

    /**
     * indicates if weeks of year shall be visible
     */
    protected boolean weekOfYearVisible = true;
    
    private boolean isTodayButtonVisible;
    private boolean isNullDateButtonVisible;
    
    private static final String DEFAULT_TODAY_BUTTON_TEXT = "Today";
    private static final String DEFAULT_NULL_DATE_BUTTON_TEXT = "No Date";
    private String todayButtonText;
    private String nullDateButtonText;
    
    private boolean monthSpinner;

    /**
     * the locale
     */
    protected Locale locale;

    protected Date minSelectableDate;

    protected Date maxSelectableDate;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * the day chooser
    */
    private JDayChooser dayChooser;
    /**
    * the month chooser
    */
    protected JMonthChooser monthChooser;
    private JButton nullDateButton;
    private JPanel specialButtonPanel;
    private JButton todayButton;
    /**
    * the year chooser
    */
    private JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables

    /**
     * Default JCalendar constructor.
     */
    public JCalendar() {
        this(null, null, true, true);
    }

    /**
     * JCalendar constructor which allows the initial date to be set.
     *
     * @param date the date
     */
    public JCalendar(Date date) {
        this(date, null, true, true);
    }

    /**
     * JCalendar constructor which allows the initial calendar to be set.
     *
     * @param calendar the calendar
     */
    public JCalendar(Calendar calendar) {
        this(null, null, true, true);
        setCalendar(calendar);
    }

    /**
     * JCalendar constructor allowing the initial locale to be set.
     *
     * @param locale the new locale
     */
    public JCalendar(Locale locale) {
        this(null, locale, true, true);
    }

    /**
     * JCalendar constructor specifying both the initial date and locale.
     *
     * @param date the date
     * @param locale the new locale
     */
    public JCalendar(Date date, Locale locale) {
        this(date, locale, true, true);
    }

    /**
     * JCalendar constructor specifying both the initial date and the month
     * spinner type.
     *
     * @param date the date
     * @param monthSpinner false, if no month spinner should be used
     */
    public JCalendar(Date date, boolean monthSpinner) {
        this(date, null, monthSpinner, true);
    }

    /**
     * JCalendar constructor specifying both the locale and the month spinner.
     *
     * @param locale the locale
     * @param monthSpinner false, if no month spinner should be used
     */
    public JCalendar(Locale locale, boolean monthSpinner) {
        this(null, locale, monthSpinner, true);
    }

    /**
     * JCalendar constructor specifying the month spinner type.
     *
     * @param monthSpinner false, if no month spinner should be used
     */
    public JCalendar(boolean monthSpinner) {
        this(null, null, monthSpinner, true);
    }

    /**
     * JCalendar constructor with month spinner parameter.
     *
     * @param date the date
     * @param locale the locale
     * @param monthSpinner false, if no month spinner should be used
     * @param weekOfYearVisible true, if weeks of year shall be visible
     */
    public JCalendar(Date date, Locale locale, boolean monthSpinner, boolean weekOfYearVisible) {
        this.weekOfYearVisible = weekOfYearVisible;
        this.monthSpinner = monthSpinner;

        if (locale == null) {
            this.locale = Locale.getDefault();
        } else {
            this.locale = locale;
        }
        initComponents();
        initialize(date);
    }

    private void initialize(Date date) {
        calendar = getInstance();
        
        // Set the initialized flag before setting the calendar. This will
        // cause the other components to be updated properly.
        if (date != null) {
            calendar.setTime(date);
        }
        
        initialized = true;

        setCalendar(calendar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todayButton = new JButton();
        nullDateButton = new JButton();
        JPanel monthYearPanel = new JPanel();
        yearChooser = new JYearChooser();
        monthChooser = new JMonthChooser(monthSpinner)
        ;
        dayChooser = new JDayChooser(weekOfYearVisible);
        specialButtonPanel = new JPanel();
        specialButtonPanel.setVisible(false);

        todayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                todayButtonActionPerformed(evt);
            }
        });

        nullDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nullDateButtonActionPerformed(evt);
            }
        });

        setName("JCalendar"); // NOI18N
        setLayout(new BorderLayout());

        monthYearPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        monthYearPanel.setLayout(new BorderLayout());

        yearChooser.setDayChooser(dayChooser);
        yearChooser.addPropertyChangeListener(this);
        monthYearPanel.add(yearChooser, BorderLayout.CENTER);

        monthChooser.setDayChooser(dayChooser);
        monthChooser.setYearChooser(yearChooser);
        monthChooser.addPropertyChangeListener(this);
        monthYearPanel.add(monthChooser, BorderLayout.WEST);

        add(monthYearPanel, BorderLayout.NORTH);

        dayChooser.addPropertyChangeListener(this);
        add(dayChooser, BorderLayout.CENTER);
        add(specialButtonPanel, BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void nullDateButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_nullDateButtonActionPerformed
        dayChooser.firePropertyChange("day", 0, -1);
    }//GEN-LAST:event_nullDateButtonActionPerformed

    private void todayButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_todayButtonActionPerformed
        setDate(new Date());
    }//GEN-LAST:event_todayButtonActionPerformed

    /**
     * Returns the calendar property.
     *
     * @return the value of the calendar property.
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Gets the dayChooser attribute of the JCalendar object
     *
     * @return the dayChooser value
     */
    public JDayChooser getDayChooser() {
        return dayChooser;
    }

    /**
     * Returns the locale.
     *
     * @return the value of the locale property.
     *
     * @see #setLocale
     */
    @Override
    public Locale getLocale() {
        return locale;
    }

    /**
     * Gets the monthChooser attribute of the JCalendar object
     *
     * @return the monthChooser value
     */
    public JMonthChooser getMonthChooser() {
        return monthChooser;
    }

    /**
     * Gets the yearChooser attribute of the JCalendar object
     *
     * @return the yearChooser value
     */
    public JYearChooser getYearChooser() {
        return yearChooser;
    }

    /**
     * Indicates if the weeks of year are visible..
     *
     * @return boolean true, if weeks of year are visible
     */
    public boolean isWeekOfYearVisible() {
        return dayChooser.isWeekOfYearVisible();
    }

    /**
     * JCalendar is a PropertyChangeListener, for its day, month and year
     * chooser.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (calendar != null) {
            Calendar c = (Calendar) calendar.clone();

            switch (evt.getPropertyName()) {
                case "day":
                    c.set(Calendar.DAY_OF_MONTH, ((Integer) evt.getNewValue()));
                    setCalendar(c, false);
                    break;
                case "month":
                    c.set(Calendar.MONTH, ((Integer) evt.getNewValue()));
                    setCalendar(c, false);
                    break;
                case "year":
                    c.set(Calendar.YEAR, ((Integer) evt.getNewValue()));
                    setCalendar(c, false);
                    break;
                case "date":
                    c.setTime((Date) evt.getNewValue());
                    setCalendar(c, true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sets the background color.
     *
     * @param bg the new background
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);

        if (dayChooser != null) {
            dayChooser.setBackground(bg);
        }
    }

    /**
     * Sets the calendar property. This is a bound property.
     *
     * @param c the new calendar
     * @throws NullPointerException - if c is null;
     * @see #getCalendar
     */
    public void setCalendar(Calendar c) {
        setCalendar(c, true);
    }

    /**
     * Sets the calendar attribute of the JCalendar object
     *
     * @param c the new calendar value
     * @param update the new calendar value
     * @throws NullPointerException - if c is null;
     */
    private void setCalendar(Calendar c, boolean update) {
        if (c == null) {
            setDate(null);
        }
        Calendar oldCalendar = calendar;
        calendar = c;

        if (update) {
            if(calendar == null){
                calendar = getInstance();
            }
            // Thanks to Jeff Ulmer for correcting a bug in the sequence :)
            yearChooser.setYear(calendar.get(Calendar.YEAR));
            monthChooser.setMonth(calendar.get(Calendar.MONTH));
            dayChooser.setDay(calendar.get(Calendar.DATE));
        }

        firePropertyChange("calendar", oldCalendar, calendar);
    }

    /**
     * Enable or disable the JCalendar.
     *
     * @param enabled the new enabled value
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (dayChooser != null) {
            dayChooser.setEnabled(enabled);
            monthChooser.setEnabled(enabled);
            yearChooser.setEnabled(enabled);
        }
    }

    /**
     * Sets the font property.
     *
     * @param font the new font
     */
    @Override
    public void setFont(Font font) {
        super.setFont(font);

        if (dayChooser != null) {
            dayChooser.setFont(font);
            monthChooser.setFont(font);
            yearChooser.setFont(font);
        }
    }

    /**
     * Sets the foreground color.
     *
     * @param fg the new foreground
     */
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);

        if (dayChooser != null) {
            dayChooser.setForeground(fg);
            monthChooser.setForeground(fg);
            yearChooser.setForeground(fg);
        }
    }

    /**
     * Sets the locale property. This is a bound property.
     *
     * @param l the new locale value
     *
     * @see #getLocale
     */
    @Override
    public void setLocale(Locale l) {
        if (!initialized) {
            super.setLocale(l);
        } else {
            Locale oldLocale = locale;
            locale = l;
            dayChooser.setLocale(locale);
            monthChooser.setLocale(locale);
            relayoutSpecialButtonPanel();
            firePropertyChange("locale", oldLocale, locale);
        }
    }


    public void repaintSpecialButtonSection() {
        todayButton.invalidate();
        todayButton.repaint();
        nullDateButton.invalidate();
        nullDateButton.repaint();
        specialButtonPanel.invalidate();
        specialButtonPanel.doLayout();
        specialButtonPanel.repaint();
        invalidate();
        repaint();
    }

    private void addButtonsToButtonPanel() {
        if (isTodayButtonVisible) {
            specialButtonPanel.add(todayButton);
        }
        if (isNullDateButtonVisible) {
            specialButtonPanel.add(nullDateButton);
        }
    }

    private int configureSpecialButtons(ResourceBundle resourceBundle) {
        int buttonCount = 0;
        if (isTodayButtonVisible) {
            configureTodayButton(resourceBundle);
            buttonCount++;
        }
        if (isNullDateButtonVisible) {
            configureNullDateButton(resourceBundle);
            buttonCount++;
        }
        return buttonCount;
    }

    private void configureNullDateButton(ResourceBundle resourceBundle) {
        String text = nullDateButtonText;
        if (text == null && resourceBundle != null) {
            try {
                text = resourceBundle.getString("nullDateButton.text");
            } catch (Exception e) {
                // ignore, fall back to set texts or defaults
            }
        }
        if (text == null) {
            text = DEFAULT_NULL_DATE_BUTTON_TEXT;
        }
        nullDateButton.setText(text);
    }

    private void configureTodayButton(ResourceBundle resourceBundle) {
        String text = todayButtonText;
        if (text == null && resourceBundle != null) {
            try {
                text = resourceBundle.getString("todayButton.text");
            } catch (Exception e) {
                // ignore, fall back to set texts or defaults
            }
        }
        if (text == null) {
            text = DEFAULT_TODAY_BUTTON_TEXT;
        }
        todayButton.setText(text);
    }

    /**
     * Sets the week of year visible.
     *
     * @param weekOfYearVisible true, if weeks of year shall be visible
     */
    public void setWeekOfYearVisible(boolean weekOfYearVisible) {
        dayChooser.setWeekOfYearVisible(weekOfYearVisible);
        setLocale(locale); // hack for doing complete new layout :)
    }

    /**
     * Gets the visibility of the decoration background.
     *
     * @return true, if the decoration background is visible.
     */
    public boolean isDecorationBackgroundVisible() {
        return dayChooser.isDecorationBackgroundVisible();
    }

    /**
     * Sets the decoration background visible.
     *
     * @param decorationBackgroundVisible true, if the decoration background
     * should be visible.
     */
    public void setDecorationBackgroundVisible(boolean decorationBackgroundVisible) {
        dayChooser.setDecorationBackgroundVisible(decorationBackgroundVisible);
        setLocale(locale); // hack for doing complete new layout :)
    }

    /**
     * Gets the visibility of the decoration border.
     *
     * @return true, if the decoration border is visible.
     */
    public boolean isDecorationBordersVisible() {
        return dayChooser.isDecorationBordersVisible();
    }

    /**
     * Sets the decoration borders visible.
     *
     * @param decorationBordersVisible true, if the decoration borders should be
     * visible.
     */
    public void setDecorationBordersVisible(boolean decorationBordersVisible) {
        dayChooser.setDecorationBordersVisible(decorationBordersVisible);
        setLocale(locale); // hack for doing complete new layout :)
    }

    /**
     * Returns the color of the decoration (day names and weeks).
     *
     * @return the color of the decoration (day names and weeks).
     */
    public Color getDecorationBackgroundColor() {
        return dayChooser.getDecorationBackgroundColor();
    }

    /**
     * Sets the background of days and weeks of year buttons.
     *
     * @param decorationBackgroundColor the background color
     */
    public void setDecorationBackgroundColor(Color decorationBackgroundColor) {
        dayChooser.setDecorationBackgroundColor(decorationBackgroundColor);
    }

    /**
     * Returns the Sunday foreground.
     *
     * @return Color the Sunday foreground.
     */
    public Color getSundayForeground() {
        return dayChooser.getSundayForeground();
    }

    /**
     * Returns the weekday foreground.
     *
     * @return Color the weekday foreground.
     */
    public Color getWeekdayForeground() {
        return dayChooser.getWeekdayForeground();
    }

    /**
     * Sets the Sunday foreground.
     *
     * @param sundayForeground the sundayForeground to set
     */
    public void setSundayForeground(Color sundayForeground) {
        dayChooser.setSundayForeground(sundayForeground);
    }

    /**
     * Sets the weekday foreground.
     *
     * @param weekdayForeground the weekdayForeground to set
     */
    public void setWeekdayForeground(Color weekdayForeground) {
        dayChooser.setWeekdayForeground(weekdayForeground);
    }

    /**
     * Returns a Date object.
     *
     * @return a date object constructed from the calendar property.
     */
    public Date getDate() {
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * Sets the date. Fires the property change "date".
     *
     * @param date the new date.
     * @throws NullPointerException - if the date is null
     */
    public void setDate(Date date) throws NullPointerException {
        Date oldDate = calendar.getTime();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        yearChooser.setYear(year);
        monthChooser.setMonth(month);
        dayChooser.setCalendar(calendar);
        dayChooser.setDay(day);

        firePropertyChange("date", oldDate, date);
    }

    /**
     * Sets a valid date range for selectable dates. If max is before min, the
     * default range with no limitation is set.
     *
     * @param min the minimum selectable date or null (then the minimum date is
     * set to 01\01\0001)
     * @param max the maximum selectable date or null (then the maximum date is
     * set to 01\01\9999)
     */
    public void setSelectableDateRange(Date min, Date max) {
        dayChooser.setSelectableDateRange(min, max);
    }

    /**
     * Gets the minimum selectable date.
     *
     * @return the minimum selectable date
     */
    public Date getMaxSelectableDate() {
        return dayChooser.getMaxSelectableDate();
    }

    /**
     * Gets the maximum selectable date.
     *
     * @return the maximum selectable date
     */
    public Date getMinSelectableDate() {
        return dayChooser.getMinSelectableDate();
    }

    /**
     * Sets the maximum selectable date.
     *
     * @param max maximum selectable date
     */
    public void setMaxSelectableDate(Date max) {
        dayChooser.setMaxSelectableDate(max);
    }

    /**
     * Sets the minimum selectable date.
     *
     * @param min minimum selectable date
     */
    public void setMinSelectableDate(Date min) {
        dayChooser.setMinSelectableDate(min);
    }

    /**
     * Gets the current {@link DateVerifier}.
     *
     * @return the {@link DateVerifier}, or <code>null</code> if no verifier is
     * established.
     */
    public DateVerifier getDateVerifier() {
        return dayChooser.getDateVerifier();
    }

    /**
     * Sets the argument as the {@link DateVerifier} for this calendar. If the
     * argument is <code>null</code> then any existing verifier is removed.
     * <br>
     * <strong>Note:</strong> Validation first takes place against the current
     * <code>minSelectableDate</code> and <code>maxSelectableDate</code>. Only
     * if the date passes these checks is any DateVerifier then invoked.
     *
     * @param dateVerifier The {@link DateVerifier}.
     */
    public void setDateVerifier(DateVerifier dateVerifier) {
        DateVerifier delegatingDate = dateVerifier != null
                ? new DelegatingDateVerifier(dateVerifier) : null;

        dayChooser.setDateVerifier(delegatingDate);
    }

    /**
     * Gets the maximum number of characters of a day name or 0. If 0 is
     * returned, dateFormatSymbols.getShortWeekdays() will be used.
     *
     * @return the maximum number of characters of a day name or 0.
     */
    public int getMaxDayCharacters() {
        return dayChooser.getMaxDayCharacters();
    }

    /**
     * Sets the maximum number of characters per day in the day bar. Valid
     * values are 0-4. If set to 0, dateFormatSymbols.getShortWeekdays() will be
     * used, otherwise theses strings will be reduced to the maximum number of
     * characters.
     *
     * @param maxDayCharacters the maximum number of characters of a day name.
     */
    public void setMaxDayCharacters(int maxDayCharacters) {
        dayChooser.setMaxDayCharacters(maxDayCharacters);
    }
    
    /**
     * Sets the Today button visible.
     *
     * @param isTodayButtonVisible true, is the today button shall be visible.
     */
    public void setTodayButtonVisible(boolean isTodayButtonVisible) {
        this.isTodayButtonVisible = isTodayButtonVisible;
        relayoutSpecialButtonPanel();
    }

    /**
     * @return true, if Today button is visible.
     */
    public boolean isTodayButtonVisible() {
        return isTodayButtonVisible;
    }

    /**
     * Sets the Null Date button visible.
     *
     * @param isNullDateButtonVisible true, is the Null Date button shall be
     * visible.
     */
    public void setNullDateButtonVisible(boolean isNullDateButtonVisible) {
        this.isNullDateButtonVisible = isNullDateButtonVisible;
        relayoutSpecialButtonPanel();
    }

    /**
     * @return true, if Null Date button is visible.
     */
    public boolean isNullDateButtonVisible() {
        return isNullDateButtonVisible;
    }

    private void relayoutSpecialButtonPanel() {
        ResourceBundle resourceBundle = null;

        try {
            resourceBundle = UTF8ResourceBundle.getBundle(
                    "com.toedter.calendar.jcalendar", locale);
        } catch (Exception e) {
            // ignore, fall back to set texts or defaults
            System.out.println(e.getMessage());
        }

        specialButtonPanel.removeAll();
        int buttonCount = configureSpecialButtons(resourceBundle);

        specialButtonPanel.setLayout(new GridLayout(1, buttonCount));
        addButtonsToButtonPanel();

        specialButtonPanel.setVisible(buttonCount > 0); // isNullDateButtonVisible || isTodayButtonVisible

        repaintSpecialButtonSection();
    }
    /**
     * @return the text of the Today button
     */
    public String getTodayButtonText() {
        return todayButtonText;
    }

    /**
     * Sets the Today button text.
     *
     * @param todayButtonText the new text
     */
    public void setTodayButtonText(String todayButtonText) {
        if (todayButtonText != null && todayButtonText.trim().length() == 0) {
            this.todayButtonText = null;
        } else {
            this.todayButtonText = todayButtonText;
        }
        relayoutSpecialButtonPanel();
    }

    /**
     * @return the text of the Null Date button
     */
    public String getNullDateButtonText() {
        return nullDateButtonText;
    }

    /**
     * Sets the Null Date button text.
     *
     * @param nullDateButtonText the new text
     */
    public void setNullDateButtonText(String nullDateButtonText) {
        if (nullDateButtonText != null
                && nullDateButtonText.trim().length() == 0) {
            this.nullDateButtonText = null;
        } else {
            this.nullDateButtonText = nullDateButtonText;
        }
        relayoutSpecialButtonPanel();
    }
    
    // Pass on this component instead of the JDayChooser
    private class DelegatingDateVerifier implements DateVerifier {

        private final DateVerifier verifier;

        private DelegatingDateVerifier(DateVerifier verifier) {
            this.verifier = verifier;
        }

        @Override
        public boolean valid(JComponent source, Calendar date) {
            return verifier.valid(JCalendar.this, date);
        }
    }
}
