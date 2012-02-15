/* FitTools: FitNesse Plugin for Automation of Web Applications
 * Copyright (C) 2009-2012, Christopher Schalk (www.themaskedcrusader.com)
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

package fittools.fixture;

import com.thoughtworks.selenium.*;

public class BrowserFixture extends BaseDoFixture {

    public BrowserFixture() {
    }

    public void startBrowser() {
        String selHost = args[0];
        int selPort = Integer.parseInt(args[1]);
        String baseURL = args[2];
        String browser = args[3];
        utils.debug("Selenium Host: " + selHost, "startBrowser");
        utils.debug("Selenium Port: " + selPort, "startBrowser");
        utils.debug("Browser Type : " + browser, "startBrowser");
        if (!utils.isStarted()) {
            try {
                utils.cp = new HttpCommandProcessor(selHost, selPort, browser, baseURL);
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
        doCommandWithTarget(s1, null);
    }

    public void doCommandWithTarget(String s1, String s2) {
        doCommandWithTargetAndValue(s1, s2, null);
    }

    public void doCommandWithTargetAndValue(String s1, String s2, String s3) {
        if (!utils.isStarted())
            startBrowser();
        if (utils.isStarted()) {
            String value = s1 + ":";
            if (s2 == null) {
                value += utils.cp.getString(s1, new String[]{});
            } else if (s3 == null) {
                value += utils.cp.getString(s1, new String[]{s2,});
            } else {
                value += utils.cp.getString(s1, new String[]{s2, s3,});
            }
            utils.debug(s1 + ":" + value, "doCommand");
        }
    }

    // todo: Add meaningful debugging to these fixtures
    // todo: Parse for Global before processing.

    public boolean verifyTextIsPresent(String target) {
        String test = utils.cp.getString("isTextPresent", new String[]{target,});
        return Boolean.parseBoolean(test);
    }

    public boolean verifyPageTitleIs(String target) {
        String title = utils.cp.getString("getTitle", new String[]{target,});
        return (title.equals(target));
    }

    public boolean verifyValueOfIs(String target, String value) {
        String test = utils.cp.getString("getValue", new String[]{target,});
        return (test.equals(value));
    }

    public boolean verifyTextOfElementIs(String target, String value) {
        String test = utils.cp.getString("getText", new String[]{target,});
        return (test.equals(value));
    }

    public boolean verifyTableElementAtIs(String target, String value) {
        String test = utils.cp.getString("getTable", new String[]{target,});
        return (test.equals(value));
    }

    public boolean verifyElementIsPresent(String target) {
        String test = utils.cp.getString("isElementPresent", new String[]{target,});
        return Boolean.parseBoolean(test);
    }

    public void storeTextPresentInGlobal(String target, String value) {
        String text = utils.cp.getString("isTextPresent", new String[]{target,});
        utils.setGlobal(value, text);
    }

    public void storePageTitleInGlobal(String value) {
        String text = utils.cp.getString("getTitle", null);
        utils.setGlobal(value, text);
    }

    public void storeValueOfInGlobal(String target, String value) {
        String text = utils.cp.getString("getValue", new String[]{target,});
        utils.setGlobal(value, text);
    }

    public void storeTextInGlobal(String target, String value) {
        String text = utils.cp.getString("getText", new String[]{target,});
        utils.setGlobal(value, text);
    }

    public void storeTableElementAtInGlobal(String target, String value) {
        String text = utils.cp.getString("getTable", new String[]{target,});
        utils.setGlobal(value, text);
    }

    public void storeElementPresentInGlobal(String target, String value) {
        String text = utils.cp.getString("isElementPresent", new String[] {target,});
        utils.setGlobal(value, text);
    }

    public void stopSelenium() {
        if (utils.isStarted()) {
            utils.cp.stop();
            utils.setStarted(false);
        }
    }

    public void pauseSeconds(int seconds) throws Exception {
        Thread.sleep(seconds * 1000);
    }

    public void pauseSecond(int second) throws Exception {
        pauseSeconds(second);
    }

    public boolean setDebug() {
        utils.debug = true;
        return utils.debug;
    }

}
