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
