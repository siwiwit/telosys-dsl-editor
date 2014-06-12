package org.telosys.tools.eclipse.plugin.editors.dsl.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Static tools.
 * 
 */
public class EditorsUtils {
	
    private static Properties properties;
    
    public static String getProperty(String propertyName) {
        if (properties == null) {
            EditorsUtils.loadPropertiesFile();
        }
        String property = properties.getProperty(propertyName);
        if(null == property){
        	throw new EditorsException("Incorrect property file. Missing property name : " + propertyName);
        }
        return property;
    }

    private static void loadPropertiesFile(){
        properties = new Properties();
        try {
            InputStream propertiesStream = EditorsUtils.class.getResourceAsStream("/config.properties");
            properties.load(propertiesStream);
        } catch (FileNotFoundException e) {
            throw new EditorsException("Error while loading the properties file : " + e);
        } catch (IOException e) {
            throw new EditorsException("Error while loading the properties file : " + e);
        }
    }
    
    /*
     * Constant variable for the context of auto-completion
     */
    public final static int DEFAULT = 0;
    public final static int TYPE = 1;
    public final static int ANNOTATION = 2;
}
