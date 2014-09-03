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