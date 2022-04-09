[![Codacy Badge](https://api.codacy.com/project/badge/Grade/97f419a01dbf48ceba95f349cd1d070b)](https://www.codacy.com/app/javatlacati/jcalendar?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=javatlacati/jcalendar&amp;utm_campaign=Badge_Grade) [![Known Vulnerabilities](https://snyk.io//test/github/javatlacati/jcalendar/badge.svg?targetFile=pom.xml)](https://snyk.io//test/github/javatlacati/jcalendar?targetFile=pom.xml)
[![Sonatype Vulnerabilities](https://depshield.sonatype.org/badges/javatlacati/jcalendar/depshield.svg)](https://depshield.sonatype.org/badges/javatlacati/jcalendar/depshield.svg)

Linux build status: [![Build Status](https://travis-ci.org/javatlacati/jcalendar.svg?branch=master)](https://travis-ci.org/javatlacati/jcalendar)

Windows/ MacOS build Status [![Build status](https://ci.appveyor.com/api/projects/status/6clgqnjmgnxls5sj?svg=true)](https://ci.appveyor.com/project/javatlacati/jcalendar)

[![Open Source Helpers](https://www.codetriage.com/javatlacati/jcalendar/badges/users.svg)](https://www.codetriage.com/javatlacati/jcalendar)

JCalendar
-

(C) 2019 Ruslan López
(C) 2011 Tom Sanders
(C) 2009 Luis Miranda
(C) 1999 - 2006 Kai Toedter

Released under GNU Lesser General Public License 2.1.

INTRODUCTION
---

This is a fork of the JCalendar project:

Luis Miranda:

* uses Maven 2
* supports TimeZones

Tom Sanders

* Various new features and bug fixes - see below

USAGE
--

### Luis Miranda
* The JDateChooser class has a new method called setDateFormatCalendar.
  The underlying implementation will use the TimeZone of the selected
  calendar when displaying the date and time.

### Tom Sanders

Components

* The dateVerifier property
  As well as the existing min/maxSelectableDate properties, the
  DateVerifier interface allows clients to specify arbitrary
  validation checks, for example valid business days. When present
  on JDayChooser, JCalendar or JDateChooser the DateVerifier.valid()
  method is called. For JDayChooser and JCalendar (and within
  JDateChooser obviously) the calendar buttons are disabled for dates
  that are not valid. If a JDateChooser has a spinner editor then
  invalid dates are skipped in the direction of travel. The same is
  true for values entered as text.

* The nullText property
  Sometimes it is nice to display some text in JDateChooser, rather
  than blank, when the date is null. For example null might
  mean "Open Ended" or whatever.

* The selectOnFocus property
  When a JDateChooser (that is the underlying JTextComponent) gains
  the focus, optionally select its text. Useful for keyboard junkies.

* Using a mnemonic is limited in that it does not require the
   component to have the focus. If there is more than one JDateChooser
   in your window Alt+C will only ever apply to the one most
   recently created. Changed to use the input/action map.

   When focused in the underlying JTextComponent Ctrl-C pops up the
   calendar and Ctrl-N sets the value to null.

* IDateEditor exposes the underlying DateFormat so it can be
  manipulated (for example setting a time zone)

* IDateEditor provides a method to retrieve the underlying
  JTextComponent.

Test Program

* New properties added so they can be exercised. The DateVerifier
  example excludes Mondays and otherwise includes only even numbered
  dates.

* For JDateChooser add a check box that sets the top four choosers
  to null when checked, today when not. Set them to null
  individually using ctrl-n when they have the focus.

* Set the nullText using the text field (requires cr).

Backwards compatible with 1.3.3 with the exception of keyboard
behaviour.

UPDATES
--

JCalendar is updated from time to time. Development efforts will be
added into this fork.
