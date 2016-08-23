package io.winkelmann.sel.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author m.winkelmann
 */
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    public LoggingInterceptorConfig config;

    private LoggingInterceptorService loggingInterceptorService;

    public LoggingInterceptor() {
        this(new LoggingInterceptorConfig());
    }

    public LoggingInterceptor(LoggingInterceptorConfig config) {
        this.config = config;
        this.loggingInterceptorService = new LoggingInterceptorService();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        MDC.put("KEX", UUID.randomUUID().toString());
        LOG.info(loggingInterceptorService.generateMessagePreHandle(config, httpServletRequest, httpServletResponse));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOG.info(loggingInterceptorService.generateMessagePostHandle(config, httpServletRequest, httpServletResponse, o, modelAndView));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOG.info(loggingInterceptorService.generateMessageAfterCompletion(config, httpServletRequest, httpServletResponse, o, e));
        MDC.clear();
    }

    // FUTURE TODO: make logging level settable

}
