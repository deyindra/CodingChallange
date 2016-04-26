package com.ea.challange.processor;


import com.ea.challange.rule.ExceptionLoggingRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FailedPairFileProcessorTest extends AbstractPairFileProcessorTest{

    public FailedPairFileProcessorTest(String inputPairFilePath,
                                       int adjacentWords) throws IOException {
        super(inputPairFilePath, adjacentWords);
    }

    @Rule
    public ExceptionLoggingRule exceptionLoggingRule = new ExceptionLoggingRule();
    @Rule public ExpectedException expectedException = ExpectedException.none();


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {FailedPairFileProcessorTest.class.getResource("/pair.txt").getPath(),1},
                {null,2},
                {" ",2},
                {"abc.txt",2}
        });
    }


    @Test
    public void testFailedPair() throws IOException {
        expectedException.expect(Exception.class);
        PairFileProcessor pairFileProcessor = new PairFileProcessor(inputPairFilePath,wordMap,adjacentWords);
    }


}
