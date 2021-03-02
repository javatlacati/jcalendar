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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A date chooser containing a date editor and a button, that makes a JCalendar
 * visible for choosing a date. If no date editor is specified, a
 * JTextFieldDateEditor is used as default.
 *
 * @author Ruslan Lopez Carro
 * @author Kai Toedter
 * @version $LastChangedRevision: 101 $
 * @version $LastChangedDate: 2006-06-04 14:42:29 +0200 (So, 04 Jun 2006) $
 */
public class JDateChooser extends JPanel implements ActionListener,
        PropertyChangeListener {

    private static final long serialVersionUID = -4306412745720670722L;

    protected IDateEditor dateEditor;

    protected JCalendar jcalendar;
    protected boolean dateSelected;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton calendarButton;
    private JPopupMenu popup;
    // End of variables declaration//GEN-END:variables

    private ChangeListener changeListener;

    // A working calendar
    private Calendar calendar;

    /**
     * Creates a new JDateChooser. By default, no date is set and the textfield
     * is empty.
     */
    public JDateChooser() {
        this(null, null, null, null);
    }

    /**
     * Creates a new JDateChooser with given IDateEditor.
     *
     * @param dateEditor the dateEditor to be used used to display the date. if
     * null, a JTextFieldDateEditor is used.
     */
    public JDateChooser(IDateEditor dateEditor) {
        this(null, null, null, dateEditor);
    }

    /**
     * Creates a new JDateChooser.
     *
     * @param date the date or null
     */
    public JDateChooser(Date date) {
        this(date, null);
    }

    /**
     * Creates a new JDateChooser.
     *
     * @param date the date or null
     * @param dateFormatString the date format string or null (then MEDIUM
     * SimpleDateFormat format is used)
     */
    public JDateChooser(Date date, String dateFormatString) {
        this(date, dateFormatString, null);
    }

    /**
     * Creates a new JDateChooser.
     *
     * @param date the date or null
     * @param dateFormatString the date format string or null (then MEDIUM
     * SimpleDateFormat format is used)
     * @param dateEditor the dateEditor to be used used to display the date. if
     * null, a JTextFieldDateEditor is used.
     */
    public JDateChooser(Date date, String dateFormatString,
            IDateEditor dateEditor) {
        this(null, date, dateFormatString, dateEditor);
    }

    /**
     * Creates a new JDateChooser. If the JDateChooser is created with this
     * constructor, the mask will be always visible in the date editor. Please
     * note that the date pattern and the mask will not be changed if the locale
     * of the JDateChooser is changed.
     *
     * @param datePattern the date pattern, e.g. "MM/dd/yy"
     * @param maskPattern the mask pattern, e.g. "##/##/##"
     * @param placeholder the placeholer charachter, e.g. '_'
     */
    public JDateChooser(String datePattern, String maskPattern, char placeholder) {
        this(null, null, datePattern, new JTextFieldDateEditor(datePattern,
                maskPattern, placeholder));
    }

    /**
     * Creates a new JDateChooser.
     *
     * @param jcal the JCalendar to be used
     * @param date the date or null
     * @param dateFormatString the date format string or null (then MEDIUM Date
     * format is used)
     * @param dateEditor the dateEditor to be used used to display the date. if
     * null, a JTextFieldDateEditor is used.
     */
    public JDateChooser(JCalendar jcal, Date date, String dateFormatString,
            IDateEditor dateEditor) {
        this.dateEditor = dateEditor;
        configureDateEditor(dateFormatString, date);

        configureJCalendar(jcal, date);

        initComponents();
    }

    private void configureJCalendar(JCalendar jcal, Date date) {
        if (jcal == null) {
            jcalendar = new JCalendar(date);
        } else {
            jcalendar = jcal;
            if (date != null) {
                jcalendar.setDate(date);
            }
        }

        jcalendar.getDayChooser().addPropertyChangeListener("day", this);
        // always fire"day" property even if the user selects
        // the already selected day again
        jcalendar.getDayChooser().setAlwaysFireDayProperty(true);
    }

    private void configureDateEditor(String dateFormatString, Date date) {
        if (this.dateEditor == null) {
            this.dateEditor = new JTextFieldDateEditor();
        }
        setDateFormatString(dateFormatString);
        setDate(date);

        this.dateEditor.addPropertyChangeListener("date", this);
        // Alt + 'C' selects the calendar.
        // calendarButton.setMnemonic(KeyEvent.VK_C);
        // TS: The problem with this is it does not discriminate between
        // multiple date choosers in the same window.
        // Use the input map instead - ctrl-c pops up the calendar
        KeyStroke popupCalendar = KeyStroke.getKeyStroke(KeyEvent.VK_C,
                ActionEvent.CTRL_MASK, false);
        this.dateEditor.getTextComponent().getInputMap().put(popupCalendar, "Popup");

        this.dateEditor.getTextComponent().getActionMap().put("Popup", new AbstractAction() {
            private static final long serialVersionUID = -1913725779079949632L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!JDateChooser.this.isEnabled()) {
                    return;
                }
                JDateChooser.this.actionPerformed(e);
            }
        });

        // Add an action for setting the date to null - ctrl-n
        KeyStroke nullDate = KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK, false);
        this.dateEditor.getTextComponent().getInputMap().put(nullDate, "Null");
        this.dateEditor.getTextComponent().getActionMap().put("Null", new AbstractAction() {
            private static final long serialVersionUID = -1913767779079949632L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!JDateChooser.this.isEnabled()) {
                    return;
                }
                JDateChooser.this.setDate(null);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new JPopupMenu() {
            private static final long serialVersionUID = -6078272560337577761L;

            @Override
            public void setVisible(boolean visible) {
                Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");
                if (visible
                    || (!visible && dateSelected)
                    || ((isCanceled != null) && !visible && isCanceled)) {
                    super.setVisible(visible);
                }
            }
        }
        ;
        popup.add(jcalendar);
        // Display a calendar button with an icon
        URL iconURL = getClass().getResource(
            "/com/toedter/calendar/images/JDateChooserIcon.gif");
        ImageIcon icon = new ImageIcon(iconURL);
        calendarButton = new JButton(icon) {
            private static final long serialVersionUID = -1913767779079949668L;

            @Override
            public boolean isFocusable() {
                return false;
            }
        }
        ;

        addChangeListener();

        setName("JDateChooser"); // NOI18N
        setLayout(new BorderLayout());

        calendarButton.setMargin(new Insets(0, 0, 0, 0));
        calendarButton.addActionListener(this);
        // calendarButton.addFocusListener(this);
        add(calendarButton, BorderLayout.EAST);
        add(this.dateEditor.getUiComponent(), BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the jcalendar button was pressed.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = calendarButton.getWidth()
                - (int) popup.getPreferredSize().getWidth();
        int y = calendarButton.getY() + calendarButton.getHeight();

        Calendar aCalendar = Calendar.getInstance();
        Date date = dateEditor.getDate();
        if (date != null) {
            aCalendar.setTime(date);
        }
        jcalendar.setCalendar(aCalendar);
        popup.show(calendarButton, x, y);
        dateSelected = false;
    }

    /**
     * Listens for a "date" property change or a "day" property change event
     * from the JCalendar. Updates the date editor and closes the popup.
     *
     * @param evt the event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("day")) {
//            if (popup.isVisible(){
//            if (((Integer) evt.getNewValue()).intValue() > 0) {
//                setDate(jcalendar.getCalendar().getTime());
//            } else {
//                setDate(null);
//            }
            if (popup.isVisible() && jcalendar.getCalendar().get(Calendar.MONTH) == jcalendar.monthChooser.getMonth()) {
                dateSelected = true;
                popup.setVisible(false);
                setDate(jcalendar.getCalendar().getTime());
            }
        } else if (evt.getPropertyName().equals("date")) {
            if (evt.getSource() == dateEditor) {
                // If the date came from the editor (instead of the popup)
                // then it could be invalid. Check and possibly modify it.
                // Prevent multiple events (in the reverse order!) if we do.
                // Note that if the new date is modified then the old value is
                // not the original.
                if (checkNewDate((Date) evt.getOldValue(), (Date) evt.getNewValue())) {
                    firePropertyChange("date", evt.getOldValue(), evt.getNewValue());
                }
            } else {
                setDate((Date) evt.getNewValue());
            }
        }
    }

    /**
     * Updates the UI of itself and the popup.
     */
    @Override
    public void updateUI() {
        super.updateUI();
        setEnabled(isEnabled());

        if (jcalendar != null) {
            SwingUtilities.updateComponentTreeUI(popup);
        }
    }

    /**
     * Sets the locale.
     *
     * @param l The new locale value
     */
    @Override
    public void setLocale(Locale l) {
        super.setLocale(l);
        dateEditor.setLocale(l);
        jcalendar.setLocale(l);
    }

    /**
     * Gets the date format string.
     *
     * @return Returns the dateFormatString.
     */
    public String getDateFormatString() {
        return dateEditor.getDateFormatString();
    }

    /**
     * Returns the date formatter.
     *
     * @return the date formatter
     */
    public DateFormat getDateFormat() {
        return dateEditor.getDateFormat();
    }

    /**
     * Sets the date format string. E.g "MMMMM d, yyyy" will result in "July 21,
     * 2004" if this is the selected date and locale is English.
     *
     * @param dfString The dateFormatString to set.
     */
    public void setDateFormatString(String dfString) {
        dateEditor.setDateFormatString(dfString);
        invalidate();
    }

    /**
     * Returns the text that is displayed when the editor's date is
     * <code>null</code>. If there is no null text an empty string is returned.
     *
     * @return the null text
     */
    public String getNullText() {
        return dateEditor.getNullText();
    }

    /**
     * Sets the text to be displayed by the editor when its date is
     * <code>null</code>.
     *
     * @param nullText
     */
    public void setNullText(String nullText) {
        dateEditor.setNullText(nullText);
    }

    /**
     * Sets whether the edior's text will be selected when the editor gains the
     * focus.
     *
     * @param selectOnFocus
     */
    public void setSelectOnFocus(boolean selectOnFocus) {
        dateEditor.setSelectOnFocus(selectOnFocus);
    }

    /**
     * Returns the date. If the JDateChooser is started with a null date and no
     * date was set by the user, null is returned.
     *
     * @return the current date
     */
    public Date getDate() {
        return dateEditor.getDate();
    }

    /**
     * Sets the date. Fires the property change "date" if date != null.
     *
     * @param date the new date.
     */
    public void setDate(Date date) {
        dateEditor.setDate(date);
        if (getParent() != null) {
            getParent().invalidate();
        }
    }

    /**
     * Returns the calendar. If the JDateChooser is started with a null date (or
     * null calendar) and no date was set by the user, null is returned.
     *
     * @return the current calendar
     */
    public Calendar getCalendar() {
        Date date = getDate();
        if (date == null) {
            return null;
        }
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        return aCalendar;
    }

    /**
     * Sets the calendar. Value null will set the null date on the date editor.
     *
     * @param calendar the calendar.
     */
    public void setCalendar(Calendar calendar) {
        if (calendar == null) {
            dateEditor.setDate(null);
        } else {
            dateEditor.setDate(calendar.getTime());
        }
    }

    /**
     * Sets the calendar that is associated with this date editor's date
     * formatter.
     *
     * @param calendar a Calendar
     */
    public void setDateFormatCalendar(Calendar calendar) {
        dateEditor.setDateFormatCalendar(calendar);
    }

    /**
     * Returns the calendar that is associated with this date editor's date
     * formatter.
     *
     * @return a Calendar
     */
    public Calendar getDateFormatCalendar() {
        return dateEditor.getDateFormatCalendar();
    }

    /**
     * Enable or disable the JDateChooser.
     *
     * @param enabled the new enabled value
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (dateEditor != null) {
            dateEditor.setEnabled(enabled);
            calendarButton.setEnabled(enabled);
        }
    }

    /**
     * Sets the icon of the buuton.
     *
     * @param icon The new icon
     */
    public void setIcon(ImageIcon icon) {
        calendarButton.setIcon(icon);
    }

    /**
     * Sets the font of all subcomponents.
     *
     * @param font the new font
     */
    @Override
    public void setFont(Font font) {
        if (dateEditor != null) {
            dateEditor.getUiComponent().setFont(font);
        }
        if (jcalendar != null) {
            jcalendar.setFont(font);
        }

        super.setFont(font);
    }

    /**
     * Returns the JCalendar component. This is useful if you want to set some
     * properties.
     *
     * @return the JCalendar
     */
    public JCalendar getJCalendar() {
        return jcalendar;
    }

    /**
     * Returns the calendar button.
     *
     * @return the calendar button
     */
    public JButton getCalendarButton() {
        return calendarButton;
    }

    /**
     * Returns the date editor.
     *
     * @return the date editor
     */
    public IDateEditor getDateEditor() {
        return dateEditor;
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
        jcalendar.setSelectableDateRange(min, max);
        dateEditor.setSelectableDateRange(jcalendar.getMinSelectableDate(),
                jcalendar.getMaxSelectableDate());
    }

    public void setMaxSelectableDate(Date max) {
        jcalendar.setMaxSelectableDate(max);
        dateEditor.setMaxSelectableDate(max);
    }

    public void setMinSelectableDate(Date min) {
        jcalendar.setMinSelectableDate(min);
        dateEditor.setMinSelectableDate(min);
    }

    /**
     * Gets the maximum selectable date.
     *
     * @return the maximum selectable date
     */
    public Date getMaxSelectableDate() {
        return jcalendar.getMaxSelectableDate();
    }

    /**
     * Gets the minimum selectable date.
     *
     * @return the minimum selectable date
     */
    public Date getMinSelectableDate() {
        return jcalendar.getMinSelectableDate();
    }

    /**
     * Gets the current {@link DateVerifier}.
     *
     * @return the {@link DateVerifier}, or <code>null</code> if no verifier is
     * established.
     */
    public DateVerifier getDateVerifier() {
        return jcalendar.getDateVerifier();
    }

    /**
     * Sets the argument as the {@link DateVerifier} for this date chooser. If
     * the argument is <code>null</code> then any existing verifier is removed.
     * <br>
     * <strong>Note:</strong> Validation first takes place against the current
     * <code>minSelectableDate</code> and <code>maxSelectableDate</code>. Only
     * if the date passes these checks is any DateVerifier then invoked.
     *
     * @param dateVerifier The {@link DateVerifier}.
     */
    public void setDateVerifier(DateVerifier dateVerifier) {
        DateVerifier adateVerifier;
        if (dateVerifier == null) {
            adateVerifier = null;
        } else {
            adateVerifier = new DelegatingDateVerifier(dateVerifier);
        }

        jcalendar.setDateVerifier(adateVerifier);
    }

    /**
     * Should only be invoked if the JDateChooser is not used anymore. Due to
     * popup handling it had to register a change listener to the default menu
     * selection manager which will be unregistered here. Use this method to
     * cleanup possible memory leaks.
     *
     * @deprecated It is no longer necessary to call this method.
     */
    public void cleanup() {
        MenuSelectionManager.defaultManager().removeChangeListener(changeListener);
        changeListener = null;
    }

    @Override
    public boolean requestFocusInWindow() {
        if (dateEditor instanceof JComponent) {
            return ((JComponent) dateEditor).requestFocusInWindow();
        }
        return super.requestFocusInWindow();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        MenuSelectionManager.defaultManager().removeChangeListener(changeListener);
    }

    // Check if new date is, in fact, valid. If not, look for a valid one.
    // Return true if the date was valid, false if it was changed to make
    // it valid.
    private boolean checkNewDate(Date oldValue, Date newValue) {
        // null is always ok
        if (newValue == null) {
            return true;
        }

        // No verifier then ok
        DateVerifier v = getDateVerifier();
        if (v == null) {
            return true;
        }

        // If verifies then ok
        if (calendar == null) {
            calendar = (Calendar) jcalendar.getCalendar().clone();
        }

        calendar.setTime(newValue);
        long was = newValue.getTime();
        if (!v.isInvalid(calendar)) {
            if (calendar.getTimeInMillis() != was) {
                dateEditor.setDate(new Date(calendar.getTimeInMillis()));
            }
            return true;
        }

        // The verifier is at liberty to change the date even if it is
        // still returned as not valid
        newValue.setTime(calendar.getTimeInMillis());

        // Start looking for a new date. Determine the direction of travel.
        // If moving away from null then assume forwards.
        int direction = (oldValue == null || oldValue.before(newValue)) ? 1 : -1;

        Date limit = (direction == 1) ? getMaxSelectableDate()
                : getMinSelectableDate();
        Date d = new Date();
        do {
            calendar.add(Calendar.DAY_OF_YEAR, direction);
            d.setTime(calendar.getTimeInMillis());
        } while (((direction == 1) ? d.before(limit) : d.after(limit))
                && v.isInvalid(calendar));
        if ((direction == 1) ? d.after(limit) : d.before(limit)) {
            d.setTime(limit.getTime());
        }
        dateEditor.setDate(d);

        // We may settle on the limit date even if the validator says it
        // is not valid. This would be a mis-configuration of the datechooser
        // so we have to choose one over the other
        return d.equals(newValue);
    }

    private void addChangeListener() {
        // Corrects a problem that occured when the JMonthChooser's combobox is
        // displayed, and a click outside the popup does not close it.
        // The following idea was originally provided by forum user
        // podiatanapraia:
        ChangeListener changeListener2 = new ChangeListener() {
            boolean hasListened;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (hasListened) {
                    hasListened = false;
                    return;
                }
                if (popup.isVisible()
                        && JDateChooser.this.jcalendar.monthChooser
                                .getComboBox().hasFocus()) {
                    MenuElement[] me = MenuSelectionManager.defaultManager()
                            .getSelectedPath();
                    MenuElement[] newMe = new MenuElement[me.length + 1];
                    newMe[0] = popup;
                    System.arraycopy(me, 0, newMe, 1, me.length);
                    hasListened = true;
                    MenuSelectionManager.defaultManager()
                            .setSelectedPath(newMe);
                }
            }
        };
// Thanks to forum member Kris Kemper for the memory leak fix (and below also)
        changeListener = new WeakChangeListenerProxy(changeListener2);

        MenuSelectionManager.defaultManager().addChangeListener(changeListener);
// end of code provided by forum user podiatanapraia

    }

    // Pass on this component instead of the JCalendar
    private class DelegatingDateVerifier implements DateVerifier {

        private final DateVerifier verifier;

        private DelegatingDateVerifier(DateVerifier verifier) {
            this.verifier = verifier;
        }

        @Override
        public boolean isInvalid(Calendar date) {
            return verifier.isInvalid(date);
        }
    }
}
