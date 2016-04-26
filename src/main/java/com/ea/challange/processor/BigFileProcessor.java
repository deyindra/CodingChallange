package com.ea.challange.processor;

import com.ea.challange.constant.EAConstant;
import com.ea.challange.model.RowColIndex;
import com.ea.challange.util.PropertyUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author idey
 * A class which is responsible for parallel processing big file as Stream
 */
public class BigFileProcessor extends AbstractProcessFile{
    //this will be responsible for holding individual word with the corresponding row and colIndex;
    private ConcurrentMap<String, Set<RowColIndex>> wordRowColIndexMap;
    //this will be responsible for hold row offset;
    private AtomicLong rowOffset;

    /**
     * @param filePath file path of the bigger File
     * @throws IOException if case file path is empty
     */
    public BigFileProcessor(String filePath) throws IOException {
        super(filePath);
        wordRowColIndexMap = new ConcurrentHashMap<>();
        rowOffset = new AtomicLong(0L);
        process(filePath);
    }


    /**
     * call this method for each input line
     * @param s each line in a file
     */
    @Override
    protected synchronized void populate(String s){
        long rowIndex = rowOffset.get();
        if(s!=null && s.trim().length()!=0){
            /**
             * splitting String into
             * multiple word separated by {@link EAConstant#WORD_SPLITER_PROPERTY_KEY}
             */
            String[] array = s.split(PropertyUtil.getInstance()
                    .getStringPropertyValue(WORD_SPLITER_PROPERTY_KEY,
                    DEFAULT_WORD_SEP));
            long colIndex=0;
            for(String val:array){
                Set<RowColIndex> set = wordRowColIndexMap.get(val);
                if(set==null || set.isEmpty()){
                    set = new LinkedHashSet<>();
                }
                set.add(new RowColIndex(rowIndex,colIndex++));
                wordRowColIndexMap.put(val,set);
            }
        }else{
            LOGGER.warn("Empty line..... hence skipping");
        }
        rowOffset.set(++rowIndex);
    }

    public Map<String, Set<RowColIndex>> getWordRowColIndexMap() {
        return Collections.unmodifiableMap(wordRowColIndexMap);
    }


}
