package io.winkelmann.sel.interceptor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author m.winkelmann
 */
@Setter
@Getter
public class LoggingInterceptorConfig {

    private static final String DEFAULT_PRE_HANDLE_LOG_PATTERN = initDefaultPreHandlePattern();

    private static String initDefaultPreHandlePattern() {
        StringBuilder pattern = new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD);
        pattern.append(" - RequestURI: ").append(LogField.HTTP_REQUEST_URI);
        pattern.append(" - RequestURL: ").append(LogField.HTTP_REQUEST_URL);
        pattern.append(" - RequestedSessionId: ").append(LogField.HTTP_REQUEST_SESSION_ID);
        pattern.append(" - CreatedSessionId: ").append(LogField.HTTP_REQUEST_CREATED_SESSION_ID);
        return pattern.toString();
    }

    private static final String DEFAULT_AFTER_COMPLETION_LOG_PATTERN = initDefaultAfterCompletionPattern();

    private static String initDefaultAfterCompletionPattern() {
        StringBuilder pattern = new StringBuilder("Method: " + LogField.HTTP_REQUEST_METHOD);
        pattern.append(" - RequestURI: " + LogField.HTTP_REQUEST_URI);
        pattern.append(" - HttpStatus: " + LogField.HTTP_RESPONSE_STATUS);
        pattern.append(" - Runtime: " + LogField.REQUEST_RUNTIME + "ms");
        return pattern.toString();
    }

    private static final String DEFAULT_POST_HANDLE_LOG_PATTERN = initDefaultPostHandlePattern();

    private static String initDefaultPostHandlePattern() {
        // TODO
        return initDefaultAfterCompletionPattern();
    }

    private String preHandleLogPattern = DEFAULT_PRE_HANDLE_LOG_PATTERN;

    private String postHandleLogPattern = DEFAULT_POST_HANDLE_LOG_PATTERN;

    private String afterCompletionLogPattern = DEFAULT_AFTER_COMPLETION_LOG_PATTERN;


}
