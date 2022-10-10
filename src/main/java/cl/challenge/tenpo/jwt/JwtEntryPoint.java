package cl.challenge.tenpo.jwt;

import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.services.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final RegistrationRequestService registrationRequestService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("Unauthorized user: {}, message: {}", exception.getLocalizedMessage(), exception.getMessage());
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEndpoint(request.getRequestURI());
        registrationRequest.setMethod(request.getMethod());
        registrationRequest.setRemoteUser(request.getRemoteUser());
        registrationRequest.setStatusCode(response.getStatus());
        registrationRequestService.save(registrationRequest);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized user");
    }
}
