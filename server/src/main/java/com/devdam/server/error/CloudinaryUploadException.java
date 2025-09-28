package com.devdam.server.error;

/**
 * CloudinaryUploadException is an exception that is thrown when there is an error
 * during the upload process to Cloudinary, indicating a failure in uploading media files.
 */
public class CloudinaryUploadException extends Exception {
    public CloudinaryUploadException(String message) {
        super(message);
    }
}
