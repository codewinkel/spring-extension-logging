package io.winkelmann.sel.config;

/**
 * @author m.winkelmann
 */
public enum LogField {

    // request fields
    /**
     * requested http method: POST, GET, PUT, DELETE, ...
     */
    HTTP_REQUEST_METHOD("%http_request_method%"),
    /**
     * request uri
     */
    HTTP_REQUEST_URI("%http_request_uri%"),
    /**
     * request url of the resource
     */
    HTTP_REQUEST_URL("%http_request_url%"),
    /**
     * requested session id, if passed
     */
    HTTP_REQUEST_SESSION_ID("%http_request_session_id%"),
    /**
     * if no session id was passed, spring will create one for you
     */
    HTTP_REQUEST_CREATED_SESSION_ID("%http_request_created_session_id%"),
    /**
     * all request params, concatenated
     */
    HTTP_REQUEST_PARAMS("%http_request_params%"),

    // response fields
    /**
     * response status of the request: 200(OK), 201(CREATED), 401(UNAUTHORIZED), ...
     */
    HTTP_RESPONSE_STATUS("%http_response_status%"),
    /**
     * all available response headers like sessionIds, ...
     */
    HTTP_RESPONSE_HEADERS("%http_response_headers%"),
    /**
     * the runtime of the whole request
     */
    REQUEST_RUNTIME("%request_runtime%");

    public static final String DEFAULT_NO_STRING = "NO_";

    private String pattern;

    LogField(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }

    public String getDefault() {
        return DEFAULT_NO_STRING + this.name();
    }


    public String getPattern() {
        return pattern;
    }
}