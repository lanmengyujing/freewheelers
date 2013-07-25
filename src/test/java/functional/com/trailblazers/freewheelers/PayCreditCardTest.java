package functional.com.trailblazers.freewheelers;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.trailblazers.freewheelers.model.Address;
import functional.com.trailblazers.freewheelers.helpers.SyntaxSugar;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class PayCreditCardTest extends UserJourneyBase {


    @Test
    public void testPayWithValidCreditCard() throws Exception {
        String Hugo = "Hugo Huser";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card();

        screen
                .shows_credit_card_payment_page();

        user
                .input_credit_card_payment_detail(SyntaxSugar.CREDIT_CARD_NUMBER, SyntaxSugar.SECURITY_CODE, SyntaxSugar.EXPIRY_DATE, Hugo);

        screen
                .shows_payment_success();
    }

    @Test
    public void shouldSendMailToCustomerWhenPaymentSuccessful() throws Exception {
        assumeTrue("Only run this test in test env, ant test would run this one", "test".equals(System.getProperty("server.env")));
        SimpleSmtpServer server = SimpleSmtpServer.start(8889);
        String Hugo = "Hugo Huser";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        Address address = new Address("One", "Two", "City", "State", "UK", "94103");

        admin
                .there_is_a_user_with_an_address(Hugo, SOME_PASSWORD, address)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card();

        screen
                .shows_credit_card_payment_page();

        user
                .input_credit_card_payment_detail(SyntaxSugar.CREDIT_CARD_NUMBER, SyntaxSugar.SECURITY_CODE, SyntaxSugar.EXPIRY_DATE, Hugo);

        screen
                .shows_payment_success();

        user
                .visits_home_page();

        screen
                .should_not_list_item(Simplon_Frame);


        server.stop();

        assertEquals(1, server.getReceivedEmailSize());
        SmtpMessage email = (SmtpMessage) server.getReceivedEmail().next();
        assertEquals(emailFor(Hugo), email.getHeaderValue("To"));
        assertThat(true, is(email.getHeaderValue("Subject").contains("FreeWheelers")));
        assertTrue(email.getBody().contains("94103"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMMM yyyy");

        assertTrue(email.getBody().contains(dateFormat.format(new Date())));
        assertThat("You need to start server test configuration, email is send to stub server", true, is(email.getBody().contains("94103")));
    }

    @Test
    public void testPayWithInvalidCreditCard() throws Exception {
        String Hugo = "Hugo Huser";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card();

        screen
                .shows_credit_card_payment_page();

        user
                .input_credit_card_payment_detail(SyntaxSugar.CREDIT_CARD_NUMBER, "533", SyntaxSugar.EXPIRY_DATE, Hugo);

        screen
                .shows_payment_failure();
    }

    @Test
    public void testRedirectToShoppingCartWhenUserClickCancelOnPaymentPage() throws Exception {
        String Hugo = "Hugo Huser";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card();

        screen
                .shows_credit_card_payment_page();

        user
                .clicks_cancel_payment();

        screen
                .redirect_to_shopping_cart();
    }
}
