package functional.com.trailblazers.freewheelers;


import org.junit.Test;

public class UserProfileTest extends UserJourneyBase {

    @Test
    public void shouldShowMyProfileWhenIAmLoggedIn() {
        String userName = "Jan Plewka";
        String password = "1234";
        admin
                .there_is_a_user(userName, password);
        user
                .logs_in_with(userName, password);
        user
                .visits_his_profile();
        screen
                .shows_profile_for(userName);
        admin
                .there_is_no_account_for(userName);
    }

    @Test
    public void shouldNotViewOtherUsersProfile() {
        String userName = "Jan Plewka";
        String password = "1234";
        String otherUser = "Mircea";
        String otherPassword = "1234";
        admin
                .there_is_a_user(userName, password);
        admin
                .there_is_a_user(otherUser, otherPassword);
        user
                .logs_in_with(userName, password);
        user
                .visits_profile_for(otherUser);
        screen
                .shouldShowAccessDeniedPage();
        admin
                .there_is_no_account_for(userName);

    }

}
