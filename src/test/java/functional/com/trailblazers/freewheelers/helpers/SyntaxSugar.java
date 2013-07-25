package functional.com.trailblazers.freewheelers.helpers;

import com.trailblazers.freewheelers.model.Address;

public class SyntaxSugar {

    public static final String SOME_PHONE_NUMBER = "555-123456";
    public static final String SOME_PASSWORD = "secret";
    public static final Address SOME_ADDRESS = new Address("One", "Second", "Sao Paulo", "New Jersey", "UK", "000-0000");
    public static final Address NEW_ADDRESS = new Address("One", "Second", "San Diego", "New Jersey", "UK", "000-0000");
    public static final String NEW_PHONE_NUMBER = "111-1111";
    public static final String NEW_EMAIL_ADDRESS = "test@test.com";
    public static final String TERMS_ACCEPTED = "on";
    public static final String EMPTY_PASSWORD = "";
    public static final String NO_QUANTITY = "";
    public static final long ONLY_ONE_LEFT = 1L;
    public static final String REALLY_EXPENSIVE = "2899.00";
    public static final String SOME_DESCRIPTION = "4 x red, curved Arrow shape, screw fastening";
    public static final String A_LOT = "1000";
    public static final String IMAGE_NAME = "freewheelers-120.png";
    public static final String CREDIT_CARD_NUMBER = "4111111111111111";
    public static final String EXPIRY_DATE = "11-2020";
    public static final String SECURITY_CODE = "534";

    public static String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }

    public static String from(String s) {
        return s;
    }

    public static String to(String s) {
        return s;
    }

    public static String TERMS_NOT_ACCEPTED() {
        return null;
    }


}