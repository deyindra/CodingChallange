package com.ea.challange.util;

import com.ea.challange.constant.EAConstant;
import org.junit.Before;

import java.lang.reflect.Field;

public abstract class AbstractPropertyUtilTest implements EAConstant {
    protected String filePath;

    protected AbstractPropertyUtilTest(String filePath) {
        this.filePath = filePath;
    }

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field instance = PropertyUtil.class.getDeclaredField("propertyUtil");
        instance.setAccessible(true);
        instance.set(null, null);
        System.setProperty(PROPERTY_FILE_LOCATION,filePath);
    }
}
