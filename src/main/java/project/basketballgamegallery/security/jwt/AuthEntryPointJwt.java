package project.basketballgamegallery.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom implementation of AuthenticationEntryPoint for handling unauthorized requests.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Handles unauthorized requests by sending an error response with the appropriate status code and message.
     *
     * @param request       the HttpServletRequest
     * @param response      the HttpServletResponse
     * @param authException the AuthenticationException that occurred
     * @throws IOException      if an I/O error occurs during the handling of the request
     * @throws ServletException if a servlet-specific error occurs during the handling of the request
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}