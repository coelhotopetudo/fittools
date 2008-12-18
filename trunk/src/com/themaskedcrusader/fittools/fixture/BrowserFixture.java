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
  Selenium sel = null;
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

  public void doCommandWithTarget(String s1, String s2) {
    if (!started) {
      startBrowser(); // start the browser if it isn't started already.
      started = true;
    }

  }

}
