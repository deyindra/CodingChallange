package com.ea.challange.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : idey
 * A utility class which will load the configurable property and return the value/default value
 * based on the property key lookup
 */
public class PropertyUtil {
    private static final String PROPERTY_FILE_LOCATION = "property.path";
    private static final String DEFAULT_PROPERTY_PATH = "/ea.properties";
    private static volatile PropertyUtil propertyUtil=null;
    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtil.class);
    private Properties properties;
    /**
     * Private Constructor which will try to load the property file as specified in the
     * property.path environmental variable as specified in {@link #PROPERTY_FILE_LOCATION},
     * in case, it is not supplied it will load the
     * {@link #DEFAULT_PROPERTY_PATH}
     * @throws java.lang.IllegalArgumentException in case file not found or error in loading property file
     */
    private PropertyUtil(){
        properties=new Properties();
        InputStream inputStream=null;
        String propertyPath="";
        try{
            //Try to load the property from the PROPERTY_FILE_LOCATION
            propertyPath = System.getProperty(PROPERTY_FILE_LOCATION);
            if (propertyPath != null && !("").equals(propertyPath.trim())) {
                propertyPath = propertyPath.trim();
                inputStream = new FileInputStream(propertyPath);
            }else{
                //If the path is empty or blank then load the DEFAULT_PROPERTY_PATH from classpath
                LOG.warn("No property file found this will load the default property");
                inputStream = PropertyUtil.class.getResourceAsStream(DEFAULT_PROPERTY_PATH);
            }
            properties.load(inputStream);
        }catch (FileNotFoundException ex){
            throw new IllegalArgumentException("Invalid Property Path "+propertyPath);
        }catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }finally {
            if(inputStream!=null){
                try{
                    inputStream.close();
                }catch (IOException ex){
                   throw new IllegalArgumentException(ex);
                }
            }
        }

    }

    /**
     *
     * @return PropertyUtil
     * @throws IllegalArgumentException in case of error
     */
    public static PropertyUtil getInstance(){
        if(propertyUtil==null){
            synchronized (PropertyUtil.class){
                if(propertyUtil==null){
                    propertyUtil = new PropertyUtil();
                }
            }
        }
        return propertyUtil;
    }

    /**
     * @param key  A Property key
     * @param defaultValue A default value which will be returned in case of key not found
     * @return return the value/default value
     */
    public String getStringPropertyValue(String key, String defaultValue){
        return properties.getProperty(key,defaultValue);
    }




}
