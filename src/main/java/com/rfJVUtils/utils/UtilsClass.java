package com.rfJVUtils.utils;

import java.io.File;
import java.util.ArrayList;

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
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	public static Class loadClassForName(String name) throws ClassNotFoundException {
		try {
			return Class.forName(name, false, UtilsClass.class.getClassLoader());
		} catch (ClassNotFoundException e) {
			try {
				return Class.forName(name, false, getContextClassLoader());
			} catch (ClassNotFoundException e2) {
				throw e2;
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
	 * @return array for classes inside package name
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final static Class[] getAllClasses(String packageName) {
		try {
			ArrayList classes = new ArrayList();
			// Get a File object for the package
			File directory = null;
			try {
				directory = new File(Thread.currentThread().getContextClassLoader()
						.getResource(packageName.replace('.', '/')).getFile());
			} catch (NullPointerException x) {
				System.out.println("Nullpointer");
				throw new ClassNotFoundException(packageName + " does not appear to be a valid package");
			}
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();
				for (int i = 0; i < files.length; i++) {
					// we are only interested in .class files
					if (files[i].endsWith(".class")) {
						// removes the .class extension
						classes.add(Class.forName(packageName + '.' + files[i].substring(0, files[i].length() - 6)));
					}
				}
			} else {
				System.out.println("Directory does not exist");
				throw new ClassNotFoundException(packageName + " does not appear to be a valid package");
			}
			Class[] classesA = new Class[classes.size()];
			classes.toArray(classesA);

			return classesA;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
