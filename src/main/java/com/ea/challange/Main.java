package com.ea.challange;


import com.ea.challange.processor.BigFileProcessor;
import com.ea.challange.processor.PairFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Class Responsible for taking two input Files and Display the adjacent word list
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    protected static void process(String[] args) throws Exception{
        if(args == null || (args.length!=2 && args.length!=3)){
            throw new IllegalArgumentException("Invalid input... Usage Main.class <<BigFilePath>> <<PairFilePath>>");
        }else{

            int kAdjacentWords = 2;
            if(args.length==3){
                kAdjacentWords = Integer.parseInt(args[2]);
            }
            BigFileProcessor fileProcessor = new BigFileProcessor(args[0]);
            PairFileProcessor pairFileProcessor = new PairFileProcessor(args[1],
                    fileProcessor.getWordRowColIndexMap(),kAdjacentWords);

            System.out.println(pairFileProcessor.getWordList());
        }
    }

    public static void main(String[] args) {
        try {
            process(args);
        }catch (Exception ex){
            LOGGER.error("Error ", ex);
        }
    }

}
