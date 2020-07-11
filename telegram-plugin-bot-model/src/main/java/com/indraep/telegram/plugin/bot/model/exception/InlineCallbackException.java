package com.indraep.telegram.plugin.bot.model.exception;

/**
 * Created by indraep on 3/11/17.
 */
public class InlineCallbackException extends RuntimeException {
    public InlineCallbackException(String message) {
        super(message);
    }
}
