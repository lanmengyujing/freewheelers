package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.trailblazers.freewheelers.model.AccountValidation.verifyInputs;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountValidationTest {

    public static final String SOME_EMAIL = "guenter.grass@gmail.com";
    public static final String SOME_PASSWORD = "V3ry Secure!";
    public static final String SOME_NAME = "Günter Grass";
    public static final String SOME_PHONE = "004945542741";
    public static final Address SOME_ADDRESS = new Address();
    public static final String SOME_COUNTRY = "UK";
    public static final String ACCEPTED_TERMS = "ON";
    public static final String SOME_CITY = "London";
    private Account account;
    private HashMap errors;

    @Before
    public void setup() {
        account = new Account()
                .setEmailAddress(SOME_EMAIL)
                .setPassword(SOME_PASSWORD)
                .setAccount_name(SOME_NAME)
                .setPhoneNumber(SOME_PHONE)
                .setAddress(SOME_ADDRESS)
                .setAcceptedTerms(ACCEPTED_TERMS)
                .setEnabled(true);
        SOME_ADDRESS.setCountry(SOME_COUNTRY);
        SOME_ADDRESS.setCity(null);
        SOME_ADDRESS.setState(null);

        errors = new HashMap();

    }

    @Test
    public void shouldHaveNoErrorsForValidInput() throws Exception {
        errors = verifyInputs(account);

        assertThat(errors.size(), is(0));
    }

    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";

        account.setEmailAddress(invalidEmail);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("email", "enter a valid email", errors);
    }

    @Test
    public void shouldNotComplainAboutValidEmail() throws Exception {
        String validEmail = "valid@email.address";

        account.setEmailAddress(validEmail);

        errors = verifyInputs(account);

        assertThat(errors.size(), is(0));
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";

        account.setPassword(emptyPassword);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("password", "enter a password", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        account.setAccount_name(emptyName);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("name", "enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnInvalidCity() throws Exception {
        String invalidCity = "a-b#c";

        account.getAddress().setCity(invalidCity);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("city", "Must enter only letters", errors);
    }

    @Test
    public void shouldNotComplainAboutAnValidCity() throws Exception {
        String validCity = "a-b c";

        account.getAddress().setCity(validCity);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldNotComplainAboutAnEmptyCity() throws Exception {
        String emptyCity = "";

        account.getAddress().setCity(emptyCity);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyCountry() throws Exception {
        String emptyCountry = "";

        account.getAddress().setCountry(emptyCountry);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("country", "enter a country", errors);
    }

    @Test
    public void shouldComplainAboutAnInvalidState() throws Exception {
        String invalidState = "a-b#c";

        account.getAddress().setState(invalidState);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("state", "Must enter only letters", errors);
    }

    @Test
    public void shouldNotComplainAboutAnValidState() throws Exception {
        String validState = "a-b c";

        account.getAddress().setState(validState);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldNotComplainAboutAnEmptyState() throws Exception {
        String emptyState = "";

        account.getAddress().setState(emptyState);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldNotComplainAboutEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        account.setPhoneNumber(emptyPhoneNumber);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldNotComplainAboutAnyPhoneNumber() throws Exception {
        String anyPhoneNumber = "any phone number";

        account.setPhoneNumber(anyPhoneNumber);

        errors = verifyInputs(account);

        assertThereIsNoError(errors);
    }

    @Test
    public void shouldComplainAboutNotAcceptingTermsAndConditions() throws Exception {
        account.setAcceptedTerms(null);

        errors = verifyInputs(account);

        assertThereIsOneErrorFor("acceptedTerms", "Must accept the terms and conditions!", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }

    private void assertThereIsNoError(HashMap<String, String> errors) {
        assertThat(errors.size(), is(0));
    }


}
