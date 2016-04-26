package com.ea.challange.util;

import com.ea.challange.rule.ExceptionLoggingRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FailedPropertyUtilTest extends AbstractPropertyUtilTest{
    public FailedPropertyUtilTest(String filePath) {
        super(filePath);
    }

    @Rule
    public ExceptionLoggingRule exceptionLoggingRule = new ExceptionLoggingRule();
    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"test.properties"}
        });
    }

    @Test
    public void testFailedProperty(){
        expectedException.expect(Exception.class);
        PropertyUtil.getInstance();
    }
}
