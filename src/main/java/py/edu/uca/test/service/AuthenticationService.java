package py.edu.uca.test.service;

import py.edu.uca.test.security.AuthenticationValidation;

/**
 * Created by sodep on 11/02/14.
 */
public interface AuthenticationService {

    public AuthenticationValidation checkCredentials(String email, String password);

    /**
     * Checks if the user is ACTIVE.
     * <p/>
     * For a user to be ACTIVE it should NOT have
     * one of the following states:
     * <ul>
     * <li>WAITING_FOR_APPROVAL </li>
     * <li>SUSPENDED_BY_ADMIN</li>
     * </ul>
     *
     * @param email
     * @return
     */
    public AuthenticationValidation checkUserState(String email);
}
