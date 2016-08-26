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
        StringBuilder pattern = new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD);
        pattern.append(" - RequestURI: ").append(LogField.HTTP_REQUEST_URI);
        pattern.append(" - RequestURL: ").append(LogField.HTTP_REQUEST_URL);
        pattern.append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms");
        pattern.append(" - RequestedSessionId: ").append(LogField.HTTP_REQUEST_SESSION_ID);
        pattern.append(" - CreatedSessionId: ").append(LogField.HTTP_REQUEST_CREATED_SESSION_ID);
        return pattern.toString();
    }

    private static String initDefaultAfterCompletionPattern() {
        StringBuilder pattern = new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD);
        pattern.append(" - RequestURI: " + LogField.HTTP_REQUEST_URI);
        pattern.append(" - RequestParams: " + LogField.HTTP_REQUEST_PARAMS);
        pattern.append(" - HttpStatus: " + LogField.HTTP_RESPONSE_STATUS);
        pattern.append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms");
        return pattern.toString();
    }

    private static String initDefaultPostHandlePattern() {
        StringBuilder pattern = new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD);
        pattern.append(" - RequestURI: " + LogField.HTTP_REQUEST_URI);
        pattern.append(" - HttpStatus: " + LogField.HTTP_RESPONSE_STATUS);
        pattern.append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms");
        return pattern.toString();
    }

}
