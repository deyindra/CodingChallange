package com.ea.challange.processor;

import com.ea.challange.constant.EAConstant;
import com.ea.challange.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;
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
    private final int totalNumberOfThread;

    protected AbstractProcessFile(String filePath) throws IOException{
        if(filePath==null || filePath.trim().length()==0){
            throw new IOException("Invalid File Path");
        }
        this.filePath = filePath;
        String strTotalNumberOfThread = PropertyUtil.getInstance().getStringPropertyValue(FORK_JOIN_POOL,"");
        if("".equals(strTotalNumberOfThread)){
            totalNumberOfThread=DEFAULT_FORK_JOIN_POOL;
        }else{
            totalNumberOfThread=Integer.parseInt(strTotalNumberOfThread);
        }
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
            ForkJoinPool forkJoinPool = new ForkJoinPool(totalNumberOfThread);
            forkJoinPool.submit(()->
                stringStream.parallel().forEach(this::populate)).join();
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
