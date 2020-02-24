package com.toedter.calendar;

import com.toedter.pageobject.calendar.JHourMinuteChooserPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(value = CONCURRENT)
public class JHourMinuteChooserTest {
    private JHourMinuteChooser jHourMinuteChooser;
    private JFrame frame;
    private JHourMinuteChooserPageObject pageObject;
    private static final SimpleDateFormat MERIDIAN_FORMAT = new SimpleDateFormat("a");

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
        frame.add(jHourMinuteChooser);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pageObject = new JHourMinuteChooserPageObject("AJFrame");
        System.out.println("JFrame should be visible");
    }

    @Test
    @DisplayName("Time should be the current for the component if no parameters are specified")
    public void defaultDate() {
        Calendar cal = Calendar.getInstance();
        jHourMinuteChooser = new JHourMinuteChooser();
        secondSetup();
        assertEquals(2, pageObject.getMeridianSpinnerValueCount());

        assertEquals(MERIDIAN_FORMAT.format(cal.getTime()), pageObject.getMeridianSpinnerValue());
        int hour = cal.get(Calendar.HOUR);
        if(hour == 0){
            hour = 12;
        }
        assertEquals(Integer.toString(hour), pageObject.getHourSpinnerValue());
        assertEquals(String.format("%02d", cal.get(Calendar.MINUTE)), pageObject.getMinuteSpinnerValue());
        assertTrue(pageObject.isHourSpinnerEnabled(), "Hour spinner should be enabled if the checkbox was not clicked");
        assertTrue(pageObject.isMeridianSpinnerEnabled(), "Meridian spinner should be enabled if the checkbox was not clicked");
        assertTrue(pageObject.isMinuteSpinnerEnabled(), "Minute spinner should be enabled if the checkbox was not clicked");
    }
    
    @Test
    @DisplayName("Time should be the specified for the component")
    public void customDate() {
        Calendar cal = Calendar.getInstance();
        jHourMinuteChooser = new JHourMinuteChooser(0,12);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 12);
        secondSetup();
        assertEquals(2, pageObject.getMeridianSpinnerValueCount());

        assertEquals(MERIDIAN_FORMAT.format(cal.getTime()), pageObject.getMeridianSpinnerValue());
        int hour = cal.get(Calendar.HOUR);
        if (hour == 0) {
            hour = 12;
        }

        assertEquals(Integer.toString(hour), pageObject.getHourSpinnerValue());
        assertEquals(String.format("%02d", cal.get(Calendar.MINUTE)), pageObject.getMinuteSpinnerValue());
        assertTrue(pageObject.isHourSpinnerEnabled(), "Hour spinner should be enabled if the checkbox was not clicked");
        assertTrue(pageObject.isMeridianSpinnerEnabled(), "Meridian spinner should be enabled if the checkbox was not clicked");
        assertTrue(pageObject.isMinuteSpinnerEnabled(), "Minute spinner should be enabled if the checkbox was not clicked");
    }

    @Test
    @DisplayName("Time should move if checkbox is clicked after 1 minute")
    @Disabled
    public void checkBox() {
        jHourMinuteChooser = new JHourMinuteChooser();
        secondSetup();
        Calendar cal = Calendar.getInstance();
        pageObject.clickCheckbox();
        assertFalse(pageObject.isHourSpinnerEnabled());
        assertFalse(pageObject.isMeridianSpinnerEnabled());
        assertFalse(pageObject.isMinuteSpinnerEnabled());

        String minuteSpinnerValue = pageObject.getMinuteSpinnerValue();
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotEquals(pageObject.getMinuteSpinnerValue(), minuteSpinnerValue);

        Calendar cal1 = Calendar.getInstance();
        if (cal1.get(Calendar.HOUR) > cal.get(Calendar.HOUR)) {
            assertThat(Integer.parseInt(pageObject.getHourSpinnerValue()), greaterThanOrEqualTo(cal.get(Calendar.HOUR)));
        }
        if (cal1.get(Calendar.MINUTE) > cal.get(Calendar.MINUTE)) {
            assertThat(Integer.parseInt(minuteSpinnerValue), greaterThanOrEqualTo(cal.get(Calendar.MINUTE)));
        }
    }
}
