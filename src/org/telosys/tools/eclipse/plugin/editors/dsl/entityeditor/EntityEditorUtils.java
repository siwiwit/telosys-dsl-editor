package org.telosys.tools.eclipse.plugin.editors.dsl.entityeditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.swt.graphics.RGB;

/**
 * Static tools.
 * 
 */
public class EntityEditorUtils {
	
    private static Properties properties;
    
    public static String getProperty(String propertyName) {
        if (properties == null) {
            EntityEditorUtils.loadPropertiesFile();
        }
        String property = properties.getProperty(propertyName);
        if(null == property){
        	throw new EntityEditorException("Incorrect property file. Missing property name : " + propertyName);
        }
        return property;
    }

    private static void loadPropertiesFile(){
        properties = new Properties();
        try {
            InputStream propertiesStream = EntityEditorUtils.class.getResourceAsStream("/config.properties");
            properties.load(propertiesStream);
        } catch (FileNotFoundException e) {
            throw new EntityEditorException("Error while loading the properties file : " + e.getMessage());
        } catch (IOException e) {
            throw new EntityEditorException("Error while loading the properties file : " + e.getMessage());
        }
    }
    
    public final static int DEFAULT = 0;
    public final static int TYPE = 1;
    public final static int ANNOTATION = 2;
}
