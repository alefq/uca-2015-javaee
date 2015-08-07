package py.edu.uca.test.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import py.edu.uca.test.service.AuthenticationService;
import py.edu.uca.test.web.session.UserCurrentInfo;

public final class SecurityHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);


    private SecurityHelper() {

    }

    public static Boolean checkUserState(UserCurrentInfo user, AuthenticationService authService) {
        LOGGER.debug("Checking state of current user : " + user.getEmail());
        AuthenticationValidation checkUserState = authService.checkUserState(user.getEmail());
        return checkUserState.getSuccess();
    }

}
