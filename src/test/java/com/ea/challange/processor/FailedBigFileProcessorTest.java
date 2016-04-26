package com.ea.challange.processor;

import com.ea.challange.rule.ExceptionLoggingRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FailedBigFileProcessorTest extends AbstractBigFileProcessorTest{

    public FailedBigFileProcessorTest(String filePath) {
        super(filePath);
    }

    @Rule
    public ExceptionLoggingRule exceptionLoggingRule = new ExceptionLoggingRule();
    @Rule public ExpectedException expectedException = ExpectedException.none();



    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null},
                {" "},
                {"abc.txt"},
        });
    }

    @Test
    public void failedProcessTest() throws IOException {
        expectedException.expect(Exception.class);
        BigFileProcessor bigFileProcessor = new BigFileProcessor(filePath);
    }
}
