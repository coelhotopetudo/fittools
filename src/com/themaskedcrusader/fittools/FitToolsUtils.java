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

package com.themaskedcrusader.fittools;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FitToolsUtils {
  private Map global = new HashMap();
  private int count = 0;
  private static FitToolsUtils thisInstance = new FitToolsUtils();

  private FitToolsUtils() {
    System.out.println("" + 
    
      "----- GPL License Notice: ------------------------------------------\n" +
      "FitTools version 0.1, Copyright (C) 2008 Christopher Schalk  \n" +
      "FitTools comes with ABSOLUTELY NO WARRANTY; for details      \n" +
      "add the following code to a fitnesse test page and execute:  \n\n" +
      "!| com.themaskedcrusader.fittools.fixture.BaseDoFixture |\n" +
      "| print gpl warranty |\n\n" +
      "FitTools is free software, and you are welcome to redistribute\n" +
      "it under certain conditions; add the following code to a \n" +
      "fitnesse test page and execute for details on condititions:\n\n" +
      "!| com.themaskedcrusader.fittools.fixture.BaseDoFixture |\n" + 
      "| print redistribution conditions |\n\n" +
      "--------------------------------------------------------------------\n"
      
      
      );
  }

  public static FitToolsUtils getInstance() {
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
