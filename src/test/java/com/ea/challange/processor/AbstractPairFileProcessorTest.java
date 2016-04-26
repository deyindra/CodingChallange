package com.ea.challange.processor;

import com.ea.challange.AbstractPropertyLoader;
import com.ea.challange.model.RowColIndex;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public abstract class AbstractPairFileProcessorTest extends AbstractPropertyLoader {
    protected static final String BIG_FILE_PATH =
            AbstractPairFileProcessorTest.class.getResource("/inputFile.txt").getPath();

    protected String inputPairFilePath;
    protected int adjacentWords;
    protected Map<String, Set<RowColIndex>> wordMap;
    protected AbstractPairFileProcessorTest(String inputPairFilePath,
                                            int adjacentWords) throws IOException {
        this.inputPairFilePath = inputPairFilePath;
        this.adjacentWords = adjacentWords;
        wordMap = new BigFileProcessor(BIG_FILE_PATH).getWordRowColIndexMap();
    }


}
