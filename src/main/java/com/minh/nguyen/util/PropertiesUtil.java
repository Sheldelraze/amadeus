package com.minh.nguyen.util;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Mr.Minh
 * @since 06/01/2018
 * Purpose:
 */
@Component("PropertiesUtil")
public class PropertiesUtil {

    public static final String MESSAGE_PROP = "messages.properties.xml";

    public static final String ITEMS_PROP = "items";

    private Properties messageProp;

    public PropertiesUtil() {
        if (messageProp == null) {
            messageProp = this.getProperties(MESSAGE_PROP);
        }
    }

    public String getMsgProperty(String key) {
        return messageProp.getProperty(key);
    }

    public Properties getProperties(String propertiesFileName) {
        Properties prop = new Properties();
        InputStream istream = null;
        try {
            istream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(propertiesFileName);
            prop.loadFromXML(istream);

            return prop;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public Properties getMessageProp() {
        return messageProp;
    }


    public void setMessageProp(Properties messageProp) {
        this.messageProp = messageProp;
    }

}
