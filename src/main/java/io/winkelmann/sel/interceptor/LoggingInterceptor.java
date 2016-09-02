package io.winkelmann.sel.interceptor;

import io.winkelmann.sel.config.LoggingInterceptorConfig;
import io.winkelmann.sel.service.LoggingInterceptorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor to log all incomming and outgoing request. You can also configure your log-format and set
 * some important replacements in the config which will be replaced like the HTTP status.
 * <p><p>
 * e.g. format <p><b>"HttpMethod: %http_request_method% - RequestURI: %http_request_uri% - Runtime: %request_runtime%"</b>
 * <p>this will generate a log message like this:
 * <p><b>"HttpMethod: GET - RequestURI: /v1/products - Runtime: 5ms"</b>
 *
 * @author m.winkelmann
 */
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    public LoggingInterceptorConfig config;

    private LoggingInterceptorService loggingInterceptorService;

    /**
     * Generates Logginginterceptor with default config.
     */
    public LoggingInterceptor() {
        this(new LoggingInterceptorConfig());
    }

    /**
     * Generate a LoggingInterceptor with a self created logging config.
     *
     * @param config
     */
    public LoggingInterceptor(LoggingInterceptorConfig config) {
        this.config = config;
        this.loggingInterceptorService = new LoggingInterceptorService();
    }

    /**
     * Logs the message you have configured when event preHandle will be thrown.
     *
     * @see HandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOG.debug(loggingInterceptorService.generateMessagePreHandle(config, httpServletRequest, httpServletResponse));
        return true;
    }

    /**
     * Logs the message you have configured when event postHandle will be thrown.
     *
     * @see HandlerInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOG.debug(loggingInterceptorService.generateMessagePostHandle(config, httpServletRequest, httpServletResponse, o, modelAndView));
    }

    /**
     * Logs the message you have configured when event afterCompletion was thrown.
     *
     * @see HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOG.info(loggingInterceptorService.generateMessageAfterCompletion(config, httpServletRequest, httpServletResponse, o, e));
    }

}
