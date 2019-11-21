package lu.cgi.d4g.security.services;

import lu.cgi.d4g.security.entities.UserEntity;
import lu.cgi.d4g.security.dto.UserBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class UserService {

    private static final String DEFAULT_ROLE = "user";

    @Inject
    EntityManager entityManager;

    @Inject
    PasswordService passwordService;

    @Transactional
    public void createUser(UserBean user) {
        try {
            final String username = user.getUsername();
            final String password = user.getPassword();
            if (username == null || "".equals(username.trim())) {
                throw new BadRequestException("Username is mandatory");
            }
            if (password == null || "".equals(password.trim())) {
                throw new BadRequestException("Password is mandatory");
            }

            final byte[] salt = passwordService.getSalt();
            final byte[] hash = passwordService.encodePassword(password.trim(), salt);

            // final Encoder encoder = Base64.getEncoder();

            final UserEntity entity = new UserEntity();
            entity.setUserId(username.trim());
            // entity.setPassword(encoder.encodeToString(hash));
            // entity.setSalt(encoder.encodeToString(salt));
            entity.setPassword(encodeHexString(hash));
            entity.setSalt(encodeHexString(salt));
            entity.setIterations(passwordService.getIterationCount());
            entity.setRole(DEFAULT_ROLE);

            entityManager.persist(entity);

        } catch (InvalidKeySpecException e) {
            throw new BadRequestException("The password could not be encrypted");
        }
    }

    private String encodeHexString(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
        }
        return hexStringBuffer.toString();
    }

    private String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
}
