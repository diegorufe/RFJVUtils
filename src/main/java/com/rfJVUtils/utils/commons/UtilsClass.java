package com.rfJVUtils.utils.commons;

import java.io.File;
import java.util.ArrayList;

import com.rfJVUtils.constants.core.EnumErrorCodes;
import com.rfJVUtils.exceptions.RFException;
import com.rfJVUtils.resourceBundle.factory.RFResourceBundleFactory;

/**
 * Utilities for class
 * 
 * <ul>
 * 
 * <li>{@link #loadClassForName(String)}</li>
 * <li>{@link #getContextClassLoader()}</li>
 * <li>{@link #getAllClasses(String)}</li>
 * 
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsClass {

	private UtilsClass() {

	}

	/**
	 * Method for load class for name
	 * 
	 * @param name for class to load
	 * @return class for name
	 * @throws RFException if class not found
	 */
	@SuppressWarnings("rawtypes")
	public static Class loadClassForName(String name) throws RFException {
		try {
			return Class.forName(name, false, UtilsClass.class.getClassLoader());
		} catch (ClassNotFoundException firtExpcetionClassNotFound) {
			try {
				return Class.forName(name, false, getContextClassLoader());
			} catch (ClassNotFoundException exception) {
				throw new RFException(EnumErrorCodes.CLASS_NOT_FOUND,
						RFResourceBundleFactory.getInstance().translate(null, "rf_error_classNotFoundExpcetion"),
						exception);
			}
		}
	}

	/**
	 * Method for get context class loader
	 * 
	 * @return class loader for context
	 */
	public final static ClassLoader getContextClassLoader() {
		// TODO we probably need to check the SecurityManager and do this via
		// AccessController
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * Method for get all class from package
	 * 
	 * @param packageName to get all classes
	 * @throws RFException if class not found
	 * @return array for classes inside package name
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final static Class[] getAllClasses(String packageName) throws RFException {

		ArrayList classes = new ArrayList();
		// Get a File object for the package
		File directory = null;
		try {
			directory = new File(Thread.currentThread().getContextClassLoader()
					.getResource(packageName.replace(UtilsChar.DOT, '/')).getFile());
		} catch (NullPointerException exception) {
			throw new RFException(EnumErrorCodes.CLASS_NOT_FOUND,
					RFResourceBundleFactory.getInstance().translate(null, "rf_error_classNotFoundExpcetion"),
					exception);
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					try {
						classes.add(Class
								.forName(packageName + UtilsChar.DOT + files[i].substring(0, files[i].length() - 6)));
					} catch (ClassNotFoundException exception) {
						throw new RFException(EnumErrorCodes.CLASS_NOT_FOUND, RFResourceBundleFactory.getInstance()
								.translate(null, "rf_error_classNotFoundExpcetion"), exception);
					}
				}
			}
		} else {
			throw new RFException(EnumErrorCodes.CLASS_NOT_FOUND,
					RFResourceBundleFactory.getInstance().translate(null, "rf_error_classNotFoundExpcetion"));
		}
		Class[] classesA = new Class[classes.size()];
		classes.toArray(classesA);

		return classesA;

	}
}
