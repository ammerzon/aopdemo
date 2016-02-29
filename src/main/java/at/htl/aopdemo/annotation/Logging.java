package at.htl.aopdemo.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@InterceptorBinding
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Logging {
}
