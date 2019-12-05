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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

/**
 * JTextFieldDateEditor is the default editor used by JDateChooser. It is a
 * formatted text field, that colores valid dates green/black and invalid dates
 * red. The date format patten and mask can be set manually. If not set, the
 * MEDIUM pattern of a SimpleDateFormat with regards to the actual locale is
 * used.
 *
 * @author Kai Toedter
 * @version $LastChangedRevision: 97 $
 * @version $LastChangedDate: 2006-05-24 17:30:41 +0200 (Mi, 24 Mai 2006) $
 */
public class JTextFieldDateEditor extends JFormattedTextField implements IDateEditor,
        CaretListener, FocusListener, ActionListener {

    private static final long serialVersionUID = -8901842591101625304L;

    protected Date date;

    protected SimpleDateFormat dateFormatter;

    protected MaskFormatter maskFormatter;

    protected String datePattern;

    protected String maskPattern;

    protected char placeholder;

    protected Color darkGreen;

    protected DateUtil dateUtil;

    protected String nullText = "";

    private boolean isMaskVisible;

    private boolean ignoreDatePatternChange;

    private boolean selectOnFocus;

    public JTextFieldDateEditor() {
        this(false, null, null, ' ');
    }

    public JTextFieldDateEditor(String datePattern, String maskPattern, char placeholder) {
        this(true, datePattern, maskPattern, placeholder);
    }

    public JTextFieldDateEditor(boolean showMask, String datePattern, String maskPattern,
            char placeholder) {
        dateFormatter = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateFormatter.setLenient(false);

        if (datePattern != null) {
            ignoreDatePatternChange = true;
        }

        this.placeholder = placeholder;
        darkGreen = new Color(0, 150, 0);
        dateUtil = new DateUtil();
        
        init(datePattern, maskPattern, showMask);
    }

    private void init(String datePattern, String maskPattern, boolean showMask) {
        setDateFormatString(datePattern);
        if (maskPattern == null) {
            this.maskPattern = createMaskFromDatePattern(this.datePattern);
        } else {
            this.maskPattern = maskPattern;
        }
        setToolTipText(this.datePattern);
        setMaskVisible(showMask);
        addCaretListener(this);
        addFocusListener(this);
        addActionListener(this);
        setDateFormatCalendar(Calendar.getInstance());
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#getDate()
     */
    @Override
    public Date getDate() {
        try {
            date = dateFormatter.parse(getText());
        } catch (ParseException e) {
            date = null;
        }
        return date == null ? null : new Date(date.getTime());
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#setDate(java.util.Date)
     */
    @Override
    public void setDate(Date date) {
        setDate(date, true);
    }

    /**
     * Sets the date.
     *
     * @param date the date
     * @param firePropertyChange true, if the date property should be fired.
     */
    protected void setDate(Date date, boolean firePropertyChange) {
        Date oldDate = this.date;

        if (date == null) {
            setText(nullText);
        } else {
            this.date = new Date(date.getTime());
            String formattedDate = dateFormatter.format(date);
            try {
                setText(formattedDate);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            if (dateUtil.checkDate(date)) {
                setForeground(Color.BLACK);
            }
        }

        // && ... prevent repeated events when old and new are null.
        if (firePropertyChange && oldDate != date) {
            firePropertyChange("date", oldDate, date);
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#setDateFormatString(java.lang.String)
     */
    @Override
    public void setDateFormatString(String dateFormatString) {
        if (ignoreDatePatternChange) {
            return;
        }

        try {
            dateFormatter.applyPattern(dateFormatString);
        } catch (RuntimeException e) {
            dateFormatter = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM);
            dateFormatter.setLenient(false);
        }
        this.datePattern = dateFormatter.toPattern();
        setToolTipText(this.datePattern);
        setDate(date, false);
    }

    /*
   * (non-Javadoc)
   * 
   * @see com.toedter.calendar.IDateEditor#getDateFormatString()
     */
    @Override
    public String getDateFormatString() {
        return datePattern;
    }

    /*
   * (non-Javadoc)
   * 
   * @see com.toedter.calendar.IDateEditor#getDateFormat()
     */
    @Override
    public DateFormat getDateFormat() {
        return dateFormatter;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#getUiComponent()
     */
    @Override
    public JComponent getUiComponent() {
        return this;
    }

    /**
     * Sets the calendar that is associated with this date editor's date
     * formatter.
     *
     * @param calendar a Calendar
     */
    @Override
    public void setDateFormatCalendar(Calendar calendar) {
        dateFormatter.setCalendar(calendar != null ? calendar : Calendar.getInstance());
        setDate(date, false);
    }

    /**
     * Returns the calendar that is associated with this date editor's date
     * formatter.
     *
     * @return a Calendar
     */
    @Override
    public Calendar getDateFormatCalendar() {
        return dateFormatter.getCalendar();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#getTextComponent()
     */
    @Override
    public JTextComponent getTextComponent() {
        return this;
    }

    /**
     * After any user input, the value of the textfield is proofed. Depending on
     * being a valid date, the value is colored green or red.
     *
     * @param event the caret event
     */
    @Override
    public void caretUpdate(CaretEvent event) {
        String text = getText().trim();
        String emptyMask = maskPattern.replace('#', placeholder);

        if (text.length() == 0 || text.equals(emptyMask)) {
            setForeground(Color.BLACK);
            return;
        }

        try {
            Date parsedDate = dateFormatter.parse(getText());
            if (dateUtil.checkDate(parsedDate)) {
                setForeground(darkGreen);
            } else {
                setForeground(Color.RED);
            }
        } catch (ParseException e) {
            if (!nullText.equals(text)) {
                setForeground(Color.RED);
            }
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent focusEvent) {
        String text = getText();
        if (text.length() == 0) {
            setDate(null);
        } else {
            checkText();
        }
    }

    private void checkText() {
        try {
            Date parsedDate = dateFormatter.parse(getText());
            setDate(parsedDate, true);
        } catch (ParseException e) {
            // If the text is bad then set it to something good
            if (date == null) {
                setText(nullText);
            } else {
                setText(dateFormatter.format(date));
            }
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (selectOnFocus) {
            selectAll();
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setLocale(java.util.Locale)
     */
    @Override
    public void setLocale(Locale locale) {
        if (locale == getLocale() || ignoreDatePatternChange) {
            return;
        }

        super.setLocale(locale);
        dateFormatter = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        setToolTipText(dateFormatter.toPattern());

        setDate(date, false);
        doLayout();
    }

    /**
     * Creates a mask from a date pattern. This is a very simple (and
     * incomplete) implementation thet works only with numbers. A date pattern
     * of "MM/dd/yy" will result in the mask "##/##/##". Probably you want to
     * override this method if it does not fit your needs.
     *
     * @param datePattern the date pattern
     * @return the mask
     */
    public String createMaskFromDatePattern(String datePattern) {
        String symbols = "GyMdkHmsSEDFwWahKzZ";
        String mask = "";
        for (int i = 0; i < datePattern.length(); i++) {
            char ch = datePattern.charAt(i);
            boolean symbolFound = false;
            for (int n = 0; n < symbols.length(); n++) {
                if (symbols.charAt(n) == ch) {
                    mask += "#";
                    symbolFound = true;
                    break;
                }
            }
            if (!symbolFound) {
                mask += ch;
            }
        }
        return mask;
    }

    /**
     * Returns true, if the mask is visible.
     *
     * @return true, if the mask is visible
     */
    public boolean isMaskVisible() {
        return isMaskVisible;
    }

    /**
     * Sets the mask visible.
     *
     * @param isMaskVisible true, if the mask should be visible
     */
    public void setMaskVisible(boolean isMaskVisible) {
        this.isMaskVisible = isMaskVisible;
        if (isMaskVisible && maskFormatter == null) {
            try {
                maskFormatter = new MaskFormatter(createMaskFromDatePattern(datePattern));
                maskFormatter.setPlaceholderCharacter(this.placeholder);
                maskFormatter.install(this);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the preferred size. If a date pattern is set, it is the size the
     * date pattern would take.
     */
    @Override
    public Dimension getPreferredSize() {
        if (datePattern != null) {
            return new JTextField(datePattern).getPreferredSize();
        }
        return super.getPreferredSize();
    }

    /**
     * Validates the typed date and sets it (only if it is valid).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        checkText();
    }

    /**
     * Enables and disabled the compoment.It also fixes the background bug
     * 4991597 and sets the background explicitely to a
     * TextField.inactiveBackground.
     *
     * @param b enabled
     */
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (!b) {
            super.setBackground(UIManager.getColor("TextField.inactiveBackground"));
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#getMaxSelectableDate()
     */
    @Override
    public Date getMaxSelectableDate() {
        return dateUtil.getMaxSelectableDate();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#getMinSelectableDate()
     */
    @Override
    public Date getMinSelectableDate() {
        return dateUtil.getMinSelectableDate();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#setMaxSelectableDate(java.util.Date)
     */
    @Override
    public void setMaxSelectableDate(Date max) {
        dateUtil.setMaxSelectableDate(max);
        if (max != null) {
            Date d = getDate();
            if (d != null && d.after(max)) {
                setDate(max);
            }
        }
        checkText();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#setMinSelectableDate(java.util.Date)
     */
    @Override
    public void setMinSelectableDate(Date min) {
        dateUtil.setMinSelectableDate(min);
        if (min != null) {
            Date d = getDate();
            if (d != null && d.before(min)) {
                setDate(min);
            }
        }
        checkText();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.toedter.calendar.IDateEditor#setSelectableDateRange(java.util.Date,
	 *      java.util.Date)
     */
    @Override
    public void setSelectableDateRange(Date min, Date max) {
        dateUtil.setSelectableDateRange(min, max);
        checkText();
    }

    /**
     * @see com.toedter.calendar.IDateEditor#getNullText()
     */
    @Override
    public String getNullText() {
        return nullText;
    }

    /**
     * @see com.toedter.calendar.IDateEditor#getNullText()
     */
    @Override
    public void setNullText(String nullText) {
        if (nullText == null) {
            this.nullText = "";
        } else {
            this.nullText = nullText;
        }
        checkText();
    }

    /**
     * @see com.toedter.calendar.IDateEditor#setSelectOnFocus(boolean)
     */
    @Override
    public void setSelectOnFocus(boolean selectOnFocus) {
        this.selectOnFocus = selectOnFocus;
    }

    /**
     * Creates a JFrame with a JCalendar inside and can be used for testing.
     *
     * @param s The command line arguments
     */
    public static void main(String[] s) {
        JFrame frame = new JFrame("JTextFieldDateEditor");
        JTextFieldDateEditor jTextFieldDateEditor = new JTextFieldDateEditor();
        jTextFieldDateEditor.setDate(new Date());
        frame.getContentPane().add(jTextFieldDateEditor);
        frame.pack();
        frame.setVisible(true);
    }
}
