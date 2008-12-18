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

package com.themaskedcrusader.fittools.fixture;

public class StringFixture extends BaseDoFixture {

  public String makeUniqueStringForGlobal(String global) {
    String unique = super.utils.generateUnique();
    unique = unique.substring(unique.length() - 5);
    super.utils.setGlobal(global, unique);
    return unique;
  }

  public String makeUniqueStringForGlobalWithLength(String global, int length) {
    String toReturn = "";
    do {
      toReturn += super.utils.generateUnique();
    } while (toReturn.length() < length);
    toReturn = toReturn.substring(0, length);
    super.utils.setGlobal(global, toReturn);
    return toReturn;
  }

  public void makeUniqueParagraphForGlobal(String global) {
  }

  public void storeStringInGlobal(String toStore, String global) {
    toStore = parse(toStore);
    utils.setGlobal(global, toStore.trim());
  }

  public void storeTokenOfStringInGlobal(int token, String string, String global) {
    string = parse(string);
    String[] tokens = string.split(" ");
    utils.setGlobal(global, tokens[token]);
  }

  public void printGlobal(String global) {
    System.out.println(utils.getGlobal(global));
  }

  public void trimCharactersFromBeginningOfGlobal(int characters, String global) {
    String toTrim = utils.getGlobal(global);
    toTrim = toTrim.substring(characters);
    utils.setGlobal(global, toTrim);
  }

  public void trimCharactersFromEndOfGlobal(int characters, String global) {
    String toTrim = utils.getGlobal(global);
    toTrim = toTrim.substring(0, toTrim.length() - characters);
    utils.setGlobal(global, toTrim);
  }

  public void concatenateWithAndStoreInGlobal(String c1, String c2, String global) {
    c1 = parse(c1).trim();
    c2 = parse(c2).trim();
    c1 = c1 + c2;
    utils.setGlobal(global, c1);
  }

  public void storeStringLengthInGlobal(String toGet, String global) {
    toGet = parse(toGet);
    utils.setGlobal(global, "" + toGet.trim().length());
  }

  public void compareWith(String c1, String c2) {
    c1 = parse(c1);
    c2 = parse(c2);
    System.out.println(c1 + " : " + c2);
  }

  public void removeCharacterFromGlobal(String character, String global) {
    removeCharactersFromGlobal(character, global);
  }

  public void removeCharactersFromGlobal(String character, String global) {
    String toRemove = utils.getGlobal(global);
    toRemove = toRemove.replace(character, "");
    utils.setGlobal(global, toRemove);
  }


}
