package py.edu.uca.test.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import py.edu.uca.test.service.UserService;
import py.edu.uca.test.web.session.SessionManager;
import py.edu.uca.test.web.session.UserCurrentInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sodep on 8/27/14.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        SessionManager sm = new SessionManager(request);
        UserCurrentInfo user = sm.getUser();

        if (user != null) {
            if (userService.isUserSuspended(user.getUserId())) {
                // User is suspended
                int responseStatus = HttpServletResponse.SC_FORBIDDEN;
                LOGGER.debug("User is suspended. Requesting: " + request.getRequestURI()
                        + " and responding with HTTP " + responseStatus);

                response.setStatus(responseStatus);
                return false;
            }
        } else {
            LOGGER.error("User is null.");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOGGER.debug("afterCompletion");
    }
}
