/* FitTools: FitNesse Plugin for Automation of Web Applications
 * Copyright (C) 2009, Christopher Schalk (www.themaskedcrusader.com)
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

  public BrowserFixture() {
  }

  public void startBrowserOnHostWithBaseUrl(String browser, String host, String baseUrl) {
    String[] seleniumHost = host.split(":");
    String selHost = seleniumHost[0];
    int selPort = Integer.parseInt(seleniumHost[1]);
    System.out.println("Selenium Host: " + selHost);
    System.out.println("Selenium Port: " + selPort);
    System.out.println("Browser Type : " + browser);
    if (!utils.isStarted()) {
      try {
        utils.cp = new HttpCommandProcessor(selHost, selPort, browser, baseUrl);
        utils.cp.start();
        utils.setStarted(true);
      } catch (Exception e) {
        if (e.getMessage().indexOf("Connection refused: connect") != -1) {
          throw new RuntimeException("Could not contact Selenium Server; have you started it?\n" + 
                                     e.getMessage());
        }
      }
    }
  }

  public void doCommand(String s1) {
    if (utils.isStarted())
      utils.cp.doCommand(s1, new String[] {});
  }

  public void doCommandWithTarget(String s1, String s2) {
    if (utils.isStarted())
      utils.cp.doCommand(s1, new String[] { s2, });
  }

  public void doCommandWithTargetAndValue(String s1, String s2, String s3) {
    if (utils.isStarted())
      utils.cp.doCommand(s1, new String[] { s2, s3, });
  }

  public void stopSelenium() {
    if (utils.isStarted()) {
      utils.cp.stop();
      utils.setStarted(false);
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
