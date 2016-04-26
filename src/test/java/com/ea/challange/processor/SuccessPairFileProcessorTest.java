package com.ea.challange.processor;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SuccessPairFileProcessorTest extends AbstractPairFileProcessorTest{
    private String expectedOutput;

    public SuccessPairFileProcessorTest(String inputPairFilePath,
                                        int adjacentWords,
                                        String expectedOutput) throws IOException {
        super(inputPairFilePath, adjacentWords);
        this.expectedOutput = expectedOutput;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SuccessPairFileProcessorTest.class.getResource("/pair.txt").getPath(),
                        2, "I am:(0,0),(0,1);(1,1),(1,2)\n" +
                        "work at:(1,5),(1,6)\n" +
                        "EA And:null"},

                {SuccessPairFileProcessorTest.class.getResource("/emptyPair.txt").getPath(),
                        2, ""},

                {SuccessPairFileProcessorTest.class.getResource("/threeAdjacentPair.txt").getPath(),
                        3, "I am enjoying:(1,1),(1,2),(1,3)\n" +
                        "work at:null\n" +
                        "EA And:null"},

                {SuccessPairFileProcessorTest.class.getResource("/pairWithEmptyLineAndNonMatch1stWord.txt").getPath(),
                        2, "Abc am:null\n" +
                        "I will:null\n" +
                        "I am:(0,0),(0,1);(1,1),(1,2)\n" +
                        "work at:(1,5),(1,6)\n" +
                        "EA And:null"}

        });
    }


    @Test
    public void testSuccessPair() throws IOException {
        PairFileProcessor pairFileProcessor = new PairFileProcessor(inputPairFilePath,wordMap,adjacentWords);
        String string = pairFileProcessor.getWordList();
        Assert.assertEquals(string,expectedOutput);
    }


}
