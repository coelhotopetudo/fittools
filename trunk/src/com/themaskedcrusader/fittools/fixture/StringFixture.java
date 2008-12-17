package com.themaskedcrusader.fittools.fixture;

public class StringFixture extends BaseDoFixture {

    public String makeUniqueStringForGlobal(String global) {
        String unique = super.utils.generateUnique();
        unique = unique.substring(unique.length() - 5);
        super.utils.setGlobal(global,unique);
        return unique;
    }

    public String makeUniqueStringForGlobalWithLength(String global, int length) {
        String toReturn = "";
        do {
            toReturn += super.utils.generateUnique();
        } while (toReturn.length() < length);
        toReturn = toReturn.substring(0,length);
        super.utils.setGlobal(global,toReturn);
        return toReturn;
    }

    
}
