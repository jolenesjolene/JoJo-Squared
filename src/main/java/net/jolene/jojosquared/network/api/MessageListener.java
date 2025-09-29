package net.jolene.jojosquared.network.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/// Purely decorator annotation: tells the Message Events system that this method has networking.
public @interface MessageListener {
    /// The namespace of the listener.
    public String value();
}