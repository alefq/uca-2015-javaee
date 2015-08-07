package py.edu.uca.test.domain.enums;

/**
 * Enumarated type to manage the types of roles.
 *
 * @author rodrigo
 */
public enum RoleTypeEnum {

    // Can do root stuff on the overall system.
    ROOT,
    // Can admin only client's accounts.
    ADMIN,
    // Just a regular user.
    USER;

    @Override
    public String toString() {
        return this.name();
    }

    public Boolean isAdmin() {
        switch (this) {
            case ADMIN:
                return true;
            default:
                return false;
        }
    }

    public Boolean isUser() {
        if (this == USER) {
            return true;
        }
        return false;
    }

    public Boolean isRoot() {
        switch (this) {
            case ROOT:
                return true;
            default:
                return false;
        }
    }

}
