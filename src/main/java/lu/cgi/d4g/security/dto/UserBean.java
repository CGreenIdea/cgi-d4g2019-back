package lu.cgi.d4g.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for user creation queries.
 *
 * @author Cyrille Chopelet
 */
@Getter
@Setter
public class UserBean {

    /**
     * The username.
     * <p>
     * This should be an email when creating a new user.
     */
    private String username;

    /**
     * The requested password.
     * <p>
     * This should be only available for a new user creation request.
     */
    private String password;
}
