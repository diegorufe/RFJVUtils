package com.rfJVUtils.resourceBundle.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfJVUtils.exceptions.RFException;

/**
 * 
 * @author diego
 *
 */
public class RFResourceBundleFactory extends BaseResourceBundleFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(RFResourceBundleFactory.class);

	private static final String RESOURCE_BUNDLE_FILENAME = "rf";

	private static final RFResourceBundleFactory INSTANCE = initInstance();

	private RFResourceBundleFactory() throws RFException {
		super(RESOURCE_BUNDLE_FILENAME);
	}

	/**
	 * Mhetod for get init instance
	 * 
	 * @return instance resource bundle
	 */
	private static RFResourceBundleFactory initInstance() {
		RFResourceBundleFactory instance = null;
		try {
			instance = new RFResourceBundleFactory();
		} catch (RFException ignored) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error(ignored.getLocalizedMessage(), ignored);
			}
		}
		return instance;
	}

	/**
	 * Method for get instance resource bundle
	 * 
	 * @return instance for resource bundle
	 */
	public static RFResourceBundleFactory getInstance() {
		return INSTANCE;
	}

}
