package py.edu.uca.test.security;

import py.edu.uca.test.domain.enums.UserStateEnum;

public class AuthenticationValidation {

    private String message;
    private Boolean success = true;
    private UserStateEnum userStateEnum;

    public AuthenticationValidation(boolean success, UserStateEnum pUserStateEnum, String message) {
        this.message = message;
        this.userStateEnum = pUserStateEnum;
        this.success = success;
    }

    public AuthenticationValidation() {
    }

    public UserStateEnum getUserStateEnum() {
        return userStateEnum;
    }

    public void setUserStateEnum(UserStateEnum pUserStateEnum) {
        this.userStateEnum = pUserStateEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
