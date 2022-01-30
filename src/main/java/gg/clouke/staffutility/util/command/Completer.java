package gg.clouke.staffutility.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Clouke
 * @since 30.01.2022 06:05
 * All Rights Reserved
 */
@Target(ElementType.METHOD) @Retention(RetentionPolicy.RUNTIME)
public @interface Completer {

    String name();
    String[] aliases() default {};

}
