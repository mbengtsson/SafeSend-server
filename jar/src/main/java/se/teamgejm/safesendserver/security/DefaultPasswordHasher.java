package se.teamgejm.safesendserver.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * PBKDF implementation of PasswordHasher interface, used to create salted PBKDF2 hashes of passwords and to validate
 * them. The password-validation compares the password hashes in "length-constant" time to prevent timing attacks
 *
 * @author Marcus Bengtsson
 */
@Singleton
@Startup
public class DefaultPasswordHasher implements PasswordHasher {

	private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final String SALT_ALGORITHM = "SHA1PRNG";

	private static final int PBKDF2_ITERATIONS = 4096;
	private static final int SALT_BYTES = 32;
	private static final int HASH_BITS = 512;

	/**
	 * Returns salted hash of the password
	 *
	 * @param password the password to hash
	 * @return base64 encoded salted hash of the password together with salt and iterations (iterations:salt:password)
	 */
	@Override
	public String generateHash(final String password) {

		try {
			final byte[] salt = getSalt();
			final byte[] hash = getHash(password, salt, PBKDF2_ITERATIONS, HASH_BITS);

			return String.format("%s:%s:%s", PBKDF2_ITERATIONS, new String(Base64.encodeBase64(salt)),
					new String(Base64.encodeBase64(hash)));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Validates password against a stored hash
	 *
	 * @param password    the password to validate
	 * @param correctHash the correct hash
	 * @return true if the password is valid
	 */
	@Override
	public boolean validatePassword(final String password, final String correctHash) {

		final String[] hashParams = correctHash.split(":");
		final int iterations = Integer.parseInt(hashParams[0]);
		final byte[] salt = Base64.decodeBase64(hashParams[1].getBytes());
		final byte[] hash = Base64.decodeBase64(hashParams[2].getBytes());

		try {
			final byte[] testHash = getHash(password, salt, iterations, hash.length * 8);

			return slowHashEquals(hash, testHash);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] getHash(final String password, final byte[] salt, final int iterations,
			final int hashBits) throws NoSuchAlgorithmException, InvalidKeySpecException {

		final char[] chars = password.toCharArray();

		final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, hashBits);
		final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);

		return keyFactory.generateSecret(spec).getEncoded();
	}

	private byte[] getSalt() throws NoSuchAlgorithmException {

		final SecureRandom random = SecureRandom.getInstance(SALT_ALGORITHM);
		final byte[] salt = new byte[SALT_BYTES];
		random.nextBytes(salt);

		return salt;
	}

	/**
	 * Compares two byte-arrays "length-constant" time.
	 *
	 * @param hash1 byte-array
	 * @param hash2 byte-array
	 * @return true if equal
	 */
	private boolean slowHashEquals(final byte[] hash1, final byte[] hash2) {

		int diff = hash1.length ^ hash2.length;
		for (int i = 0; i < hash1.length && i < hash2.length; i++) {
			diff |= hash1[i] ^ hash2[i];
		}

		return diff == 0;

	}

}
