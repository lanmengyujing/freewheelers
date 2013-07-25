package functional.com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class ShoppingCartTest extends UserJourneyBase {

    @Test
    public void testShoppingCartLink() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .should_display_shopping_cart_link();
    }

    @Test
    public void testNoItemInShoppingCart() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_shopping_cart();

        screen
                .display_empty_shopping_cart();

        admin
                .there_is_no_item(Simplon_Frame_1)
                .there_is_no_item(Simplon_Frame);
    }

    @Test
    public void testItemInShoppingCart() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";
        int ITEM_COUNT = 1;

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame)
                .display_cart_with_item(ITEM_COUNT);
        admin
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Simplon_Frame_1);

    }

    @Test
    public void shouldHaveItemsWithQuantityGreaterThanOneInShoppingCart() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";
        int ITEM_COUNT = 1;
        Long ONLY_TWO_LEFT = 2L;

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_TWO_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame_1)
                .visits_home_page()
                .reserves_item(Simplon_Frame_1)
                .visits_home_page()
                .visits_shopping_cart()
                .display_cart_with_item(ITEM_COUNT)
                .display_cart_with_quantity(Simplon_Frame_1, 2L);
        admin
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Simplon_Frame_1);

    }

    @Test
    public void testItemsAreFlushedOnPayInShoppingCart() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";
        Long ONLY_TWO_LEFT = 2L;

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_TWO_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card()
                .input_credit_card_payment_detail(CREDIT_CARD_NUMBER, SECURITY_CODE, EXPIRY_DATE, Bob);
        screen
                .shows_payment_success();
        user
                .visits_shopping_cart();
        screen
                .display_empty_shopping_cart();
        admin
                .there_is_no_account_for(Arno)
                .there_is_no_account_for(Bob)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Simplon_Frame_1);


    }

    @Ignore
    @Test
    public void shouldDisplayTotalPriceOfItemsInShoppingCart() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Simplon_Frame_1 = "Simplon Pavo 4 Ultra";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT)
                .there_is_a_frame(Simplon_Frame_1, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .reserves_item(Simplon_Frame)
                .display_cart_total_amount(new BigDecimal(1));
        admin
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Simplon_Frame_1);
    }

    @Test
    public void shouldRemoveItemFromShoppingCartWhenUserClicksDelete() throws Exception {
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        Long ONLY_TWO_LEFT = 2L;

        admin
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_TWO_LEFT) ;


        user
                .logs_in_with(Bob,SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_delete_item_from_shopping_cart(Simplon_Frame);

        screen
                .display_empty_shopping_cart();

        admin
                .there_is_no_account_for(Bob)
                .there_is_no_item(Simplon_Frame);

    }

}
