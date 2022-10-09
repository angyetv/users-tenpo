package cl.challenge.tenpo.configuration;

import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.services.RegistrationRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Help: As of Spring 5.3 in favor of implementing HandlerInterceptor and/or AsyncHandlerInterceptor directly.
 */
@Slf4j
@Component
@AllArgsConstructor
public class RequestInterceptor implements AsyncHandlerInterceptor {

    private final RegistrationRequestService registrationRequestService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion request...");
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEndpoint(request.getRequestURI());
        registrationRequest.setMethod(request.getMethod());
        registrationRequest.setRemoteUser(request.getRemoteUser());
        registrationRequest.setStatusCode(response.getStatus());
        log.info("method: {}, endpoint: {}, remoteUser: {}, status: {}",
                request.getMethod(), request.getRequestURI(), request.getRemoteUser(), response.getStatus());
        registrationRequestService.save(registrationRequest);
        AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
