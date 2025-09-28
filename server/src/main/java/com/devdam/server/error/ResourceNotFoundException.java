package com.devdam.server.error;

/**
 * ResourceNotFoundException is an exception that is thrown when a requested resource
 * is not found in the system, indicating that the resource does not exist or is not available.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
