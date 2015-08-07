package py.edu.uca.test.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import py.edu.uca.test.domain.enums.UserStateEnum;
import py.edu.uca.test.exception.UserSuspendedException;
import py.edu.uca.test.service.AuthenticationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Reference:
 * http://stackoverflow.com/questions/3205469/custom-authentication-in-spring
 *
 * @author sodep S.A.
 */
@Component
public class MainAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainAuthenticationProvider.class);

    @Autowired
    private AuthenticationService authService;

    /**
     * La interfaz de Spring que se implementa, define este thrown de un Runtime Exception.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                "Only UsernamePasswordAuthenticationToken is supported");

        final UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
        String mail = userToken.getName();
        String password = (String) authentication.getCredentials();

        LOGGER.debug("Authenticating user: " + mail);
        AuthenticationValidation checkCredentials = authService.checkCredentials(mail, password);
        if (checkCredentials.getSuccess()) {
            String[] roles = new String[]{"ROLE_USER"};
            return createSuccessfulAuthentication(mail, password,
                    null, roles);
        } else {
            if (UserStateEnum.SUSPENDED_BY_ADMIN.equals(checkCredentials.getUserStateEnum())) {
                throw new UserSuspendedException(checkCredentials.getMessage());
            } else {
                throw new BadCredentialsException(checkCredentials.getMessage());
            }
        }
    }

    protected UsernamePasswordAuthenticationToken createSuccessfulAuthentication(String mail, String password,
                                                                                 Object details, String[] authoritiesList) {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : authoritiesList) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        Collection<GrantedAuthority> unmodifiableCollection = Collections.unmodifiableCollection(authorities);
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(mail, password,
                unmodifiableCollection);
        result.setDetails(details);

        return result;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}