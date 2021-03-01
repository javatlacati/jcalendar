package com.toedter.calendar;

import com.toedter.pageobject.calendar.JDateChooserPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JDateChooserTest {
    private static final Color CORRECT_COLOR = new Color(0, 150, 0);
    private static final Color DEFAULT_COLOR = new Color(51, 51, 51);
    private JDateChooser jDateChooser;
    private JFrame frame;
    private JDateChooserPageObject pageObject;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("creating JFrame");
        frame = new JFrame("AJFrame");
        frame.setLayout(new BorderLayout());
    }

    @AfterEach
    public void tearDown() {
        frame.setVisible(false);
        frame.dispose();
        frame = null;
    }

    public void secondSetup() {
        frame.add(jDateChooser);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pageObject = new JDateChooserPageObject("AJFrame");
        System.out.println("JFrame should be visible");
    }

    @Test
    @DisplayName("Date should be empty for the component if no parameters are specified")
    public void defaultDate() {
        jDateChooser = new JDateChooser();
        jDateChooser.setLocale(Locale.US);
        // JDateChooser dateChooser = new JDateChooser(null, new Date(), null,
        // null);
        // dateChooser.setLocale(new Locale("de"));
        // dateChooser.setDateFormatString("dd. MMMM yyyy");

        // dateChooser.setPreferredSize(new Dimension(130, 20));
        // dateChooser.setFont(new Font("Verdana", Font.PLAIN, 10));
        // dateChooser.setDateFormatString("yyyy-MM-dd HH:mm");
        // URL iconURL = dateChooser.getClass().getResource(
        // "/com/toedter/calendar/images/JMonthChooserColor32.gif");
        // ImageIcon icon = new ImageIcon(iconURL);
        // dateChooser.setIcon(icon);
        secondSetup();
        assertEquals("", pageObject.getTextFieldValue());
    }

    @Test
    @DisplayName("It should change the color of the textfield to black when entered a valid value")
    public void redTextForNonNumbers() {
        jDateChooser = new JDateChooser();
        jDateChooser.setLocale(Locale.US);
        secondSetup();
        assertEquals(System.getProperty("os.name", "generic").toLowerCase().contains("mac") ? Color.BLACK : DEFAULT_COLOR, pageObject.getTextFieldForegroundColor());
        pageObject.setTextFieldContent("hello");
        assertEquals(Color.RED, pageObject.getTextFieldForegroundColor());
        pageObject.setTextFieldContent("Jan 5, 2020");
        assertEquals(CORRECT_COLOR, pageObject.getTextFieldForegroundColor());
    }

    @Test
    @DisplayName("It should change the color of the textfield to red when entered an invalid value")
    public void blackTextForNumbers() {
        jDateChooser = new JDateChooser();
        jDateChooser.setLocale(Locale.US);
        secondSetup();
        assertEquals(System.getProperty("os.name").contains("Mac") ? Color.BLACK : DEFAULT_COLOR, pageObject.getTextFieldForegroundColor());
        pageObject.setTextFieldContent("Jan 5, 2020");
        assertEquals(CORRECT_COLOR, pageObject.getTextFieldForegroundColor());
        pageObject.setTextFieldContent("hello");
        assertEquals(Color.RED, pageObject.getTextFieldForegroundColor());
    }
}
