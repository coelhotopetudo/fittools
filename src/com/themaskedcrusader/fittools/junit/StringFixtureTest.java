package com.themaskedcrusader.fittools.junit;

import junit.framework.TestCase;
import com.themaskedcrusader.fittools.fixture.StringFixture;
import com.themaskedcrusader.fittools.FixtureUtils;

public class StringFixtureTest extends TestCase {
    StringFixture sf = new StringFixture();
    FixtureUtils utils = FixtureUtils.getInstance();

    String testGlobal1 = "testGlobal1";
    String testGlobal2 = "textGlobal2";

    public void testMakeUniqueStringForGlobal() {
        String unique1 = sf.makeUniqueStringForGlobal(testGlobal1);
        String unique2 = sf.makeUniqueStringForGlobal(testGlobal2);
        System.out.println(unique1 + "," + unique2);
        assertFalse("Unique Values Generated Improperly", unique1.equals(unique2));
        assertFalse("Unique Values Stored Improperly", utils.getGlobal(testGlobal1).equals(utils.getGlobal(testGlobal2)));
    }

    public void testMakeUniqueStringWithLength() {
        int length = 25;
        String uniqueLength = sf.makeUniqueStringForGlobalWithLength(testGlobal1, length);
        System.out.println(uniqueLength);
        assertEquals("Global Length not as specified", utils.getGlobal(testGlobal1).length(), length );
        assertEquals("Returned length not as specified", uniqueLength.length(), length);
    }
    
}
