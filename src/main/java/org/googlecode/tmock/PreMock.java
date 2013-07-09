package org.googlecode.tmock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
 
@Retention(RetentionPolicy.RUNTIME)
public @interface PreMock {
Class<?>[] value();
}
