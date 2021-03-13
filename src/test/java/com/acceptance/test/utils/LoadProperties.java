package com.acceptance.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LoadProperties {

    private static LoadProperties INSTANCE = null;
    private final Properties props = new Properties();

    public LoadProperties() {
        this.loadProperties("config.properties");
        this.props.putAll(System.getProperties());
    }

    private static LoadProperties getInstance() {
        if (isNull(LoadProperties.INSTANCE)) {
            LoadProperties.INSTANCE = new LoadProperties();
        }
        return LoadProperties.INSTANCE;
    }

    public  void loadProperties(final String path) {

        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemResourceAsStream(path);
            if (nonNull(inputStream)) {
                this.props.load(inputStream);
            } else {
                throw new UnableToLoadPropertiesException("property file " + path + " not found in the classpath ");
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return;

    }

    public static Properties getProps() {
        return LoadProperties.getInstance().props;
    }
    public static String getProperty(String propKey) {
       return getProps().getProperty(propKey);
    }
}

class  UnableToLoadPropertiesException extends  RuntimeException {
    UnableToLoadPropertiesException(final String message) {
        super(message);
    }

    public UnableToLoadPropertiesException(final String message, final Exception ex) {
        super(message, ex);
    }


}