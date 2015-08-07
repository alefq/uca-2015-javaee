package py.edu.uca.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import py.edu.uca.test.domain.UserEntity;
import py.edu.uca.test.domain.enums.UserStateEnum;
import py.edu.uca.test.security.AuthenticationValidation;
import py.edu.uca.test.service.AuthenticationService;
import py.edu.uca.test.web.repository.UserRepository;

import javax.annotation.Resource;

/**
 * Created by sodep on 11/02/14.
 */
// FIXME Eliminate the duplication of message and code in this class
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    protected static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    @Resource
    private UserRepository userRepository;


    @Override
    public AuthenticationValidation checkCredentials(String email, String password) {

        String msg;
        UserEntity user = userRepository.findOneByMailAndPassword(email, password);
        if (user == null) {
            logger.error("Bad credentials for '" + email + "'");
            return new AuthenticationValidation(false, null,
                    "Bad credentials for '" + email + "'");
        }
        msg = "User: " + user.getMail();
        if (UserStateEnum.SUSPENDED_BY_ADMIN.toString().equals(user.getState())) {
            logger.info(msg + " is suspended by admin");
            return new AuthenticationValidation(false, UserStateEnum.SUSPENDED_BY_ADMIN,
                    "Para continuar utilizando la aplicación por favor comuníquese con : soporte@sodep.com.py");
        }

        if (UserStateEnum.WAITING_FOR_APPROVAL.toString().equals(user.getState())) {
            logger.info(msg + " is still waiting for registration approval");
            return new AuthenticationValidation(false, UserStateEnum.WAITING_FOR_APPROVAL,
                    "Su cuenta todavía está en espera de aprobación");
        }

        return new AuthenticationValidation();

    }


    @Override
    public AuthenticationValidation checkUserState(String email) {

        UserEntity user = userRepository.findOneByMail(email);

        if (UserStateEnum.SUSPENDED_BY_ADMIN.toString().equals(user.getState())) {
            logger.info("User: " + user.getMail() + " is suspended by admin");
            return new AuthenticationValidation(false, UserStateEnum.SUSPENDED_BY_ADMIN,
                    "Para continuar utilizando la aplicación por favor comuníquese con : soporte@sodep.com.py");
        }

        if (UserStateEnum.WAITING_FOR_APPROVAL.toString().equals(user.getState())) {
            logger.info("User: " + user.getMail() + " is still waiting for registration approval");
            return new AuthenticationValidation(false, UserStateEnum.WAITING_FOR_APPROVAL,
                    "Su cuenta todavía está en espera de aprobación");
        }

        return new AuthenticationValidation();

    }

}
