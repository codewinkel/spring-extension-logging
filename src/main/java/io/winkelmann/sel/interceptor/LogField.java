package io.winkelmann.sel.interceptor;

/**
 * @author m.winkelmann
 */
enum LogField {

    // request fields
    HTTP_REQUEST_METHOD("%http_request_method%"),
    HTTP_REQUEST_URI("%http_request_uri%"),
    HTTP_REQUEST_URL("%http_request_url%"),
    HTTP_REQUEST_SESSION_ID("%http_request_session_id%"),
    HTTP_REQUEST_CREATED_SESSION_ID("%http_request_created_session_id%"),

    // response fields
    HTTP_RESPONSE_STATUS("%http_response_status%"),
    HTTP_RESPONSE_HEADERS("%http_response_headers%"),
    REQUEST_RUNTIME("%request_runtime%");

    private String pattern;

    LogField(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }

    public String getPattern() {
        return pattern;
    }
}