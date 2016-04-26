package com.ea.challange.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class SuccessPropertyUtilTest extends AbstractPropertyUtilTest{
    private String key;
    private String expectedValue;
    private String defaultValue;

    public SuccessPropertyUtilTest(String filePath, String key,
                                   String expectedValue,
                                   String defaultValue) {
        super(filePath);
        this.key = key;
        this.expectedValue = expectedValue;
        this.defaultValue = defaultValue;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SuccessPropertyUtilTest.class.getResource("/ea-test.properties").getPath(),"foo", "xyz", ""},
                {"","foo", "foo", ""},
                {SuccessPropertyUtilTest.class.getResource("/ea-test.properties").getPath(),"foo1", "NOT_FOUND", "NOT_FOUND"},
                {"","foo1", "NOT_FOUND", "NOT_FOUND"},

        });
    }


    @Test
    public void testSuccessProperty(){
        String value = PropertyUtil.getInstance().getStringPropertyValue(key,defaultValue);
        Assert.assertEquals(value,expectedValue);
    }


}
