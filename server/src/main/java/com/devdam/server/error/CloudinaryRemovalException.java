package com.devdam.server.error;

/**
 * CloudinaryRemovalException is an exception that is thrown when there is an error
 * during the removal of a resource from Cloudinary, indicating a failure in the
 * cloud storage operation.
 */
public class CloudinaryRemovalException extends Exception {
    public CloudinaryRemovalException(String message) {
        super(message);
    }
}
