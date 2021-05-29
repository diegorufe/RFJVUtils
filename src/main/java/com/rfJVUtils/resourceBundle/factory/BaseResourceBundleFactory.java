package com.rfJVUtils.resourceBundle.factory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfJVUtils.constants.core.EnumErrorCodes;
import com.rfJVUtils.exceptions.RFException;
import com.rfJVUtils.utils.commons.UtilsCollection;
import com.rfJVUtils.utils.commons.UtilsString;

/**
 * Base resource bundle factory for translate messages
 *
 * @author diego
 */
public abstract class BaseResourceBundleFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResourceBundleFactory.class);

    /**
     * Name for resource bundle
     */
    private final String resourceBundleFileName;

    public BaseResourceBundleFactory(String resourceBundleFileName) throws RFException {
        if (UtilsString.isEmpty(resourceBundleFileName)) {
            throw new RFException(EnumErrorCodes.RESOURCE_BUNDLE_FILE_NAME_IS_NULL_OR_EMPTY);
        }
        this.resourceBundleFileName = resourceBundleFileName;
    }

    /**
     * Method for get resource bundle file name
     *
     * @return resource bundle file name
     */
    public String getResourcleBundleFileName() {
        return this.resourceBundleFileName;
    }

    /**
     * Method for translate i18n key
     *
     * @param key for get message to translate
     * @return message translate if found
     */
    public String translate(String key) {
        return translate(null, key, null);
    }

    /**
     * Method for translate i18n key
     *
     * @param locale for translate
     * @param key    for get message to translate
     * @return message translate if found
     */
    public String translate(Locale locale, String key) {
        return translate(locale, key, null);
    }

    /**
     * Method for translate i18n key
     *
     * @param key    for get message to translate
     * @param params for pass message
     * @return message translate if found
     */
    public String translate(String key, Object[] params) {
        return translate(null, key, params);
    }

    /**
     * Method for translate i18n key
     *
     * @param locale for translate
     * @param key    for get message to translate
     * @param params for pass message
     * @return message translate if found
     */
    public String translate(Locale locale, String key, Object[] params) {
        String messageBundle = key;
        try {
            Locale localeBundle = locale == null ? Locale.getDefault() : locale;

            // DONT SAVE resource bundle in properties. Java do it save in cache
            ResourceBundle bundle = ResourceBundle.getBundle(this.getResourcleBundleFileName(), localeBundle);

            messageBundle = bundle.getString(key);

            if (UtilsCollection.isArrayNotEmpty(params)) {
                messageBundle = MessageFormat.format(messageBundle, params);
            }

        } catch (Throwable exception) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Error translate. Key " + key + " , ResourceBundleFileName "
                        + this.getResourcleBundleFileName() + ". Error:  " + exception.getLocalizedMessage());
            }
        }
        return messageBundle;
    }

}
