package ar.edu.unlp.dsa.utils;

/**
 * Created by acollard on 22/3/17.
 */

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Password and Hash Utils.
 *
 * @author Axel Collard Bovy
 *
 */
public class PasswordUtils {

    private static Integer HASH_COST = 12;
    private static Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    public static String hashPassword(String plainTextPassword){
        String hashedPassword = BCrypt.hashpw(plainTextPassword,BCrypt.gensalt(HASH_COST));
        log.info(hashedPassword);
        return hashedPassword;
    }

    public static Boolean validatePassword(String storedPassword, String inputUnhashedPassword){
        hashPassword(inputUnhashedPassword); // This is for debug only, should be removed later. It helps to get the hashed password to store in DB
        return (storedPassword != null && inputUnhashedPassword != null &&
                StringUtils.isNotEmpty(storedPassword) && StringUtils.isNotEmpty(inputUnhashedPassword)
                && BCrypt.checkpw(inputUnhashedPassword,storedPassword));
    }

}