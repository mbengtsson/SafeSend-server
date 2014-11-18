package se.teamgejm.safesendserver.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Class used to create salted PBKDF2 hashes of passwords and to validate them.
 * The password-validation compares the password hashes in length-constant time to prevent timing attacks
 * <p/>
 * Created by Marcus Bengtsson on 2014-11-14.
 */
public class PasswordHasher {

	private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final String SALT_ALGORITHM = "SHA1PRNG";

	private static final int PBKDF2_ITERATIONS = 4096;
	private static final int SALT_BYTES = 32;
	private static final int HASH_BITS = 512;

	/**
	 * Returns salted hash of the password
	 *
	 * @param password the password to hash
	 * @return salted hash of the password
	 */
	public String getPasswordHash(String password) {

		try {
			byte[] salt = getSalt();
			byte[] hash = getHash(password, salt, PBKDF2_ITERATIONS, HASH_BITS);

			return String.format("%s:%s:%s", PBKDF2_ITERATIONS, toHex(salt), toHex(hash));

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
	public boolean validatePassword(String password, String correctHash) {

		String[] hashParams = correctHash.split(":");
		int iterations = Integer.parseInt(hashParams[0]);
		byte[] salt = fromHex(hashParams[1]);
		byte[] hash = fromHex(hashParams[2]);

		try {
			byte[] testHash = getHash(password, salt, iterations, hash.length * 8);

			return slowHashEquals(hash, testHash);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] getHash(String password, byte[] salt, int iterations, int hashBits) throws NoSuchAlgorithmException, InvalidKeySpecException {

		char[] chars = password.toCharArray();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, hashBits);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);

		return keyFactory.generateSecret(spec).getEncoded();
	}

	private byte[] getSalt() throws NoSuchAlgorithmException {

		SecureRandom random = SecureRandom.getInstance(SALT_ALGORITHM);
		byte[] salt = new byte[SALT_BYTES];
		random.nextBytes(salt);

		return salt;
	}

	private String toHex(byte[] bytes) {

		BigInteger bigInt = new BigInteger(1, bytes);
		String hex = bigInt.toString(16);
		int paddingLength = (bytes.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private byte[] fromHex(String hex) {

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	private boolean slowHashEquals(byte[] hash1, byte[] hash2) {

		int diff = hash1.length ^ hash2.length;
		for (int i = 0; i < hash1.length && i < hash2.length; i++) {
			diff |= hash1[i] ^ hash2[i];
		}

		return diff == 0;

	}

}
