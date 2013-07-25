package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class AccountTest extends UserJourneyBase {

    @Test
    public void testLogin() throws Exception {
        String name = "Mircea";

        admin
                .there_is_no_account_for(name);

        user
                .is_logged_out()
                .logs_in_with(name, SOME_PASSWORD);

        screen
                .shows_error_alert("login attempt was not successful");

    }

    @Test
    public void testCreateAccountWithEmptyPassword() {
        String name = "Yaodan";
        String email = "yaodan@mail.com";

        user
                .creates_an_account(name, email, EMPTY_PASSWORD, SOME_PHONE_NUMBER, SOME_ADDRESS, TERMS_ACCEPTED);

        screen
                .shows_error_alert("There were errors");
    }

    @Test

    public void testRetainUserInformationWhenErrorWithEmptyPassword() throws Exception {
        String name = "Yaodan";
        String email = "yaodan@mail.com";

        user
                .creates_an_account(name, email, EMPTY_PASSWORD, SOME_PHONE_NUMBER, SOME_ADDRESS, TERMS_ACCEPTED);

        screen
                .shows_error_alert("There were errors");

        screen
                .retain_user_phone_number(SOME_PHONE_NUMBER);

        screen
                .retain_user_email(email);

    }

    @Test

    public void testCreateAccountWithoutAgreeingTerms() {
        String name = "June";
        String email = "june@mail.com";

        user
                .creates_an_account(name, email, SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_ADDRESS, TERMS_NOT_ACCEPTED());

        screen
                .shows_error_alert("There were errors");

    }

    @Test

    public void testCreateAccount() {
        String name = "Mia";
        String email = "mia@mail.com";

        admin
                .there_is_no_account_for(name);

        user
                .creates_an_account(name, email, SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_ADDRESS, TERMS_ACCEPTED);

        screen
                .shows_message("account has been created");

        user
                .is_logged_out()
                .logs_in_with(name, SOME_PASSWORD);

        screen
                .shows_in_navbar("Welcome " + name);

        user
                .visits_his_profile();

        screen
                .should_show_address_details(SOME_ADDRESS);
        admin
                .there_is_no_account_for(name);

    }

    @Test

    public void testAccessRights() throws Exception {
        String Hugo = "Hugo Huser";
        String Arno = "Arno Admin";

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_an_admin(Arno, SOME_PASSWORD);

        user
                .is_logged_out();

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_his_profile();

        screen
                .shows_profile_for(Hugo);

        user
                .go_to_admin_url();
        screen
                .shows_error_alert("access is denied");

        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();
        screen
                .shows_admin_profile();

        user
                .visits_profile_for(Hugo);
        screen
                .shows_profile_for(Hugo);
    }

    @Test
    public void testEditAccount() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_a_user(jan, SOME_PASSWORD);

        user
                .logs_in_with(jan, SOME_PASSWORD);

        user
                .edit_details(NEW_ADDRESS, NEW_PHONE_NUMBER, NEW_EMAIL_ADDRESS);

        screen
                .should_show_address_details(NEW_ADDRESS)
                .should_show_personal_info(NEW_PHONE_NUMBER, NEW_EMAIL_ADDRESS);
        admin
                .there_is_no_account_for(jan);
    }


}
