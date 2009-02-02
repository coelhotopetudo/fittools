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
 
 /**
 * parse pasted fittools code into selenium IDE code
 * function parse(testCase, source) {  // IMPLEMENT LATER // }
 */

function format(testCase, name) {
  return formatCommands(testCase.commands);
}

function formatCommands(commands) {
  var commandText = "";
  commandText = commandText +  "!| com.themaskedcrusader.fittools.fixture.BrowserFixture |";
  commandText = commandText +  " ${SEL_HOST} | ${SEL_PORT} | ${BASEURL} | ${BROWSER} |\n";

  for (var i = 0; i < commands.length; i++) {
    commandText = commandText + "| doCommand | " +  commands[i].command + " |";
    if (commands[i].target != '')
      commandText = commandText + " with target | " + commands[i].target + " |";
    if (commands[i].value != '')
      commandText = commandText + " and value | " + commands[i].value + " |";
    commandText = commandText + "\n";
  }
 

  return commandText; 
}