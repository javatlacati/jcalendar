package com.toedter.calendar;

import com.toedter.pageobject.calendar.JDayChooserPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormatSymbols;
import java.util.Locale;

import static java.lang.System.arraycopy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JDayChooserTest {
  private static final Color RED = new Color(164, 0, 0);
  private JDayChooser jDayChooser;
  private JFrame frame;
  private JDayChooserPageObject pageObject;

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
    frame.add(jDayChooser);
    frame.setSize(400, 400);
    frame.setVisible(true);
    pageObject = new JDayChooserPageObject("AJFrame");
    System.out.println("JFrame should be visible");
  }

  @Test
  @DisplayName("Week day names should match the short version if no parameters provided")
  public void weekDayNames() {
    Locale englishLocale = Locale.ENGLISH;
    Locale.setDefault(englishLocale);
    jDayChooser = new JDayChooser();
    secondSetup();

    String[] defaultWeekdays = DateFormatSymbols.getInstance(Locale.ENGLISH).getShortWeekdays();
    String[] expected = new String[7];
    arraycopy(defaultWeekdays, 1, expected, 0, 7);
    assertArrayEquals(expected, pageObject.getWeekDayNames());
  }

  //    @Test
  //    @DisplayName("Sunday should be in red")
  //    public void redSunday() {
  //        jDayChooser = new JDayChooser();
  //        secondSetup();
  //        assertEquals(RED, pageObject.getSundayForegroundColor());
  //    }

  public static void main(String[] s) {
    JFrame frame = new JFrame("JDayChooser");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JDayChooser());
    frame.pack();
    frame.setVisible(true);
  }
}
