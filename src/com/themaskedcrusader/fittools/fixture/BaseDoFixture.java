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

package com.themaskedcrusader.fittools.fixture;

import com.themaskedcrusader.fittools.FitToolsUtils;
import fitlibrary.DoFixture;

public class BaseDoFixture extends DoFixture {
  protected FitToolsUtils utils = FitToolsUtils.getInstance(); // for utils variables

  public String parse(String toParse) {
    String[] tokens = toParse.split(" ");
    String toReturn = "";
    for (String token: tokens) {
      if (token.indexOf("#[") > -1 && token.indexOf("]") > -1) {
        toReturn += utils.getGlobal(token) + " ";
      } else {
        toReturn += token + " ";
      }
    }
    return toReturn.trim();
  }
  
  public void printGplWarranty(){
    System.out.println("add gpl warranty here!");
  }
  
  public void printRedistributionConditions() {
    System.out.println("add redistibution conditions here");
  }

}
