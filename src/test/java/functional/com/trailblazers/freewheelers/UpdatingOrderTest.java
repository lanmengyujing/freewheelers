package functional.com.trailblazers.freewheelers;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.trailblazers.freewheelers.model.Address;
import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.model.OrderStatus.PAID;
import static com.trailblazers.freewheelers.model.OrderStatus.READY_FOR_SHIPMENT;
import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class UpdatingOrderTest extends UserJourneyBase {

    @Ignore("WIP: June & Yaodan were working on it, and it would be broken")
    @Test
    public void shouldSendEmailToUserWhenOrderStatusIsReadyToShipment() throws Exception {

        assumeTrue("Only run this test in test env, ant test would run this one", "test".equals(System.getProperty("server.env")));
        SimpleSmtpServer server = SimpleSmtpServer.start(8889);
        String Hugo = "Hugo Huser";
        String Arno = "Arno Admin";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        Address address = new Address("One", "Two", "City", "State", "UK", "94103");

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
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
                .input_credit_card_payment_detail(CREDIT_CARD_NUMBER, SECURITY_CODE, EXPIRY_DATE, Hugo);

        screen
                .shows_payment_success();

        user
                .visits_home_page()
                .is_logged_out();

        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();

        screen
                .there_should_be_an_order(Simplon_Frame, PAID.toString());

        user
                .changes_order_status(Simplon_Frame, READY_FOR_SHIPMENT.toString());

        screen
                .there_should_be_an_order(Simplon_Frame, READY_FOR_SHIPMENT.toString());

        admin
                .there_is_no_account_for(Hugo)
                .there_is_no_account_for(Arno)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_order();


        server.stop();

        assertEquals(2, server.getReceivedEmailSize());
        SmtpMessage email = (SmtpMessage) server.getReceivedEmail().next();
        assertEquals(emailFor(Hugo), email.getHeaderValue("To"));
        assertThat(email.getHeaderValue("Subject"), is("Your Order from FreeWheeler is Now Ready to Ship"));
        assertTrue(email.getBody().contains(Hugo));

    }
}
