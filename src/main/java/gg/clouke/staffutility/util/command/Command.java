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
public @interface Command {

    public String name();
    public String permission() default "";
    public String noPerm() default "§cI'm sorry you do not have permission to perform this command.";
    public String[] aliases() default {};
    public String description() default "";
    public String usage() default "";
    public boolean inGameOnly() default true;

}
