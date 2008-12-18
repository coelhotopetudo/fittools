/* FitTools: FitNesse Plugin for Automation of Web Applications
 * Copyright (C) 2008, Christopher Schalk (www.themaskedcrusader.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * Contact the author via email at: christopher.schalk@gmail.com
 */

package com.themaskedcrusader.fittools.fixture;

import com.thoughtworks.selenium.*;

public class BrowserFixture extends BaseDoFixture {
  CommandProcessor cp;
  boolean started = false;

  public BrowserFixture() { /* empty constructor */
  }

  public void startBrowser() {
    String selHost = args[0];
    int selPort = Integer.parseInt(args[1]);
    String baseUrl = args[2];
    String browser = args[3];
    cp = new HttpCommandProcessor(selHost, selPort, browser, baseUrl);
    cp.start();
  }

  public void doCommand(String s1) {
    doCommandWithTarget(s1, null);
  }

  public void doCommandWithTarget(String s1, String s2) {
    doCommandWithTargetAndValue(s1, s2, null);
  }

  public void doCommandWithTargetAndValue(String s1, String s2, String s3) {
    if (!started) {
      startBrowser(); // start the browser if it isn't started already.
      started = true;
    }
  }
  
  /* Needed Fixures:
   * 
   * storeTextFromTargetInGlobal
   *   | store text from target | xpath:\\HTML | in global | myGlobal |
   * storeValueFromTargetInGlobal
   *   | store value from target | \\input[@name='my button'] | in global | myGlobal |
   * storeAttributeFromTargetInGlobal
   *   | store attribute from target | form[0].myTarget | in global | myGlobal |
   * pauseForSeconds
   *   | pause for | 8 | seconds |
   * 
   */
}
