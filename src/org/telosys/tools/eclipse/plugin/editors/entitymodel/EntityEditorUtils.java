package org.telosys.tools.eclipse.plugin.editors.entitymodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EntityEditorUtils {
    private static Properties properties;

    public static String getProperty(String propertyName) {
        if (properties == null) {
            EntityEditorUtils.loadPropertiesFile();
        }

        return properties.getProperty(propertyName);
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
}
