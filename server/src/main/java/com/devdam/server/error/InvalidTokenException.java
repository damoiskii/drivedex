package com.devdam.server.error;

/**
 * InvalidTokenException is an exception that is thrown when a token is invalid,
 * indicating that the provided token does not meet the required criteria or has expired.
 */
public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message) {
        super(message);
    }
}
