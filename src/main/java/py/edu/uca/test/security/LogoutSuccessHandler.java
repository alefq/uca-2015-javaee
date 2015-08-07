package py.edu.uca.test.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sodep on 8/25/14.
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandler.class);


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOGGER.debug("Successfull logout");
    }
}
