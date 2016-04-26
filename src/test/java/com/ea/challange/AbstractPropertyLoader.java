package com.ea.challange;

import com.ea.challange.constant.EAConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPropertyLoader implements EAConstant {
    private static final String FILE_PATH =AbstractPropertyLoader.class.getResource("/ea-test.properties").getPath();
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPropertyLoader.class);

    static {
        LOGGER.info("Loading Property file "+FILE_PATH);
        System.setProperty(PROPERTY_FILE_LOCATION,FILE_PATH);
    }
}
