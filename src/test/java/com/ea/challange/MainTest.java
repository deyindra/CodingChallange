package com.ea.challange;

import com.ea.challange.rule.ExceptionLoggingRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MainTest {
    @Rule
    public ExceptionLoggingRule exceptionLoggingRule = new ExceptionLoggingRule();
    @Rule public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void failTest1() throws Exception {
        expectedException.expect(Exception.class);
        Main.process(null);
    }

    @Test
    public void failTest2()throws Exception {
        expectedException.expect(Exception.class);
        Main.process(new String[]{"ABC", "ABC", "XYZ", "AAA"});
    }

    @Test
    public void failTest3()throws Exception {
        expectedException.expect(Exception.class);
        Main.process(new String[]{"ABC", "ABC", "XYZ"});
    }
}
