package py.edu.uca.test.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.uca.test.domain.UserEntity;
import py.edu.uca.test.domain.enums.RoleTypeEnum;
import py.edu.uca.test.domain.enums.UserStateEnum;
import py.edu.uca.test.service.UserService;
import py.edu.uca.test.web.dto.UserDTO;
import py.edu.uca.test.web.dto.UserProfileDTO;
import py.edu.uca.test.web.repository.UserRepository;

/**
 * Created by sodep on 17/01/14.
 */
@Service
public class UserServiceImpl implements UserService {

    protected static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public UserEntity findById(Long id) {
        UserEntity user = userRepository.findOne(id);
        return user;
    }

    @Override
    public UserEntity findByMail(String email) {
        UserEntity user = userRepository.findOneByMail(email);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void disableUser(Long adminUserId, Long userIdToDisable, UserStateEnum state) {
        UserEntity admin = null;
        if (adminUserId != null && adminUserId.equals(userIdToDisable)) {
            throw new AuthorizationServiceException("You can't disable your own user");
        }
        admin = userRepository.findOne(adminUserId);

        if (!admin.getRole().isRoot()) {
            throw new AuthorizationServiceException("Not an admin user");
        } else {
            UserEntity userToDisable = userRepository.findOne(userIdToDisable);
            String previousState = userToDisable.getState();
            userToDisable.setState(state.toString());
            userRepository.save(userToDisable);
            if (state == UserStateEnum.SUSPENDED_BY_ADMIN) {
                logger.debug("Suspended by admin");
            }
            if (userToDisable.getRole().isAdmin() && previousState.equals(UserStateEnum.WAITING_FOR_APPROVAL.toString())) {
                // if it is the admin user and the previous state was WAITING_FOR_APPROVAL
            	logger.debug("User approved");
            }
        }
    }

    @Transactional
    @Override
    public void updateUser(Long userId, UserProfileDTO userProfileDTO) {

        UserEntity user = userRepository.findOne(userId);

        // Validate
        if (!validateUniqueEmail(userProfileDTO.getMail(), user)) {
            throw new EntityExistsException("Usuario con e-mail '" + userProfileDTO.getMail() + "' ya existe");
        }

        // Populate
        BeanUtils.copyProperties(userProfileDTO, user);
        user.setName(userProfileDTO.getName());
        user.setLastname(userProfileDTO.getLastname());
        user.setMail(userProfileDTO.getMail());

        if (user.getRole() == null) {
            // Set a default role
            user.setRole(RoleTypeEnum.USER);
        }
        // Update
        userRepository.save(user);

    }

    @Override
    public Boolean validateUniqueEmail(String email) {
        return validateUniqueEmail(email, null);
    }

    private Boolean validateUniqueEmail(String email,
                                        UserEntity user) {
        List<UserEntity> others = userRepository.findAllByMail(email);
        if (user != null) {
            others.remove(user);
        }
        if (others.size() != 0) {
            return false;
        }
        return true;
    }

    @Override
    public void updateUser(Long userId, UserDTO userDTO) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(userDTO, userProfileDTO);
        this.updateUser(userId, userProfileDTO);
    }


    @Override
    public UserEntity saveUser(Long currentUserId, UserDTO userDTO) {
        UserEntity newUser = new UserEntity();

        //FIXME el campo currentUserId no se utiliza en la implementación actual.

         // Validate
        if (!validateUniqueEmail(userDTO.getMail())) {
            throw new EntityExistsException("Usuario con e-mail '" + userDTO.getMail() + "' ya existe");
        }

        // Populate
        BeanUtils.copyProperties(userDTO, newUser);

        newUser.setState(UserStateEnum.ACTIVE.toString());
        newUser.setRole(RoleTypeEnum.USER);
        // FIXME default password
        // Random password with 8 characters in the range -> 'abcDEF123'
        newUser.setPassword(RandomStringUtils.random(8, "abcDEF123"));

        return this.saveUser(newUser);
    }

    @Override
    public void deleteUser(Long currentUserId, Long userToDelete) {
        if (currentUserId != null && currentUserId.equals(userToDelete)) {
            throw new AuthorizationServiceException(
                    "You can't remove your own user");
        }
        userRepository.delete(userToDelete);
    }


    @Override
    public boolean isUserActive(Long userId) {
        return isUserInState(userId, UserStateEnum.ACTIVE);
    }

    @Override
    public boolean isUserSuspended(Long userId) {
        return isUserInState(userId, UserStateEnum.SUSPENDED_BY_ADMIN);
    }

    private boolean isUserInState(Long userId, UserStateEnum state) {
        UserEntity userEntity = userRepository.findOne(userId);
        String currentState = userEntity.getState();
        if (state != null && state.toString().equals(currentState)) {
            return true;
        } else {
            return false;
        }
    }

}
