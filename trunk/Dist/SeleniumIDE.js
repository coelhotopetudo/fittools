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

var browserHeader = "!| fittools.fixture.BrowserFixture | ${SEL_HOST} | ${SEL_PORT} | ${BASEURL} | ${BROWSER} |\n";
var command = "${commands[i].command}";
var target = "${commands[i].target}";
var value = "${commands[i].value}";
var doCommand = "| do command | " + command + " |";
var withTarget = " with target | " + target + " |";
var andValue = " and value | " + value + " |";
var assert = "| verify | " + command + " |";
var storeTextInGlobal = "| store text | " + target + " | in global | " + value + " |";

function format(testCase, name) {
    return formatCommands(testCase.commands);
}

function formatCommands(commands) {
    var template = "";
    var commandText = browserHeader;

    for (var i = 0; i < commands.length; i++) {

        if (commands[i].command.substring(0, 6) == "assert" || commands[i].command.substring(0, 6) == "verify") {
            commands[i].command = commands[i].command.replace("verify", "assert");
            template = assert;
            template = addSuffix(template, commands[i]);

        } else if (commands[i].command.substring(0, 5) == "store") {
            template = storeTextInGlobal;

        } else {
            template = doCommand;
            template = addSuffix(template, commands[i]);
        }

        if (template != '') {
            var newText = template.replace(/\$\{([a-zA-Z0-9_\.\[\]]+)\}/g,
                function(str, p1, offset, s) {
                    result = eval(p1);
                    return result != null ? result : '';
                });

            commandText = commandText + newText + "\n";
        }
    }

    return commandText;
}

function addSuffix(template, command) {
    if (command.target != '') {
        template = template + withTarget;
    }

    if (command.value != '') {
        template = template + andValue;
    }

    return template;
}