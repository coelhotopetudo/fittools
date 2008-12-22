/* FitTools: FitNesse Plugin for Automation of Web Applications
 * Copyright (C) 2008, Christopher Schalk (www.themaskedcrusader.com)
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

package com.themaskedcrusader.fittools.junit;

import junit.framework.TestCase;
import com.themaskedcrusader.fittools.fixture.StringFixture;
import com.themaskedcrusader.fittools.FitToolsUtils;

public class StringFixtureTest extends TestCase {
  StringFixture sf = new StringFixture();
  FitToolsUtils utils = FitToolsUtils.getInstance();

  String testGlobal1 = "testGlobal1";
  String testGlobal2 = "textGlobal2";

  public void testMakeUniqueStringForGlobal() {
    String unique1 = sf.makeUniqueStringForGlobal(testGlobal1);
    String unique2 = sf.makeUniqueStringForGlobal(testGlobal2);
    System.out.println(unique1 + "," + unique2);
    assertFalse("Unique Values Generated Improperly", unique1.equals(unique2));
    assertFalse("Unique Values Stored Improperly", 
                utils.getGlobal(testGlobal1).equals(utils.getGlobal(testGlobal2)));
  }

  public void testMakeUniqueStringWithLength() {
    int length = 25;
    String uniqueLength = sf.makeUniqueStringForGlobalWithLength(testGlobal1, length);
    System.out.println(uniqueLength);
    assertEquals("Global Length not as specified", utils.getGlobal(testGlobal1).length(), length);
    assertEquals("Returned length not as specified", uniqueLength.length(), length);
  }

}
