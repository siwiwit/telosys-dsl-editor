package org.telosys.tools.eclipse.plugin.editors.dsl.enum_editor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Static tools.
 * 
 */
public class EnumEditorUtils {
	
    private static Properties properties;

    public static String getProperty(String propertyName) {
        if (properties == null) {
            EnumEditorUtils.loadPropertiesFile();
        }
        String property = properties.getProperty(propertyName);
        if(null == property){
        	throw new EnumEditorException("Incorrect property file. Missing property name : " + propertyName);
        }
        return property;
    }

    private static void loadPropertiesFile(){
        properties = new Properties();
        try {
            InputStream propertiesStream = EnumEditorUtils.class.getResourceAsStream("/config.properties");
            properties.load(propertiesStream);
        } catch (FileNotFoundException e) {
            throw new EnumEditorException("Error while loading the properties file : " + e.getMessage());
        } catch (IOException e) {
            throw new EnumEditorException("Error while loading the properties file : " + e.getMessage());
        }
    }
}
