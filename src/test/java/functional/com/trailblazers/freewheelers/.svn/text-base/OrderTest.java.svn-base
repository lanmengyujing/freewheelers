package functional.com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.model.Address;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.ONLY_ONE_LEFT;
import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.SOME_PASSWORD;

public class OrderTest extends UserJourneyBase {

    public static final String NEW_SPOKE_REFLECTORS_ARROW_RED = "NEW - Spoke - Reflectors Arrow red";

    @Ignore("shopping cart")
    @Test
    public void testOrderProcess() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_no_item(NEW_SPOKE_REFLECTORS_ARROW_RED)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);
        user
                .clicks_more_details();
        screen
                .should_display_details_in_a_dialog(Simplon_Frame);
        user
                .reserves_item(Simplon_Frame)
                .visits_home_page();
        screen
                .should_list_item(Simplon_Frame);
        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();
        screen
                .there_should_be_an_order(Simplon_Frame, "NEW");
        user
                .changes_order_status(Simplon_Frame, "IN_PROGRESS");
        screen
                .there_should_be_an_order(Simplon_Frame, "IN_PROGRESS");
        admin
                .there_is_no_account_for(Bob)
                .there_is_no_account_for(Arno)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_order();
    }

    @Ignore("WIP: need to find better way to test it.")
    @Test
    public void testOrderProcessWhenUserDidNotPaid() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame)
                .visits_home_page()
                .visits_his_profile();

        screen
                .should_not_in_ordered_list(Simplon_Frame);

        admin
                .there_is_no_account_for(Bob)
                .there_is_no_account_for(Arno)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_order();
    }

    
    @Test
    @Ignore("TODO: a part of the story regarding total for shopping cart")

    public void testIfDutyTaxIsDisplayed() throws Exception {

        String Bob = "another Bob Buyer";
        String Simplon_Frame = "another Simplon Pavo 3 Ultra";
        BigDecimal itemPrice = new BigDecimal(100);
        BigDecimal dutyTax = new BigDecimal(7);

        Address canadaAddress = new Address();
        canadaAddress.setCountry("CANADA");

        admin
                .there_is_a_user_with_an_address(Bob, SOME_PASSWORD, canadaAddress)
                .there_is_an_item_with_price(Simplon_Frame, ONLY_ONE_LEFT, itemPrice);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame);

        screen
                .should_display_duty_tax(dutyTax);
        admin
                .there_is_no_account_for(Bob)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_order();

    }

    @Ignore("TODO: a part of the story regarding total for shopping cart")
    @Test
    public void testIfVATIsDisplayed() throws Exception {
        String Charley = "Charley";
        String Simplon_Frame = "simplon pava 3 ultra";
        BigDecimal itemPrice = new BigDecimal(100);
        BigDecimal vat = new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        Address addressForUK = new Address();
        addressForUK.setCountry("UK");

        admin
                .there_is_a_user_with_an_address(Charley, SOME_PASSWORD, addressForUK)
                .there_is_an_item_with_price(Simplon_Frame, ONLY_ONE_LEFT, itemPrice);

        user
                .logs_in_with(Charley, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame);

        screen
                .should_display_vat(vat);
        admin
                .there_is_no_account_for(Charley)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_order();

    }

}
