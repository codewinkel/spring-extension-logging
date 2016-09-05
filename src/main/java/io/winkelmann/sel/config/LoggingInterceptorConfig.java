package io.winkelmann.sel.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Config to set you own logging message for preHandle, postHandle and afteCompletion events.
 *
 * @author m.winkelmann
 */
@Setter
@Getter
public class LoggingInterceptorConfig {

    private static final String DEFAULT_PRE_HANDLE_LOG_PATTERN = initDefaultPreHandlePattern();
    private static final String DEFAULT_AFTER_COMPLETION_LOG_PATTERN = initDefaultAfterCompletionPattern();
    private static final String DEFAULT_POST_HANDLE_LOG_PATTERN = initDefaultPostHandlePattern();

    private String preHandleLogPattern;
    private String postHandleLogPattern;
    private String afterCompletionLogPattern;

    public LoggingInterceptorConfig() {
        this.preHandleLogPattern = DEFAULT_PRE_HANDLE_LOG_PATTERN;
        this.postHandleLogPattern = DEFAULT_POST_HANDLE_LOG_PATTERN;
        this.afterCompletionLogPattern = DEFAULT_AFTER_COMPLETION_LOG_PATTERN;
    }

    public LoggingInterceptorConfig(String preHandleLogPattern, String postHandleLogPattern, String afterCompletionLogPattern) {
        this.preHandleLogPattern = preHandleLogPattern;
        this.postHandleLogPattern = postHandleLogPattern;
        this.afterCompletionLogPattern = afterCompletionLogPattern;
    }

    private static String initDefaultPreHandlePattern() {
        return new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD)
                .append(" - RequestURI: ").append(LogField.HTTP_REQUEST_URI)
                .append(" - RequestURL: ").append(LogField.HTTP_REQUEST_URL)
                .append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms")
                .append(" - RequestedSessionId: ").append(LogField.HTTP_REQUEST_SESSION_ID)
                .append(" - CreatedSessionId: ").append(LogField.HTTP_REQUEST_CREATED_SESSION_ID)
                .toString();
    }

    private static String initDefaultAfterCompletionPattern() {
        return new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD)
                .append(" - RequestURI: " + LogField.HTTP_REQUEST_URI)
                .append(" - RequestParams: " + LogField.HTTP_REQUEST_PARAMS)
                .append(" - HttpStatus: " + LogField.HTTP_RESPONSE_STATUS)
                .append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms")
                .toString();
    }

    private static String initDefaultPostHandlePattern() {
        return new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD)
                .append(" - RequestURI: " + LogField.HTTP_REQUEST_URI)
                .append(" - HttpStatus: " + LogField.HTTP_RESPONSE_STATUS)
                .append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms")
                .toString();
    }

}
