package com.ea.challange.constant;

public interface EAConstant {
    //Property File location
    String PROPERTY_FILE_LOCATION = "property.path";
    //Default Property file name which will be loaded from the classapth
    String DEFAULT_PROPERTY_PATH = "/ea.properties";

    String WORD_SPLITER_PROPERTY_KEY = "word.splitter";
    String DEFAULT_WORD_SEP = "\\s+";

    String FORK_JOIN_POOL = "fork-join-pool";
    int DEFAULT_FORK_JOIN_POOL = Runtime.getRuntime().availableProcessors()*2;

}
