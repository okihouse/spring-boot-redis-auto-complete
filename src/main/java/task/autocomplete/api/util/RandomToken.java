package task.autocomplete.api.util;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomToken {
	private final static Logger logger = LoggerFactory.getLogger(RandomToken.class);
	
	private static SecureRandom securerandom = null;
    
	/** Random Number Generation Algorithm name SHA-1 */
	public static final String ALGM_NAME_RNG_SHA1 = "SHA1PRNG";

	public static String generateRandomToken(int nLength) {
        try {
            if (nLength <= 0) {
                return null;
            }
            if (securerandom == null) {
                securerandom = SecureRandom.getInstance(ALGM_NAME_RNG_SHA1);
            }
            
            // below source is obtained apache common RandomUtil
            char[] buffer = new char[nLength];
            char ch;
            int start = 32;
            int end = 123;
            int gap = end - start;
            while (nLength-- != 0) {
                ch = (char) (securerandom.nextInt(gap) + start);
                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    if (ch >= '\uDC00' && ch <= '\uDFFF') {
                        if (nLength == 0) {
                            nLength++;
                        } else {
                            buffer[nLength] = ch;
                            nLength--;
                            buffer[nLength] = (char) (55296 + securerandom.nextInt(128));
                        }
                    } else if (ch >= '\uD800' && ch <= '\uDB7F') {
                        if (nLength == 0) {
                            nLength++;
                        } else {
                            buffer[nLength] = (char) (56320 + securerandom.nextInt(128));
                            nLength--;
                            buffer[nLength] = ch;
                        }
                    } else if (ch >= '\uDB80' && ch <= '\uDBFF') {
                    	nLength++;
                    } else {
                    	buffer[nLength] = ch;
                    }
                } else {
                    nLength++;
                }
            }
            return new String(buffer);
            
        } catch (GeneralSecurityException e) {
            logger.error("GeneralSecurityException:", e);
        } catch (Exception e) {
            logger.error("Exception:", e);
        }
        return null;
    }
}
