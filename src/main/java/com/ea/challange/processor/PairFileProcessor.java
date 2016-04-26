package com.ea.challange.processor;


import com.ea.challange.model.RowColIndex;
import com.ea.challange.util.PropertyUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author idey
 * Responsible for process Pair file as stream
 * Assumption is file contains distinct set of K adjacent words
 */
public class PairFileProcessor extends AbstractProcessFile{
    /**
     * word map which will be supplied by {@link BigFileProcessor}
     */
    private Map<String, Set<RowColIndex>> wordMap;
    //number of K adjacent words
    private final int kAdjacentWords;
    //Map contains the K adjacent word pair and their occurance
    private Map<String, LinkedList<LinkedList<RowColIndex>>> adjacentMap;

    /**
     *
     * @param filePath Pair File Path
     * @param wordMap word map which will be supplied by {@link BigFileProcessor}
     * @param kAdjacentWords number of Adjacent words
     * @throws IOException
     */
    public PairFileProcessor(String filePath,
                             Map<String,Set<RowColIndex>> wordMap,
                             int kAdjacentWords) throws IOException {
        super(filePath);
        if(wordMap==null || wordMap.isEmpty()){
            throw new IllegalArgumentException("Invalid Word Map");
        }
        this.wordMap = wordMap;
        if(kAdjacentWords<2){
            throw new IllegalArgumentException("Invalid number of adjacent " +
                    "word; Has to be more than or equal to 2");
        }
        this.kAdjacentWords=kAdjacentWords;
        adjacentMap = new LinkedHashMap<>();
        process(filePath);
    }

    @Override
    protected synchronized void populate(String s) {
        if(s!=null && s.trim().length()!=0){
            //Make an entry of word list with empty LinkedList with RowColIndex
            if(!adjacentMap.containsKey(s)) {
                adjacentMap.put(s, new LinkedList<>());
                //Split the word pair
                String[] array = s.split(PropertyUtil.getInstance()
                        .getStringPropertyValue(WORD_SPLITER_PROPERTY_KEY,
                                DEFAULT_WORD_SEP));
                // Assumption if the number of words is not equal to kAdjacentWords then do not process the line
                if (array.length != kAdjacentWords) {
                    LOGGER.error(String.format("Line %s does not have %d adjacent words", s, kAdjacentWords));
                } else {
                    //Take the 1st word from the word list
                    String firstWord = array[0];
                    Set<RowColIndex> firstRowColIndexSets = wordMap.get(firstWord);
                    //Basically first word is not found in the wordMap
                    if (firstRowColIndexSets == null || firstRowColIndexSets.isEmpty()) {
                        LOGGER.error(String.format("1st word %s is not found in the bigger file", firstWord));
                    } else {
                        for (RowColIndex rowColIndex : firstRowColIndexSets) {
                            //else get row and col index of the 1st word
                            final long rowIndex = rowColIndex.getRowIndex();
                            final long colIndex = rowColIndex.getColIndex();
                            int count = 1;
                            LinkedList<RowColIndex> secondLinkedList = new LinkedList<>();
                            //Loop through all adjacent word and check if the adjacent word is present in the same order
                            //or not
                            for (; count < kAdjacentWords; count++) {
                                Set<RowColIndex> adjacentRowColIndex = wordMap.get(array[count]);
                                RowColIndex newRowColIndex = new RowColIndex(rowIndex, colIndex + count);
                                if (adjacentRowColIndex != null && adjacentRowColIndex.contains(newRowColIndex)) {
                                    secondLinkedList.add(newRowColIndex);
                                } else {
                                    LOGGER.error(String.format("%s does not have adjacent row and col Index", array[count]));
                                    secondLinkedList.clear();
                                    break;
                                }
                            }
                            //if the 2nd linkedList is not empty
                            if (!secondLinkedList.isEmpty()) {
                                //add the 1st row col Index
                                secondLinkedList.addFirst(rowColIndex);
                                LinkedList<LinkedList<RowColIndex>> linkedLists = adjacentMap.get(s);
                                LinkedList<RowColIndex> newLinkedList = new LinkedList<>();
                                newLinkedList.addAll(secondLinkedList);
                                linkedLists.add(newLinkedList);
                            }
                        }
                    }
                }
            }else{
                LOGGER.warn(String.format("%s is duplicate line... hence ignored processing", s));
            }
        }else{
            LOGGER.warn("Empty line..... hence skipping");
        }
    }


    /**
     *
     * @return String formatted word List
     */
    public String getWordList(){
        StringBuilder builder = new StringBuilder("");
        String newLine="";
        for(Map.Entry<String, LinkedList<LinkedList<RowColIndex>>> entry : adjacentMap.entrySet()){
            builder.append(newLine);
            builder.append(entry.getKey()).append(":");
            LinkedList<LinkedList<RowColIndex>> linkedLists = entry.getValue();
            if(!linkedLists.isEmpty()){
                String firstSeparator="";
                for(LinkedList<RowColIndex> linkedList:linkedLists){
                    builder.append(firstSeparator);
                    String secondSeparator="";
                    for(RowColIndex rowColIndex:linkedList){
                        builder.append(secondSeparator).append(rowColIndex);
                        secondSeparator=",";
                    }
                    firstSeparator=";";
                }
            }else{
                builder.append("null");
            }
            newLine="\n";
        }
        return builder.toString();
   }


}
