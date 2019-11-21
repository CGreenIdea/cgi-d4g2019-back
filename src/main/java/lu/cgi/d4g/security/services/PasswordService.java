package lu.cgi.d4g.security.services;

import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.IteratedSaltedPasswordAlgorithmSpec;

import javax.enterprise.context.ApplicationScoped;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * A service dedicated to the encryption of passwords.
 *
 * @author Cyrille Chopelet
 */
@ApplicationScoped
public class PasswordService {

    private static final Provider ELYTRON_PROVIDER = new WildFlyElytronPasswordProvider();

    private static final int ITERATION_COUNT = 12;

    /**
     * Returns the number of interations applied when hashing a password.
     *
     * @return the number of interations applied when hashing a password
     */
    public int getIterationCount() {
        return ITERATION_COUNT;
    }

    /**
     * Generates a salt for hashing a password.
     *
     * @return a salt
     */
    public byte[] getSalt() {
        final byte[] salt = new byte[BCryptPassword.BCRYPT_SALT_SIZE];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    /**
     * Encodes a password for storing it into the database.
     *
     * @param password the password to hash
     * @param salt     the salt to use
     * @return the hashed password
     * @throws InvalidKeySpecException
     */
    public byte[] encodePassword(String password, byte[] salt) throws InvalidKeySpecException {
        try {
            final PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, ELYTRON_PROVIDER);

            IteratedSaltedPasswordAlgorithmSpec iteratedAlgorithmSpec = new IteratedSaltedPasswordAlgorithmSpec(ITERATION_COUNT, salt);
            EncryptablePasswordSpec encryptableSpec = new EncryptablePasswordSpec(password.toCharArray(), iteratedAlgorithmSpec);

            BCryptPassword original = (BCryptPassword) passwordFactory.generatePassword(encryptableSpec);

            return original.getHash();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("This exception should never occur.", e);
        }
    }
}
