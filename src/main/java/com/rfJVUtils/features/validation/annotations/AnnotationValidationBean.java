package com.rfJVUtils.features.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for know bean is validable
 * 
 * @author diego
 *
 */
//Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
//Allow to use only on types:
@Target(ElementType.TYPE)
public @interface AnnotationValidationBean {

}
