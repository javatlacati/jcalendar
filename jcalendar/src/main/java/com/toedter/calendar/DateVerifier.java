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

import java.util.Calendar;

import javax.swing.JComponent;

/**
 * Determine whether a date is valid. When a DateVerifier is established
 * the implementation may indicate whether a given date is permitted
 * as an allowed value.
 * 
 * @author Tom Sanders
 * @version $LastChangedRevision: $
 * @version $LastChangedDate: $
 *
 */
public interface DateVerifier {
  
  /**
   * Returns <code>true</code> if the given date is valid, <code>false</code>
   * if it is not.
   * @param date the date
   * @return whether valid
   */
  public boolean valid(Calendar date);
}
