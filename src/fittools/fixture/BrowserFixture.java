/* FitTools: FitNesse Plugin for Automation of Web Applications
 * Copyright (C) 2009-2014, Christopher Schalk (www.themaskedcrusader.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
        utils.log("Selenium Host: " + selHost);
        utils.log("Selenium Port: " + selPort);
        utils.log("Browser Type : " + browser);
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

    public String doCommand(String s1) {
        return doCommandWithTarget(s1, null);
    }

    public String doCommandWithTarget(String s1, String s2) {
        return doCommandWithTargetAndValue(s1, s2, null);
    }

    public String doCommandWithTargetAndValue(String s1, String s2, String s3) {
        if (!utils.isStarted())
            startBrowser();
        if (utils.isStarted()) {
            String debugString = "C: \"" + s1 + "\"";
            String value = s1 + ":";
            if (s2 == null) {
                value += utils.cp.doCommand(s1, new String[]{});
            } else if (s3 == null) {
                debugString += " T: \"" + s2 + "\"";
                value += utils.cp.doCommand(s1, new String[]{parse(s2),});
            } else {
                debugString += " T: \"" + s2 + "\" V: \"" + s3 + "\"";
                value += utils.cp.doCommand(s1, new String[]{parse(s2), parse(s3),});
            }
            utils.debug(debugString);
            utils.debug("Result: " + value);
            return value;
        }
        return "Selenium Not Running!";
    }

    public boolean verifyWithTarget(String command, String target) {
        return verifyWithTargetAndValue(command, target, null);
    }

    public boolean verifyWithTargetAndValue(String command, String target, String value) {
        try {
            utils.debug("Verifying Next Command...");
            doCommandWithTargetAndValue(command, target, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void storeTextInGlobal(String text, String global) throws Exception {
        if (global.contains(" ")) {
            String error = "ERROR: Global variable names cannot contain a space";
            utils.debug(error);
            throw new Exception(error);
        } else {
            utils.setGlobal(global, text);
            utils.debug("Global stored: " + global + "=\"" + text + "\"");
        }
    }

    public void storeTextAtTargetInGlobal(String target, String global) throws Exception {
        String text = utils.cp.doCommand("getText", new String[]{target,});
        storeTextInGlobal(text.substring(text.indexOf(",") + 1), global);
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
