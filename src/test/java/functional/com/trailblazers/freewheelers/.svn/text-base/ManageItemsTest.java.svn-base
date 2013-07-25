package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class ManageItemsTest extends UserJourneyBase {

    @Test
    public void shouldCreateAndUpdateItems() throws Exception {
        String Arno = "Arno Admin";

        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Spoke_Reflectors = "Spoke - Reflectors Arrow red";

        String New_Simplon_Name = "NEW - Simplon Pavo 3 Ultra";
        String New_Spoke_Name = "NEW - Spoke - Reflectors Arrow red";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Spoke_Reflectors)
                .there_is_no_item(New_Simplon_Name)
                .there_is_no_item(New_Spoke_Name);
        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .wants_to_manage_items();

        user
                .creates_an_item(Simplon_Frame, "FRAME", NO_QUANTITY, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        screen
                .shows_error("Please enter Item Quantity");

        user
                .creates_an_item(Simplon_Frame, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);
        screen
                .shows_in_manage_item_list(Simplon_Frame);

        user
                .creates_an_item(Spoke_Reflectors, "ACCESSORIES", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        screen
                .shows_in_manage_item_list(Simplon_Frame)
                .shows_in_manage_item_list(Spoke_Reflectors);

        user
                .changes_item_name(from(Simplon_Frame), to(New_Simplon_Name))
                .changes_item_name(from(Spoke_Reflectors), to(New_Spoke_Name));

        screen
                .shows_in_manage_item_list(New_Simplon_Name)
                .shows_in_manage_item_list(New_Spoke_Name);

        user
                .delete_item(New_Simplon_Name);

        screen
                .shows_in_manage_item_list(New_Spoke_Name)
                .shows_not_in_manage_item_list(New_Simplon_Name);
        admin
                .there_is_no_item(New_Simplon_Name)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Spoke_Reflectors)
                .there_is_no_item(New_Spoke_Name);


    }

    @Test
    public void shouldUploadAndShowImage() throws Exception {
        String Arno = "Arno Admin";
        String path = getClass().getClassLoader().getResource(IMAGE_NAME).getPath();

        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_no_item(Simplon_Frame);
        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .wants_to_manage_items();

        user
                .fill_create_item_form_with_image_info(Simplon_Frame, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION, path);

        screen
                .should_display_image(IMAGE_NAME);

        user
                .user_submit();

        screen
                .shows_in_manage_item_list_with_image(Simplon_Frame, IMAGE_NAME);

        user
                .delete_item(Simplon_Frame);

    }

    @Test
    public void shouldDisplayAllTheOrder() throws Exception {
        String Arno = "Arno Admin";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Hugo = "Hugo Huser";
        admin
                .there_is_an_admin(Arno, SOME_PASSWORD);

        user
                .logs_in_with(Arno, SOME_PASSWORD);

        user
                .go_to_admin_url()
                .wants_to_manage_items();

        user
                .creates_an_item(Simplon_Frame, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        user
                .is_logged_out();

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .chooses_to_pay_by_credit_card();

        user
                .input_credit_card_payment_detail(CREDIT_CARD_NUMBER, SECURITY_CODE, EXPIRY_DATE, Hugo);

        user
                .is_logged_out();

        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .go_to_admin_url();

        screen
                .shows_the_paid_order_with_item(Simplon_Frame);
    }

    @Test
    public void shouldNotDisplayAllShoppingCartOrderUntilPay() throws Exception {
        String Arno = "Arno Admin";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Hugo = "Hugo Huser";
        admin
                .there_is_an_admin(Arno, SOME_PASSWORD);

        user
                .logs_in_with(Arno, SOME_PASSWORD);

        user
                .go_to_admin_url()
                .wants_to_manage_items();

        user
                .creates_an_item(Simplon_Frame, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        user
                .is_logged_out();

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_home_page()
                .reserves_item(Simplon_Frame)
                .is_logged_out()
                .logs_in_with(Arno, SOME_PASSWORD)
                .go_to_admin_url();
        screen
                .should_not_display_orders_in_shopping_cart();
    }
}
