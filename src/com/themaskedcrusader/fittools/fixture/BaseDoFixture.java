package com.themaskedcrusader.fittools.fixture;

import com.themaskedcrusader.fittools.FixtureUtils;
import fitlibrary.DoFixture;


public class BaseDoFixture extends DoFixture {
    protected FixtureUtils utils = FixtureUtils.getInstance(); // for utils variables

    public String parse(String toParse) {
        String[] tokens = toParse.split(" ");
        String toReturn = "";
        for (String token : tokens) {
            if (token.indexOf("#[") > -1 && token.indexOf("]") > -1 ) {
                toReturn += utils.getGlobal(token) + " ";
            } else {
                toReturn += token + " ";
            }
        }
        return toReturn.trim();
    }

}
