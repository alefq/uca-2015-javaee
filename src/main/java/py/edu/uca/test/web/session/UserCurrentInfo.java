package py.edu.uca.test.web.session;


import java.io.Serializable;

import py.edu.uca.test.domain.UserEntity;

/**
 * A class that is used to store information about a logged user
 * It leaves in a session as an attribute
 */
public final class UserCurrentInfo implements Serializable {

    private static final long serialVersionUID = -5900154335461105339L;

    private String email;
    private Long userId;
    private Boolean root;
    private Boolean admin;

    private UserCurrentInfo() {

    }

    public static UserCurrentInfo fromUserEntity(UserEntity userEntity) {
        UserCurrentInfo userCurrentInfo = new UserCurrentInfo();
        userCurrentInfo.setEmail(userEntity.getMail());
        userCurrentInfo.setUserId(userEntity.getId());

        // The user role
        userCurrentInfo.setRoot(userEntity.getRole().isRoot());
        userCurrentInfo.setAdmin(userEntity.getRole().isAdmin());

        return userCurrentInfo;
    }

    public Boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getRoot() {
        return this.root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
