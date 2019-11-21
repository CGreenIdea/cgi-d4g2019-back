package lu.cgi.d4g.security.services;

import lu.cgi.d4g.commons.services.CsvImportService;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;
import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.entities.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;

@ApplicationScoped
public class UserService {

    private static final String DEFAULT_ROLE = "user";

    @Inject
    PasswordService passwordService;

    @Inject
    CsvImportService csvImportService;

    @Inject
    HomeService homeService;

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createUser(UserBean user, String token) {
        final String username = user.getUsername();
        final String password = user.getPassword();
        if (username == null || "".equals(username.trim())) {
            throw new BadRequestException("Username is mandatory");
        }
        if (password == null || "".equals(password.trim())) {
            throw new BadRequestException("Password is mandatory");
        }

        final UserEntity entity = new UserEntity();
        entity.setUserId(username.trim());
        entity.setRole(DEFAULT_ROLE);
        entity.setExpiryRegistration(LocalDate.now().plusDays(1));
        entity.setActive(false);
        entity.setRegistrationToken(token);
        encryptAndSavePassword(entity, password);

        entityManager.persist(entity);
    }

    private void encryptAndSavePassword(UserEntity user, String password) {
        try {
            final byte[] salt = passwordService.getSalt();
            final byte[] hash = passwordService.encodePassword(password.trim(), salt);

            user.setPassword(encodeHexString(hash));
            user.setSalt(encodeHexString(salt));
            user.setIterations(passwordService.getIterationCount());

        } catch (InvalidKeySpecException e) {
            throw new BadRequestException("The password could not be encrypted");
        }
    }

    @Transactional
    public void update(UserEntity user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    public UserEntity findByRegistrationValidation(String registrationToken) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.registrationToken = :registration", UserEntity.class)
            .setParameter("registration", registrationToken)
            .getSingleResult();
    }

    @Transactional
    public void importCsvData(String csv) {
        csvImportService.importCsvData(csv, record -> {
            UserEntity user = new UserEntity();
            user.setUserId(record.get("identifiant"));
            encryptAndSavePassword(user, record.get("password"));
            user.setRole(DEFAULT_ROLE);
            user.setActive(true);

            final String homeLabel = record.get("Foyer");
            if (homeLabel != null && !"".equals(homeLabel.trim())) {
                final HomeEntity home = homeService.findHomeByLabel(homeLabel);
                user.setHome(home);
            }

            return user;
        });
    }

    public UserEntity findByRegistrationToken(String token) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.registrationToken = :token", UserEntity.class)
            .setParameter("token", token)
            .getSingleResult();
    }

    public UserEntity findByResetToken(String token) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.resetToken = :token", UserEntity.class)
            .setParameter("token", token)
            .getSingleResult();
    }

    public UserEntity findByUserId(String userId) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.userId = :user", UserEntity.class)
            .setParameter("user", userId)
            .getSingleResult();
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
