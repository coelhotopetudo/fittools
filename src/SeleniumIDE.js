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
 
var assert = "| verify | ${commands[i].command} |" ;
var assertWithTarget = " with target | ${commands[i].target} |";
var assertWithTargetAndValue = " and value | ${commands[i].value} |";
var doCommand = "| do command | ${commands[i].command} |";
var doCommandWithTarget = " with target | ${commands[i].target} |";
var doCommandWithTargetAndValue = " and value | ${commands[i].value} |";

 /**
 * parse pasted fittools code into selenium IDE code
 * function parse(testCase, source) {  // IMPLEMENT LATER // }
 */

function format(testCase, name) {
  return formatCommands(testCase.commands);
}

function formatCommands(commands) {
  var template = "";
  var commandText = "";
  commandText = commandText +  "!| com.themaskedcrusader.fittools.fixture.BrowserFixture |";
  commandText = commandText +  " ${SEL_HOST} | ${SEL_PORT} | ${BASEURL} | ${BROWSER} |\n";

  for (var i = 0; i < commands.length; i++) {
    // format assert commands for FitTools
    if (commands[i].command.substring(0,6) == "assert") {
      template = assert;
      if (commands[i].target != '')
        template = template + assertWithTarget;
      if (commands[i].value != '')
        template = template + assertWithTargetAndValue;
    } else if (commands[i].command.substring(0,5) == "store") {
      continue; // FitTools does not support store commands
    } else if (commands[i].command.substring(0,6) == "verify") {
      continue; // FitTools does not support verify commands 
    } else {
       // format do command commands for FitTools
      template = doCommand;
      if (commands[i].target != '')
          template = template + doCommandWithTarget;
      if (commands[i].value != '')
          template = template + doCommandWithTargetAndValue;
    }

    var newText = template.replace(/\$\{([a-zA-Z0-9_\.\[\]]+)\}/g,
                                  function(str, p1, offset, s) {
                                    result = eval(p1);
                                    return result != null ? result : '';
                                  });
             
    commandText = commandText + newText + "\n";
  }
 
  return commandText; 
}