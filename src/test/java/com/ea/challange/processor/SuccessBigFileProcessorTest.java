package com.ea.challange.processor;

import com.ea.challange.model.RowColIndex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@RunWith(Parameterized.class)
public class SuccessBigFileProcessorTest extends AbstractBigFileProcessorTest{
    private LinkedHashSet<RowColIndex> expectedOutput;

    public SuccessBigFileProcessorTest(String filePath,
                                       RowColIndex[] expectedOutput) {
        super(filePath);
        if(expectedOutput!=null) {
            this.expectedOutput = new LinkedHashSet<>(Arrays.asList(expectedOutput));
        }
    }



    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SuccessBigFileProcessorTest.class.getResource("/inputFile.txt").getPath(),
                        new RowColIndex[]{new RowColIndex(0,0),new RowColIndex(1,1)}},
                {SuccessBigFileProcessorTest.class.getResource("/inputFileWithEmpty.txt").getPath(),
                        new RowColIndex[]{new RowColIndex(1,0),new RowColIndex(2,1)}},
                {SuccessBigFileProcessorTest.class.getResource("/inputEmptyFile.txt").getPath(),
                        null}
        });
    }

    @Test
    public void successProcessTest() throws IOException {
        BigFileProcessor bigFileProcessor = new BigFileProcessor(filePath);
        Set<RowColIndex> set = bigFileProcessor.getWordRowColIndexMap().get("I");
        if(expectedOutput==null){
            Assert.assertNull(set);
        }else {
            Assert.assertEquals(set, expectedOutput);
        }
    }
}
