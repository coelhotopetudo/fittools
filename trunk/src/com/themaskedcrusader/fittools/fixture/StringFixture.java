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
  
  public void storeStringInGlobal(String toStore, String global) {
    toStore = parse(toStore);
    utils.setGlobal(global,toStore.trim());
  }
  
  public void storeTokenOfStringInGlobal (int token, String string, String global) {
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
    utils.setGlobal(global,toTrim);
  }
  
  public void trimCharactersFromEndOfGlobal(int characters, String global) {
    String toTrim = utils.getGlobal(global);
    toTrim = toTrim.substring(0,toTrim.length() - characters);
    utils.setGlobal(global,toTrim);
  }
  
  public void concatenateWithAndStoreInGlobal(String c1, String c2, String global){
    c1 = parse(c1).trim();
    c2 = parse(c2).trim();
    c1 = c1 + c2;
    utils.setGlobal(global,c1);
  }
  
  public void storeStringLengthInGlobal(String toGet, String global) {
    toGet = parse(toGet);
    utils.setGlobal(global,"" + toGet.trim().length());
  }
  
  public void compareWith(String c1, String c2) {
    c1 = parse(c1);
    c2 = parse(c2);
    System.out.println(c1 + " : " + c2);                  
  }
  

/**
 * 
 * Remove all occurances of character from string
 *    removeCharacterFromString
 *    removeCharacterFromStringAll
 *
 * 
 * 
 */


}
