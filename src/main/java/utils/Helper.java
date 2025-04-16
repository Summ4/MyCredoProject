package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Helper {

    public static String generateRandomString(){

        return RandomStringUtils.random(10, true, false);

    }

}
