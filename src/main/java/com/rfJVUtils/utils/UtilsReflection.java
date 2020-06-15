package com.rfJVUtils.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Utilities for reflection
 * 
 * @author diego
 *
 */
public final class UtilsReflection {

	private UtilsReflection() {

	}
	
	@SuppressWarnings("rawtypes")
	public static <T> Class<?>[] getGenericInterfaceParameter(Class clazz, Class<T> interfaceType) {
	    Class<?>[] result = new Class<?>[0];
	    // make sure interfaceType is a generic interface.
	    if(!interfaceType.isInterface() || interfaceType.getTypeParameters().length < 1) {
	        return result;
	    }
	    // get all interfaces implemented by concrete class.
	    Type[] interfaceTypes = clazz.getGenericInterfaces();

	    // for each interface the concrete class implements
	    // we check if that interface is actually equal to interfaceType
	    // and is a parametrized type,
	    // i.e has a type parameter.
	    // Once a match is found, we return the type parameters.
	    for(Type it : interfaceTypes) {
	        if(it instanceof ParameterizedType) {
	            ParameterizedType parameterizedType = (ParameterizedType) it;
	            if(!parameterizedType.getRawType().equals(interfaceType)) {
	                continue;
	            }
	            Type[] typeParameters = parameterizedType.getActualTypeArguments();
	            result = new Class[typeParameters.length];
	            for(int j = 0; j < typeParameters.length; j++) {
	                result[j] = (Class) typeParameters[j];
	            }
	        }
	    }

	    return result;
	}
}
