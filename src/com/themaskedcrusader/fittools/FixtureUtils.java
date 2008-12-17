package com.themaskedcrusader.fittools;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FixtureUtils {
  private Map global = new HashMap();
  private int count = 0;
  private static FixtureUtils thisInstance = new FixtureUtils();

  private FixtureUtils() {
    // empty constructor for singlet
  }

  public static FixtureUtils getInstance() {
    return thisInstance;
  }

  public String getGlobal(String key) {
    return (String)global.get(key);
  }

  public void setGlobal(String key, String value) {
    this.global.put(key, value);
  }

  private int countUp() {
    count++;
    if (count == 10)
      count = 0;
    return count;
  }

  public String generateUnique() {
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssS");
    return sdf.format(d) + countUp();
  }
}
