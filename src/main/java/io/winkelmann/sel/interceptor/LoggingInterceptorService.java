package io.winkelmann.sel.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author m.winkelmann
 */
public class LoggingInterceptorService {

    private static final String START_TIME_ATTRIBUTE = "startTime";

    public String generateMessagePreHandle(LoggingInterceptorConfig config, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequest.setAttribute(START_TIME_ATTRIBUTE, LocalDateTime.now());
        String pattern = config.getPreHandleLogPattern();
        return generateMessage(pattern, httpServletRequest, httpServletResponse, Duration.ZERO);
    }

    public String generateMessageAfterCompletion(LoggingInterceptorConfig config, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Duration runtime = calculateRuntime(httpServletRequest);
        String pattern = config.getAfterCompletionLogPattern();
        return generateMessage(pattern, httpServletRequest, httpServletResponse, runtime);
    }

    public String generateMessagePostHandle(LoggingInterceptorConfig config, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        Duration runtime = calculateRuntime(httpServletRequest);
        String pattern = config.getPostHandleLogPattern();
        return generateMessage(pattern, httpServletRequest, httpServletResponse, runtime);
    }

    private Duration calculateRuntime(HttpServletRequest httpServletRequest) {
        LocalDateTime startTime = (LocalDateTime) httpServletRequest.getAttribute(START_TIME_ATTRIBUTE);
        return Duration.between(startTime, LocalDateTime.now());
    }

    private String generateMessage(String pattern, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Duration runtime) {
        String generatedMessage = pattern;
        for (LogField field : LogField.values()) {

            switch (field) {
                // request fields
                case HTTP_REQUEST_METHOD:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletRequest.getMethod());
                    break;
                case HTTP_REQUEST_URI:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletRequest.getRequestURI());
                    break;
                case HTTP_REQUEST_URL:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletRequest.getRequestURL().toString());
                    break;
                case HTTP_REQUEST_SESSION_ID:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletRequest.getRequestedSessionId());
                    break;
                case HTTP_REQUEST_CREATED_SESSION_ID:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletRequest.getSession().getId());
                    break;
                // response fields
                case HTTP_RESPONSE_STATUS:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), String.valueOf(httpServletResponse.getStatus()));
                    break;
                case HTTP_RESPONSE_HEADERS:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), httpServletResponse.getHeaderNames().stream()
                            .map(httpServletResponse::getHeader).collect(Collectors.joining(",")));
                    break;
                case REQUEST_RUNTIME:
                    generatedMessage = StringUtils.replace(generatedMessage, field.getPattern(), String.valueOf(runtime.toMillis()));
                    break;
            }
        }
        return generatedMessage;
    }

}
