package com.devdam.server.error;

/**
 * ResourceConflictException is an exception that is thrown when there is a conflict
 * with the current state of a resource, indicating that the requested operation
 * cannot be completed due to a conflict with the existing resource state.
 */
public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
