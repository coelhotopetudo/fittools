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

import fitlibrary.DoFixture;
import fittools.FitToolsUtils;

public class BaseDoFixture extends DoFixture {
  
  protected final String SPACE = " ";
  
  protected FitToolsUtils utils = FitToolsUtils.getInstance(); // for utils variables

    public String parse(String toParse) {
        String[] tokens = toParse.split(SPACE);
        String toReturn = "";
        for (String token : tokens) {
            if (token.indexOf("#[") > -1 && token.indexOf("]") > -1) {
                String value = utils.getGlobal(token.substring(2, token.length() - 1));
                toReturn += value + SPACE;
                utils.debug("Global found: " + token + "=\"" + value + "\"");
            } else {
                toReturn += token + SPACE;
            }
        }
        return toReturn.trim();
    }

  public boolean printRedistributionConditions() {
    System.out.println("REDISTRIBUTION CONDITIONS NOT AVAILABLE, YET");
    return false;
  }
}
