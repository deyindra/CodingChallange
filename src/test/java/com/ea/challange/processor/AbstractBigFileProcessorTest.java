package com.ea.challange.processor;

import com.ea.challange.AbstractPropertyLoader;

public abstract class AbstractBigFileProcessorTest extends AbstractPropertyLoader{
    protected String filePath;

    protected AbstractBigFileProcessorTest(String filePath) {
        this.filePath = filePath;
    }


}
