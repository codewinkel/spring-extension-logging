# Spring Logging Interceptor
... was build for every Spring developer and needs to be placed in every spring project! :)

##About
This is a small library for Spring which will give you the ability to add a logging interceptor to spring to log all http requests.
You could also configure a lot for the logging interceptor e.g. what should be logged, when should the log appear, ...

##How to use??
You only need to add this Interceptor to your spring contexts InterceptorRegistry. In Spring Boot you only need to override the _addInterceptors_ in your service starter.

    @SpringBootApplication
    public class WebApplicationStarter extends WebMvcConfigurerAdapter {
    ...
         @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoggingInterceptor());
            }
    ... 
    }
For older Spring versions you also need to add this interceptor to your available interceptor registry/list but in a little bit different way. If you have some questions for it feel free to ask me.

##Configuration
You could configure how your logs should look like. There are some uniform events which will be triggered by spring.
For every event/method you could configure a log pattern({eventName}LogPattern) and a log level this message should be logged({eventName}LogLevel)

###Configurable Replacements
[All replacement strings are documented in this file - LogField.java](https://github.com/mwinkelmann/spring-extension-logging/blob/master/src/main/java/io/winkelmann/sel/config/LogField.java)

###pre handle
    This method will be called after HandlerMapping determined an appropriate handler object, but before HandlerAdapter invokes the handler
* preHandleLogPattern - Log pattern you want to have replaced and logged in <b>PRE_HANDLE</b> 
    [Default: _Method: %http_request_method% - RequestURI: %http_request_uri% - RequestURL: %http_request_url% - Runtime: %request_runtime%ms - RequestedSessionId: %http_request_session_id% - CreatedSessionId: %http_request_created_session_id%_]
* preHandleLogLevel - specify the LogLevel the log should appear
    [Default: debug]
###post handle 
    This method will be called after HandlerAdapter actually invoked the handler, but before the DispatcherServlet renders the view.
* postHandleLogPattern - Log pattern you want to have replaced and logged in <b>POST_HANDLE</b>
    [Default: _Method: %http_request_method% - RequestURI: %http_request_uri% - HttpStatus: %http_response_status% - Runtime: %request_runtime%ms_]
* postHandleLogLevel - specify the LogLevel the log should appear
    [Default: debug]
###after completion
    This method will be called after completion of request processing, that is, after rendering the view.
* afterCompletionLogPattern - Log pattern you want to have replaced and logged in <b>AFTER_COMPLETION</b>   
    [Default: _Method: %http_request_method% - RequestURI: %http_request_uri% - RequestParams: %http_request_params% - HttpStatus: %http_response_status% - Runtime: %request_runtime%ms_]
* afterCompletionLogLevel - specify the LogLevel the log should appear
    [Default: info]
##Maven
Current version is available at central repository

    <dependencies>
        ...
        <dependency>
	        <groupId>io.winkelmann</groupId>
	        <artifactId>spring-extension-logging</artifactId>
	        <version>{CURRENT_VERSION}</version>
	    </dependency>
        ...
    </dependencies>
For the current version take a look at the maven central page: https://mvnrepository.com/artifact/io.winkelmann/spring-extension-logging

###TODO
* add tests
* make logging level settable
* some performance tests