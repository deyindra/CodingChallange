package com.ea.challange.processor;

import com.ea.challange.constant.EAConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author idey
 * Abstract Class for processing file
 * @see BigFileProcessor
 * @see PairFileProcessor
 */
public abstract class AbstractProcessFile implements EAConstant {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractProcessFile.class);
    // File path for Big file or Pair file
    protected String filePath;

    protected AbstractProcessFile(String filePath) throws IOException{
        if(filePath==null || filePath.trim().length()==0){
            throw new IOException("Invalid File Path");
        }
        this.filePath = filePath;
    }



    /**
     * Process the file in a parallel stream
     * @param file file path
     * @throws IOException
     */
    protected void process(String file) throws IOException {
        BufferedReader reader=null;
        try{
            reader = Files.newBufferedReader(Paths.get(file));
            // get the Stream of String
            Stream<String> stringStream = reader.lines();
            //parallel process Stream
            stringStream.parallel().forEach(this::populate);
        }finally {
            if(reader!=null){
                reader.close();
            }
        }
    }


    /**
     * method responsible for processing individual file line
     * @param s Individual File Line
     */
    protected abstract void populate(String s);
}
