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

package fittools;

import com.thoughtworks.selenium.CommandProcessor;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("unchecked")
public class FitToolsUtils {
    public static CommandProcessor cp;
    public static boolean debug;

    private boolean started;
    private Map global = new HashMap();
    private int count = 0;
    private static FitToolsUtils thisInstance = new FitToolsUtils();

    private FitToolsUtils() {}

    public static FitToolsUtils getInstance() {
        return thisInstance;
    }

    public String getGlobal(String key) {
        return (String) global.get(key);
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

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }

    public void log(String message) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String caller = stack[2].getMethodName();
        if (debug)
            System.out.println("[" + caller + "]:: " + message);
    }
    
    public void debug(String message) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String caller = stack[2].getMethodName();
        if (debug)
            System.out.println("DEBUG [" + caller + "]:: " + message);
    }
}
