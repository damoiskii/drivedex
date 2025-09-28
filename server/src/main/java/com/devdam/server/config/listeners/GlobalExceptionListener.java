package com.devdam.server.config.listeners;

// import java.util.HashMap;
// import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.authorization.AuthorizationDeniedException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.devdam.server.error.ApiError;
import com.devdam.server.error.CloudinaryRemovalException;
import com.devdam.server.error.CloudinaryUploadException;
import com.devdam.server.error.InvalidTokenException;
import com.devdam.server.error.ResourceConflictException;
import com.devdam.server.error.ResourceNotFoundException;

// import com.devdam.server.dto.requests.ChatbotRequest;
// import com.devdam.server.dto.requests.ContactFormRequest;
// import com.devdam.server.dto.requests.NewsletterFormRequest;
// import com.devdam.server.dto.requests.OfferFormRequest;
// import com.devdam.server.exception.ApiError;
// import com.devdam.server.exception.CloudinaryRemovalException;
// import com.devdam.server.exception.CloudinaryUploadException;
// import com.devdam.server.exception.InvalidTokenException;
// import com.devdam.server.exception.ChatbotException;
// import com.devdam.server.exception.ResourceConflictException;
// import com.devdam.server.exception.ResourceNotFoundException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception listener for the application.
 * This class handles various exceptions and returns standardized error responses.
 */
@ControllerAdvice
public class GlobalExceptionListener {
    /**
     * Builds a standardized error response for exceptions.
     *
     * @param status  the HTTP status code
     * @param message the error message
     * @param path    the request path
     * @return a ResponseEntity containing the ApiError
     */
    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message, String path) {
        ApiError error = new ApiError(status, message, path);
        return new ResponseEntity<>(error, status);
    }

    // /**
    //  * Builds a standardized error response for exceptions with a retry-after header.
    //  *
    //  * @param status              the HTTP status code
    //  * @param message             the error message
    //  * @param path                the request path
    //  * @param retryAfterSeconds   the number of seconds to wait before retrying
    //  * @return a ResponseEntity containing the ApiError and retry-after header
    //  */
    // private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message, String path, String retryAfterSeconds) {
    //     ApiError error = new ApiError(status, message, path);
        
    //     ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(status);

    //     if (path != null && path.equalsIgnoreCase("/chatbot") && retryAfterSeconds != null) {
    //         responseBuilder.header("Retry-After", retryAfterSeconds);
    //     }

    //     return responseBuilder.body(error);
    // }


    /**
     * Handles ResourceNotFoundException.
     * Returns a 404 Not Found response with an error message.
     *
     * @param ex      the ResourceNotFoundException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles ResourceConflictException.
     * Returns a 409 Conflict response with an error message.
     *
     * @param ex      the ResourceConflictException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiError> handleConflict(ResourceConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
    }

    // /**
    //  * Handles validation errors for specific request types.
    //  * Returns a 400 Bad Request response with validation error details.
    //  *
    //  * @param ex the MethodArgumentNotValidException containing validation errors
    //  * @return a ResponseEntity with error details
    //  */
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    //     Map<String, String> errors = new HashMap<>();

    //     // Check the validated object type
    //     Object target = ex.getBindingResult().getTarget();
    //     if (target instanceof ContactFormRequest || target instanceof NewsletterFormRequest ||  target instanceof OfferFormRequest || target instanceof ChatbotRequest) {
    //         ex.getBindingResult().getFieldErrors().forEach(error ->
    //             errors.put(error.getField(), error.getDefaultMessage())
    //         );

    //         return ResponseEntity.badRequest().body(Map.of(
    //             "success", false,
    //             "message", "Enter the required fields!",
    //             "errors", errors
    //         ));
    //     }

    //     // Fallback for other @Valid objects
    //     return ResponseEntity.badRequest().body(Map.of(
    //         "success", false,
    //         "message", "Validation failed",
    //         "errors", ex.getMessage()
    //     ));
    // }

    /**
     * Handles messaging exceptions, such as email sending failures.
     * Returns a 417 Expectation Failed response with an error message.
     *
     * @param ex      the MessagingException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ApiError> handleMessagingException(MessagingException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles InvalidTokenException.
     * Returns a 401 Unauthorized response with an error message.
     *
     * @param ex      the InvalidTokenException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> handleInvalidToken(InvalidTokenException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CloudinaryUploadException.
     * Returns a 500 Internal Server Error response with an error message.
     *
     * @param ex      the CloudinaryUploadException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(CloudinaryUploadException.class)
    public ResponseEntity<ApiError> handleCloudinaryUploadException(CloudinaryUploadException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CloudinaryRemovalException.
     * Returns a 500 Internal Server Error response with an error message.
     *
     * @param ex      the CloudinaryRemovalException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(CloudinaryRemovalException.class)
    public ResponseEntity<ApiError> handleCloudinaryRemovalException(CloudinaryRemovalException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), request.getRequestURI());
    }

    // /**
    //  * Handles OpenRouterException.
    //  * Returns a 503 Service Unavailable response if not rate limited, or a 429 Too Many Requests response if rate limited.
    //  *
    //  * @param ex      the OpenRouterException
    //  * @param request the HttpServletRequest
    //  * @return a ResponseEntity with error details
    //  */
    // @ExceptionHandler(ChatbotException.class)
    // public ResponseEntity<ApiError> handleChatbotException(ChatbotException ex, HttpServletRequest request) {
    //     HttpStatus status = ex.isRateLimited()
    //         ? HttpStatus.TOO_MANY_REQUESTS // 429
    //         : HttpStatus.SERVICE_UNAVAILABLE; // 503

    //     return buildErrorResponse(status, ex.getMessage(), "/chatbot", status.value() == 503 ? "120" : "360"); // Retry after x seconds
    // }

    /**
     * Handles IllegalArgumentException.
     * Returns a 400 Bad Request response with an error message.
     *
     * @param ex      the IllegalArgumentException
     * @param request the HttpServletRequest
     * @return a ResponseEntity with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles 404 Not Found errors.
     * If the request is not for an API route, it returns a custom 404 error page.
     * For API routes, it lets Spring handle the 404 response.
     *
     * @param ex      the NoHandlerFoundException
     * @param request the HttpServletRequest
     * @return the name of the 404 error view or null for API routes
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handle404(NoHandlerFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles 500 Internal Server Error.
     * Returns a custom 500 error page.
     *
     * @param ex the Exception
     * @return the name of the 500 error view
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handle500(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
    }

    // /**
    //  * Handles access denied errors (403 Forbidden).
    //  * Returns a custom 403 error page.
    //  *
    //  * @return the name of the 403 error view
    //  */
    // @ExceptionHandler(AuthorizationDeniedException.class)
    // public ResponseEntity<ApiError> handleAccessDenied(Exception ex, HttpServletRequest request) {
    //     return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage(), request.getRequestURI());
    // }
}
